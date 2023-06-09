package pl.nlo.jira.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.nlo.jira.dto.ProjectDTO;
import pl.nlo.jira.entity.ProjectEntity;
import pl.nlo.jira.entity.ProjectUserEntity;
import pl.nlo.jira.entity.UserEntity;
import pl.nlo.jira.mapper.ProjectMapper;
import pl.nlo.jira.repository.ProjectRepository;
import pl.nlo.jira.repository.ProjectUserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectService {
    private final ProjectRepository projectRepository;
    private final ProjectMapper projectMapper;
    private final AuthenticationService authenticationService;
    private final ProjectUserRepository projectUserRepository;

    public void createProject(ProjectDTO projectDTO) {
        ProjectEntity projectEntity = projectRepository.save(projectMapper.toEntity(projectDTO));
        addConnectProjectWithUser(projectEntity.getId());
    }

    private void addConnectProjectWithUser(Integer projectId) {
        UserEntity activeUser = authenticationService.getActiveUser();
        ProjectUserEntity projectUserEntity = new ProjectUserEntity();
        projectUserEntity.setProjectId(projectId);
        projectUserEntity.setUserId(activeUser.getId());
        projectUserRepository.save(projectUserEntity);
    }

    public List<ProjectEntity> getCurrentUserProjects() {
        UserEntity activeUser = authenticationService.getActiveUser();
        return projectRepository.getProjectEntityByUserId(activeUser.getId());
    }
}
