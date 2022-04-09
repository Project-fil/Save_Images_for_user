package com.example.save_images_for_user.controller.impl;

import com.example.save_images_for_user.controller.UserController;
import com.example.save_images_for_user.payload.request.UserUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
@RequiredArgsConstructor
@RequestMapping("/api/")
public class UserControllerImpl implements UserController {

    @Override
    public ResponseEntity<Object> getAllUsers() {
        return null;
    }

    @Override
    public ResponseEntity<Object> getById(Long id) {
        return null;
    }

    @Override
    public ResponseEntity<Object> getByPath(String path) {
        return null;
    }

    @Override
    public ResponseEntity<Object> updateUser(UserUpdateRequest userUpdateRequest) {
        return null;
    }

    @Override
    public ResponseEntity<Object> deleteUser(Long id) {
        return null;
    }
}
