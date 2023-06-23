package pl.nlo.jira.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.nlo.jira.entity.Task;
import pl.nlo.jira.entity.enums.State;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Integer> {

    //@Query("SELECT t FROM Task t JOIN t.sprints s WHERE s.id = :sprintId")
    @Query("SELECT t FROM Task t JOIN t.sprint s WHERE s.id = :sprintId")
    List<Task> findAllBySprintId(@Param("sprintId") Integer sprintId);

    @Modifying
    @Query("DELETE FROM Task t WHERE t.id = :id")
    void deleteById(@Param("id") Integer id);

    @Modifying
    @Query("UPDATE Task t SET t.state = :state WHERE t.id = :id")
    void updateState(@Param("id") Integer id, @Param("state") State state);
}
