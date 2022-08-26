package com.ddimitko.prototype.objects;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User {

    @Id
    private String staffId;

    private String firstName;
    private String lastName;

    @NotNull
    private String password;

    @ManyToOne
    private Shop shop;

    //Working hours for Staff
    /*private LocalTime startTime;
    private LocalTime endTime;*/

}
