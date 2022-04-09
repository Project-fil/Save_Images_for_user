package com.example.save_images_for_user.controller;

import com.example.save_images_for_user.payload.request.UserUpdateRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public interface UserController {

    @GetMapping("user/all")
    ResponseEntity<Object> getAllUsers();

    @GetMapping("user/{id}")
    ResponseEntity<Object> getById(@PathVariable Long id);

    @GetMapping("user/path/{path}")
    ResponseEntity<Object> getByPath(@PathVariable String path);

    @PutMapping("user")
    ResponseEntity<Object> updateUser(@RequestBody UserUpdateRequest userUpdateRequest);

    @DeleteMapping("user/{id}")
    ResponseEntity<Object> deleteUser(@PathVariable Long id);

}
