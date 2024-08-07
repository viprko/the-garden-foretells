package pet.authservice.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Date;
import javax.crypto.SecretKey;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import pet.authservice.exception.InvalidJwtTokenAuthenticationException;
import pet.authservice.model.User;
import pet.authservice.service.CustomUserDetailsService;

@Component
@RequiredArgsConstructor
public class JwtTokenProvider {
    private final CustomUserDetailsService customUserDetailsService;
    private final KeyProvider keyProvider;
    @Value("${jwt.validity-in-milliseconds}")
    private Long validityInMilliseconds;

    public String createToken(User user) {
        Claims claims = Jwts.claims()
                .subject(user.getEmail())
                .add("role", user.getRole())
                .add("userId", user.getId())
                .build();
        Date now = new Date();
        Date validity = new Date(now.getTime() + validityInMilliseconds);
        return Jwts.builder()
                .claims(claims)
                .issuedAt(now)
                .expiration(validity)
                .signWith(keyProvider.getPrivateKey())
                .compact();
    }

    public Authentication getAuthentication(String token) {
        UserDetails userDetails = customUserDetailsService.loadUserByUsername(token);
        return new UsernamePasswordAuthenticationToken(userDetails, "",
                userDetails.getAuthorities());
    }

    public String getUsername(String token) {
        return Jwts.parser().decryptWith(keyProvider.getPrivateKey()).build()
                .parseSignedClaims(token).getPayload()
                .getSubject();
    }

    public String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }

    public boolean validateToken(String token) {
        try {
            Jws<Claims> claimsJws =
                    Jwts.parser().decryptWith(keyProvider.getPrivateKey()).build()
                            .parseSignedClaims(token);
            return !claimsJws.getPayload().getExpiration().before(new Date());
        } catch (JwtException | IllegalArgumentException e) {
            throw new InvalidJwtTokenAuthenticationException("Expired or invalid JWT token", e);
        }
    }
}
