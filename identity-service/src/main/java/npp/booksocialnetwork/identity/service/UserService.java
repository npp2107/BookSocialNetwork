package npp.booksocialnetwork.identity.service;

import java.util.HashSet;
import java.util.List;

import npp.booksocialnetwork.identity.mapper.ProfileMapper;
import npp.booksocialnetwork.identity.repository.httpclient.ProfileClient;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import npp.booksocialnetwork.identity.constant.PredefinedRole;
import npp.booksocialnetwork.identity.dto.request.UserCreationRequest;
import npp.booksocialnetwork.identity.dto.request.UserUpdateRequest;
import npp.booksocialnetwork.identity.dto.response.UserResponse;
import npp.booksocialnetwork.identity.entity.Role;
import npp.booksocialnetwork.identity.entity.User;
import npp.booksocialnetwork.identity.exception.AppException;
import npp.booksocialnetwork.identity.exception.ErrorCode;
import npp.booksocialnetwork.identity.mapper.UserMapper;
import npp.booksocialnetwork.identity.repository.RoleRepository;
import npp.booksocialnetwork.identity.repository.UserRepository;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class UserService {
    UserRepository userRepository;
    RoleRepository roleRepository;
    UserMapper userMapper;
    ProfileMapper profileMapper;
    PasswordEncoder passwordEncoder;
    ProfileClient profileClient;
    KafkaTemplate<String, String> kafkaTemplate;

    public UserResponse createUser(UserCreationRequest request) {
        if (userRepository.existsByUsername(request.getUsername())) throw new AppException(ErrorCode.USER_EXISTED);

        User user = userMapper.toUser(request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        HashSet<Role> roles = new HashSet<>();
        roleRepository.findById(PredefinedRole.USER_ROLE).ifPresent(roles::add);

        user.setRoles(roles);
        user = userRepository.save(user);

        var profileRequest = profileMapper.toProfileCreationRequest(request);
        profileRequest.setUserId(user.getId());

        profileClient.createProfile(profileRequest);

        // Publish message to kafka
        kafkaTemplate.send("onboard-successful", "Welcome our new member " + user.getUsername());

        return userMapper.toUserResponse(user);
    }

    public UserResponse getMyInfo() {
        var context = SecurityContextHolder.getContext();
        String name = context.getAuthentication().getName();

        User user = userRepository.findByUsername(name).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));

        return userMapper.toUserResponse(user);
    }

    @PreAuthorize("hasRole('ADMIN')")
    public UserResponse updateUser(String userId, UserUpdateRequest request) {
        User user = userRepository.findById(userId).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));

        userMapper.updateUser(user, request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        var roles = roleRepository.findAllById(request.getRoles());
        user.setRoles(new HashSet<>(roles));

        return userMapper.toUserResponse(userRepository.save(user));
    }

    @PreAuthorize("hasRole('ADMIN')")
    public void deleteUser(String userId) {
        userRepository.deleteById(userId);
    }

    @PreAuthorize("hasRole('ADMIN')")
    public List<UserResponse> getUsers() {
        log.info("In method get Users");
        return userRepository.findAll().stream().map(userMapper::toUserResponse).toList();
    }

    @PreAuthorize("hasRole('ADMIN')")
    public UserResponse getUser(String id) {
        return userMapper.toUserResponse(
                userRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED)));
    }
}
