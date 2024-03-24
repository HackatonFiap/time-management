package fiap.hackaton.grupo32.hackatoncompany.infrastructure.repositories;

import fiap.hackaton.grupo32.hackatoncompany.domain.enums.TimeEntriesTypeEnum;
import fiap.hackaton.grupo32.hackatoncompany.infrastructure.repositories.entity.TimeEntryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface TimeEntryPostgreSqlRepository extends JpaRepository<TimeEntryEntity, UUID> {

    @Query("SELECT t FROM TimeEntryEntity t WHERE t.startTime = :startTime AND t.employeeId = :employeeId")
    List<TimeEntryEntity> findByDateAndEmployeeId(@Param("startTime") LocalDateTime startTime, @Param("employeeId") UUID employeeId);

    @Query("SELECT t FROM TimeEntryEntity t WHERE t.startTime = :date AND t.employeeId = :userId AND t.timeEntriesTypeEnum = :type AND t.endTime IS NULL")
    List<TimeEntryEntity> findOpenByDateAndEmployeeIdAndType(@Param("date") LocalDateTime date, @Param("userId") UUID userId, @Param("type") TimeEntriesTypeEnum type);

    @Query("SELECT t FROM TimeEntryEntity t WHERE t.startTime IS NULL AND t.employeeId = :userId AND t.timeEntriesTypeEnum = :type AND t.endTime = :date")
    List<TimeEntryEntity> findClosedByDateAndEmployeeIdAndType(@Param("date") LocalDateTime date, @Param("userId") UUID userId, @Param("type") TimeEntriesTypeEnum type);
}
