package uvt.sc.controller;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import uvt.sc.model.ActionAlgorithm;
import uvt.sc.service.composite.CipherCompositeService;

import java.io.File;

@RestController
@RequestMapping("/")
@AllArgsConstructor
@Slf4j
public class CipherController {

    private final CipherCompositeService cipherCompositeService;

    @PostMapping(value = "", produces = "application/json", consumes = "application/json")
    public String executeAction(@RequestBody String message,
                                             @RequestParam("action") ActionAlgorithm actionAlgorithm) throws Exception {
        return cipherCompositeService.executeAction(message, actionAlgorithm);
    }

    @PostMapping(value = "file", produces = "application/json", consumes = "multipart/form-data")
    public String executeAction(@RequestParam("file") MultipartFile file,
                                @RequestParam("action") ActionAlgorithm actionAlgorithm) throws Exception {
        return cipherCompositeService.executeAction(file, actionAlgorithm);
    }

    @PostMapping(value = "file", produces = "application/json", consumes = "application/json")
    public File executeFileAction(@RequestBody String fileEncrypted,
                                  @RequestParam("action") ActionAlgorithm actionAlgorithm) throws Exception {
        return cipherCompositeService.executeFileAction(fileEncrypted, actionAlgorithm);
    }
}
