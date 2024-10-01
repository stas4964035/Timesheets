package k44.timesheets.service;

import k44.timesheets.model.Employee;
import k44.timesheets.model.Timesheet;
import k44.timesheets.repository.EmployeeRepository;
import k44.timesheets.repository.TimesheetRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@AllArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final TimesheetRepository timesheetRepository;

    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    public Employee findById(Long id) {
        return employeeRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Employee not found."));
    }

    public List<Timesheet> findProjectsByEmployeeId(Long id) {
        List<Timesheet> timesheets = timesheetRepository.findByEmployeeId(id);
        if (timesheets.isEmpty()) {
            throw new NoSuchElementException("Employee projects not found.");
        }else return timesheets;
    }



}
