package com.example.save_images_for_user.service;

import com.example.save_images_for_user.entity.UserEntity;

import java.util.List;

public interface UserService {

    boolean checkByEmail(String email);

    List<UserEntity> getAll();

    UserEntity getById(long id);

    UserEntity getByEmail(String email);

    UserEntity create(UserEntity userEntity);

    UserEntity update(UserEntity userEntity);

    void delete(long id);

}
