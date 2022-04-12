package com.example.save_images_for_user.payload.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TokenResponse {

    private String token;

    private long id;

    @JsonProperty("user_name")
    private String userName;

    private String role;

}
