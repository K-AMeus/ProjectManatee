package ee.cyber.manatee.statemachine;


import jakarta.transaction.Transactional;

import lombok.val;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ee.cyber.manatee.model.Application;
import ee.cyber.manatee.model.Candidate;
import ee.cyber.manatee.repository.ApplicationRepository;
import ee.cyber.manatee.service.ApplicationService;

import static ee.cyber.manatee.statemachine.ApplicationState.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;



/**
 * Unit tests for the application state machine, specifically testing state transitions and their effects.
 */
@SpringBootTest
public class ApplicationStateMachineTests {

    @Autowired
    ApplicationService applicationService;

    @Autowired
    ApplicationStateMachine applicationStateMachine;

    @Autowired
    ApplicationRepository applicationRepository;


    /**
     * Tests the rejection process of an application, ensuring the state transitions from NEW to REJECTED properly.
     */
    @Test
    @Transactional
    public void applicationGetsRejected() {
        val newCandidate = Candidate.builder().firstName("Mari").lastName("Maasikas").build();
        val newApplication = Application.builder().candidate(newCandidate).build();

        val applicationSaved = applicationService.insertApplication(newApplication);
        val initialUpdatedOn = applicationSaved.getUpdatedOn();
        assertEquals(NEW, applicationSaved.getApplicationState());

        val stateMachine = applicationStateMachine.rejectApplication(applicationSaved.getId());
        assertEquals(REJECTED, stateMachine.getState().getId());

        val optionalRejectedApplication = applicationRepository.findById(applicationSaved.getId());
        assertFalse(optionalRejectedApplication.isEmpty());

        val rejectedApplication = optionalRejectedApplication.get();
        assertEquals(REJECTED, rejectedApplication.getApplicationState());
        assertNotEquals(initialUpdatedOn, rejectedApplication.getUpdatedOn());

    }


    /**
     * Tests the transition of an application to the INTERVIEW state, ensuring the state machine updates correctly.
     */
    @Test
    @Transactional
    public void transitionToInterviewState() {

        // Arrange
        val newCandidate = Candidate.builder().firstName("Mari").lastName("Maasikas").build();
        val newApplication = Application.builder().candidate(newCandidate).build();

        // Act: Save new application and assert initial state
        val applicationSaved = applicationService.insertApplication(newApplication);
        assertEquals(NEW, applicationSaved.getApplicationState());

        // Act: Transition to INTERVIEW state
        val stateMachine = applicationStateMachine.transitionToInterviewState(applicationSaved.getId());
        assertEquals(INTERVIEW, stateMachine.getState().getId());

        // Assert: Verify that the state change is reflected in the database
        val optionalUpdatedApplication = applicationRepository.findById(applicationSaved.getId());
        assertFalse(optionalUpdatedApplication.isEmpty());

        val updatedApplication = optionalUpdatedApplication.get();
        assertEquals(INTERVIEW, updatedApplication.getApplicationState());
    }

}
