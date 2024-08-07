package pet.authservice.security;

import jakarta.annotation.PostConstruct;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import lombok.Getter;
import org.springframework.stereotype.Component;

@Component
@Getter
public class KeyProvider {
    private static final String KEY_PAIR_ALGORITHM = "RSA";
    private static final int KEY_SIZE = 2048;
    private PrivateKey privateKey;
    private PublicKey publicKey;

    @PostConstruct
    public void init() {
        KeyPair keyPair = generateKeyPair();
        this.privateKey = keyPair.getPrivate();
        this.publicKey = keyPair.getPublic();
    }

    private KeyPair generateKeyPair() {
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(KEY_PAIR_ALGORITHM);
            keyPairGenerator.initialize(KEY_SIZE);
            return keyPairGenerator.generateKeyPair();
        } catch (Exception e) {
            throw new RuntimeException("Can't generate the security keys", e);
        }
    }
}
