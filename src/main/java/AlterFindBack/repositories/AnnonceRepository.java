package AlterFindBack.repositories;

import AlterFindBack.entities.Annonce;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnnonceRepository  extends JpaRepository<Annonce, Long> {
    List<Annonce> findByEntrepriseId(Long entrepriseId);

}
