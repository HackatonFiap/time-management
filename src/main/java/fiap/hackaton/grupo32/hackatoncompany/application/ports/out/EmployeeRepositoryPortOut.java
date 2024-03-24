package fiap.hackaton.grupo32.hackatoncompany.application.ports.out;

import fiap.hackaton.grupo32.hackatoncompany.application.dtos.EmployeeDto;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepositoryPortOut {
    EmployeeDto save(EmployeeDto employeeDto);
    Optional<EmployeeDto> findByRegistry(String name);

    Optional<List<EmployeeDto>> findAll();
}
