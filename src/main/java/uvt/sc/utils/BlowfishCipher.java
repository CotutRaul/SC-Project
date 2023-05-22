package uvt.sc.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Component
public class BlowfishCipher {
    private final String algorithm = "Blowfish";

    @Value("${cipher.secretKey}")
    private String keyString;

    public String encrypt(String plaintext) throws Exception {
        SecretKey secretKey = new SecretKeySpec(keyString.getBytes(StandardCharsets.UTF_8), algorithm);

        Cipher cipher = Cipher.getInstance(algorithm);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);

        byte[] encryptedBytes = cipher.doFinal(plaintext.getBytes(StandardCharsets.UTF_8));

        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    public String encrypt(MultipartFile file) throws Exception {
        byte[] fileData = file.getBytes();

        SecretKey secretKey = new SecretKeySpec(keyString.getBytes(StandardCharsets.UTF_8), algorithm);

        Cipher cipher = Cipher.getInstance(algorithm);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);

        byte[] encryptedBytes = cipher.doFinal(fileData);

        return new String(Base64.getEncoder().encodeToString(encryptedBytes));
    }

    public String decrypt(String ciphertext) throws Exception {
        SecretKey secretKey = new SecretKeySpec(keyString.getBytes(StandardCharsets.UTF_8), algorithm);

        Cipher cipher = Cipher.getInstance(algorithm);
        cipher.init(Cipher.DECRYPT_MODE, secretKey);

        byte[] encryptedBytes = Base64.getDecoder().decode(ciphertext);

        byte[] decryptedBytes = cipher.doFinal(encryptedBytes);

        return new String(decryptedBytes, StandardCharsets.UTF_8);
    }

    public File decryptFile(String fileEncrypted) throws Exception {
        SecretKey secretKey = new SecretKeySpec(keyString.getBytes(StandardCharsets.UTF_8), algorithm);

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
