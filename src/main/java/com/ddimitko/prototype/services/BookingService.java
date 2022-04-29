package com.ddimitko.prototype.services;

import com.ddimitko.prototype.objects.Booking;
import com.ddimitko.prototype.objects.Shop;
import com.ddimitko.prototype.repositories.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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

    public Booking createBooking(Shop shop, Booking booking) throws Exception {

        booking.setShop(shop);

        Long length = Long.valueOf(booking.getService().getLengthInMinutes());

        booking.setToDate(booking.getFromDate().plusMinutes(length));

        if(booking.getToDate().isAfter(LocalDateTime.now())) {

            if (!repo.existsByFromDateBeforeAndShopId(booking.getToDate(), shop.getId()) || !repo.existsByToDateAfterAndShopId(booking.getFromDate(), shop.getId())) {
                return repo.save(booking);
            } else {
                throw new Exception("Error! A date within that range already exists! Please, choose a new one.");
            }
        }
        else{
            throw new Exception("Date is before now.");
        }


    }

}
