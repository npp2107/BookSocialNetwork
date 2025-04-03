package npp.booksocialnetwork.identity.controller;

import java.util.List;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import npp.booksocialnetwork.identity.dto.request.ApiResponse;
import npp.booksocialnetwork.identity.dto.request.PermissionRequest;
import npp.booksocialnetwork.identity.dto.response.PermissionResponse;
import npp.booksocialnetwork.identity.service.PermissionService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/permissions")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
@Tag(name = "Permission Controller", description = "Controller for CRD mission")
public class PermissionController {
    PermissionService permissionService;
    @Operation(
            summary = "Create Permission",
            description = "API Create Permission"
    )
    @PostMapping
    ApiResponse<PermissionResponse> create(@RequestBody PermissionRequest request) {
        return ApiResponse.<PermissionResponse>builder()
                .result(permissionService.create(request))
                .build();
    }

    @Operation(
            summary = "Get All Permission",
            description = "API Get All Permission"
    )
    @GetMapping
    ApiResponse<List<PermissionResponse>> getAll() {
        return ApiResponse.<List<PermissionResponse>>builder()
                .result(permissionService.getAll())
                .build();
    }

    @Operation(
            summary = "Delete Permission",
            description = "API Delete Permission"
    )
    @DeleteMapping("/{permission}")
    ApiResponse<Void> delete(@PathVariable String permission) {
        permissionService.delete(permission);
        return ApiResponse.<Void>builder().build();
    }
}
