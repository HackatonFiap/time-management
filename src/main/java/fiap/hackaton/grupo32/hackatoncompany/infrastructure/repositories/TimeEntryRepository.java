package fiap.hackaton.grupo32.hackatoncompany.infrastructure.repositories;

import fiap.hackaton.grupo32.hackatoncompany.domain.entities.TimeEntry;

import java.time.LocalDate;
import java.util.List;

public interface TimeEntryRepository {
    void save(TimeEntry timeEntry);
    void update(TimeEntry timeEntry);
    void delete(Long id);
    TimeEntry find(Long id);
    List<TimeEntry> findOpenTimeEntriesForDay(Long employeeId, LocalDate date);
}
