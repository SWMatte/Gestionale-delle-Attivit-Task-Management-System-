package com.taskManagmentSystem.service;

import com.taskManagmentSystem.model.Authentication;
import com.taskManagmentSystem.model.DTO.request.AuthenticationRequest;
import com.taskManagmentSystem.model.DTO.request.UserDTO;
import com.taskManagmentSystem.model.DTO.response.AuthenticationResponse;
import com.taskManagmentSystem.model.DTO.response.UserLoggedSuccess;
import com.taskManagmentSystem.model.StatusUser;
import com.taskManagmentSystem.model.User;
import com.taskManagmentSystem.repository.AuthenticationRepository;
import com.taskManagmentSystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import lombok.extern.slf4j.Slf4j;


import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;


@Service
@Slf4j
public class AuthenticationServiceImpl implements AuthenticationService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    AuthenticationRepository authenticationRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    JwtService jwtService;

    @Override
    public Authentication findByid(int id) {
        Authentication authentication = authenticationRepository.findByIdUserIdUser(id).get();
        return authentication;
    }

    @Override
    public Authentication findByEmail(String email) {
        return authenticationRepository.findByEmail(email);
    }


    @Override
    public Authentication loadUserById(String id) {
        Authentication auth = authenticationRepository.findByIdUserIdUser(Integer.parseInt(id)).get();
        return auth;

    }

    @Override
    public UserLoggedSuccess register(UserDTO userDTO) throws Exception {
        log.info("Enter into: AuthenticationServiceImpl - register");
        UserLoggedSuccess responseLogged = UserLoggedSuccess.builder().success(false).build();
        User user = User.builder().name(userDTO.getName()).lastName(userDTO.getLastName()).age(userDTO.getAge()).creationDate(LocalDate.now()).build();
        Authentication authentication = authenticationRepository.findByEmail(userDTO.getEmail());
        if (authentication == null) {
            if (user != null && !userDTO.getPassword().isEmpty() && !userDTO.getEmail().isEmpty() && !userDTO.getUserRole().name().isEmpty()) {
                log.info("User saved correctly");
                userRepository.save(user);

                Authentication auth =
                        Authentication.builder()
                                .email(userDTO.getEmail())
                                .password(passwordEncoder.encode(userDTO.getPassword()))
                                .role(userDTO.getUserRole())
                                .idUser(user)
                                .statusUser(StatusUser.ATTIVO)
                                .build();
                log.info("Authentication saved correctly");
                authenticationRepository.save(auth);
                responseLogged.setSuccess(true);
                log.info("Informations saved correctly - end method register");
            }
            log.error("some issues with params {}", userDTO);
            throw new Exception();
        } else {
            log.info("User with this email already exist on database");
        }
        return responseLogged;

    }

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        log.info("Enter into: AuthenticationServiceImpl - authenticate");
        Authentication auth = authenticationRepository.findByEmail(request.getEmail());
        if (!passwordEncoder.matches(request.getPassword(), auth.getPassword())) {
            throw new RuntimeException("Bad credential with password");
        }
        log.info("Changed last-access");
        auth.setLastAccess(new Date());
        authenticationRepository.save(auth);

        var jwtToken = jwtService.generateToken(auth);
        log.info("The token is generated and the user is authenticated! {}", auth);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }


}
