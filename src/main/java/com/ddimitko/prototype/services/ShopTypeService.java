package com.ddimitko.prototype.services;

import com.ddimitko.prototype.objects.ShopType;
import com.ddimitko.prototype.repositories.ShopTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShopTypeService {

    @Autowired
    private ShopTypeRepository repo;

    public List<ShopType> findAll(){
        return repo.findAll();
    }

    public Optional<ShopType> findById(Integer id){
        return repo.findById(id);
    }
}
