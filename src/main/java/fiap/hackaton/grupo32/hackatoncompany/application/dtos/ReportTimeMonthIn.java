package fiap.hackaton.grupo32.hackatoncompany.application.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;
import java.util.UUID;

public record ReportTimeMonthIn(

        UUID employeeId,
        String email,
        @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss") LocalDateTime startTime,
        @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss") LocalDateTime endTime) {
}
