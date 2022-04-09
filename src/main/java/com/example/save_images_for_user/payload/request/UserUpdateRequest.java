package com.example.save_images_for_user.payload.request;

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

    private String firstName;

    private String lastName;

}
