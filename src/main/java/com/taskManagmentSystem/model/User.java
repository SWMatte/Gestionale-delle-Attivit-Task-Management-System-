package com.taskManagmentSystem.model;


import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "User entity")
public class User {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(accessMode = Schema.AccessMode.READ_ONLY, description = "Id - autoincrementale", example = "111")
    private int idUser;
    @Schema(description = "User name", example = "Mario Rossi")
    private String name;
    private String lastName;
    private Integer age;
    private LocalDate creationDate;
    private boolean deleteFlag;
}
