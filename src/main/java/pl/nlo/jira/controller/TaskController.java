package pl.nlo.jira.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import pl.nlo.jira.controller.validator.TaskValidator;
import pl.nlo.jira.dto.TaskDTO;
import pl.nlo.jira.repository.TaskRepository;
import pl.nlo.jira.service.TaskService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/task")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    private TaskValidator taskValidator;
    private TaskRepository taskRepository;

    @InitBinder(value = "taskDTO")
    void initStudentValidator(WebDataBinder binder) {
        binder.setValidator(taskValidator);
    }

    @PostMapping
    public ResponseEntity<Void> createTask(@RequestBody TaskDTO taskDTO) {
        taskService.createTask(taskDTO);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/update")
    public ResponseEntity<Void> updateTask(@RequestBody TaskDTO taskDTO) {
        taskService.updateTask(taskDTO);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<TaskDTO>> findAllTasks() {
        List<TaskDTO> tasks = taskService.findAllTasks();
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskDTO> getTaskById(@PathVariable Integer id) {
        return ResponseEntity.ok(taskService.findTaskById(id));
    }

    @GetMapping("/sprintTasks/{sprintId}")
    public ResponseEntity<List<TaskDTO>> getTasksBySprintId(@PathVariable("sprintId") Integer sprintId) {
        return ResponseEntity.ok(taskService.findAllBySprintId(sprintId));
    }

    @DeleteMapping("/delete/{id}")
    public void deleteTaskById(@PathVariable("id") Integer id) {
        taskService.deleteTaskById(id);
    }
}
