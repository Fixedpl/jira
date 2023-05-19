package pl.nlo.jira.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.nlo.jira.entity.UserEntity;

import java.util.Optional;

/**
 * @author marcin
 * @since 15.03.2023
 */
public interface UserRepository extends JpaRepository<UserEntity, Integer> {


	Optional<UserEntity> findByEmail(String email);
}
