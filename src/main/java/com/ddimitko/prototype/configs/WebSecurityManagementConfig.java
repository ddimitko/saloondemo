package com.ddimitko.prototype.configs;

import com.ddimitko.prototype.userdetails.user.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
@Order(1)
public class WebSecurityManagementConfig {

    @Bean
    public UserDetailsService userDetailsService() {
        return new CustomUserDetailsService();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider1() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }

    @Bean
    public SecurityFilterChain filterChain1(HttpSecurity http) throws Exception {
        http.authenticationProvider(authenticationProvider1());
        http.authorizeRequests().antMatchers("/").permitAll();

        http.antMatcher("/management/**")
                .authorizeRequests().anyRequest().hasAuthority("ROLE_WORKER")
                .and()
                .formLogin()
                .loginPage("/management/login")
                .usernameParameter("email")
                .loginProcessingUrl("/management/login")
                .defaultSuccessUrl("/management/")
                .permitAll()
                .and()
                .logout()
                .logoutUrl("/management/logout")
                .logoutSuccessUrl("/");

        return http.build();
    }
}
