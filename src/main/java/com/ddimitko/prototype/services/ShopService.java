package com.ddimitko.prototype.services;

import com.ddimitko.prototype.objects.Services;
import com.ddimitko.prototype.objects.Shop;
import com.ddimitko.prototype.repositories.ServicesRepository;
import com.ddimitko.prototype.repositories.ShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ShopService {

    @Autowired
    ShopRepository repo;

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

        if(!shop.getName().equals(null) && !shop.getCity().equals(null) && !shop.getType().equals(null)){
            return repo.save(shop);
        }
        else{
            throw new NullPointerException();
        }

    }




}
