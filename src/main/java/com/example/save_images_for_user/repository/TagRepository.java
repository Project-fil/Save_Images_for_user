package com.example.save_images_for_user.repository;

import com.example.save_images_for_user.entity.TagEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TagRepository extends JpaRepository<TagEntity, Long> {

    Optional<TagEntity> findByTag(String tag);

}
