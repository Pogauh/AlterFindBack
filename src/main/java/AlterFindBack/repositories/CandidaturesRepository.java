package AlterFindBack.repositories;
import AlterFindBack.entities.Candidature;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CandidaturesRepository extends JpaRepository <Candidature, Long>{
    List<Candidature> findByCandidatId(Long candidatId);

}

