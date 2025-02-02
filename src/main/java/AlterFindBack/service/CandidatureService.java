package AlterFindBack.service;

import AlterFindBack.entities.Candidature;
import AlterFindBack.repositories.CandidaturesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CandidatureService {

    @Autowired
    private CandidaturesRepository candidaturesRepository;

    public List<Candidature> getCandidaturesByCandidatId(Long candidatId) {
        return candidaturesRepository.findByCandidatId(candidatId);
    }
}
