package fiap.hackaton.grupo32.hackatoncompany.infrastructure.mappers;

import fiap.hackaton.grupo32.hackatoncompany.application.dtos.UserDto;
import fiap.hackaton.grupo32.hackatoncompany.domain.entities.User;
import fiap.hackaton.grupo32.hackatoncompany.infrastructure.repositories.entity.UserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GeneralObjectMapper {

    UserDto userToUserDto(User user);

    User userDtoToUser(UserDto userDto);

    UserDto userEntityToUserDto(UserEntity userEntity);

    UserEntity userDtoToUserEntity(UserDto userDto);
}
