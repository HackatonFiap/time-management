package fiap.hackaton.grupo32.hackatoncompany.application.usecase;

import fiap.hackaton.grupo32.hackatoncompany.application.dtos.UserDto;
import fiap.hackaton.grupo32.hackatoncompany.application.pots.out.TimeManagementRespositoryOut;
import fiap.hackaton.grupo32.hackatoncompany.domain.entities.User;
import fiap.hackaton.grupo32.hackatoncompany.infrastructure.exceptions.BadRequestException;
import fiap.hackaton.grupo32.hackatoncompany.infrastructure.mappers.GeneralObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

@Slf4j
public class UserUseCase {

    private final TimeManagementRespositoryOut timeManagementRespositoryOut;
    private final GeneralObjectMapper generalObjectMapper;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserUseCase(TimeManagementRespositoryOut timeManagementRespositoryOut,
                       GeneralObjectMapper generalObjectMapper,
                       BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.timeManagementRespositoryOut = timeManagementRespositoryOut;
        this.generalObjectMapper = generalObjectMapper;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public void create(UserDto userDto) {
        log.info("Creating user: {}", userDto.matricula());

        var userFromDb = timeManagementRespositoryOut.findByRegistry(userDto.matricula());

        if (userFromDb.isPresent()) {
            throw new BadRequestException("User already exists");
        }

        User user = generalObjectMapper.userDtoToUser(userDto);
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

        timeManagementRespositoryOut.save(generalObjectMapper.userToUserDto(user));
    }

    public List<UserDto> findAll() {
        log.info("Finding all users");
        return timeManagementRespositoryOut.findAll().orElseThrow(
                () -> new BadRequestException("User not found")
        );
    }
}
