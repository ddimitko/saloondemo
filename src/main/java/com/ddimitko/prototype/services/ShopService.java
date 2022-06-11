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
import javax.transaction.Transactional;
import java.util.*;

@Service
public class ShopService {

    @Autowired
    ShopRepository repo;

    @Autowired
    UserRepository userRepo;

    @Autowired
    ServicesRepository servicesRepo;

    @PersistenceContext
    private EntityManager entityManager;


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

        if(!shop.getName().equals(null) && !shop.getCity().equals(null) && !shop.getType().equals(null)){
            return repo.save(shop);
        }
        else{
            throw new NullPointerException();
        }


    }

    @Transactional
    public void addStaff(Long shopId, Long userId){

        Shop shop = new Shop();

        if(repo.findById(shopId).isPresent()){
            shop = repo.findById(shopId).get();
        }

        User user = new User();

        if(userRepo.findById(userId).isPresent()){
            user = userRepo.findById(userId).get();
        }

        shop.getStaff().add(user);

        this.entityManager.persist(user);

    }

/*    public void assignStaff(Long shopId, Long userId, String startTime, String endTime){

        Shop shop = new Shop();
        if(findById(shopId).isPresent()){
            shop = findById(shopId).get();
        }

        LocalTime start = LocalTime.parse(startTime);
        LocalTime end = LocalTime.parse(endTime);

        User user = new User();
        Staff staff = new Staff();
        if(userRepo.findById(userId).isPresent()){
            userRepo.findById(userId).get();
            if(staffRepo.findById(userId).isEmpty()){
                staff.setShopId(shopId);
                staff.setId(userId);
                staff.setStartTime(start);
                staff.setEndTime(end);
                staff.setFirstName(user.getFirstName());
                staff.setLastName(user.getLastName());
                staff.setEmail(user.getEmail());
                staff.setPassword(user.getPassword());
                staffRepo.save(staff);
            }
        }

        shop.getStaff().add(staff);

    }*/




}
