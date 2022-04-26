package com.example.save_images_for_user.service;

import com.example.save_images_for_user.entity.TagEntity;

public interface TagService {

    TagEntity getById(long id);

    TagEntity getByTag(String tag);

    TagEntity update(TagEntity tagEntity);

    TagEntity checkIdentityTag(String tag);

}
