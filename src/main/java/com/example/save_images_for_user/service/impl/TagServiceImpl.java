package com.example.save_images_for_user.service.impl;

import com.example.save_images_for_user.entity.TagEntity;
import com.example.save_images_for_user.exception.EntityNotFoundException;
import com.example.save_images_for_user.repository.TagRepository;
import com.example.save_images_for_user.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TagServiceImpl implements TagService {

    private final TagRepository tagRepository;

    @Override
    public TagEntity getById(long id) {
        return this.tagRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Tag not found"));
    }

    @Override
    public TagEntity getByTag(String tag) {
        return this.tagRepository.findByTag(tag)
                .orElseThrow(() -> new EntityNotFoundException("Tag not found"));
    }

    @Override
    public TagEntity update(TagEntity tagEntity) {
        return this.tagRepository.save(tagEntity);
    }

    @Override
    public TagEntity checkIdentityTag(String tag) {
        return this.tagRepository.findByTag(tag).orElse(null);
    }

}
