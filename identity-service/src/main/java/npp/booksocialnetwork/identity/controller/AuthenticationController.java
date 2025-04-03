package npp.booksocialnetwork.identity.controller;

import java.text.ParseException;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import npp.booksocialnetwork.identity.dto.request.*;
import npp.booksocialnetwork.identity.dto.response.AuthenticationResponse;
import npp.booksocialnetwork.identity.dto.response.IntrospectResponse;
import npp.booksocialnetwork.identity.service.AuthenticationService;
import com.nimbusds.jose.JOSEException;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Tag(name = "Authentication Controller", description = "Controller for login, logout, refresh token")
public class AuthenticationController {
    AuthenticationService authenticationService;

    @Operation(
            summary = "User Login",
            description = "Authenticates a user with provided credentials and returns an access token."
    )
    @PostMapping("/token")
    ApiResponse<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
        var result = authenticationService.authenticate(request);
        return ApiResponse.<AuthenticationResponse>builder().result(result).build();
    }

    @Operation(
            summary = "Token Introspection",
            description = "Validates an access token and provides details about its validity and associated user."
    )
    ApiResponse<IntrospectResponse> authenticate(@RequestBody IntrospectRequest request)
            throws ParseException, JOSEException {
        var result = authenticationService.introspect(request);
        return ApiResponse.<IntrospectResponse>builder().result(result).build();
    }

    @Operation(
            summary = "Refresh Access Token",
            description = "Verify access token then generate a new access token when the previous one expires."
    )
    @PostMapping("/refresh")
    ApiResponse<AuthenticationResponse> authenticate(@RequestBody RefreshRequest request)
            throws ParseException, JOSEException {
        var result = authenticationService.refreshToken(request);
        return ApiResponse.<AuthenticationResponse>builder().result(result).build();
    }

    @Operation(
            summary = "User Logout",
            description = "Logs out a user saving invalidated token."
    )
    @PostMapping("/logout")
    ApiResponse<Void> logout(@RequestBody LogoutRequest request) throws ParseException, JOSEException {
        authenticationService.logout(request);
        return ApiResponse.<Void>builder().build();
    }
}
