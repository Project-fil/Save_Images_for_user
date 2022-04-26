package com.example.save_images_for_user.payload.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserUpdateRequest {

    @NotEmpty(message = "Id cannot be empty ")
    private Long id;

    @JsonProperty("first_name")
    private String firstName;

    @JsonProperty("last_name")
    private String lastName;

}
