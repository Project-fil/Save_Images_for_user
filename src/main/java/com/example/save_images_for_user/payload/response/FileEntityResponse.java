package com.example.save_images_for_user.payload.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FileEntityResponse {

    private long id;

    private String path;

    @JsonProperty("content_type")
    private String contentType;

    private long size;

    private String tag;

}
