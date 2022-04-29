package com.ddimitko.prototype.objects;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalTime;

@Getter
@Setter
@Entity
public class DaySchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime startDate;

    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime endDate;

    private Boolean dayOff;

}
