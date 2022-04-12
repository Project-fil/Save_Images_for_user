package com.example.save_images_for_user.controller.impl;

import com.example.save_images_for_user.controller.UserController;
import com.example.save_images_for_user.entity.UserEntity;
import com.example.save_images_for_user.payload.request.UserUpdateRequest;
import com.example.save_images_for_user.payload.response.UserResponse;
import com.example.save_images_for_user.service.UserService;
import com.example.save_images_for_user.util.transfer_object.UserTransferObject;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin("*")
@RequiredArgsConstructor
@RequestMapping("/api/")
public class UserControllerImpl implements UserController {

    private final UserService userService;

    @Override
    @CrossOrigin("*")
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        return ResponseEntity.ok(
                this.userService.getAll().stream()
                        .map(UserTransferObject::fromUser)
                        .collect(Collectors.toList())
        );
    }

    @Override
    @CrossOrigin("*")
    public ResponseEntity<UserResponse> getById(Long id) {
        return ResponseEntity.ok(UserTransferObject.fromUser(this.userService.getById(id)));
    }

    @Override
    @CrossOrigin("*")
    public ResponseEntity<UserResponse> getByEmail(String email) {
        return ResponseEntity.ok(UserTransferObject.fromUser(this.userService.getByEmail(email)));
    }

    @Override
    @CrossOrigin("*")
    public ResponseEntity<UserResponse> updateUser(UserUpdateRequest userUpdateRequest) {
        UserEntity user = this.userService.getById(userUpdateRequest.getId());
        UserTransferObject.updateUser(user, userUpdateRequest);
        return ResponseEntity.ok(UserTransferObject.fromUser(this.userService.update(user)
        ));
    }

    @Override
    @CrossOrigin("*")
    public ResponseEntity<Object> deleteUser(Long id) {
        this.userService.delete(id);
        return ResponseEntity.ok("User deleted");
    }
}