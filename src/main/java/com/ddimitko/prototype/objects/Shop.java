package com.ddimitko.prototype.objects;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.sql.Time;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.*;

@Getter
@Setter
@Entity
public class Shop {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String name;

    @ManyToOne
    private ShopType type;

    @ManyToOne
    private City city;

    @ManyToOne
    private User owner;

    @OneToMany(mappedBy = "shop")
    private List<Services> services;

    @OneToMany(mappedBy = "shopId")
    private List<Booking> bookings;

    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime openTime;

    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime closeTime;

    @Transient
    private ArrayList<LocalTime> slots = new ArrayList<>();

    public void addDaySchedule(LocalTime open, LocalTime close){

        LocalTime openTime = open;
        LocalTime closeTime = close;

        slots.add(openTime);

        LocalTime workTime = openTime;

        while(workTime.isBefore(closeTime) && workTime.plusMinutes(25).isBefore(closeTime)){
            slots.add(workTime.plusMinutes(25));
            workTime = workTime.plusMinutes(25);
        }
    }

}
