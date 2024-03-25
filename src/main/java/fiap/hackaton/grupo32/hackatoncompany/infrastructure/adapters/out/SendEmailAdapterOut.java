package fiap.hackaton.grupo32.hackatoncompany.infrastructure.adapters.out;

import fiap.hackaton.grupo32.hackatoncompany.application.dtos.SendEmail;
import fiap.hackaton.grupo32.hackatoncompany.application.ports.out.SendEmailPortOut;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class SendEmailAdapterOut implements SendEmailPortOut {

    private final JavaMailSender mailSender;

    public SendEmailAdapterOut(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }
    @Override
    public void sendEmail(SendEmail email) {

        var message = new SimpleMailMessage();

        message.setFrom("grupo32.fiap@gmail.com");
        message.setTo(email.to());
        message.setText(email.body());
        message.setSubject(email.subject());
        mailSender.send(message);

    }

}
