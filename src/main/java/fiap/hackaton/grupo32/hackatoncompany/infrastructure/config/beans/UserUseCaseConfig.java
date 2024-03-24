package fiap.hackaton.grupo32.hackatoncompany.infrastructure.config.beans;

import fiap.hackaton.grupo32.hackatoncompany.application.ports.out.EmployeeRepositoryPortOut;
import fiap.hackaton.grupo32.hackatoncompany.application.usecases.EmployeeUseCase;
import fiap.hackaton.grupo32.hackatoncompany.infrastructure.mappers.GeneralObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class UserUseCaseConfig {

    @Bean
    public EmployeeUseCase userUseCase(EmployeeRepositoryPortOut employeeRepositoryPortOut,
                                       BCryptPasswordEncoder bCryptPasswordEncoder,
                                       GeneralObjectMapper generalObjectMapper) {
        return new EmployeeUseCase(employeeRepositoryPortOut, generalObjectMapper, bCryptPasswordEncoder);
    }
}
