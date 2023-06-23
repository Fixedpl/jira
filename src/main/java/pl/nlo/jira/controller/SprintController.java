package pl.nlo.jira.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.nlo.jira.entity.SprintEntity;
import pl.nlo.jira.repository.SprintRepository;
import pl.nlo.jira.service.SprintService;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author marcin
 * @since 06.06.2023
 */
@RestController
@RequestMapping("/api/v1/sprint")
@RequiredArgsConstructor
public class SprintController {

    private final SprintRepository sprintRepository;
    private final SprintService sprintService;

    @GetMapping("/{projectId}")
    public List<SprintEntity> getSprints(@PathVariable("projectId") Integer projectId) {
        return sprintRepository.findByProjectId(projectId);
    }
    @GetMapping("/{projectId}/completedTasks")
    public Integer getCompletedTasks(@PathVariable("projectId") Integer projectId) {
        return sprintService.getCompletedTasks(projectId);
    }
    @GetMapping("/{projectId}/totalTasks")
    public Integer getTotalTasks(@PathVariable("projectId") Integer projectId) {
        return sprintService.getTotalTasks(projectId);
    }
    @PostMapping("/")
    public void addSprint(@RequestBody SprintEntity sprintEntity) {
        sprintRepository.save(sprintEntity);
    }

    @GetMapping("/{id}/start")
    public void startSprint(@PathVariable("id") Integer id) {
        SprintEntity sprintEntity = sprintRepository.findById(id).get();
        // TODO: Walidacja
        sprintEntity.setActualStartDate(LocalDateTime.now());
        sprintEntity.setActive(true);
        sprintRepository.save(sprintEntity);
    }

    @GetMapping("/{id}/end")
    public void endSprint(@PathVariable("id") Integer id) {
        SprintEntity sprintEntity = sprintRepository.findById(id).get();
        // TODO: Walidacja
        sprintEntity.setActualEndDate(LocalDateTime.now());
        sprintEntity.setActive(false);
        sprintRepository.save(sprintEntity);
    }
}
