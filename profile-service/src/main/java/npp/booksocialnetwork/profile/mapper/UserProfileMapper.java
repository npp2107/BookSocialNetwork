package npp.booksocialnetwork.profile.mapper;

import org.mapstruct.Mapper;

import npp.booksocialnetwork.profile.dto.request.ProfileCreationRequest;
import npp.booksocialnetwork.profile.dto.response.UserProfileResponse;
import npp.booksocialnetwork.profile.entity.UserProfile;

@Mapper(componentModel = "spring")
public interface UserProfileMapper {
    UserProfile toUserProfile(ProfileCreationRequest request);

    UserProfileResponse toUserProfileResponse(UserProfile entity);
}