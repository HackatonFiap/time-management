package fiap.hackaton.grupo32.hackatoncompany.infrastructure.repositories;

import fiap.hackaton.grupo32.hackatoncompany.application.ports.out.TimeEntryRepositoryPort;
import fiap.hackaton.grupo32.hackatoncompany.domain.entities.TimeEntry;
import fiap.hackaton.grupo32.hackatoncompany.domain.enums.TimeEntriesTypeEnum;
import fiap.hackaton.grupo32.hackatoncompany.infrastructure.repositories.entities.TimeEntryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface TimeEntryRepository extends JpaRepository<TimeEntryEntity, UUID>, TimeEntryRepositoryPort {

    @Query("SELECT t FROM TimeEntryEntity t WHERE t.startTime = :startTime AND t.employeeId = :employeeId")
    List<TimeEntryEntity> findByDateAndUserId(@Param("startTime") LocalDateTime startTime, @Param("employeeId") UUID employeeId);

    @Query("SELECT t FROM TimeEntryEntity t WHERE t.startTime = :date AND t.employeeId = :userId AND t.type = :type AND t.endTime IS NULL")
    List<TimeEntryEntity> findOpenByDateAndUserIdAndType(@Param("date") LocalDateTime date, @Param("userId") UUID userId, @Param("type") TimeEntriesTypeEnum type);
}
