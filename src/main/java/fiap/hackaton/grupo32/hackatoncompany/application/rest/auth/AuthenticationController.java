package fiap.hackaton.grupo32.hackatoncompany.application.rest.auth;

import fiap.hackaton.grupo32.hackatoncompany.application.dtos.LoginDto;
import fiap.hackaton.grupo32.hackatoncompany.application.dtos.LoginResponse;
import fiap.hackaton.grupo32.hackatoncompany.application.usecases.AuthenticationUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/v1/auth")
public class AuthenticationController {

    private final AuthenticationUseCase authenticationUseCase;

    @PostMapping(
            value = "/login",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<LoginResponse> login(@RequestBody @Valid LoginDto loginDto) {
        return ResponseEntity.ok().body(authenticationUseCase.login(loginDto));
    }
}
