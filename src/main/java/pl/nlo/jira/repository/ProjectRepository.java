package pl.nlo.jira.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.nlo.jira.entity.ProjectEntity;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author marcin
 * @since 06.06.2023
 */
@Repository
@Transactional
public interface ProjectRepository extends JpaRepository<ProjectEntity, Integer> {

	@Query("SELECT p FROM project p WHERE p.id IN "
			+ "(SELECT u.projectId FROM project_user u WHERE u.userId = :userId)")
	List<ProjectEntity> getProjectEntityByUserId(Integer userId);

}
