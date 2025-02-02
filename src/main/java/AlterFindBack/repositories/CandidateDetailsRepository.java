package AlterFindBack.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import AlterFindBack.entities.CandidateDetails;


public interface CandidateDetailsRepository extends JpaRepository<CandidateDetails, Long> {
    CandidateDetails findByUserId(Long userId);

}
