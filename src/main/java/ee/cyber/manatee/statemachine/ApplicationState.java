package ee.cyber.manatee.statemachine;


import com.fasterxml.jackson.annotation.JsonFormat;

public enum ApplicationState {
    NEW,
    INTERVIEW,
    OFFER,
    PRE_ONBOARD,
    HIRED,
    REJECTED

}
