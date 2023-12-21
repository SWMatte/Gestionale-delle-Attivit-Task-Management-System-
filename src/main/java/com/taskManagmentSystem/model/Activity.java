package com.taskManagmentSystem.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
@Builder
public class Activity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idActivity;

    private String activityName;

    private String description;

    private LocalDate creationDate;

    @Enumerated(EnumType.STRING)
    private Category activityCategory;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idUser")
    private User idUser;
}
