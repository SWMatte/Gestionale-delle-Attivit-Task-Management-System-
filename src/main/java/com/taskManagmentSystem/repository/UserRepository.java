package com.taskManagmentSystem.repository;

import com.taskManagmentSystem.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {

    @Query(value = """
                 SELECT u.id_user, u.name, u.last_name, u.age,u.creation_date,delete_flag
                 FROM `user` u
                 WHERE CONCAT(u.name,' ',u.last_name) LIKE %:search% AND  delete_flag=false
                 OR    CONCAT(u.last_name,' ',u.name) LIKE %:search% AND  delete_flag=false
                 OR u.age LIKE %:search% AND  delete_flag=false
                 ORDER BY u.age,u.last_name,u.name
            """, nativeQuery = true)
    List<User> filterSearch(String search);


    @Query(value = """
            SELECT COUNT(u.age),delete_flag
            FROM `user` u
            WHERE (u.age < :search) AND u.delete_flag=false;
            """, nativeQuery = true)
    int underSpecificAge(String search);

    @Query(value = """
                SELECT COUNT(u.age),delete_flag
                FROM `user` u
                WHERE (u.age  between :firstValue AND :secondValue) AND u.delete_flag=false;
                            
                """, nativeQuery = true)
    int betweenSpecificAge(String firstValue, String secondValue);


    User findUserByNameAndLastName(String name, String lastName);
}
