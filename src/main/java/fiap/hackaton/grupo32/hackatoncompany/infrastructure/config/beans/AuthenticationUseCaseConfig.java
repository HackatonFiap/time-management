package fiap.hackaton.grupo32.hackatoncompany.infrastructure.config.beans;

import fiap.hackaton.grupo32.hackatoncompany.application.pots.out.TimeManagementRespositoryOut;
import fiap.hackaton.grupo32.hackatoncompany.application.usecase.AuthenticationUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;

@Configuration
public class AuthenticationUseCaseConfig {

    @Bean
    public AuthenticationUseCase authenticationUseCase(
            TimeManagementRespositoryOut timeManagementRespositoryOut,
            JwtDecoder jwtDecoder, JwtEncoder jwtEncoder,
            BCryptPasswordEncoder bCryptPasswordEncoder
    ) {
        return new AuthenticationUseCase(
                timeManagementRespositoryOut, jwtDecoder, jwtEncoder, bCryptPasswordEncoder
        );
    }
}
