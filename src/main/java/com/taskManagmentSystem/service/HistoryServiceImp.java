package com.taskManagmentSystem.service;

import com.taskManagmentSystem.model.DTO.response.HistoryResponseDTO;
import com.taskManagmentSystem.model.History;
import com.taskManagmentSystem.model.StatusActivity;
import com.taskManagmentSystem.repository.HistoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@Service
public class HistoryServiceImp implements HistoryService {
    @Autowired
    HistoryRepository historyRepository;
    // ricevi l'id dell attivita lo status che cambia se non è completato lo aggiorni e basta altrimenti setti anche una data di fine e poi farai i filtri

    @Override
    public void changeStatus(int idActivity, StatusActivity statusActivity) {
        log.info("Enter into: HistoryServiceImp - changeStatus");
        History history = historyRepository.findByidActivityIdActivity(idActivity);
        if (history != null && !statusActivity.name().isEmpty() && !statusActivity.name().isBlank()) {
            log.info("Look if status is correct..");

            if (statusActivity == StatusActivity.COMPLETATA) {
                history.setEndDateActivity(LocalDate.now());
            }
            history.setStatusActivity(statusActivity);
            log.info("Status changed");
            historyRepository.save(history);
            log.info("History status changed correctly - method changeStatus");

        }

    }

    @Override
    public List<HistoryResponseDTO> findHistory(StatusActivity[] status, int idUser) {
        log.info("Enter into: HistoryServiceImp - findHistory");
        if (status.length == 0) {
            log.info("Fetch all data from this id user {} ", idUser);
            List<HistoryResponseDTO> historyResponseDTO = historyRepository.findHistory(idUser);
            return historyResponseDTO;

        } else {
            log.info("Fetch data with this status {}", status);
            List<HistoryResponseDTO> historyResponseDTO = historyRepository.findHistory(status, idUser);
            return historyResponseDTO;
        }


    }

    @Override
    public List<HistoryResponseDTO> findHistoryForUser(int idUser) {
        log.info("Enter into: HistoryServiceImp - findHistory");
        log.info("Fetch all data from this id user {} ", idUser);
        List<HistoryResponseDTO> historyResponseDTO = historyRepository.findHistoryForUser(idUser);
        return historyResponseDTO;
    }


    @Override
    public int countActivity(StatusActivity[] status, int idUser) {
        int number = historyRepository.countActivity(List.of(status), idUser);

        return number;
    }

    @Override
    public List<HistoryResponseDTO> findActivityDueDate(int idUser) {
        log.info("Enter into: HistoryServiceImp - findActivityDueDate");
        log.info("Fetch all data from this id user {} ", idUser);
        List<HistoryResponseDTO> historyResponseDTO = historyRepository.findActivityDueDate(idUser);
        return historyResponseDTO;
    }
}
