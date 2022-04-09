package com.example.save_images_for_user.controller;

import com.example.save_images_for_user.security.JwtTokenProvider;
import com.example.save_images_for_user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
@RequiredArgsConstructor
@RequestMapping("/api/")
public class AuthController {

    private final UserService userService;

    private final JwtTokenProvider tokenProvider;

    private final PasswordEncoder passwordEncoder;

}
