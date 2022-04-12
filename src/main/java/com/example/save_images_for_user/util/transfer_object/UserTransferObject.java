package com.example.save_images_for_user.util.transfer_object;

import com.example.save_images_for_user.entity.UserEntity;
import com.example.save_images_for_user.payload.request.RegistrationRequest;
import com.example.save_images_for_user.payload.request.UserUpdateRequest;
import com.example.save_images_for_user.payload.response.UserResponse;
import com.example.save_images_for_user.entity.enums.RoleType;
import lombok.experimental.UtilityClass;

@UtilityClass
public class UserTransferObject {

    public static UserEntity toUser(UserEntity user, RegistrationRequest payload) {
        user.setFirstName(payload.getFirstName());
        user.setLastName(payload.getLastName());
        user.setEmail(payload.getEmail());
        user.setRole(RoleType.ROLE_USER);
        return user;
    }

    public static UserResponse fromUser(UserEntity payload) {
        return new UserResponse(
                payload.getId(),
                payload.getFirstName(),
                payload.getLastName(),
                payload.getEmail(),
                payload.getRole().getAuthority(),
                FileTransferObject.fromFile(payload.getFileEntity())
        );
    }

    public static UserEntity updateUser(UserEntity user, UserUpdateRequest userUpdateRequest) {
        user.setFirstName(userUpdateRequest.getFirstName());
        user.setLastName(user.getLastName());
        return user;
    }
}
