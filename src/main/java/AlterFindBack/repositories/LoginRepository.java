package AlterFindBack.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import AlterFindBack.controller.dto.UserDto;

@Repository
public interface LoginRepository extends JpaRepository<UserDto, Integer> {

    Optional<UserDto> findByEmail(String email);
}