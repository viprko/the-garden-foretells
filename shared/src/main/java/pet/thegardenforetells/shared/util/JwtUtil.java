package pet.thegardenforetells.shared.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.annotation.PostConstruct;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import pet.thegardenforetells.shared.exception.PublicKeyGeneratorException;

@Component
@RequiredArgsConstructor
public class JwtUtil {
    @Value("${shared.jwt-util.claims.user-id:userId}")
    private String userIdClaimName;
    @Value("${shared.jwt-util.claims.role:role}")
    private String userAuthoritiesClaimName;
    @Value("${shared.jwt-util.public-key-url}")
    private String publicKeyUrl;
    private final AtomicReference<PublicKey> publicKey;
    private final RestTemplate restTemplate;

    @PostConstruct
    public void init() {
        fetchPublicKey();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().verifyWith(publicKey.get()).build().parseSignedClaims(token);
            return true;
        } catch (Exception e) {
            fetchPublicKey();
            try {
                Jwts.parser().verifyWith(publicKey.get()).build().parseSignedClaims(token);
                return true;
            } catch (Exception ex) {
                return false;
            }
        }
    }

    public String extractUsername(String token) {
        return extractAllClaims(token).getSubject();
    }

    public String extractUserId(String token) {
        return extractAllClaims(token).get(userIdClaimName, String.class);
    }

    public List<GrantedAuthority> extractAuthorities(String token) {
        String role = extractAllClaims(token).get(userAuthoritiesClaimName, String.class);
        return role != null ? List.of(new SimpleGrantedAuthority(role)) : Collections.emptyList();
    }

    private void fetchPublicKey() {
        String encodedPublicKey = restTemplate.getForObject(publicKeyUrl, String.class);
        publicKey.set(generatePublicKey(encodedPublicKey));
    }

    private PublicKey generatePublicKey(String encodedPublicKey) {
        try {
            byte[] keyBytes = Base64.getDecoder().decode(encodedPublicKey);
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            return keyFactory.generatePublic(keySpec);
        } catch (Exception e) {
            throw new PublicKeyGeneratorException("Couldn't generate the public key", e);
        }
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(publicKey.get()).build()
                .parseSignedClaims(token)
                .getPayload();
    }
}
