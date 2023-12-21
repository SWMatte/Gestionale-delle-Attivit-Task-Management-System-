package com.taskManagmentSystem.repository;

import com.taskManagmentSystem.model.Activity;
import com.taskManagmentSystem.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActivityRepository extends JpaRepository<Activity,Integer> {
}
