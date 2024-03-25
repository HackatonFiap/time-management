package fiap.hackaton.grupo32.hackatoncompany.infrastructure.config.beans;

import fiap.hackaton.grupo32.hackatoncompany.application.ports.out.SendEmailPortOut;
import fiap.hackaton.grupo32.hackatoncompany.application.ports.out.TimeEntryRepositoryPortOut;
import fiap.hackaton.grupo32.hackatoncompany.application.usecases.ReportMonthUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ReportMonthUseCaseConfig {

    @Bean
    public ReportMonthUseCase reportMonthUseCase(TimeEntryRepositoryPortOut timeEntryRepositoryPortOut, SendEmailPortOut sendEmailPortOut){
        return new ReportMonthUseCase(timeEntryRepositoryPortOut, sendEmailPortOut);
    }

}
