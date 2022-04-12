package com.example.save_images_for_user.util.transfer_object;

import com.example.save_images_for_user.entity.FileEntity;
import com.example.save_images_for_user.payload.response.FileEntityResponse;
import lombok.experimental.UtilityClass;

import java.util.Objects;

@UtilityClass
public class FileTransferObject {

    public static FileEntityResponse fromFile(FileEntity payload) {
        if (Objects.isNull(payload)) {
            return null;
        }
        return new FileEntityResponse(
                payload.getId(),
                payload.getPath(),
                payload.getContentType(),
                payload.getSize(),
                payload.getTagEntity().getTag()
        );
    }

}
