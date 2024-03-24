package fiap.hackaton.grupo32.hackatoncompany.domain.entities;


import java.util.List;
import java.util.UUID;

public class Employee {
    private UUID id;
    private String name;
    private String email;

    private List<TimeEntry> timeEntries;

}