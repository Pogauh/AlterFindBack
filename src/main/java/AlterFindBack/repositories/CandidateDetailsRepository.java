package AlterFindBack.repositories;
import AlterFindBack.entities.CandidateDetails;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;


@Repository
public interface CandidateDetailsRepository extends JpaRepository<CandidateDetails, Long> {
}
