package com.ddimitko.prototype.repositories;

import com.ddimitko.prototype.objects.ShopType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShopTypeRepository extends JpaRepository<ShopType, Integer> {
}
