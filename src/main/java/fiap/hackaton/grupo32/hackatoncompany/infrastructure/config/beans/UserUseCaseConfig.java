package fiap.hackaton.grupo32.hackatoncompany.infrastructure.config.beans;

import fiap.hackaton.grupo32.hackatoncompany.application.pots.out.TimeManagementRespositoryOut;
import fiap.hackaton.grupo32.hackatoncompany.application.usecase.UserUseCase;
import fiap.hackaton.grupo32.hackatoncompany.infrastructure.mappers.GeneralObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class UserUseCaseConfig {

    @Bean
    public UserUseCase userUseCase(TimeManagementRespositoryOut timeManagementRespositoryOut,
                                   BCryptPasswordEncoder bCryptPasswordEncoder,
                                   GeneralObjectMapper generalObjectMapper) {
        return new UserUseCase(timeManagementRespositoryOut, generalObjectMapper, bCryptPasswordEncoder);
    }
}
