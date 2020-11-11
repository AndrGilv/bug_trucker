package com.example.but_trucker2.controller;

import com.example.but_trucker2.controller.modelAssembler.ProjectModelAssembler;
import com.example.but_trucker2.controller.request.ProjectRequest;
import com.example.but_trucker2.controller.response.extended.ExtendedErrorResponse;
import com.example.but_trucker2.controller.response.extended.ExtendedProjectResponse;
import com.example.but_trucker2.entity.ErrorEntity;
import com.example.but_trucker2.entity.ProjectEntity;
import com.example.but_trucker2.entity.UserEntity;
import com.example.but_trucker2.exception.ErrorNotFoundException;
import com.example.but_trucker2.exception.ProjectNotFoundException;
import com.example.but_trucker2.exception.UserNotFoundException;
import com.example.but_trucker2.repository.ErrorRepo;
import com.example.but_trucker2.repository.ProjectRepo;
import com.example.but_trucker2.repository.UserRepo;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping(path = "/project")
public class ProjectController {

    private final ProjectRepo projectsRepository;
    private final ErrorRepo errorRepository;
    private final UserRepo userRepository;

    private final ProjectModelAssembler projectAssembler;

    public ProjectController(ProjectRepo projectRepo, ErrorRepo errorRepository, UserRepo userRepository, ProjectModelAssembler projectAssembler){
        this.projectsRepository = projectRepo;
        this.projectAssembler = projectAssembler;
        this.errorRepository = errorRepository;
        this.userRepository = userRepository;
    }

    // Aggregate root

    @GetMapping("/all")
    public CollectionModel<EntityModel<ExtendedProjectResponse>> all() {
        List<EntityModel<ExtendedProjectResponse>> projects = ((List<ProjectEntity>) projectsRepository.findAll())
                .stream()
                .map( project -> projectAssembler.toModel(new ExtendedProjectResponse(project)))
                .collect(Collectors.toList());

        return CollectionModel.of(projects, linkTo(methodOn(ProjectController.class).all()).withSelfRel());
    }

    @PostMapping()
    public ResponseEntity<EntityModel<ExtendedProjectResponse>> newProject(@RequestBody ProjectRequest request) {
        UserEntity manager = userRepository.findById(request.getManagerId())
                .orElseThrow(() -> new UserNotFoundException(request.getManagerId()));
        ProjectEntity newProject = new ProjectEntity(request.getName(),manager);

        EntityModel<ExtendedProjectResponse> projectModel = projectAssembler.toModel( new ExtendedProjectResponse(projectsRepository.save(newProject)));
        return ResponseEntity //
                .created(projectModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
                .body(projectModel);
    }

    @PostMapping("/addError?projectId={projectId}&errorId={errorId}")
    public ResponseEntity<EntityModel<ExtendedProjectResponse>> addError(@PathVariable Long errorId, @PathVariable Long projectId) {

        ErrorEntity error = errorRepository.findById(errorId)
                .orElseThrow(() -> new ErrorNotFoundException(errorId));
        ProjectEntity project = projectsRepository.findById(projectId)
                .orElseThrow(() -> new ProjectNotFoundException(projectId));
        project.getErrors().add(error);

        EntityModel<ExtendedProjectResponse> projectModel = projectAssembler.toModel( new ExtendedProjectResponse(projectsRepository.save(project)));
        return ResponseEntity //
                .created(projectModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
                .body(projectModel);
    }

    @PostMapping("/setManager?projectId={projectId}&managerId={managerId}")
    public ResponseEntity<EntityModel<ExtendedProjectResponse>> setManager(@PathVariable Long managerId, @PathVariable Long projectId) {

        UserEntity manager = userRepository.findById(managerId)
                .orElseThrow(() -> new UserNotFoundException(managerId));
        ProjectEntity project = projectsRepository.findById(projectId)
                .orElseThrow(() -> new ProjectNotFoundException(projectId));
        project.setManager(manager);

        EntityModel<ExtendedProjectResponse> projectModel = projectAssembler.toModel( new ExtendedProjectResponse(projectsRepository.save(project)));
        return ResponseEntity //
                .created(projectModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
                .body(projectModel);
    }

    @PostMapping("/addDeveloper?projectId={projectId}&developerId={developerId}")
    public ResponseEntity<EntityModel<ExtendedProjectResponse>> addDeveloper(@PathVariable Long developerId, @PathVariable Long projectId) {

        UserEntity developer = userRepository.findById(developerId)
                .orElseThrow(() -> new UserNotFoundException(developerId));
        ProjectEntity project = projectsRepository.findById(projectId)
                .orElseThrow(() -> new ProjectNotFoundException(projectId));
        project.getDevelopersGroup().add(developer);

        EntityModel<ExtendedProjectResponse> projectModel = projectAssembler.toModel( new ExtendedProjectResponse(projectsRepository.save(project)));
        return ResponseEntity //
                .created(projectModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
                .body(projectModel);
    }

    // Single item

    @GetMapping("/{id}")
    public EntityModel<ExtendedProjectResponse> one(@PathVariable Long id) {
        ProjectEntity project = projectsRepository.findById(id)
                .orElseThrow(() -> new ProjectNotFoundException(id));
        return projectAssembler.toModel(new ExtendedProjectResponse(project));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<ExtendedProjectResponse>> replaceProject(@RequestBody ProjectRequest request, @PathVariable Long id) {
        UserEntity manager = userRepository.findById(request.getManagerId())
                .orElseThrow(() -> new UserNotFoundException(request.getManagerId()));
        ProjectEntity newProject = new ProjectEntity(request.getName(),manager);

        ProjectEntity updatedProject = projectsRepository.findById(id)
                .map(project -> {
                    project.updateAllFieldsFrom(newProject);
                    return projectsRepository.save(project);
                })
                .orElseGet(() -> {
                    newProject.setId(id);
                    return projectsRepository.save(newProject);
                });
        EntityModel<ExtendedProjectResponse> projectModel = projectAssembler.toModel(new ExtendedProjectResponse(updatedProject));
        return ResponseEntity //
                .created(projectModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
                .body(projectModel);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProject(@PathVariable Long id) {
        projectsRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/removeErrorFromProject?errorId={errorId}&projectId={projectId}")
    public ResponseEntity<?> removeErrorFromProject(@PathVariable Long errorId, @PathVariable Long projectId) {
        ProjectEntity project = projectsRepository.findById(projectId)
                .orElseThrow(() -> new ProjectNotFoundException(projectId));
        project.getErrors().removeIf(error -> error.getId().equals(errorId));
        return ResponseEntity.noContent().build();
    }
}
