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


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

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
        User user = User.builder().name(userDTO.getName()).lastName(userDTO.getLastName()).age(userDTO.getAge()).creationDate(LocalDate.now()).build();
        userRepository.save(user);

        Authentication auth =
                Authentication.builder()
                        .email(userDTO.getEmail())
                        .password(passwordEncoder.encode(userDTO.getPassword()))
                        .role(userDTO.getUserRole())
                        .idUser(user)
                        .statusUser(StatusUser.ATTIVO)
                        .build();
        authenticationRepository.save(auth);
        return new UserLoggedSuccess(true);

    }

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        log.info("Looking for the user");
        Authentication auth = authenticationRepository.findByEmail(request.getEmail());
        if (!passwordEncoder.matches(request.getPassword(), auth.getPassword())) {
            throw new RuntimeException("Bad credential");
        }

        log.info("Calling method Generate Token in JwtService");
        var jwtToken = jwtService.generateToken(auth);
        log.info("The token is generated and the user is authenticated!");
        log.info(String.valueOf(auth));
        return AuthenticationResponse.builder().token(jwtToken).build();
    }


}
