package k44.timesheets.repository;

import k44.timesheets.model.Timesheet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface TimesheetRepository extends JpaRepository<Timesheet, Long> {
    List<Timesheet> findByProjectId(Long id);

    List<Timesheet> findByCreatedAtBetween(LocalDate start, LocalDate end);

    List<Timesheet> findByEmployeeId(Long id);


}
