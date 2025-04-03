package npp.booksocialnetwork.identity.mapper;

import org.mapstruct.Mapper;

import npp.booksocialnetwork.identity.dto.request.ProfileCreationRequest;
import npp.booksocialnetwork.identity.dto.request.UserCreationRequest;

@Mapper(componentModel = "spring")
public interface ProfileMapper {
    ProfileCreationRequest toProfileCreationRequest(UserCreationRequest request);
}