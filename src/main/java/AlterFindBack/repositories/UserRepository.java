package AlterFindBack.repositories;

import AlterFindBack.entities.User;
import AlterFindBack.entities.UserType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository <User, Long> {
    boolean existsByEmail(String email);


//    @Query("SELECT u FROM User u WHERE u.userType = :type")
//        List<User> findByUserType(@Param("type") UserType type);

}


