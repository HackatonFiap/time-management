package fiap.hackaton.grupo32.hackatoncompany.infrastructure.repositories.entities;

import com.fasterxml.jackson.annotation.JsonInclude;
import fiap.hackaton.grupo32.hackatoncompany.domain.enums.TimeEntriesTypeEnum;
import fiap.hackaton.grupo32.hackatoncompany.infrastructure.exceptions.TimeEntryConstraintException;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TimeEntryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private UUID employeeId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private TimeEntriesTypeEnum type;

    public TimeEntryEntity() {
    }

    protected TimeEntryEntity(UUID employeeId, LocalDateTime startTime) {
        this.employeeId = employeeId;
        this.startTime = startTime;
    }

    public  TimeEntriesTypeEnum getType(){
        return this.type;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public Long getDurationInMinutes() {
        if (endTime == null) {
            throw new TimeEntryConstraintException("Time entry end time is not set");
        }
        return java.time.Duration.between(startTime, endTime).toMinutes();
    }

    public Long getDurationsinMillis() {
        if (endTime == null) {
            throw new TimeEntryConstraintException("Time entry end time is not set");
        }
        return java.time.Duration.between(startTime, endTime).toMillis();
    }
}
