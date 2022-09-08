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
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

    Booking findByShopId(Long shopId);

    List<Booking> findAllByUserId(Long userId);

    ArrayList<Booking> findByShopIdAndDayDate(Long shopId, LocalDate date);

    ArrayList<Booking> findByShopIdAndStaffIdAndDayDate(Long shopId, String staffId, LocalDate date);

    ArrayList<Booking> findByStaffIdAndDayDate(String staffId, LocalDate date);

    Boolean existsByShopId(Long shopId);

    Boolean existsByShopIdAndDayDate(Long shopId, LocalDate date);

}
