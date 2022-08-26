package com.ddimitko.prototype.objects;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
@Setter
@Entity
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private Long serviceId;

    private Long shopId;

    private Long userId;

    private String username;


    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dayDate;

    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime timeDate;
}
