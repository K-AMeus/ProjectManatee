package ee.cyber.manatee.service;


import ee.cyber.manatee.model.Interview;
import ee.cyber.manatee.repository.InterviewRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service layer for managing interviews.
 */
@Service
@AllArgsConstructor
public class InterviewService {
    private final InterviewRepository interviewRepository;

    /**
     * Retrieves all interviews.
     *
     * @return A list of all interviews.
     */
    public List<Interview> findAll() {
        return interviewRepository.findAll();
    }




    /**
     * Schedules a new interview and saves it to the database.
     *
     * @param interview The interview to schedule.
     * @return The scheduled interview.
     */
    @Transactional
    public Interview scheduleInterview(Interview interview) {
        return interviewRepository.save(interview);
    }


}
