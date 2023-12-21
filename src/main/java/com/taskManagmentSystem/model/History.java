package com.taskManagmentSystem.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Builder
public class History {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idHistory;

    private LocalDate creationHistory;

    private LocalDate presumeEndDateActivity;

    private LocalDate endDateActivity;

    @Enumerated(EnumType.STRING)
    private StatusActivity statusActivity;

    @ManyToOne
    @JoinColumn(name = "idUser")
    private User idUser;

    @ManyToOne
    @JoinColumn(name = "idActivity")
    private Activity idActivity;

}
