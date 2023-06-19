package pl.nlo.jira.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.nlo.jira.entity.Task;

public interface TaskRepository extends JpaRepository<Task, Integer> {

}
