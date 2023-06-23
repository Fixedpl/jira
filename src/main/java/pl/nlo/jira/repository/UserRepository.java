package pl.nlo.jira.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.nlo.jira.entity.UserEntity;

import java.util.List;
import java.util.Optional;

/**
 * @author marcin
 * @since 15.03.2023
 */
public interface UserRepository extends JpaRepository<UserEntity, Integer> {


	Optional<UserEntity> findByEmail(String email);

	@Query("SELECT u FROM user_base u WHERE u.id IN " +
			"(SELECT pu.userId FROM project_user pu WHERE pu.projectId = :projectId)")
	List<UserEntity> getProjectMembers(@Param("projectId") Integer projectId);

//	@Query("SELECT u FROM UserEntity u JOIN ProjectUserEntity pu ON u.id = pu.userId " +
//			"JOIN ProjectSprintEntity ps ON pu.projectId = ps.projectId WHERE ps.sprintId = :sprintId")
	@Query("SELECT u FROM user_base u JOIN project_user pu ON u.id = pu.userId " +
		"JOIN sprint s ON pu.projectId = s.projectId WHERE s.id = :sprintId")
    List<UserEntity> getProjectMembersBySprintId(@Param("sprintId") Integer sprintId);
}
