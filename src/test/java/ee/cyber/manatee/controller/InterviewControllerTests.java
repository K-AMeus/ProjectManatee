package ee.cyber.manatee.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import ee.cyber.manatee.dto.InterviewDto;
import ee.cyber.manatee.mapper.InterviewMapper;
import ee.cyber.manatee.model.Application;
import ee.cyber.manatee.model.Interview;
import ee.cyber.manatee.service.InterviewService;
import ee.cyber.manatee.statemachine.InterviewType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.OffsetDateTime;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


/**
 * Tests for the InterviewController class. These tests check the functionality of the endpoints
 * by simulating HTTP requests and asserting the responses. All dependencies are mocked to ensure
 * that tests run in isolation without requiring actual database access.
 */
@SpringBootTest
@AutoConfigureMockMvc
public class InterviewControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private InterviewService interviewService;

    @MockBean
    private InterviewMapper interviewMapper;

    @Autowired
    private ObjectMapper objectMapper;


    /**
     * Sets up the testing environment before each test. This method configures the MockMvc to use
     * a standalone setup which isolates the testing from other controllers or global configurations.
     */
    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(new InterviewController(interviewService, interviewMapper))
                .build();
    }



    /**
     * Tests the successful scheduling of an interview via POST request. It verifies that the correct
     * data is returned in the response and that the HTTP status is 200 OK.
     */
    @Test
    public void scheduleInterview_Success() throws Exception {
        // Arrange
        Application application = new Application();
        application.setId(1);  // Simulate an existing application with ID 1

        InterviewDto newInterview = new InterviewDto(1, "Mari Maasikas", OffsetDateTime.parse("2024-04-15T10:00:00Z"), InterviewDto.InterviewTypeEnum.TECHNICAL);
        Interview mockInterview = new Interview();
        mockInterview.setApplication(application);
        mockInterview.setInterviewerName("Mari Maasikas");
        mockInterview.setInterviewTime(OffsetDateTime.parse("2024-04-15T10:00:00Z"));
        mockInterview.setInterviewType(InterviewType.TECHNICAL);

        when(interviewMapper.toEntity(any(InterviewDto.class))).thenReturn(mockInterview);
        when(interviewService.scheduleInterview(any(Interview.class))).thenReturn(mockInterview);
        when(interviewMapper.toDto(any(Interview.class))).thenReturn(newInterview);

        String jsonRequest = objectMapper.writeValueAsString(newInterview);
        mockMvc.perform(post("/interviews")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.interviewerName").value("Mari Maasikas"))
                .andExpect(jsonPath("$.interviewType").value("TECHNICAL"));
    }

    /**
     * Tests the retrieval of all scheduled interviews, ensuring the list is not empty and contains
     * correctly mapped data.
     */
    @Test
    public void getAllInterviews_ReturnsNotEmptyList() throws Exception {
        // Arrange
        Application application = new Application();
        application.setId(1);

        Interview interview = new Interview();
        interview.setId(1);
        interview.setApplication(application);
        interview.setInterviewerName("Mari Maasikas");
        interview.setInterviewTime(OffsetDateTime.parse("2024-04-15T10:00:00Z"));
        interview.setInterviewType(InterviewType.TECHNICAL);

        List<Interview> interviews = Collections.singletonList(interview);
        when(interviewService.findAll()).thenReturn(interviews);

        InterviewDto interviewDto = new InterviewDto(1, "Mari Maasikas", OffsetDateTime.parse("2024-04-15T10:00:00Z"), InterviewDto.InterviewTypeEnum.TECHNICAL);
        interviewDto.setId(1);
        List<InterviewDto> interviewDtos = Collections.singletonList(interviewDto);
        when(interviewMapper.toDto(any(Interview.class))).thenReturn(interviewDto);

        // Act and Assert
        mockMvc.perform(get("/interviews")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].interviewerName").value("Mari Maasikas"))
                .andExpect(jsonPath("$[0].interviewType").value("TECHNICAL"))
                .andExpect(jsonPath("$[0].applicationId").value(1));
    }


}
