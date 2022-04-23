package com.example.save_images_for_user.util.transfer_object;

import com.example.save_images_for_user.entity.FileEntity;
import com.example.save_images_for_user.entity.TagEntity;
import lombok.experimental.UtilityClass;

@UtilityClass
public class TagTransferObject {

    public static TagEntity createTag(TagEntity tagEntity, FileEntity fileEntity) {
        tagEntity.setFileEntity(fileEntity);
        return tagEntity;
    }

}
