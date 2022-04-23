package com.example.save_images_for_user.security;

import com.example.save_images_for_user.entity.UserEntity;
import com.example.save_images_for_user.exception.EntityNotFoundException;
import com.example.save_images_for_user.service.UserService;
import com.example.save_images_for_user.util.UserUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Objects;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtFilter extends GenericFilterBean {

    public final UserService userService;

    public final JwtTokenProvider jwtTokenProvider;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        String token = UserUtil.getJWTFromRequest((HttpServletRequest)request);
        if (token != null && this.jwtTokenProvider.validateToken(token)) {
            var userId = Long.parseLong(this.jwtTokenProvider.getIdFromToken(token));
            UserEntity userEntity = null;
            try {
                userEntity = this.userService.getById(userId);
            } catch (EntityNotFoundException ignored) {
            }
            if (Objects.nonNull(userEntity)) {
                UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                        userEntity, null, userEntity.getAuthorities()
                );
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        }
        filterChain.doFilter(request, response);
    }

}
