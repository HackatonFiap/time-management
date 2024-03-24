package fiap.hackaton.grupo32.hackatoncompany.infrastructure.config.beans;

import fiap.hackaton.grupo32.hackatoncompany.application.ports.out.TimeEntryRepositoryPortOut;
import fiap.hackaton.grupo32.hackatoncompany.application.usecases.StartTimeUseCase;
import fiap.hackaton.grupo32.hackatoncompany.infrastructure.mappers.GeneralObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StartTimeUseCaseConfig {

    @Bean
    public StartTimeUseCase startTimeUseCase(TimeEntryRepositoryPortOut timeEntryRepositoryPortOut, GeneralObjectMapper generalObjectMapper) {
        return new StartTimeUseCase(timeEntryRepositoryPortOut, generalObjectMapper);
    }
}
