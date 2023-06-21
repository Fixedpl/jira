package pl.nlo.jira.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.nlo.jira.dto.TaskDTO;
import pl.nlo.jira.entity.SprintEntity;
import pl.nlo.jira.entity.Task;
import pl.nlo.jira.entity.UserEntity;
import pl.nlo.jira.mapper.TaskMapper;
import pl.nlo.jira.repository.SprintRepository;
import pl.nlo.jira.repository.TaskRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;

    private final TaskMapper taskMapper;

    private final AuthenticationService authenticationService;

    private final SprintRepository sprintRepository;

    @Transactional
    public void createTask(TaskDTO taskDTO) {
        Task task = taskMapper.toEntity(taskDTO);
        task.setReporter(authenticationService.getActiveUser());
        taskRepository.save(task);
    }

    public void updateTask(TaskDTO taskDTO) {
        Task task = taskMapper.toEntity(taskDTO);
        UserEntity activeUser = authenticationService.getActiveUser();
        taskRepository.save(task);
    }

    public List<TaskDTO> findAllTasks() {
        return taskMapper.toDTOs(taskRepository.findAll());
    }

    public TaskDTO findTaskById(Integer taskId) {
        return taskRepository.findById(taskId)
                .map(taskMapper::toDTO)
                .orElseThrow(() -> new RuntimeException("Task not found"));
    }

    public List<TaskDTO> findAllBySprintId(Integer sprintId) {
        return taskMapper.toDTOs(taskRepository.findAllBySprintId(sprintId));
    }

    @Transactional
    public void deleteTaskById(Integer id) {
        taskRepository.deleteById(id);
    }
}
