package fiap.hackaton.grupo32.hackatoncompany.application.usecases;

import fiap.hackaton.grupo32.hackatoncompany.application.dtos.EmployeeDto;
import fiap.hackaton.grupo32.hackatoncompany.application.dtos.EmployeeRequest;
import fiap.hackaton.grupo32.hackatoncompany.application.ports.out.EmployeeRepositoryPortOut;
import fiap.hackaton.grupo32.hackatoncompany.domain.entities.Employee;
import fiap.hackaton.grupo32.hackatoncompany.domain.entities.TimeEntry;
import fiap.hackaton.grupo32.hackatoncompany.infrastructure.exceptions.BadRequestException;
import fiap.hackaton.grupo32.hackatoncompany.infrastructure.mappers.GeneralObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class EmployeeUseCase {

    private final EmployeeRepositoryPortOut employeeRepositoryPortOut;
    private final GeneralObjectMapper generalObjectMapper;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public EmployeeUseCase(EmployeeRepositoryPortOut employeeRepositoryPortOut,
                           GeneralObjectMapper generalObjectMapper,
                           BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.employeeRepositoryPortOut = employeeRepositoryPortOut;
        this.generalObjectMapper = generalObjectMapper;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public void create(EmployeeRequest employeeDto) {
        log.info("Creating user: {}", employeeDto.matricula());

        var userFromDb = employeeRepositoryPortOut.findByRegistry(employeeDto.matricula());

        if (userFromDb.isPresent()) {
            throw new BadRequestException("User already exists");
        }

        List<TimeEntry> timeEntries = new ArrayList<>();

        Employee employee = new Employee(
                employeeDto.matricula(),
                bCryptPasswordEncoder.encode(employeeDto.password()),
                employeeDto.role(),
                timeEntries
        );

        employeeRepositoryPortOut.save(generalObjectMapper.employeeToEmployeeDto(employee));
    }

    public List<EmployeeDto> findAll() {
        log.info("Finding all users");
        return employeeRepositoryPortOut.findAll().orElseThrow(
                () -> new BadRequestException("User not found")
        );
    }
}
