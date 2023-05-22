package uvt.sc.service.composite;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import uvt.sc.model.ActionAlgorithm;
import uvt.sc.service.CipherService;

import java.io.File;

@Service
@Slf4j
@AllArgsConstructor
public class CipherCompositeService {

    private final CipherService cipherService;

    public String executeAction(String message, ActionAlgorithm actionAlgorithm) throws Exception {
        return switch (actionAlgorithm) {
            case BLOWFISH_ENCRYPT -> cipherService.blowfishEncrypt(message);
            case BLOWFISH_DECRYPT -> cipherService.blowfishDecrypt(message);
            case AES_ENCRYPT -> cipherService.aesEncrypt(message);
            case AES_DECRYPT -> cipherService.aesDecrypt(message);
            case ELGAMAL_ENCRYPT -> cipherService.elGamalEncrypt(message);
            case ELGAMAL_DECRYPT -> cipherService.elGamalDecrypt(message);
            case RSA_ENCRYPT -> cipherService.rsaEncrypt(message);
            case RSA_DECRYPT -> cipherService.rsaDecrypt(message);
        };
    }

    public String executeAction(MultipartFile file, ActionAlgorithm actionAlgorithm) throws Exception {
        return switch (actionAlgorithm) {
            case BLOWFISH_ENCRYPT -> cipherService.blowfishEncrypt(file);
            case AES_ENCRYPT -> cipherService.aesEncrypt(file);
            case RSA_ENCRYPT -> cipherService.rsaEncrypt(file);
            default -> throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        };
    }

    public File executeFileAction(String fileEncrypted, ActionAlgorithm actionAlgorithm) throws Exception {
        return switch (actionAlgorithm) {
            case BLOWFISH_DECRYPT -> cipherService.blowfishFileDecrypt(fileEncrypted);
            case AES_DECRYPT -> cipherService.aesFileDecrypt(fileEncrypted);
            case RSA_DECRYPT -> cipherService.rsaFileDecrypt(fileEncrypted);
            default -> throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        };
    }
}
