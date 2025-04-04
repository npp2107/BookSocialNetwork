package npp.booksocialnetwork.gateway.service;

import npp.booksocialnetwork.gateway.dto.ApiResponse;
import npp.booksocialnetwork.gateway.dto.request.IntrospectRequest;
import npp.booksocialnetwork.gateway.dto.response.IntrospectResponse;
import npp.booksocialnetwork.gateway.repository.IdentityClient;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class IdentityService {
    IdentityClient identityClient;

    public Mono<ApiResponse<IntrospectResponse>> introspect(String token){
        return identityClient.introspect(IntrospectRequest.builder()
                .token(token)
                .build());
    }
}