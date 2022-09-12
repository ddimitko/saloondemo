package com.ddimitko.prototype.services;

import com.ddimitko.prototype.objects.User;
import com.ddimitko.prototype.repositories.UserRepository;
import com.ddimitko.prototype.userdetails.user.CustomUserDetails;
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

    public String getFirstName(Authentication authentication) {
        CustomUserDetails principal = (CustomUserDetails) authentication.getPrincipal();
        return principal.getFirstName();
    }

    public String getFullName(Authentication authentication) {
        CustomUserDetails principal = (CustomUserDetails) authentication.getPrincipal();
        return principal.getFullName();
    }

    public String getId(Authentication authentication){
        CustomUserDetails principal = (CustomUserDetails) authentication.getPrincipal();
        return principal.getId();
    }

    public Boolean getIsOwner(Authentication authentication){
        CustomUserDetails principal = (CustomUserDetails) authentication.getPrincipal();
        return principal.getIsOwner();
    }

    public Optional<User> findById(Long id){
        return repo.findById(id);
    }

    public Optional<User> findByStaffId(String staffId){
        return repo.findByStaffId(staffId);
    }

    public List<User> findAllByShopId(Long shopId){
        return repo.findAllByShopId(shopId);
    }

    public User createUser(User user){

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        return repo.save(user);
    }

    /*
    public void removeUser(User user){

        Long id = user.getId();

        if(findById(id).isPresent()){
            repo.deleteById(id);
        }

    }

    public User updateUser(String email, String oldPassword, String newPassword){

        User user;
        if(repo.findByEmail(email).isPresent()){
            user = repo.findByEmail(email).get();

            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            if(passwordEncoder.matches(oldPassword, user.getPassword())){
                String encodedNewPassword = passwordEncoder.encode(newPassword);
                user.setPassword(encodedNewPassword);
            }

            return repo.save(user);
        }
        else{
            throw new NullPointerException();
        }

    }*/


}
