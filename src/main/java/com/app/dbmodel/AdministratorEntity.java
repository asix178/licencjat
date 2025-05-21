package com.app.dbmodel;

import com.app.model.Administrator;
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
@Table(name = "admin")
public class AdministratorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private UUID domainId;
    private String login;
    private String password;

    //change from domain class
    public static AdministratorEntity fromDomain(Administrator administrator) {
        return new AdministratorEntity(null,administrator.getId(), administrator.getLogin(), administrator.getPassword());
    }


    //change to domain class
    public Administrator toDomain(){
        return new Administrator(domainId, login, password);
    }
}
