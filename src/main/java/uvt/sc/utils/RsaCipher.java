package uvt.sc.utils;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.crypto.Cipher;
import java.io.File;
import java.io.FileOutputStream;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;

@Component
public class RsaCipher {

    private final String algorithm = "RSA";

    public String encrypt(String plaintext, PublicKey publicKey) throws Exception {
        Cipher cipher = Cipher.getInstance(algorithm);
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] ciphertextBytes = cipher.doFinal(plaintext.getBytes());

        return Base64.getEncoder().encodeToString(ciphertextBytes);
    }


    public String decrypt(String ciphertext, PrivateKey privateKey) throws Exception {
        byte[] ciphertextBytes = Base64.getDecoder().decode(ciphertext);

        Cipher cipher = Cipher.getInstance(algorithm);
        cipher.init(Cipher.DECRYPT_MODE, privateKey);

        byte[] decryptedBytes = cipher.doFinal(ciphertextBytes);
        return new String(decryptedBytes);
    }

    public String encrypt(MultipartFile file, PublicKey publicKey) throws Exception {
        byte[] fileData = file.getBytes();

        Cipher cipher = Cipher.getInstance(algorithm);
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] ciphertextBytes = cipher.doFinal(fileData);
        return Base64.getEncoder().encodeToString(ciphertextBytes);
    }



    public File decryptFile(String fileEncrypted, PrivateKey privateKey) throws Exception{
        byte[] ciphertextBytes = Base64.getDecoder().decode(fileEncrypted);

        Cipher cipher = Cipher.getInstance(algorithm);
        cipher.init(Cipher.DECRYPT_MODE, privateKey);

        byte[] decryptedBytes = cipher.doFinal(ciphertextBytes);

        File decryptedFile = new File("decrypted_file.txt");
        FileOutputStream outputStream = new FileOutputStream(decryptedFile);
        outputStream.write(decryptedBytes);

        return decryptedFile;
    }
}
