package com.example.save_images_for_user.service;

import com.example.save_images_for_user.entity.FileEntity;
import com.example.save_images_for_user.entity.TagEntity;

public interface TagService {

    TagEntity getById(long id);

    TagEntity getByTag(String tag);

    TagEntity create(TagEntity tagEntity, FileEntity fileEntity);

    TagEntity update(TagEntity tagEntity);

    void delete(long id);

    TagEntity checkIdentityTag(String tag);

}
