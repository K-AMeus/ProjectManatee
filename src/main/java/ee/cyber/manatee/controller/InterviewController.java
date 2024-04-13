package ee.cyber.manatee.controller;


import ee.cyber.manatee.dto.InterviewDto;
import ee.cyber.manatee.mapper.InterviewMapper;
import ee.cyber.manatee.model.Interview;
import ee.cyber.manatee.service.InterviewService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


/**
 * Handles incoming requests related to interview scheduling and retrieval.
 */
@RestController
@AllArgsConstructor
@RequestMapping("/interviews")
public class InterviewController {

    private final InterviewService interviewService;
    private final InterviewMapper interviewMapper;


    /**
     * Retrieves all scheduled interviews.
     *
     * @return A list of interviews as data transfer objects.
     */
    @GetMapping
    public ResponseEntity<List<InterviewDto>> getAllInterviews() {
        List<Interview> interviews = interviewService.findAll();
        List<InterviewDto> interviewDtos = interviews.stream()
                .map(interviewMapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(interviewDtos);
    }




    /**
     * Schedules a new interview based on the provided Interview DTO.
     *
     * @param interviewDto Data transfer object containing the interview details.
     * @return The scheduled interview as a data transfer object.
     */
    @PostMapping
    public ResponseEntity<InterviewDto> scheduleInterview(@Valid @RequestBody InterviewDto interviewDto) {
        Interview interview = interviewMapper.toEntity(interviewDto);
        interview = interviewService.scheduleInterview(interview);
        return ResponseEntity.ok(interviewMapper.toDto(interview));
    }

}
