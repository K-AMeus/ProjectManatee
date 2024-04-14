package ee.cyber.manatee.repository;

import ee.cyber.manatee.model.Application;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Repository interface for {@link Application} entities.
 */
public interface ApplicationRepository extends JpaRepository<Application, Integer> {

    /**
     * Fetches all applications with their associated interviews.
     * Uses a JOIN FETCH to avoid N+1 query problems and efficiently fetch related interviews.
     * @return a list of all applications with their loaded interviews.
     */
    @Query("SELECT a FROM Application a LEFT JOIN FETCH a.interviews")
    List<Application> findAllWithInterviews();
}
