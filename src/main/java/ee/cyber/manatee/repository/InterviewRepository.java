package ee.cyber.manatee.repository;


import ee.cyber.manatee.model.Interview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for accessing the database table corresponding to the Interview entity.
 */
@Repository
public interface InterviewRepository extends JpaRepository<Interview, Integer> {
}
