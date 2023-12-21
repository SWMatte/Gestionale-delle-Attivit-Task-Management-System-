package com.taskManagmentSystem.service;

import com.taskManagmentSystem.model.DTO.UserDTO;
import com.taskManagmentSystem.model.User;
import com.taskManagmentSystem.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void createUser(UserDTO userDTO) throws Exception {
        log.info("Enter into: UserServiceImpl - createUser");
        if (userDTO != null) {
            User user = User.builder().name(userDTO.getName()).lastName(userDTO.getLastName()).age(userDTO.getAge()).creationDate(LocalDate.now()).build();
            userRepository.save(user);
            log.info("User added - finish method : createUser");

        } else {
            log.error("Error into UserServiceImpl - createUser");
            throw new Exception();
        }
    }
}
