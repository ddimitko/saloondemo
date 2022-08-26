package com.ddimitko.prototype.services;

import com.ddimitko.prototype.objects.Booking;
import com.ddimitko.prototype.objects.Services;
import com.ddimitko.prototype.objects.Shop;
import com.ddimitko.prototype.objects.User;
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

    public List<Booking> findAllByUserId(Long userId){

        return repo.findAllByUserId(userId);
    }

    public Optional<Booking> findById(Long id){
        return repo.findById(id);
    }

    public Booking createBooking(Long shopId, Long userId, Long serviceId, String username, Booking booking) throws Exception {

        booking.setShopId(shopId);
        booking.setUserId(userId);
        booking.setServiceId(serviceId);
        booking.setUsername(username);


        return repo.save(booking);

    }

    public void deleteBooking(Booking booking){

        if(repo.existsById(booking.getId())){
            repo.delete(booking);
        }

    }

    public List<LocalTime> addSlots(Shop shop, Services service, User user, LocalDate date, LocalTime open, LocalTime close){

        List<Booking> bookings = repo.findByShopIdAndDayDate(shop.getId(), date);
        List<LocalTime> slots = new ArrayList<>();
        Integer length = service.getLengthInMinutes();

        slots.add(open);

        LocalTime workTime = open;

        if(!date.isBefore(LocalDate.now())) {


            while (workTime.isBefore(close) && workTime.plusMinutes(length).isBefore(close)) {
                slots.add(workTime.plusMinutes(length));
                workTime = workTime.plusMinutes(length);
            }


            for (Booking book : bookings) {
                slots.removeIf(book.getTimeDate()::equals);
            }

        }

        return slots;
    }

}
