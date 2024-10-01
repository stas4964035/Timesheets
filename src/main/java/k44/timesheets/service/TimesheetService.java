package k44.timesheets.service;

import k44.timesheets.model.Timesheet;
import k44.timesheets.repository.TimesheetRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TimesheetService {
    private final TimesheetRepository timesheetRepository;

    public List<Timesheet> getAllTimeSheets(LocalDate after, LocalDate before) {
        if(after == null && before == null) {
            return timesheetRepository.findAll();
        }
        return timesheetRepository.findByCreatedAtBetween(after, before);
    }

    public Timesheet getTimesheetById(Long id) {
        Optional<Timesheet> timesheet = timesheetRepository.findById(id);
        return timesheet.orElseThrow(() -> new NoSuchElementException("Timesheet not found"));
    }
}
