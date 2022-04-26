package com.example.save_images_for_user.util.transfer_object;

import com.example.save_images_for_user.entity.FileEntity;
import com.example.save_images_for_user.entity.TagEntity;
import com.example.save_images_for_user.payload.response.TagResponse;
import lombok.experimental.UtilityClass;

import java.util.Objects;

@UtilityClass
public class TagTransferObject {

    public static TagResponse fromTag(TagEntity payload) {
        if (Objects.isNull(payload)) {
            return null;
        }
        return new TagResponse(
                payload.getId(),
                payload.getTag(),
                payload.getCratedAt(),
                payload.getUpdatedAt()
        );
    }

    public static TagEntity createTag(TagEntity tagEntity, FileEntity fileEntity) {
        tagEntity.setFileEntity(fileEntity);
        return tagEntity;
    }

}
