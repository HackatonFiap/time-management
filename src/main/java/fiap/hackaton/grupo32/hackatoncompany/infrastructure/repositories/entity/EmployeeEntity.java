package fiap.hackaton.grupo32.hackatoncompany.infrastructure.repositories.entity;


import fiap.hackaton.grupo32.hackatoncompany.domain.enums.RolesEnum;
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
    private String corporateId;
    private String password;
    @Enumerated(EnumType.STRING)
    private RolesEnum role;

    @OneToMany(mappedBy = "employeeId")
    private List<TimeEntryEntity> timeEntries;

}