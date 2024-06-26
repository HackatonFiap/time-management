package fiap.hackaton.grupo32.hackatoncompany.application.usecases;

import fiap.hackaton.grupo32.hackatoncompany.application.dtos.ReportTimeMonthIn;
import fiap.hackaton.grupo32.hackatoncompany.application.dtos.SendEmail;
import fiap.hackaton.grupo32.hackatoncompany.application.ports.out.SendEmailPortOut;
import fiap.hackaton.grupo32.hackatoncompany.application.ports.out.TimeEntryRepositoryPortOut;

import java.time.Duration;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.stream.Collectors;

public class ReportMonthUseCase {

    private final TimeEntryRepositoryPortOut timeEntryRepositoryPortOut;
    private final SendEmailPortOut sendEmailPortOut;

    public ReportMonthUseCase(TimeEntryRepositoryPortOut timeEntryRepositoryPortOut, SendEmailPortOut sendEmailPortOut) {
        this.timeEntryRepositoryPortOut = timeEntryRepositoryPortOut;
        this.sendEmailPortOut = sendEmailPortOut;
    }

    public void report(ReportTimeMonthIn reportTimeMonthin){

        var dateFormatStart = reportTimeMonthin.startTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        var datesWork = timeEntryRepositoryPortOut.reportMonth(reportTimeMonthin.startTime(), reportTimeMonthin.endTime(), reportTimeMonthin.employeeId());

        var body = datesWork.stream().map(dates -> {

            var bodyEmail = new StringBuilder();
            bodyEmail.append("Data entrada: " + dates.startTime());
            bodyEmail.append(" Data saida: " + dates.endTime());
            bodyEmail.append(" Tipo apontamento: " + dates.entryType());
            if(dates.entryType().name().equals("WORK")){
                var duration = Duration.between(dates.startTime(), dates.endTime());
                bodyEmail.append(" Tempo Trabalhado: " + duration.toString());
            }
            bodyEmail.append("\n");
            return bodyEmail;
        });

        var email = new SendEmail(reportTimeMonthin.email(), "Segue o resumo da folha de ponto", body.collect(Collectors.joining()));

        sendEmailPortOut.sendEmail(email);

    }
}
