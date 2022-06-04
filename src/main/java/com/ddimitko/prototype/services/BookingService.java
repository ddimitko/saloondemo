package com.ddimitko.prototype.services;

import com.ddimitko.prototype.objects.Booking;
import com.ddimitko.prototype.objects.Services;
import com.ddimitko.prototype.objects.Shop;
import com.ddimitko.prototype.repositories.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
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


    public ArrayList addSlots(Shop shop, Services service, LocalDate date, LocalTime open, LocalTime close){

        List<Booking> bookings = shop.getBookings();
        ArrayList<LocalTime> slots = new ArrayList<>();
        Integer length = service.getLengthInMinutes();

        LocalTime openTime = open;
        LocalTime closeTime = close;

        slots.add(openTime);

        LocalTime workTime = openTime;

        /*ArrayList<Booking> booking = repo.findByShopIdAndDayDate(shop.getId(), date);*/

        while(workTime.isBefore(closeTime) && workTime.plusMinutes(length).isBefore(closeTime)){
            slots.add(workTime.plusMinutes(length));
            workTime = workTime.plusMinutes(length);
        }

        //DOES NOT WORK
        for(LocalTime time : slots){
            for(Booking bookingList : bookings){
                LocalTime reserved = bookingList.getTimeDate();
                if(time.equals(reserved)){
                    slots.remove(time);
                }
            }
        }

        return slots;
    }

}
