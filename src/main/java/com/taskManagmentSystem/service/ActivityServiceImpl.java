package com.taskManagmentSystem.service;

import com.taskManagmentSystem.model.Activity;
import com.taskManagmentSystem.model.Category;
import com.taskManagmentSystem.model.DTO.request.ActivityDTO;
import com.taskManagmentSystem.model.DTO.response.ActivityResponseDTO;
import com.taskManagmentSystem.model.History;
import com.taskManagmentSystem.model.User;
import com.taskManagmentSystem.repository.ActivityRepository;
import com.taskManagmentSystem.repository.HistoryRepository;
import com.taskManagmentSystem.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;


@Service
@Slf4j
public class ActivityServiceImpl implements ActivityService {

    @Autowired
    private ActivityRepository activityRepository;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private HistoryRepository historyRepository;

    @Override
    @Transactional
    public void addElement(ActivityDTO activityDTO) throws Exception {
        log.info("Enter into: ActivityServiceImpl - addElement");
        User user = userRepository.findById(activityDTO.getIdUser().getIdUser()).get();
        log.info("Try to find a user to set in activity");
        if (activityDTO != null) {

            if (user != null && user.isDeleteFlag() == false) {
                log.info("User found with this ID {}", activityDTO.getIdUser().getIdUser());

                Activity activity = Activity.builder().activityCategory(activityDTO.getActivityCategory())
                        .activityName(activityDTO.getActivityName())
                        .creationDate(LocalDate.now())
                        .description(activityDTO.getDescription())
                        .idUser(activityDTO.getIdUser())
                        .build();
                activityRepository.save(activity);
                log.info("Activity added correctly");
                log.info("Start to add History");
                History history = History.builder()
                        .creationHistory(LocalDate.now())
                        .presumeEndDateActivity(activityDTO.getHistory().getPresumeEndDateActivity())
                        .statusActivity(activityDTO.getHistory().getStatusActivity())
                        .note(activityDTO.getHistory().getNote())
                        .idUser(user)
                        .idActivity(activity)
                        .build();

                historyRepository.save(history);
                log.info("History added correctly");
                log.info("finish method : addElement");
            } else {
                log.error("User not found with this ID {}", activityDTO.getIdUser().getIdUser());
                throw new Exception();
            }
        } else {
            log.error("Error into ActivityServiceImpl - addElement");
            throw new Exception();
        }

    }

    @Override
    public List<ActivityResponseDTO> allActivity(String params,int IdUser) throws Exception {
        log.info("Enter into: ActivityServiceImpl - allActivity");
        List<ActivityResponseDTO> response = null;
        if(!params.isEmpty()){

            Category category = Category.valueOf(params.toUpperCase());
            log.info("Fetch all filtered data  - allActivity");
            response = activityRepository.findAllActivity(category,IdUser);
        } else{
            log.info("Fetch all data  - allActivity");
            response = activityRepository.findAllActivityNoFilter(IdUser);

        }

        log.info("Finish method - allActivity");

        return response;
    }

    @Override
    public void modifyActivity(ActivityDTO updateActivityDTO) throws Exception {
        log.info("Enter into: ActivityServiceImpl - modifyActivity");
        if (updateActivityDTO.getHistory() == null) {
            Activity activity = activityRepository.findById(updateActivityDTO.getIdActivity()).get();
            log.info("Try to find activity with ID {}", updateActivityDTO.getIdActivity());
            if (activity != null) {
                if (updateActivityDTO.getActivityName() != null) {
                    activity.setActivityName(updateActivityDTO.getActivityName());
                }
                if (updateActivityDTO.getDescription() != null) {
                    activity.setDescription(updateActivityDTO.getDescription());
                }
                if (updateActivityDTO.getActivityCategory() != null) {
                    activity.setActivityCategory(updateActivityDTO.getActivityCategory());
                }
                activityRepository.save(activity);
                log.info("Activity modified correctly");
                log.info("Finish - method : modifyActivity");
            } else {
                log.error("Error activity not found with this ID {}", updateActivityDTO.getIdActivity());
                throw new Exception();
            }

        } else {
            log.error("Error into ActivityServiceImpl - addElement");
            throw new Exception();
        }

    }

    @Override
    public void deleteActivity(int id) throws Exception {
        log.info("Enter into: ActivityServiceImpl - deleteActivity");
    if(id!=0){
        log.info("Finding the activity with ID {}",id);
        Activity activity = activityRepository.findById(id).get();
        activity.setDeleteFlag(true);
        log.info("Eliminated logically the activity");
        activityRepository.save(activity);
        log.info("Finish - method : deleteActivity");
    } else {
        log.error("Error activity not found with this ID {}", id);
        throw new Exception();
    }
    }
}
