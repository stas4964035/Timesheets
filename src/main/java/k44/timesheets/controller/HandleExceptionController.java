package k44.timesheets.controller;

import k44.timesheets.aspect.Recover;
import lombok.SneakyThrows;
import org.hibernate.TypeMismatchException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/exception")
public class HandleExceptionController {
    @GetMapping("/int")
    @Recover
    @SneakyThrows
    public int intException() {
        throw new TypeMismatchException("int type exception");
    }

    @GetMapping("/void")
    @Recover
    @SneakyThrows
    public void voidException() {
        throw new TypeMismatchException("void type exception");
    }

    @GetMapping("/string")
    @Recover
    @SneakyThrows
    public String stringException() {
        throw new TypeMismatchException("String type exception");
    }



}
