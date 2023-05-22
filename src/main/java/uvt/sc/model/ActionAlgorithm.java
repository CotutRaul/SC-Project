package uvt.sc.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ActionAlgorithm {
    BLOWFISH_ENCRYPT("Blowfish", "encrypt"),
    BLOWFISH_DECRYPT("Blowfish", "decrypt"),
    AES_ENCRYPT("AES", "encrypt"),
    AES_DECRYPT("AES", "decrypt"),
    ELGAMAL_ENCRYPT("ElGamal", "encrypt"),
    ELGAMAL_DECRYPT("ElGamal", "decrypt"),
    RSA_ENCRYPT("RSA", "encrypt"),
    RSA_DECRYPT("RSA", "decrypt");

    private final String algorithm;
    private final String method;

}
