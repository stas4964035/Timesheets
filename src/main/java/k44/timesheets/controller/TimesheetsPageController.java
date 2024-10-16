package k44.timesheets.controller;

import k44.timesheets.model.Timesheet;
import k44.timesheets.service.TimesheetService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/home/timesheets")
@AllArgsConstructor
public class TimesheetsPageController {
    private final TimesheetService timesheetService;

    @GetMapping()
    public String getAllTimesheets(Model model, @RequestParam(required = false) LocalDate before, @RequestParam(required = false) LocalDate after) {
        List<Timesheet> timesheetList = timesheetService.getAllTimeSheets(before, after);
        model.addAttribute("timesheets", timesheetList);
        return "timesheets";
    }
}
