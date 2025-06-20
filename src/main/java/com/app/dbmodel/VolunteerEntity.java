package com.app.dbmodel;

import com.app.model.Volunteer;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "volunteer")
public class VolunteerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private UUID domainId;
    private String name;
    private String password;

    //change from domain class
    public static VolunteerEntity fromDomain(Volunteer volunteer) {
        return new VolunteerEntity(null,volunteer.getId(),volunteer.getName(),volunteer.getPassword());
    }


    //change to domain class
    public Volunteer toDomain(){
        return new Volunteer(domainId, name, password);
    }

}
