package fiap.hackaton.grupo32.hackatoncompany.application.ports.in;

import fiap.hackaton.grupo32.hackatoncompany.application.dtos.TimeEntryDto;
import fiap.hackaton.grupo32.hackatoncompany.application.usecases.DeleteTimeUseCase;
import fiap.hackaton.grupo32.hackatoncompany.application.usecases.EndTimeUseCase;
import fiap.hackaton.grupo32.hackatoncompany.application.usecases.ListTimeUseCase;
import fiap.hackaton.grupo32.hackatoncompany.application.usecases.StartTimeUseCase;
import fiap.hackaton.grupo32.hackatoncompany.domain.entities.TimeEntry;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/v1/api/time")
public class TimeEntryController {

    private final StartTimeUseCase startTimeUseCase;
    private final EndTimeUseCase endTimeUseCase;
    private final DeleteTimeUseCase deleteTimeUseCase;
    private final ListTimeUseCase listTimeUseCase;

    public TimeEntryController(StartTimeUseCase startTimeUseCase, EndTimeUseCase endTimeUseCase, DeleteTimeUseCase deleteTimeUseCase, ListTimeUseCase listTimeUseCase) {
        this.startTimeUseCase = startTimeUseCase;
        this.endTimeUseCase = endTimeUseCase;
        this.deleteTimeUseCase = deleteTimeUseCase;
        this.listTimeUseCase = listTimeUseCase;
    }

    @PostMapping
        public ResponseEntity<TimeEntryDto> start(@RequestBody TimeEntryDto timeEntryDto) throws Exception {
            return ResponseEntity.ok(startTimeUseCase.execute(timeEntryDto));
    }

    @PutMapping
    public ResponseEntity<TimeEntry> end(@RequestBody TimeEntryDto timeEntryDto) throws Exception{
        endTimeUseCase.execute(timeEntryDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<TimeEntry>> findAll() {
        listTimeUseCase.findAll();
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TimeEntry> findById(@PathVariable UUID id) {
        listTimeUseCase.findById(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        deleteTimeUseCase.execute(id);
        return ResponseEntity.ok().build();
    }
}
