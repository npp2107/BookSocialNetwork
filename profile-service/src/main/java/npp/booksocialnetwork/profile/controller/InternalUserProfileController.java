package npp.booksocialnetwork.profile.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import npp.booksocialnetwork.profile.dto.ApiResponse;
import npp.booksocialnetwork.profile.dto.request.ProfileCreationRequest;
import npp.booksocialnetwork.profile.dto.response.UserProfileResponse;
import npp.booksocialnetwork.profile.service.UserProfileService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Tag(name = "Internal User Profile Controller", description = "Controller For Internal Create Profile ")
public class InternalUserProfileController {
    UserProfileService userProfileService;

    @Operation(summary = "Create Profile", description = "API For Internal Create Profile")
    @PostMapping("/internal/users")
    ApiResponse<UserProfileResponse> createProfile(@RequestBody ProfileCreationRequest request) {
        return ApiResponse.<UserProfileResponse>builder()
                .result(userProfileService.createProfile(request))
                .build();
    }
}