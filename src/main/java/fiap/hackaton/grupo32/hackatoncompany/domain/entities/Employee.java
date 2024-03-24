package fiap.hackaton.grupo32.hackatoncompany.domain.entities;

import fiap.hackaton.grupo32.hackatoncompany.domain.enums.RolesEnum;

import java.util.List;

public class Employee {

    private String corporateId;
    private String password;

    private RolesEnum role;

    private List<TimeEntry> timeEntries;

    public Employee(String corporateId, String password, RolesEnum role, List<TimeEntry> timeEntries) {
        this.corporateId = corporateId;
        this.password = password;
        this.role = role;
        this.timeEntries = timeEntries;
    }

    public String getCorporateId() {
        return corporateId;
    }

    public void setCorporateId(String corporateId) {
        this.corporateId = corporateId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public RolesEnum getRole() {
        return role;
    }

    public void setRole(RolesEnum role) {
        this.role = role;
    }

    public List<TimeEntry> getTimeEntries() {
        return timeEntries;
    }

    public void setTimeEntries(List<TimeEntry> timeEntries) {
        this.timeEntries = timeEntries;
    }
}
