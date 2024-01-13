package com.taskManagmentSystem.model.DTO.request;


import com.taskManagmentSystem.model.Role;
import com.taskManagmentSystem.model.User;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;


@Data
public class UserDTO extends User {

    private String email;
    private String password;

    @Enumerated(EnumType.STRING)
    private Role userRole;

}
