package fiap.hackaton.grupo32.hackatoncompany.infrastructure.repositories.entities;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
public class EmployeeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;
    private String email;

    @OneToMany(mappedBy = "employeeId")
    private List<TimeEntryEntity> timeEntries;

}