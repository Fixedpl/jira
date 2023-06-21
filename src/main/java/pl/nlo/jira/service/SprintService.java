package pl.nlo.jira.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.nlo.jira.entity.Task;
import pl.nlo.jira.repository.SprintRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SprintService {
    final private SprintRepository sprintRepository;

    public Integer getCompletedTasks(Integer projectId) {
        List<Task> completedTasks = sprintRepository.findCompletedTasks(projectId);
        return completedTasks.size();
    }

    public Integer getTotalTasks(Integer projectId) {
        List<Task> completedTasks = sprintRepository.findTotalTasks(projectId);
        return completedTasks.size();
    }
}
