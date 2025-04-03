package npp.booksocialnetwork.identity.controller;

import java.util.List;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import npp.booksocialnetwork.identity.dto.request.ApiResponse;
import npp.booksocialnetwork.identity.dto.request.RoleRequest;
import npp.booksocialnetwork.identity.dto.response.RoleResponse;
import npp.booksocialnetwork.identity.service.RoleService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/roles")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
@Tag(name = "Role Controller", description = "Controller for CRD role")
public class RoleController {
    RoleService roleService;

    @Operation(
            summary = "Create Role",
            description = "API Create Role"
    )
    @PostMapping
    ApiResponse<RoleResponse> create(@RequestBody RoleRequest request) {
        return ApiResponse.<RoleResponse>builder()
                .result(roleService.create(request))
                .build();
    }

    @Operation(
            summary = "Get All Role",
            description = "API Get All Role"
    )
    @GetMapping
    ApiResponse<List<RoleResponse>> getAll() {
        return ApiResponse.<List<RoleResponse>>builder()
                .result(roleService.getAll())
                .build();
    }

    @Operation(
            summary = "Delete Role",
            description = "API Delete Role"
    )
    @DeleteMapping("/{role}")
    ApiResponse<Void> delete(@PathVariable String role) {
        roleService.delete(role);
        return ApiResponse.<Void>builder().build();
    }
}
