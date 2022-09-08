package com.ddimitko.prototype.services;

import com.ddimitko.prototype.objects.Booking;
import com.ddimitko.prototype.objects.Services;
import com.ddimitko.prototype.objects.Shop;
import com.ddimitko.prototype.objects.User;
import com.ddimitko.prototype.repositories.BookingRepository;
import com.ddimitko.prototype.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.threeten.extra.Interval;

import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.function.Predicate;

@Service
public class BookingService {

    @Autowired
    BookingRepository repo;

    @Autowired
    UserRepository userRepo;


    public List<Booking> findAll(){
        return repo.findAll();
    }

    public List<Booking> findAllByUserId(Long userId){

        return repo.findAllByUserId(userId);
    }

    public Optional<Booking> findById(Long id){
        return repo.findById(id);
    }

    public Booking createBooking(Long shopId, String staffId, Long userId, Long serviceId, String username, Booking booking) throws Exception {

        User staff = new User();
        if(userRepo.findByStaffId(staffId).isPresent()){
            staff = userRepo.findByStaffId(staffId).get();
        }

        booking.setShopId(shopId);
        booking.setStaffId(staffId);
        booking.setUserId(userId);
        booking.setServiceId(serviceId);
        booking.setStaffName(staff.getFirstName() + ' ' + staff.getLastName());
        booking.setUserName(username);


        return repo.save(booking);

    }

    public void deleteBooking(Booking booking){

        if(repo.existsById(booking.getId())){
            repo.delete(booking);
        }

    }

    public LinkedHashMap<LocalTime, LocalTime> addSlots(Services service, User user, LocalDate date){

        List<Booking> bookings = repo.findByStaffIdAndDayDate(user.getStaffId(), date);
        LinkedHashMap<LocalTime, LocalTime> slots = new LinkedHashMap<>();
        Integer length = service.getLengthInMinutes();

        /*slots.add(user.getStartTime());*/

        LocalTime workTime = user.getStartTime();


        if(!date.isBefore(LocalDate.now())) {

            while (workTime.isBefore(user.getEndTime()) && workTime.plusMinutes(length).isBefore(user.getEndTime())) {
                slots.put(workTime, workTime.plusMinutes(length));
                workTime = workTime.plusMinutes(length);
            }


            //TODO: Fix this.
            for (Booking book : bookings) {

                slots.keySet().removeIf(book.getStartTime()::equals);
                slots.values().removeIf(book.getStartTime()::equals);

            }

        }

        return slots;
    }

}
