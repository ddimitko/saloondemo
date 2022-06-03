package com.ddimitko.prototype.services;

import com.ddimitko.prototype.objects.Booking;
import com.ddimitko.prototype.repositories.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookingService {

    @Autowired
    BookingRepository repo;

    public List<Booking> findAll(){
        return repo.findAll();
    }

    public Optional<Booking> findById(Long id){
        return repo.findById(id);
    }

    public Booking createBooking(Long shopId, Long userId, Long serviceId, Booking booking) throws Exception {

        booking.setShopId(shopId);
        booking.setUserId(userId);
        booking.setServiceId(serviceId);


        return repo.save(booking);

    }

}
