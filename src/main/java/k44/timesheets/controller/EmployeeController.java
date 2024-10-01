package k44.timesheets.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import k44.timesheets.model.Employee;
import k44.timesheets.model.Timesheet;
import k44.timesheets.repository.TimesheetRepository;
import k44.timesheets.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springdoc.api.ErrorMessage;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpResponse;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@AllArgsConstructor
@RequestMapping("/employees")
@Tag(name="Employee API", description = "API для работы с сотрудниками")
public class EmployeeController {
    private final EmployeeService employeeService;
    private final TimesheetRepository timesheetRepository;

    @Operation(summary = "Список сотрудников",
            description = "Получение списка всех сотрудников")
    @GetMapping
    public List<Employee> getEmployees() {
        return employeeService.findAll();
    }

    @Operation(summary = "Информация о сотруднике",
            description = "Получение всех данных о сотруднике по его Id",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Успешный ответ", content = @Content(schema = @Schema(implementation = Employee.class))),
                    @ApiResponse(responseCode = "404", description = "Сотрудник не найден", content = @Content(schema = @Schema(implementation = ExceptionApiHandler.class))),
                    @ApiResponse(responseCode = "500", description = "Ошибка сервера", content = @Content(schema = @Schema(implementation = Void.class)))
            })
    @GetMapping("/{id}")
    public Employee getEmployee(@PathVariable @Parameter(description = "Id пользователя", name = "id", required = true) Long id) {
        return employeeService.findById(id);
    }

    @Operation(summary = "Временные отрезки пользователя", description = "")
    @GetMapping("/{id}/timesheets")
    public List<Timesheet> getTimesheets(@PathVariable Long id) {
        return employeeService.findProjectsByEmployeeId(id);
    }


}
