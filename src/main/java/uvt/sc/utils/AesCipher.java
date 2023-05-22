package uvt.sc.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Component
public class AesCipher {
    private final String algorithm = "AES";

    @Value("${cipher.secretKey}")
    private String keyString;

    public String encrypt(String plaintext) throws Exception {
        byte[] keyBytes = keyString.getBytes(StandardCharsets.UTF_8);
        byte[] aesKeyBytes = new byte[16];
        System.arraycopy(keyBytes, 0, aesKeyBytes, 0, Math.min(keyBytes.length, 16));
        SecretKeySpec secretKey = new SecretKeySpec(aesKeyBytes, algorithm);

        Cipher cipher = Cipher.getInstance(algorithm);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);

        byte[] encryptedBytes = cipher.doFinal(plaintext.getBytes(StandardCharsets.UTF_8));

        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    public String encrypt(MultipartFile file) throws Exception {
        byte[] fileData = file.getBytes();

        byte[] keyBytes = keyString.getBytes(StandardCharsets.UTF_8);
        byte[] aesKeyBytes = new byte[16];
        System.arraycopy(keyBytes, 0, aesKeyBytes, 0, Math.min(keyBytes.length, 16));
        SecretKeySpec secretKey = new SecretKeySpec(aesKeyBytes, algorithm);

        Cipher cipher = Cipher.getInstance(algorithm);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);

        byte[] encryptedBytes = cipher.doFinal(fileData);

        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    public String decrypt(String ciphertext) throws Exception {
        byte[] keyBytes = keyString.getBytes(StandardCharsets.UTF_8);
        byte[] aesKeyBytes = new byte[16];
        System.arraycopy(keyBytes, 0, aesKeyBytes, 0, Math.min(keyBytes.length, 16));
        SecretKeySpec secretKey = new SecretKeySpec(aesKeyBytes, algorithm);

        Cipher cipher = Cipher.getInstance(algorithm);
        cipher.init(Cipher.DECRYPT_MODE, secretKey);

        byte[] encryptedBytes = Base64.getDecoder().decode(ciphertext);

        byte[] decryptedBytes = cipher.doFinal(encryptedBytes);

        return new String(decryptedBytes, StandardCharsets.UTF_8);
    }

    public File decryptFile(String fileEncrypted) throws Exception {
        byte[] keyBytes = keyString.getBytes(StandardCharsets.UTF_8);
        byte[] aesKeyBytes = new byte[16];
        System.arraycopy(keyBytes, 0, aesKeyBytes, 0, Math.min(keyBytes.length, 16));
        SecretKeySpec secretKey = new SecretKeySpec(aesKeyBytes, algorithm);

        Cipher cipher = Cipher.getInstance(algorithm);
        cipher.init(Cipher.DECRYPT_MODE, secretKey);

        byte[] encryptedBytes = Base64.getDecoder().decode(fileEncrypted);

        byte[] decryptedBytes = cipher.doFinal(encryptedBytes);

        File decryptedFile = new File("decrypted_file.txt");
        FileOutputStream outputStream = new FileOutputStream(decryptedFile);
        outputStream.write(decryptedBytes);

        return decryptedFile;

    }
}
