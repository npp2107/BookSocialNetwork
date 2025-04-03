package npp.booksocialnetwork.identity.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import npp.booksocialnetwork.identity.dto.request.UserCreationRequest;
import npp.booksocialnetwork.identity.dto.request.UserUpdateRequest;
import npp.booksocialnetwork.identity.dto.response.UserResponse;
import npp.booksocialnetwork.identity.entity.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(UserCreationRequest request);

    UserResponse toUserResponse(User user);

    @Mapping(target = "roles", ignore = true)
    void updateUser(@MappingTarget User user, UserUpdateRequest request);
}
