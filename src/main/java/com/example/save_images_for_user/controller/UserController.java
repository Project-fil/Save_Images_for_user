package com.example.save_images_for_user.controller;

import com.example.save_images_for_user.payload.request.UserUpdateRequest;
import com.example.save_images_for_user.payload.response.UserResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface UserController {

    @GetMapping("user/all")
    ResponseEntity<List<UserResponse>> getAllUsers();

    @GetMapping("user/{id}")
    ResponseEntity<UserResponse> getById(@PathVariable Long id);

    @GetMapping("user/path/{email}")
    ResponseEntity<UserResponse> getByEmail(@PathVariable String email);

    @PutMapping("user")
    ResponseEntity<UserResponse> updateUser(@RequestBody UserUpdateRequest userUpdateRequest);

    @DeleteMapping("user/{id}")
    ResponseEntity<Object> deleteUser(@PathVariable Long id);

}
