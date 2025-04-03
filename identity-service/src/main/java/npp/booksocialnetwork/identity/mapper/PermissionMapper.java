package npp.booksocialnetwork.identity.mapper;

import org.mapstruct.Mapper;

import npp.booksocialnetwork.identity.dto.request.PermissionRequest;
import npp.booksocialnetwork.identity.dto.response.PermissionResponse;
import npp.booksocialnetwork.identity.entity.Permission;

@Mapper(componentModel = "spring")
public interface PermissionMapper {
    Permission toPermission(PermissionRequest request);

    PermissionResponse toPermissionResponse(Permission permission);
}
