package k44.timesheets;

import k44.timesheets.model.Employee;
import k44.timesheets.model.Project;
import k44.timesheets.model.Timesheet;
import k44.timesheets.repository.EmployeeRepository;
import k44.timesheets.repository.ProjectRepository;
import k44.timesheets.repository.TimesheetRepository;
import org.hibernate.annotations.NotFound;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.ThreadLocalRandom;

@SpringBootApplication
public class TimesheetsApplication {


    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(TimesheetsApplication.class, args);

        ProjectRepository projectRepository = ctx.getBean(ProjectRepository.class);
        TimesheetRepository timesheetRepository = ctx.getBean(TimesheetRepository.class);
        EmployeeRepository employeeRepository = ctx.getBean(EmployeeRepository.class);

        List<String> names = Arrays.asList("Ivan", "Petr", "Mike", "Alex", "Olga", "Anna", "Maria");
        for (int i = 0; i < 10; i++) {
            Employee employee = new Employee();
            employee.setName(names.get(ThreadLocalRandom.current().nextInt(names.size())));
            employee.setAge(ThreadLocalRandom.current().nextInt(25, 50));
            employeeRepository.save(employee);
        }

        for (int i = 1; i <= 5; i++) {
            Project project = new Project();
            project.setName("Project " + i);
            do {
                Employee employee = employeeRepository.findById(ThreadLocalRandom.current().nextLong(1,11))
                        .orElseThrow(NoSuchElementException::new);
                if(!project.getEmployees().contains(employee)) {
                    project.getEmployees().add(employee);
                }
            } while (ThreadLocalRandom.current().nextBoolean());
            projectRepository.save(project);
        }

        LocalDateTime createdAt = LocalDateTime.now();
        for (int i = 0; i < 10; i++) {
            createdAt = createdAt.plusDays(1);
            Timesheet timeSheet = new Timesheet();
            timeSheet.setProjectId(ThreadLocalRandom.current().nextLong(1, 6));
            timeSheet.setCreatedAt(createdAt.toLocalDate());
            timeSheet.setMinutes(ThreadLocalRandom.current().nextInt(100, 1000));
            timeSheet.setEmployeeId(ThreadLocalRandom.current().nextLong(names.size()));

            timesheetRepository.save(timeSheet);
        }

    }

}
