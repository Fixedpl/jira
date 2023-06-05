package pl.nlo.jira.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.nlo.jira.dto.TaskDTO;
import pl.nlo.jira.entity.Task;
import pl.nlo.jira.mapper.TaskMapper;
import pl.nlo.jira.repository.TaskRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;

    private final TaskMapper taskMapper;

    public void createTask(TaskDTO taskDTO) {
        taskRepository.save(taskMapper.toEntity(taskDTO));
    }

    public void updateTask(TaskDTO taskDTO) {
        taskRepository.save(taskMapper.toEntity(taskDTO));
    }

    public List<TaskDTO> findAllTasks() {
        return taskMapper.toDTOs(taskRepository.findAll());
    }

    public TaskDTO findTaskById(Integer taskId) {
        return taskRepository.findById(taskId)
                .map(taskMapper::toDTO)
                .orElseThrow(() -> new RuntimeException("Task not found"));
    }
}
