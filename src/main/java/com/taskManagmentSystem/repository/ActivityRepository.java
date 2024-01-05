package com.taskManagmentSystem.repository;

import com.taskManagmentSystem.model.Activity;
import com.taskManagmentSystem.model.Category;
import com.taskManagmentSystem.model.DTO.response.ActivityResponseDTO;
import com.taskManagmentSystem.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ActivityRepository extends JpaRepository<Activity, Integer> {


    @Query("""
                SELECT new com.taskManagmentSystem.model.DTO.response.ActivityResponseDTO(
                    a.activityName ,
                    a.description,
                    a.creationDate,
                    a.activityCategory ,
                    u.name,
                    u.lastName)
                FROM Activity a
                JOIN a.idUser u
                WHERE a.activityCategory LIKE :params AND u.idUser =:idUser
                AND a.deleteFlag = false
            """)
    List<ActivityResponseDTO> findAllActivity(Category params,int idUser);


    @Query("""
                SELECT new com.taskManagmentSystem.model.DTO.response.ActivityResponseDTO(
                    a.activityName ,
                    a.description,
                    a.creationDate,
                    a.activityCategory ,
                    u.name,
                    u.lastName)
                FROM Activity a
                JOIN a.idUser u
               WHERE a.deleteFlag = false AND u.idUser =:idUser
            """)
    List<ActivityResponseDTO> findAllActivityNoFilter(int idUser);
}
