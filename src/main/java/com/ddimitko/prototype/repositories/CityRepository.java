package com.ddimitko.prototype.repositories;

import com.ddimitko.prototype.objects.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {
}
