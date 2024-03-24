package fiap.hackaton.grupo32.hackatoncompany.infrastructure.exceptions;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class MessageExceptionHandler {

    private List<String> error;
    private Integer status;
    private String descriptionStatus;
    private Date timeStamp;

    public MessageExceptionHandler(String error, Integer status, String descriptionStatus, Date timeStamp) {
        this.error = List.of(error);
        this.status = status;
        this.descriptionStatus = descriptionStatus;
        this.timeStamp = timeStamp;
    }

    public MessageExceptionHandler(List<String> errors, Integer status, String descriptionStatus, Date timeStamp){
        this.error = errors;
        this.status = status;
        this.descriptionStatus = descriptionStatus;
        this.timeStamp = timeStamp;
    }
}
