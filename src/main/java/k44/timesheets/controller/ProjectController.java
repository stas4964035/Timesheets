package k44.timesheets.controller;

import k44.timesheets.aspect.Recover;
import k44.timesheets.model.Project;
import k44.timesheets.model.Timesheet;
import k44.timesheets.service.ProjectService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/projects")
@AllArgsConstructor
public class ProjectController {
    private final ProjectService service;

    @GetMapping
    public List<Project> getAllProjects(){
        return service.getAllProjects();
    }


    @GetMapping("/{id}")
    public Project getProjectById(@PathVariable Long id){
        return service.getProjectById(id);
    }

    @GetMapping("/{id}/timesheets")
    public List<Timesheet> getTimesheetsByProjectId(@PathVariable Long id){
        return service.getTimesheetsByProjectId(id);
    }
}
