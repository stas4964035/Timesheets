package k44.timesheets;

import k44.timesheets.model.*;
import k44.timesheets.repository.*;
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
        UsersRepository usersRepository = ctx.getBean(UsersRepository.class);


        RoleRepository roleRepository = ctx.getBean(RoleRepository.class);
        Role adminRole = new Role();
        adminRole.setName("admin");
        adminRole = roleRepository.save(adminRole);
        Role userRole = new Role();
        userRole.setName("user");
        userRole = roleRepository.save(userRole);
        Role restRole = new Role();
        restRole.setName("rest");
        restRole = roleRepository.save(restRole);


        User admin = new User();
        admin.setLogin("admin");
        admin.setPassword("$2a$12$RdrR3Cg.y8iNy/tGtu8rB.8vMe0bD7UoK8o5S2GwHB0ndPS1p.wfe");
        admin.setRoles(List.of(adminRole, userRole));

        User user = new User();
        user.setLogin("user");
        user.setPassword("$2a$12$EameGBUS1kNai9bxbeFTYexeWD5HagLKtWhWIv/xHdoR0qroi/zKi");
        user.setRoles(List.of(userRole));

        User anon = new User();
        anon.setLogin("anon");
        anon.setRoles(List.of(restRole));
        anon.setPassword("$2a$12$47I56K/DvzAZeazTjbdi9.7/xMnbONxNVGpNvk131GZ6T8BL9ZL42");


        admin = usersRepository.save(admin);
        user = usersRepository.save(user);
        usersRepository.saveAll(List.of(admin, user));
        usersRepository.save(anon);


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
                Employee employee = employeeRepository.findById(ThreadLocalRandom.current().nextLong(1, 11))
                        .orElseThrow(NoSuchElementException::new);
                if (!project.getEmployees().contains(employee)) {
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
