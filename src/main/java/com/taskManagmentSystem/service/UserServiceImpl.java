package com.taskManagmentSystem.service;

import com.taskManagmentSystem.model.DTO.request.UserDTO;
import com.taskManagmentSystem.model.User;
import com.taskManagmentSystem.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

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

    @Override
    public void updateUser(UserDTO userDTO) throws Exception {
        log.info("Enter into: UserServiceImpl - updateUser");
        if (userDTO != null) {
            User userRepo = userRepository.findById(userDTO.getIdUser()).orElseThrow();
            if (userDTO.getName() != null) {
                userRepo.setName(userDTO.getName());
            }
            if (userDTO.getAge() != null) {

                userRepo.setAge(userDTO.getAge());
            }
            if (userDTO.getLastName() != null) {

                userRepo.setLastName(userDTO.getLastName());
            }

            userRepository.save(userRepo);
            log.info("Finish method - updateUser");
        } else {
            log.error("Error into UserServiceImpl - updateUser");
            throw new Exception();
        }

    }

    @Override
    public List<User> filterSearch(String search) throws Exception {
        log.info("Enter into: UserServiceImpl - filterSearch");
        if (search != null) {
            List<User> listUser = userRepository.filterSearch(search);
            log.info("Finish method - filterSearch");
            return listUser;
        } else {
            log.error("Error into UserServiceImpl - filterSearch");
            throw new Exception();
        }
    }

    @Override
    public int underSpecificAge(String search) throws Exception {

        log.info("Enter into: UserServiceImpl - underSpecificAge");
        if (search != null) {
            int number = userRepository.underSpecificAge(search);
            log.info("Finish method - underSpecificAge");
            return number;
        } else {
            log.error("Error into UserServiceImpl - underSpecificAge");
            throw new Exception();
        }
    }

    @Override
    public int betweenSpecificAge(String firstValue, String secondValue) throws Exception {
        log.info("Enter into: UserServiceImpl - betweenSpecificAge");
        if (firstValue != null && secondValue != null) {
            int number = userRepository.betweenSpecificAge(firstValue, secondValue);
            log.info("Finish method - betweenSpecificAge");
            return number;
        } else {
            log.error("Error into UserServiceImpl - betweenSpecificAge");
            throw new Exception();
        }
    }

    @Override
    public void deleteUserLogic(String name, String lastName) throws Exception {
        log.info("Enter into: UserServiceImpl - deleteUserLogic");
        User user = userRepository.findUserByNameAndLastName(name, lastName);
        if (!user.equals(null)) {
            user.setDeleteFlag(true);
            userRepository.save(user);
            log.info("Finish method - deleteUserLogic");
        } else {
            log.error("Error into UserServiceImpl - betweenSpecificAge");
            throw new Exception();
        }
    }


}
