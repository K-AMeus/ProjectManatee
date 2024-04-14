package ee.cyber.manatee.config;

import ee.cyber.manatee.model.Application;
import ee.cyber.manatee.model.Candidate;
import ee.cyber.manatee.model.Interview;
import ee.cyber.manatee.repository.ApplicationRepository;
import ee.cyber.manatee.repository.InterviewRepository;
import ee.cyber.manatee.statemachine.ApplicationState;
import ee.cyber.manatee.statemachine.InterviewType;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.OffsetDateTime;


/**
 * DataSeeder configures initial seed data for the application's in-memory database.
 * It preloads candidates and applications, some of which include scheduled interviews,
 */
@Configuration
public class DataSeeder {


    /**
     * Initializes the database with predefined candidates, applications, and interviews.
     * @param applicationRepository Repository for application entities.
     * @param interviewRepository Repository for interview entities.
     * @return a CommandLineRunner that populates the database.
     */
    @Bean
    CommandLineRunner initDatabase(ApplicationRepository applicationRepository, InterviewRepository interviewRepository) {
        return args -> {
            // Create Candidates
            Candidate candidate1 = new Candidate(null, "Jack", "Sparrow");
            Candidate candidate2 = new Candidate(null, "Hector", "Barbossa");
            Candidate candidate3 = new Candidate(null, "Elizabeth", "Swann");
            Candidate candidate4 = new Candidate(null, "Will", "Turner");
            Candidate candidate5 = new Candidate(null, "Davy", "Jones");
            Candidate candidate6 = new Candidate(null, "Joshamee", "Gibbs");

            // Create Applications
            Application app1 = new Application(null, ApplicationState.NEW, candidate1, OffsetDateTime.now(), null);
            Application app2 = new Application(null, ApplicationState.INTERVIEW, candidate2, OffsetDateTime.now(), null);
            Application app3 = new Application(null, ApplicationState.OFFER, candidate3, OffsetDateTime.now(), null);
            Application app4 = new Application(null, ApplicationState.NEW, candidate4, OffsetDateTime.now(), null);
            Application app5 = new Application(null, ApplicationState.INTERVIEW, candidate5, OffsetDateTime.now(), null);
            Application app6 = new Application(null, ApplicationState.REJECTED, candidate6, OffsetDateTime.now(), null);

            // Persist applications
            applicationRepository.save(app1);
            applicationRepository.save(app2);
            applicationRepository.save(app3);
            applicationRepository.save(app4);
            applicationRepository.save(app5);
            applicationRepository.save(app6);

            // Create Interviews and link to applications with INTERVIEW state
            Interview interview1 = new Interview(null, app2, OffsetDateTime.now().plusDays(1), "Interviewer One", InterviewType.TECHNICAL);
            Interview interview2 = new Interview(null, app2, OffsetDateTime.now().plusDays(3), "Interviewer Two", InterviewType.FINAL);
            Interview interview3 = new Interview(null, app5, OffsetDateTime.now().plusDays(2), "Interviewer Three", InterviewType.BEHAVIORAL);
            Interview interview4 = new Interview(null, app5, OffsetDateTime.now().plusDays(4), "Interviewer Four", InterviewType.INFORMAL);

            // Persist interviews
            interviewRepository.save(interview1);
            interviewRepository.save(interview2);
            interviewRepository.save(interview3);
            interviewRepository.save(interview4);
        };
    }
}
