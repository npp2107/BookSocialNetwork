package npp.booksocialnetwork.identity.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import npp.booksocialnetwork.identity.dto.request.RoleRequest;
import npp.booksocialnetwork.identity.dto.response.RoleResponse;
import npp.booksocialnetwork.identity.entity.Role;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    @Mapping(target = "permissions", ignore = true)
    Role toRole(RoleRequest request);

    RoleResponse toRoleResponse(Role role);
}
