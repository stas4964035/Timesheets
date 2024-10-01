package k44.timesheets.service;

import k44.timesheets.model.Employee;
import k44.timesheets.model.Project;
import k44.timesheets.model.Timesheet;
import k44.timesheets.repository.ProjectRepository;
import k44.timesheets.repository.TimesheetRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProjectService {
    private final ProjectRepository projectRepository;
    private final TimesheetRepository timesheetRepository;

    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    public Project getProjectById(Long id) {
        Optional<Project> project = projectRepository.findById(id);
        return project.orElseThrow(()-> new NoSuchElementException("Project not found"));
    }

    public List<Timesheet> getTimesheetsByProjectId(Long id) {
        return timesheetRepository.findByProjectId(id);
    }

//    public void addEmployee(Long id, Employee employee) {
//        Project project = getProjectById(id);
//        List<Employee> projectEmployees = project.getEmployees();
//        if (!projectEmployees.contains(employee)) {
//            projectEmployees.add(employee);
//            projectRepository.save(project);
//        }
//
//    }
}
