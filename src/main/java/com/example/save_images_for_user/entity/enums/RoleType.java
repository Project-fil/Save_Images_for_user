package com.example.save_images_for_user.entity.enums;

import org.springframework.security.core.GrantedAuthority;

public enum RoleType implements GrantedAuthority {

    ROLE_USER;

    @Override
    public String getAuthority() {
        return name();
    }
}
