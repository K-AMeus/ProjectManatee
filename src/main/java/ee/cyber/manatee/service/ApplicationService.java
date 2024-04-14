package ee.cyber.manatee.service;


import java.time.OffsetDateTime;
import java.util.List;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import ee.cyber.manatee.model.Application;
import ee.cyber.manatee.repository.ApplicationRepository;
import ee.cyber.manatee.statemachine.ApplicationState;
import ee.cyber.manatee.statemachine.ApplicationStateMachine;


/**
 * Service class for managing applications.
 * Handles CRUD operations forwarded from the controller and performs business logic.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ApplicationService {

    private final ApplicationRepository applicationRepository;
    private final ApplicationStateMachine applicationStateMachine;

    /**
     * Retrieves all applications with their interviews included.
     * @return a list of all applications fetched along with their interviews.
     */
    public List<Application> getApplicationsWithInterviews() {
        return applicationRepository.findAllWithInterviews();
    }


    public Application insertApplication(Application application) {
        application.setId(null);
        application.setApplicationState(ApplicationState.NEW);
        application.setUpdatedOn(OffsetDateTime.now());

        return applicationRepository.save(application);
    }

    public void rejectApplication(Integer applicationId) {
        applicationStateMachine.rejectApplication(applicationId);
    }


}
