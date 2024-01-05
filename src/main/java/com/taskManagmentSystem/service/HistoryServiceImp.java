package com.taskManagmentSystem.service;

import com.taskManagmentSystem.model.History;
import com.taskManagmentSystem.model.StatusActivity;
import com.taskManagmentSystem.repository.HistoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

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
}
