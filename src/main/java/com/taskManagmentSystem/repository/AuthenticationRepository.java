package com.taskManagmentSystem.repository;

import com.taskManagmentSystem.model.Activity;
import com.taskManagmentSystem.model.Authentication;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthenticationRepository extends JpaRepository<Authentication,Integer> {
}
