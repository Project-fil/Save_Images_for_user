package com.example.save_images_for_user.controller;

import com.example.save_images_for_user.entity.UserEntity;
import com.example.save_images_for_user.exception.EntityExistException;
import com.example.save_images_for_user.exception.NotValidException;
import com.example.save_images_for_user.payload.request.AuthRequest;
import com.example.save_images_for_user.payload.request.RegistrationRequest;
import com.example.save_images_for_user.payload.response.TokenResponse;
import com.example.save_images_for_user.payload.response.UserResponse;
import com.example.save_images_for_user.security.JwtTokenProvider;
import com.example.save_images_for_user.service.UserService;
import com.example.save_images_for_user.util.transfer_object.UserTransferObject;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin("*")
@RequiredArgsConstructor
@RequestMapping("/api/")
public class AuthController {

    private final UserService userService;

    private final JwtTokenProvider tokenProvider;

    private final PasswordEncoder passwordEncoder;

    @PostMapping("free/create/user")
    ResponseEntity<UserResponse> registration(@Valid @RequestBody RegistrationRequest registrationRequest) {
        if (!this.userService.checkByEmail(registrationRequest.getEmail())) {
            throw new EntityExistException("Email already exist");
        }
        UserEntity userEntity = UserTransferObject.toUser(new UserEntity(), registrationRequest);
        userEntity.setPassword(this.passwordEncoder.encode(registrationRequest.getPassword()));
        return ResponseEntity.ok(UserTransferObject.fromUser(this.userService.create(userEntity)));
    }

    @PostMapping("free/auth")
    ResponseEntity<TokenResponse> authorization(@Valid @RequestBody AuthRequest authRequest) {
        UserEntity userEntity = this.userService.getByEmail(authRequest.getEmail());
        if (!this.passwordEncoder.matches(authRequest.getPassword(), userEntity.getPassword())) {
            throw new NotValidException("Password not match");
        }
        return ResponseEntity.ok(new TokenResponse(
                this.tokenProvider.generateToken(userEntity),
                userEntity.getId(),
                userEntity.getFirstName() + " " + userEntity.getLastName(),
                userEntity.getRole().getAuthority()
        ));
    }

}
