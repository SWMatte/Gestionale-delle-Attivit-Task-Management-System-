package com.taskManagmentSystem.repository;

import com.taskManagmentSystem.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {
}
