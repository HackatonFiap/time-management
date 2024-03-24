package fiap.hackaton.grupo32.hackatoncompany.infrastructure.adapters.out;

import fiap.hackaton.grupo32.hackatoncompany.application.dtos.EmployeeDto;
import fiap.hackaton.grupo32.hackatoncompany.application.ports.out.EmployeeRepositoryPortOut;
import fiap.hackaton.grupo32.hackatoncompany.infrastructure.mappers.GeneralObjectMapper;
import fiap.hackaton.grupo32.hackatoncompany.infrastructure.repositories.EmployeePostgresSqlRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class EmployeeRepositoryAdapterPortOut implements EmployeeRepositoryPortOut {

    private final GeneralObjectMapper mapper;

    private final EmployeePostgresSqlRepository repository;

    public EmployeeRepositoryAdapterPortOut(GeneralObjectMapper mapper, EmployeePostgresSqlRepository repository) {
        this.mapper = mapper;
        this.repository = repository;
    }

    @Override
    public EmployeeDto save(EmployeeDto employeeDto) {
        var employee = repository.save(mapper.employeeDtoToEmployeeEntity(employeeDto));
        return mapper.employeeEntityToEmployeeDto(employee);
    }

    @Override
    public Optional<EmployeeDto> findByRegistry(String name) {
        var employee = repository.findByCorporateId(name);
        return employee.stream()
                .filter(user -> user.getCorporateId().equals(name))
                .map(mapper::employeeEntityToEmployeeDto)
                .findFirst();
    }

    @Override
    public Optional<List<EmployeeDto>> findAll() {
        var users = repository.findAll();
        return Optional.of(users.stream()
                .map(mapper::employeeEntityToEmployeeDto)
                .toList());
    }
}
