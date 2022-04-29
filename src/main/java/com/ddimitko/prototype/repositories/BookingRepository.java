package com.ddimitko.prototype.repositories;

import com.ddimitko.prototype.objects.Booking;
import com.ddimitko.prototype.objects.Shop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

    Boolean existsByToDateBefore(LocalDateTime date);
    Boolean existsByToDateAfterAndShopId(LocalDateTime date, Long id);
    Boolean existsByFromDateAfter(LocalDateTime date);
    Boolean existsByFromDateBeforeAndShopId(LocalDateTime date, Long id);

}
