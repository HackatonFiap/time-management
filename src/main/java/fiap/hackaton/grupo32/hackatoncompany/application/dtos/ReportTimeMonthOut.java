package fiap.hackaton.grupo32.hackatoncompany.application.dtos;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record ReportTimeMonthOut(UUID employeeId, List<TimeEntryDto> dates, Long workHours) {
}
