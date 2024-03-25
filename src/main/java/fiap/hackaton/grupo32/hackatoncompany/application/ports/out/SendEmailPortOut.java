package fiap.hackaton.grupo32.hackatoncompany.application.ports.out;

import fiap.hackaton.grupo32.hackatoncompany.application.dtos.ReportTimeMonthOut;
import fiap.hackaton.grupo32.hackatoncompany.application.dtos.SendEmail;

public interface SendEmailPortOut {

    void sendEmail(SendEmail sendEmail);

}
