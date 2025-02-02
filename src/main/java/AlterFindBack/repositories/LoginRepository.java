package AlterFindBack.repositories;

import java.util.Optional;
import AlterFindBack.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginRepository extends JpaRepository<User, Integer> {

    Optional<User> findByEmail(String email);
}