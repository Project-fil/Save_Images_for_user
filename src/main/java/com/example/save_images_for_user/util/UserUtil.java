package com.example.save_images_for_user.util;

import com.example.save_images_for_user.security.JwtTokenProvider;
import lombok.experimental.UtilityClass;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

@UtilityClass
public class UserUtil {

    public static final String TOKEN_PREFIX = "Bearer ";

    public static final String AUTHORIZATION = "Authorization";

    private final JwtTokenProvider tokenProvider = new JwtTokenProvider();

    public Long getUserId(HttpServletRequest request) {
        return Long.parseLong(tokenProvider.getIdFromToken(getJWTFromRequest(request)));
    }

    public String getJWTFromRequest(HttpServletRequest req) {
        String bearer = req.getHeader(AUTHORIZATION);
        if (StringUtils.hasText(bearer) && bearer.startsWith(TOKEN_PREFIX)) {
            return bearer.split(" ") [1];
        }
        return null;
    }

}
