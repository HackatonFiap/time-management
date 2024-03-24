package fiap.hackaton.grupo32.hackatoncompany.application.usecase;

import fiap.hackaton.grupo32.hackatoncompany.application.dtos.LoginDto;
import fiap.hackaton.grupo32.hackatoncompany.application.dtos.LoginResponse;
import fiap.hackaton.grupo32.hackatoncompany.application.dtos.UserDto;
import fiap.hackaton.grupo32.hackatoncompany.application.pots.out.TimeManagementRespositoryOut;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Slf4j
public class AuthenticationUseCase {

    private final TimeManagementRespositoryOut timeManagementRespositoryOut;
    private final JwtDecoder jwtDecoder;
    private final JwtEncoder jwtEncoder;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public AuthenticationUseCase(TimeManagementRespositoryOut timeManagementRespositoryOut,
                                 JwtDecoder jwtDecoder, JwtEncoder jwtEncoder,
                                 BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.timeManagementRespositoryOut = timeManagementRespositoryOut;
        this.jwtDecoder = jwtDecoder;
        this.jwtEncoder = jwtEncoder;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public LoginResponse login(LoginDto loginDto) {
        log.info("Iniciando login do user: " + loginDto.matricula());

        log.info("Consultando user na base");
        var userDto = timeManagementRespositoryOut.findByRegistry(loginDto.matricula());

        if (userDto.isEmpty() || Boolean.FALSE.equals(isPasswordValid(loginDto, userDto.get()))) {
            throw new BadCredentialsException("user or password is invalid!");
        }

        log.info("Gerando token para o user: " + loginDto.matricula());
        var expiresIn = Instant.now().plus(1, ChronoUnit.HOURS);

        var claims = JwtClaimsSet.builder()
                .issuer("hackaton-company")
                .subject(userDto.get().matricula())
                .claim("scope", userDto.get().role().name())
                .expiresAt(expiresIn)
                .issuedAt(Instant.now())
                .build();

        var token = jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();

        log.info("Token gerado com sucesso para o user: " + loginDto.matricula());
        return new LoginResponse(token, expiresIn.getEpochSecond() / 1000);
    }

    private Boolean isPasswordValid(LoginDto loginDto, UserDto userDto) {
        return bCryptPasswordEncoder.matches(loginDto.password(), userDto.password());
    }
}
