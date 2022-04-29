package com.ddimitko.prototype.services;

import com.ddimitko.prototype.objects.DaySchedule;
import com.ddimitko.prototype.objects.Shop;
import com.ddimitko.prototype.objects.WeekSchedule;
import com.ddimitko.prototype.repositories.DayScheduleRepository;
import com.ddimitko.prototype.repositories.WeekScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
public class ScheduleService {

    @Autowired
    DayScheduleRepository dayScheduleRepository;

    @Autowired
    WeekScheduleRepository weekScheduleRepository;

    public List<DaySchedule> findAllDaySchedule(){
        return dayScheduleRepository.findAll();
    }

    public Optional<DaySchedule> findByIdDaySchedule(Long id){
        return dayScheduleRepository.findById(id);
    }

    public List<WeekSchedule> findAllWeekSchedule(){
        return weekScheduleRepository.findAll();
    }

    public Optional<WeekSchedule> findByIdWeekSchedule(Long id){
        return weekScheduleRepository.findById(id);
    }

    public DaySchedule createDaySchedule(DaySchedule schedule){

        if(schedule.getStartDate().equals(null) && schedule.getEndDate().equals(null)){
            schedule.setDayOff(true);
        }
        else{
            schedule.setDayOff(false);
        }

        return dayScheduleRepository.save(schedule);
    }

    public WeekSchedule createWeekSchedule(Shop shop, DaySchedule daySchedule, WeekSchedule weekSchedule){

        weekSchedule.setShop(shop);
        weekSchedule.setMonday(daySchedule);



        return weekScheduleRepository.save(weekSchedule);
    }

}
