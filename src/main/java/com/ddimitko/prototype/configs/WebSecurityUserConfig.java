package com.ddimitko.prototype.configs;

import com.ddimitko.prototype.loginhandlers.OAuthLoginSuccessHandler;
import com.ddimitko.prototype.oauth.CustomOAuth2UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@Order(2)
public class WebSecurityUserConfig {

    @Autowired
    private CustomOAuth2UserService oAuth2UserService;

    @Autowired
    private OAuthLoginSuccessHandler oAuthLoginSuccessHandler;

    @Bean
    protected SecurityFilterChain filterChain2(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/login").permitAll()
                .antMatchers("/signup").permitAll()
                .antMatchers("/process_signup").permitAll()
                .anyRequest().authenticated()
                .and()
                .oauth2Login()
                .loginPage("/login")
                .userInfoEndpoint()
                .userService(oAuth2UserService)
                .and()
                .successHandler(oAuthLoginSuccessHandler)
                .defaultSuccessUrl("/home")
                .permitAll()
                .and()
                .logout().logoutSuccessUrl("/").permitAll();
        return http.build();
    }


}
