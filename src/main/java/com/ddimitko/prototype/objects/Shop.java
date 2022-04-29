package com.ddimitko.prototype.objects;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

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

    @OneToMany(mappedBy = "shop")
    private List<Booking> bookings;

    @ManyToOne
    private WeekSchedule schedule;

}
