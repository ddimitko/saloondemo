package com.ddimitko.prototype.services;

import com.ddimitko.prototype.objects.Shop;
import com.ddimitko.prototype.repositories.ShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ShopService {

    @Autowired
    ShopRepository repo;


    public List<Shop> findAll(){
        return repo.findAll();
    }

    public Optional<Shop> findById(Long id){
        return repo.findById(id);
    }

    public Shop addShop(Shop shop){

        return repo.save(shop);
    }




}
