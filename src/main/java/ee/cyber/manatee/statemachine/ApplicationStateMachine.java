package ee.cyber.manatee.statemachine;


import org.springframework.statemachine.StateMachine;


public interface ApplicationStateMachine {

    StateMachine<ApplicationState, ApplicationEvent> rejectApplication(
            Integer applicationId);


    /**
     * Initiates a state transition to move an application to the INTERVIEW state.
     *
     * @param applicationId the ID of the application to transition.
     * @return the updated state machine reflecting the new state.
     */
    StateMachine<ApplicationState, ApplicationEvent> transitionToInterviewState(Integer applicationId);
}
