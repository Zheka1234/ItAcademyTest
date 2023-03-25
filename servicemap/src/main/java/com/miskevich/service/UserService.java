package com.miskevich.service;

import com.miskevich.dto.UserRequest;
import com.miskevich.dto.UserResponse;
import com.miskevich.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {

    User create(UserRequest request);

    Page<UserResponse> findAll(Pageable pageable);

}
