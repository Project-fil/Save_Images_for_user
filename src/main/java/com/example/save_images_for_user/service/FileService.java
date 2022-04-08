package com.example.save_images_for_user.service;

import com.example.save_images_for_user.entity.FileEntity;

public interface FileService {

    FileEntity getById(long id);

    FileEntity getByPath(String path);

    FileEntity create(FileEntity fileEntity);

    FileEntity update(FileEntity fileEntity);

    void delete(long id);

}
