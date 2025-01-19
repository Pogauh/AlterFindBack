package AlterFindBack.repositories;

import AlterFindBack.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);


//    @Query("SELECT u FROM User u WHERE u.userType = :type")
//        List<User> findByUserType(@Param("type") UserType type);

}


