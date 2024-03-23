package fiap.hackaton.grupo32.hackatoncompany.infrastructure.repositories.entities;


import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
public class EmployeeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;
    private String email;

    @OneToMany(mappedBy = "employeeId")
    private List<TimeEntryEntity> timeEntries;

}