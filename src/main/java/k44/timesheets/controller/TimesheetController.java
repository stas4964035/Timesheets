package k44.timesheets.controller;

import k44.timesheets.model.Timesheet;
import k44.timesheets.service.TimesheetService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/timesheets")
@AllArgsConstructor
public class TimesheetController {

    private final TimesheetService service;

    @GetMapping()
    public ResponseEntity<List<Timesheet>> getTimesheets(@RequestParam(name="after", required = false) LocalDate after,
                                                         @RequestParam(name="before", required = false) LocalDate before) {
        return ResponseEntity.status(HttpStatus.OK).body(service.getAllTimeSheets(after, before));
    }

    @GetMapping("/{id}")
    public Timesheet getTimesheet(@PathVariable Long id) {
        return service.getTimesheetById(id);
    }
}
