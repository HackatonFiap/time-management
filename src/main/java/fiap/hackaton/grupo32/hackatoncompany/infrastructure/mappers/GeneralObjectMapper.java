package fiap.hackaton.grupo32.hackatoncompany.infrastructure.mappers;

import fiap.hackaton.grupo32.hackatoncompany.application.dtos.EmployeeDto;
import fiap.hackaton.grupo32.hackatoncompany.application.dtos.TimeEntryDto;
import fiap.hackaton.grupo32.hackatoncompany.domain.entities.Employee;
import fiap.hackaton.grupo32.hackatoncompany.domain.entities.TimeEntry;
import fiap.hackaton.grupo32.hackatoncompany.infrastructure.repositories.entity.EmployeeEntity;
import fiap.hackaton.grupo32.hackatoncompany.infrastructure.repositories.entity.TimeEntryEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GeneralObjectMapper {

    EmployeeDto employeeToEmployeeDto(Employee employee);

    Employee employeeDtoToEmployee(EmployeeDto employeeDto);

    EmployeeDto employeeEntityToEmployeeDto(EmployeeEntity employeeEntity);

    EmployeeEntity employeeDtoToEmployeeEntity(EmployeeDto employeeDto);

    TimeEntryDto timeEntryEntityToTimeEntryDto(TimeEntryEntity timeEntryEntity);

    TimeEntryEntity timeEntryDtoToTimeEntryEntity(TimeEntryDto timeEntryDto);
    TimeEntryDto timeEntryToTimeEntryDto(TimeEntry timeEntry);
    TimeEntry timeEntryDtoToTimeEntry(TimeEntryDto timeEntryDto);
}
