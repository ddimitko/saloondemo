package com.ddimitko.prototype.objects;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class Services {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    private String name;

    private String description;

    private Double price;

    private Integer lengthInMinutes;

    @ManyToOne
    private Shop shop;

}
