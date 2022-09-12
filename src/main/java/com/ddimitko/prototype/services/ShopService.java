package com.ddimitko.prototype.services;

import com.ddimitko.prototype.objects.Services;
import com.ddimitko.prototype.objects.Shop;
import com.ddimitko.prototype.objects.User;
import com.ddimitko.prototype.repositories.ServicesRepository;
import com.ddimitko.prototype.repositories.ShopRepository;
import com.ddimitko.prototype.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.*;

@Service
public class ShopService {

    @Autowired
    ShopRepository repo;

    @Autowired
    UserRepository userRepo;

    @Autowired
    ServicesRepository servicesRepo;


    public List<Shop> findAll(){
        return repo.findAll();
    }

    public Optional<Shop> findById(Long id){
        return repo.findById(id);
    }

    public Optional<Services> findByServiceId(Long id){
        return servicesRepo.findById(id);
    }

    public Shop addShop(Shop shop){

        if(!shop.getName().equals(null) && !shop.getCity().equals(null) && !shop.getType().equals(null) && !shop.getOwnerId().isEmpty()){
            userRepo.findByStaffId(shop.getOwnerId()).get().setIsOwner(true);
            userRepo.findByStaffId(shop.getOwnerId()).get().setShop(shop);

            return repo.save(shop);
        }
        else{
            throw new NullPointerException();
        }


    }

    public void addStaff(Long shopId, String staffId){

        User user = new User();
        if(userRepo.findByStaffId(staffId).isPresent()){
            user = userRepo.findByStaffId(staffId).get();
        }

        Shop shop = new Shop();
        if(repo.findById(shopId).isPresent()){
            shop = repo.findById(shopId).get();
        }

        user.setShop(shop);
        userRepo.save(user);

    }

}
