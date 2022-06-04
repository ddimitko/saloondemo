package com.ddimitko.prototype.repositories;

import com.ddimitko.prototype.objects.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Optional;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

    Booking findByShopId(Long shopId);

    ArrayList<Booking> findByShopIdAndDayDate(Long shopId, LocalDate date);

    Boolean existsByShopId(Long shopId);

    Boolean existsByShopIdAndDayDate(Long shopId, LocalDate date);

    Boolean existsByShopIdAndDayDateAndTimeDate(Long shopId, LocalDate date, LocalTime time);
}
