package fiap.hackaton.grupo32.hackatoncompany.application.rest.employee;

import fiap.hackaton.grupo32.hackatoncompany.application.dtos.EmployeeDto;
import fiap.hackaton.grupo32.hackatoncompany.application.dtos.EmployeeRequest;
import fiap.hackaton.grupo32.hackatoncompany.application.usecases.EmployeeUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/v1/user")
public class UserController {

    private final EmployeeUseCase employeeUseCase;

    @PostMapping(
            value = "/create",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<String> create(@RequestBody @Valid EmployeeRequest employeeRequest) {
        employeeUseCase.create(employeeRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body("User created successfully!");
    }

    @GetMapping(
            value = "/find/all",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public ResponseEntity<List<EmployeeDto>> findByRegistry() {
        return ResponseEntity.ok(employeeUseCase.findAll());
    }
}
