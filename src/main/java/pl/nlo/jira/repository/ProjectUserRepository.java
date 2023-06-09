package pl.nlo.jira.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.nlo.jira.entity.ProjectUserEntity;

/**
 * @author marcin
 * @since 07.06.2023
 */
@Repository
public interface ProjectUserRepository extends JpaRepository<ProjectUserEntity, Integer> {
}
