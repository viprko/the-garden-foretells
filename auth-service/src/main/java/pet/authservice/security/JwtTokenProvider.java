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
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import pet.authservice.exception.InvalidJwtTokenAuthenticationException;
import pet.authservice.service.CustomUserDetailsService;

@Component
@AllArgsConstructor
public class JwtTokenProvider {
    private String encodedKey;
    private Long validityInMilliseconds;
    private SecretKey secretKey;
    private final CustomUserDetailsService customUserDetailsService;

    @PostConstruct
    protected void init() {
        byte[] decodedKey = Base64.getDecoder().decode(encodedKey);
        this.secretKey = Keys.hmacShaKeyFor(decodedKey);
    }

    public String createToken(String email, String role, Long userId) {
        Claims claims = Jwts.claims()
                .subject(email)
                .add("role", role)
                .add("userId", userId)
                .build();
        Date now = new Date();
        Date validity = new Date(now.getTime() + validityInMilliseconds);
        return Jwts.builder()
                .claims(claims)
                .issuedAt(now)
                .expiration(validity)
                .signWith(secretKey, Jwts.SIG.HS256)
                .compact();
    }

    public Authentication getAuthentication(String token) {
        UserDetails userDetails = customUserDetailsService.loadUserByUsername(token);
        return new UsernamePasswordAuthenticationToken(userDetails, "",
                userDetails.getAuthorities());
    }

    public String getUsername(String token) {
        return Jwts.parser().decryptWith(secretKey).build().parseSignedClaims(token).getPayload()
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
                    Jwts.parser().decryptWith(secretKey).build().parseSignedClaims(token);
            return !claimsJws.getPayload().getExpiration().before(new Date());
        } catch (JwtException | IllegalArgumentException e) {
            throw new InvalidJwtTokenAuthenticationException("Expired or invalid JWT token", e);
        }
    }
}
