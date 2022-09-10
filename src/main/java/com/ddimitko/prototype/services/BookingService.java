package com.ddimitko.prototype.services;

import com.ddimitko.prototype.objects.*;
import com.ddimitko.prototype.repositories.BookingRepository;
import com.ddimitko.prototype.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.threeten.extra.Interval;

import java.time.*;
import java.time.format.DateTimeFormatter;
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

    public List<Booking> findAllByStaffId(String staffId){
        return repo.findAllByStaffId(staffId);
    }

    public List<Booking> findAllByShopId(Long shopId){
        return repo.findAllByShopId(shopId);
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
        List<Interval> slots = new LinkedList<>();
        LinkedHashMap<LocalTime, LocalTime> slotsInLocal = new LinkedHashMap<>();
        Integer length = service.getLengthInMinutes();

        ZonedDateTime zonedStart = ZonedDateTime.of(date, user.getStartTime(), ZoneId.systemDefault());
        ZonedDateTime zonedEnd = ZonedDateTime.of(date, user.getEndTime(), ZoneId.systemDefault());

        if(!date.isBefore(LocalDate.now())) {

            while (zonedStart.isBefore(zonedEnd) && zonedStart.plusMinutes(length).isBefore(zonedEnd)) {
                Interval duration = Interval.of(zonedStart.toInstant(), zonedStart.plusMinutes(length).toInstant());
                slots.add(duration);
                zonedStart = zonedStart.plusMinutes(length);
            }

            for (Booking book : bookings) {

                ZonedDateTime zonedBookStart = ZonedDateTime.of(date, book.getStartTime(), ZoneId.systemDefault());
                ZonedDateTime zonedBookEnd = ZonedDateTime.of(date, book.getEndTime(), ZoneId.systemDefault());

                Instant instantStart = Instant.from(zonedBookStart);
                Instant instantEnd = Instant.from(zonedBookEnd);

                Interval interval = Interval.of(instantStart, instantEnd);

                slots.removeIf(interval::overlaps);

            }

        }

        for(Interval slot : slots){

            LocalTime representationStart = LocalTime.ofInstant(slot.getStart(), ZoneId.systemDefault());
            LocalTime representationEnd = LocalTime.ofInstant(slot.getEnd(), ZoneId.systemDefault());
            slotsInLocal.put(representationStart, representationEnd);

        }

        return slotsInLocal;
    }

}
