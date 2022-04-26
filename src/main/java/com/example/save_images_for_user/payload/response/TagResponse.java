package com.example.save_images_for_user.payload.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TagResponse {

    private long id;

    private String tag;

    @JsonProperty(value = "created_at")
    private Date cratedAt;

    @JsonProperty(value =  "updated_at")
    private Date updatedAt;

}
