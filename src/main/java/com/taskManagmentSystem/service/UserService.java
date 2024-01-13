package com.taskManagmentSystem.service;

import com.taskManagmentSystem.model.Authentication;
import com.taskManagmentSystem.model.DTO.request.UserDTO;
import com.taskManagmentSystem.model.User;

import java.util.List;

public interface UserService {


    Authentication createUser(UserDTO userDTO) throws Exception;

    void updateUser(UserDTO userDTO) throws Exception;


    List<User> filterSearch(String search) throws Exception;

    int underSpecificAge(String search)throws Exception;

    int betweenSpecificAge(String firstValue,String secondValue)throws Exception;

    void deleteUserLogic(String name,String lastName)throws Exception;
}
