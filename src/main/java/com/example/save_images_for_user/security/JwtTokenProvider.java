package com.example.save_images_for_user.security;

import com.example.save_images_for_user.entity.UserEntity;
import com.example.save_images_for_user.exception.NotValidException;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
public class JwtTokenProvider {

    @Value("${app.secret-word}")
    private String secretWord;

    public String generateToken(UserEntity payload) {
        Date date = Date.from(LocalDate.now().plusDays(1).atStartOfDay(ZoneId.systemDefault()).toInstant());
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", payload.getId());
        claims.put("email", payload.getEmail());
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(date)
                .signWith(SignatureAlgorithm.HS512, this.secretWord)
                .compact();
    }

    public boolean validateToken(String token) {
        try{
            Jwts.parser().setSigningKey(this.secretWord).parseClaimsJws(token);
            return true;
        } catch (NotValidException |
                SignatureException |
                MalformedJwtException |
                ExpiredJwtException |
                IllegalArgumentException ex) {
            log.error(ex.getMessage());
            return false;
        }
    }

    public String getIdFromToken(String token) {
        if (token.isEmpty()) {
            return "";
        }
        Claims claims = Jwts.parser()
                .setSigningKey(this.secretWord)
                .parseClaimsJws(token)
                .getBody();
        return String.valueOf(claims.get("id"));
    }

}
