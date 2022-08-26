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

    @OneToMany(mappedBy = "shop")
    private List<User> staff;

    @OneToMany(mappedBy = "shop")
    private List<Services> services;

    @OneToMany(mappedBy = "shopId")
    private List<Booking> bookings;

    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime openTime;

    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime closeTime;

}
