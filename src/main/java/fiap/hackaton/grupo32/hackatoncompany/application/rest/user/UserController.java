package fiap.hackaton.grupo32.hackatoncompany.application.rest.user;

import fiap.hackaton.grupo32.hackatoncompany.application.dtos.UserDto;
import fiap.hackaton.grupo32.hackatoncompany.application.usecase.UserUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
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

    private final UserUseCase userUseCase;

    @PostMapping(
            value = "/create",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<String> create(@RequestBody @Valid UserDto userDto) {
        userUseCase.create(userDto);
        return ResponseEntity.status(HttpStatus.CREATED).body("User created successfully!");
    }

    @GetMapping(
            value = "/find/all",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public ResponseEntity<List<UserDto>> findByRegistry() {
        return ResponseEntity.ok(userUseCase.findAll());
    }
}
