package com.taskManagmentSystem.service;

import com.taskManagmentSystem.model.Authentication;
import com.taskManagmentSystem.model.DTO.request.AuthenticationRequest;
import com.taskManagmentSystem.model.DTO.request.UserDTO;
import com.taskManagmentSystem.model.DTO.response.AuthenticationResponse;
import com.taskManagmentSystem.model.DTO.response.UserLoggedSuccess;
import com.taskManagmentSystem.model.User;

public interface AuthenticationService {

    Authentication findByid(int id);

    Authentication findByEmail(String email);

    Authentication loadUserById(String id);

    UserLoggedSuccess register(UserDTO userDTO) throws Exception;
    AuthenticationResponse authenticate(AuthenticationRequest request);

}
