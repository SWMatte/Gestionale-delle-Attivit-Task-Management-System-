package com.taskManagmentSystem.repository;

import com.taskManagmentSystem.model.History;
import com.taskManagmentSystem.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HistoryRepository extends JpaRepository<History,Integer> {
}
