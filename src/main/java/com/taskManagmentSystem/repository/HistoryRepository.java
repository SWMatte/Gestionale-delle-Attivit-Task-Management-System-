package com.taskManagmentSystem.repository;

import com.taskManagmentSystem.model.Category;
import com.taskManagmentSystem.model.DTO.response.ActivityResponseDTO;
import com.taskManagmentSystem.model.DTO.response.HistoryResponseDTO;
import com.taskManagmentSystem.model.History;
import com.taskManagmentSystem.model.StatusActivity;
import com.taskManagmentSystem.model.StatusUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface HistoryRepository extends JpaRepository<History, Integer> {


    History findByidActivityIdActivity(int idActivity);

    @Query("""
            SELECT new com.taskManagmentSystem.model.DTO.response.HistoryResponseDTO(
            h.creationHistory,
            h.presumeEndDateActivity,
            h.endDateActivity,
            h.statusActivity,
            h.note,
            u.name,
            u.lastName,
            a.activityName,
            a.description,
            a.activityCategory
             )
            FROM History h
            JOIN h.idUser u
            JOIN h.idActivity a
            WHERE h.statusActivity IN :status AND u.idUser=:idUser
            ORDER BY h.presumeEndDateActivity
            """)
    List<HistoryResponseDTO> findHistory(StatusActivity[] status, int idUser);


    @Query("""
            SELECT new com.taskManagmentSystem.model.DTO.response.HistoryResponseDTO(
            h.creationHistory,
            h.presumeEndDateActivity,
            h.endDateActivity,
            h.statusActivity,
            h.note,
            u.name,
            u.lastName,
            a.activityName,
            a.description,
            a.activityCategory
             )
            FROM History h
            JOIN h.idUser u
            JOIN h.idActivity a
            WHERE h.statusActivity <> 'COMPLETATA' AND u.idUser=:idUser
            ORDER BY h.presumeEndDateActivity
            """)
    List<HistoryResponseDTO> findHistory(int idUser);


    @Query("""
            SELECT new com.taskManagmentSystem.model.DTO.response.HistoryResponseDTO(
            h.creationHistory,
            h.presumeEndDateActivity,
            h.endDateActivity,
            h.statusActivity,
            h.note,
            u.name,
            u.lastName,
            a.activityName,
            a.description,
            a.activityCategory
             )
            FROM History h
            JOIN h.idUser u
            JOIN h.idActivity a
            WHERE u.idUser=:idUser
            ORDER BY h.presumeEndDateActivity
            """)
    List<HistoryResponseDTO> findHistoryForUser(int idUser);

    @Query("SELECT COUNT(h) FROM History h WHERE h.statusActivity IN :status AND h.idUser.idUser = :idUser")
    int countActivity( List<StatusActivity> status, int idUser);


    @Query("""
            SELECT new com.taskManagmentSystem.model.DTO.response.HistoryResponseDTO(
            h.creationHistory,
            h.presumeEndDateActivity,
            h.endDateActivity,
            h.statusActivity,
            h.note,
            u.name,
            u.lastName,
            a.activityName,
            a.description,
            a.activityCategory
             )
            FROM History h
            JOIN h.idUser u
            JOIN h.idActivity a
            WHERE u.idUser=:idUser AND DATEDIFF(presumeEndDateActivity,creationHistory) <10
            ORDER BY h.presumeEndDateActivity
            """)
    List<HistoryResponseDTO> findActivityDueDate(int idUser);
}
