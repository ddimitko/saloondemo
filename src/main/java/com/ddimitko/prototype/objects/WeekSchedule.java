package com.ddimitko.prototype.objects;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class WeekSchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne
    private DaySchedule monday;

    @ManyToOne
    private DaySchedule tuesday;

    @ManyToOne
    private DaySchedule wednesday;

    @ManyToOne
    private DaySchedule thursday;

    @ManyToOne
    private DaySchedule friday;

    @ManyToOne
    private DaySchedule saturday;

    @ManyToOne
    private DaySchedule sunday;

    @ManyToOne
    private Shop shop;

}
