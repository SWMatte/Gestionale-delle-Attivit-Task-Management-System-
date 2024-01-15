package com.taskManagmentSystem.repository;

import com.taskManagmentSystem.model.Activity;
import com.taskManagmentSystem.model.Authentication;
import com.taskManagmentSystem.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface AuthenticationRepository extends JpaRepository<Authentication, Integer> {

    Authentication findByEmail(String email);
    Optional<Authentication> findByIdUserIdUser(int idUser);
}



