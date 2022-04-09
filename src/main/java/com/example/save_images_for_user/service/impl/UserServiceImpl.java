package com.example.save_images_for_user.service.impl;

import com.example.save_images_for_user.entity.UserEntity;
import com.example.save_images_for_user.exception.EntityNotFoundException;
import com.example.save_images_for_user.repository.UserRepository;
import com.example.save_images_for_user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public List<UserEntity> getAll() {
        return this.userRepository.findAll();
    }

    @Override
    public UserEntity getById(long id) {
        return this.userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
    }

    @Override
    public UserEntity getByEmail(String email) {
        return this.userRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
    }

    @Override
    public UserEntity create(UserEntity userEntity) {
        return this.userRepository.save(userEntity);
    }

    @Override
    public UserEntity update(UserEntity userEntity) {
        return this.userRepository.save(userEntity);
    }

    @Override
    public void delete(long id) {
        this.userRepository.deleteById(id);
    }
}
