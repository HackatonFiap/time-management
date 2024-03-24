package fiap.hackaton.grupo32.hackatoncompany.application.pots.out;

import fiap.hackaton.grupo32.hackatoncompany.application.dtos.UserDto;

import java.util.List;
import java.util.Optional;

public interface TimeManagementRespositoryOut {
    UserDto save(UserDto userDto);
    Optional<UserDto> findByRegistry(String name);

    Optional<List<UserDto>> findAll();
}
