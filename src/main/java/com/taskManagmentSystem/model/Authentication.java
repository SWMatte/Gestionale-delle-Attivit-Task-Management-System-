package com.taskManagmentSystem.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
@Builder
public class Authentication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idAuthentication;

    private String email;

    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Enumerated(EnumType.STRING)
    private StatusUser statusUser;

    private LocalDate lastAccess;

    @ManyToOne
    @JoinColumn(name = "idUser")
    private User idUser;


}