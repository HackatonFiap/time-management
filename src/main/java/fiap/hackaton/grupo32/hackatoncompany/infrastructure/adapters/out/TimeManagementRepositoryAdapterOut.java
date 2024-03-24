package fiap.hackaton.grupo32.hackatoncompany.infrastructure.adapters.out;

import fiap.hackaton.grupo32.hackatoncompany.application.dtos.UserDto;
import fiap.hackaton.grupo32.hackatoncompany.application.pots.out.TimeManagementRespositoryOut;
import fiap.hackaton.grupo32.hackatoncompany.infrastructure.mappers.GeneralObjectMapper;
import fiap.hackaton.grupo32.hackatoncompany.infrastructure.repositories.entity.UserEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class TimeManagementRepositoryAdapterOut implements TimeManagementRespositoryOut {

    private final GeneralObjectMapper mapper;
    private ArrayList<UserEntity> users = new ArrayList<>();

    public TimeManagementRepositoryAdapterOut(GeneralObjectMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public UserDto save(UserDto userDto) {
        users.add(mapper.userDtoToUserEntity(userDto));
        return userDto;
    }

    @Override
    public Optional<UserDto> findByRegistry(String name) {
        return users.stream()
                .filter(user -> user.getMatricula().equals(name))
                .map(mapper::userEntityToUserDto)
                .findFirst();
    }

    @Override
    public Optional<List<UserDto>> findAll() {
        return Optional.of(users.stream()
                .map(mapper::userEntityToUserDto)
                .toList());
    }
}
