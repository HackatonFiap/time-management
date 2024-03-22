package fiap.hackaton.grupo32.hackatoncompany.domain.entities;

import fiap.hackaton.grupo32.hackatoncompany.domain.enums.TimeEntriesTypeEnum;

import java.time.LocalDateTime;

public class SickLeaveEntry extends TimeEntry {

    private String documentUrl; // URL for proof document

    public SickLeaveEntry(Long employeeId, LocalDateTime startTime, String documentUrl) {
        super(employeeId, startTime);
        this.documentUrl = documentUrl;
    }

    @Override
    public TimeEntriesTypeEnum getType() {
        return TimeEntriesTypeEnum.SICK_LEAVE;
    }

    public String getDocumentUrl() {
        return documentUrl;
    }
}
