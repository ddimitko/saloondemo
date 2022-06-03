package com.ddimitko.prototype.services;

import com.ddimitko.prototype.objects.City;
import com.ddimitko.prototype.repositories.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CityService {

    @Autowired
    private CityRepository repo;

    public List<City> findAll(){
        return repo.findAll();
    }

    public Optional<City> findById(Long id){
        return repo.findById(id);
    }

}
