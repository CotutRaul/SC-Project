package uvt.sc.utils;

import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import java.security.*;

@Component
public class ElGamalCipher {

    private final String algorithm = "ElGamal";


    public String encrypt(String message) throws Exception {
        KeyPair keyPair = generateKeyPair();

        Cipher cipher = Cipher.getInstance(algorithm);
        cipher.init(Cipher.ENCRYPT_MODE, keyPair.getPublic());
        return bytesToHex(cipher.doFinal(message.getBytes()));
    }

    public String decrypt(String encrypted) throws Exception {
        KeyPair keyPair = generateKeyPair();

        Cipher cipher = Cipher.getInstance(algorithm);
        cipher.init(Cipher.DECRYPT_MODE, keyPair.getPrivate());

        byte[] bytes = encrypted.getBytes();

        byte[] decryptedBytes = cipher.doFinal(bytes);
        return new String(decryptedBytes);
    }

    public KeyPair generateKeyPair() throws NoSuchAlgorithmException {
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance(algorithm);
        keyGen.initialize(1024);
        return keyGen.generateKeyPair();
    }

    public String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }

}
