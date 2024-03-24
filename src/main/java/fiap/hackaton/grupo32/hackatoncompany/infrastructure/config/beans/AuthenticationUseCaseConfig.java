package fiap.hackaton.grupo32.hackatoncompany.infrastructure.config.beans;

import fiap.hackaton.grupo32.hackatoncompany.application.ports.out.EmployeeRepositoryPortOut;
import fiap.hackaton.grupo32.hackatoncompany.application.usecases.AuthenticationUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;

@Configuration
public class AuthenticationUseCaseConfig {

    @Bean
    public AuthenticationUseCase authenticationUseCase(
            EmployeeRepositoryPortOut employeeRepositoryPortOut,
            JwtDecoder jwtDecoder, JwtEncoder jwtEncoder,
            BCryptPasswordEncoder bCryptPasswordEncoder
    ) {
        return new AuthenticationUseCase(
                employeeRepositoryPortOut, jwtDecoder, jwtEncoder, bCryptPasswordEncoder
        );
    }
}
