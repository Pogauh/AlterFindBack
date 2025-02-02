package AlterFindBack.repositories;

import AlterFindBack.entities.Entreprise;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EntrepriseRepository  extends JpaRepository<Entreprise, Long> {
    Entreprise findByUserId(Long userId);


}
