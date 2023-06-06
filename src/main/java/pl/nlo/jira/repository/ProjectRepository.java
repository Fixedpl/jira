package pl.nlo.jira.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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

	String ENTITY_FIELDS = "id, name, description";

	@Query("SELECT " + ENTITY_FIELDS + " FROM project p WHERE p.id IN (SELECT u.project_id FROM project_user u WHERE u.user_id = :userId)")
	List<ProjectEntity> getProjectEntityByUserId(Integer userId);

}
