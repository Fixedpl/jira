package pl.nlo.jira.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.nlo.jira.entity.SprintEntity;

import java.util.List;

/**
 * @author marcin
 * @since 07.06.2023
 */
@Repository
public interface SprintRepository extends JpaRepository<SprintEntity, Integer> {

	@Query("SELECT s FROM sprint s WHERE s.projectId = :projectId")
	List<SprintEntity> findByProjectId(@Param("projectId") Integer projectId);

}
