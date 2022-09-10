package com.ddimitko.prototype.objects;

import com.sun.istack.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalTime;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User {

    @Id
    @NotNull
    private String staffId;

    private String firstName;
    private String lastName;

    @NotNull
    private String password;

    @ManyToOne
    private Shop shop;

    //Working hours for Staff
    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime startTime;

    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime endTime;

    private Boolean isOwner;

}
