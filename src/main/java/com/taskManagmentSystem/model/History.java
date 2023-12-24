package com.taskManagmentSystem.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class History {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idHistory;

    private LocalDate creationHistory;

    private LocalDate presumeEndDateActivity;

    private LocalDate endDateActivity;

    @Enumerated(EnumType.STRING)
    private StatusActivity statusActivity;

    private boolean deleteFlag;

    private String note;


    @ManyToOne
    @JoinColumn(name = "idUser")
    private User idUser;

    @ManyToOne
    @JoinColumn(name = "idActivity")
    private Activity idActivity;

}
