package uvt.sc.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import uvt.sc.utils.*;

import java.io.File;
import java.security.PrivateKey;
import java.security.PublicKey;

@Service
@Slf4j
@AllArgsConstructor
public class CipherService {

    private final BlowfishCipher blowfishCipher;
    private final AesCipher aesCipher;
    private final ElGamalCipher elGamalCipher;
    private final RsaCipher rsaCipher;

    private final RsaKeyPairManager rsaKeyPairManager;

    // Blowfish

    public String blowfishEncrypt(String message) throws Exception {
        return blowfishCipher.encrypt(message);
    }

    public String blowfishEncrypt(MultipartFile file) throws Exception {
        return blowfishCipher.encrypt(file);
    }

    public String blowfishDecrypt(String message) throws Exception {
        return blowfishCipher.decrypt(message);
    }

    public File blowfishFileDecrypt(String fileEncrypted) throws Exception {
        return blowfishCipher.decryptFile(fileEncrypted);
    }


    // AES

    public String aesEncrypt(String message) throws Exception {
        return aesCipher.encrypt(message);
    }

    public String aesEncrypt(MultipartFile file) throws Exception {
        return aesCipher.encrypt(file);
    }

    public String aesDecrypt(String message) throws Exception {
        return aesCipher.decrypt(message);
    }

    public File aesFileDecrypt(String fileEncrypted) throws Exception {
        return aesCipher.decryptFile(fileEncrypted);
    }

    //RSA

    public String rsaEncrypt(String message) throws Exception {
        PublicKey publicKey = rsaKeyPairManager.getKeyPair().getPublic();
        return rsaCipher.encrypt(message, publicKey);
    }

    public String rsaEncrypt(MultipartFile file) throws Exception {
        PublicKey publicKey = rsaKeyPairManager.getKeyPair().getPublic();
        return rsaCipher.encrypt(file, publicKey);
    }

    public String rsaDecrypt(String message) throws Exception {
        PrivateKey privateKey = rsaKeyPairManager.getKeyPair().getPrivate();
        return rsaCipher.decrypt(message, privateKey);
    }

    public File rsaFileDecrypt(String fileEncrypted) throws Exception {
        PrivateKey privateKey = rsaKeyPairManager.getKeyPair().getPrivate();
        return rsaCipher.decryptFile(fileEncrypted, privateKey);
    }

    // ElGamal

    public String elGamalEncrypt(String message) throws Exception {
        return elGamalCipher.encrypt(message);
    }

    public String elGamalDecrypt(String message) throws Exception {
        return elGamalCipher.decrypt(message);
    }
}
