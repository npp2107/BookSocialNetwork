package npp.booksocialnetwork.identity.service;

import java.util.HashSet;
import java.util.List;

import org.springframework.stereotype.Service;

import npp.booksocialnetwork.identity.dto.request.RoleRequest;
import npp.booksocialnetwork.identity.dto.response.RoleResponse;
import npp.booksocialnetwork.identity.mapper.RoleMapper;
import npp.booksocialnetwork.identity.repository.PermissionRepository;
import npp.booksocialnetwork.identity.repository.RoleRepository;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RoleService {
    RoleRepository roleRepository;
    PermissionRepository permissionRepository;
    RoleMapper roleMapper;

    @Transactional
    public RoleResponse create(RoleRequest request) {
        var role = roleMapper.toRole(request);

        var permissions = permissionRepository.findAllById(request.getPermissions());
        role.setPermissions(new HashSet<>(permissions));

        role = roleRepository.save(role);
        return roleMapper.toRoleResponse(role);
    }

    public List<RoleResponse> getAll() {
        return roleRepository.findAll().stream().map(roleMapper::toRoleResponse).toList();
    }

    @Transactional
    public void delete(String role) {
        roleRepository.deleteById(role);
    }
}
