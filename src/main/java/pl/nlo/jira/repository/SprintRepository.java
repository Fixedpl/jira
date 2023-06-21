package pl.nlo.jira.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.nlo.jira.entity.SprintEntity;
import pl.nlo.jira.entity.Task;

import java.util.List;
import java.util.Optional;

/**
 * @author marcin
 * @since 07.06.2023
 */
@Repository
public interface SprintRepository extends JpaRepository<SprintEntity, Integer> {

	@Query("SELECT s FROM sprint s WHERE s.projectId = :projectId")
	List<SprintEntity> findByProjectId(@Param("projectId") Integer projectId);
	@Query("SELECT s FROM sprint s WHERE s.projectId = :projectId AND s.isActive = true ")
	Optional<SprintEntity> findByProjectIdAndSprintActive(Integer projectId);
	@Query("SELECT t FROM Task t JOIN t.sprints s WHERE s.projectId = :projectId and s.isActive IS TRUE and t.state = 'DONE'")
	List<Task> findCompletedTasks(@Param("projectId") Integer projectId);

	@Query("SELECT t FROM Task t JOIN t.sprints s WHERE s.projectId = :projectId and s.isActive IS TRUE ")
	List<Task> findTotalTasks(@Param("projectId") Integer projectId);

	
}
