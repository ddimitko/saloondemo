package com.ddimitko.prototype.services;

import com.ddimitko.prototype.objects.User;
import com.ddimitko.prototype.repositories.UserRepository;
import com.ddimitko.prototype.userdetails.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository repo;

    CustomUserDetails customUserDetails;

    public List<User> findAll(){
        return repo.findAll();
    }

    public User getSpecificUser(Authentication authentication) {
        CustomUserDetails principal = (CustomUserDetails) authentication.getPrincipal();
        String email = principal.getUsername();
        return repo.findByEmail(email).get();
    }

    public Optional<User> findByEmail(String email){
        return repo.findByEmail(email);
    }

    public String getFirstName(Authentication authentication) {
        CustomUserDetails principal = (CustomUserDetails) authentication.getPrincipal();
        return principal.getFirstName();
    }

    public String getFullName(Authentication authentication) {
        CustomUserDetails principal = (CustomUserDetails) authentication.getPrincipal();
        return principal.getFullName();
    }

    public Optional<User> findById(Long id){
        return repo.findById(id);
    }

    public User createUser(User user){

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        return repo.save(user);
    }

    public void removeUser(User user){

        Long id = user.getId();

        if(findById(id).isPresent()){
            repo.deleteById(id);
        }

    }

}
