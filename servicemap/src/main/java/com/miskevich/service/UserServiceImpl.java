package com.miskevich.service;

import com.miskevich.dto.UserRequest;
import com.miskevich.dto.UserResponse;
import com.miskevich.entity.Role;
import com.miskevich.entity.User;
import com.miskevich.mappers.UserMapper;
import com.miskevich.repository.RoleRepository;
import com.miskevich.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Log4j2
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public final RoleRepository roleRepository;

    private final UserMapper userMapper;


    @Override
    public User create(UserRequest request) {
        log.info("Create new user : {}", request.toString());

        User createUser = userMapper.convertCreateRequest(request);
        Optional<Role> userRole = roleRepository.findRoleByName(request.getRole().toString());

        if (userRole.isPresent()) {
            createUser.setRole(userRole.get());
            log.info("Role sets  {}", userRole.get().getName());
        }

        User createdUser = userRepository.save(createUser);
        log.info("User create  {}", createdUser);
        return createdUser;
    }

    @Override
    public Page<UserResponse> findAll(Pageable pageable) {
        log.info("All users");
        Pageable sortedPageable = PageRequest.of(
                pageable.getPageNumber(),
                pageable.getPageSize(),
                Sort.by("email").ascending());
        return userRepository.findAll(sortedPageable).map(userMapper::toUserResponse);
    }
    }

