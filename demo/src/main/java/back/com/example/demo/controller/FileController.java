package back.com.example.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import back.com.example.demo.entity.Arquivo;
import back.com.example.demo.services.FileService;
import org.springframework.http.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;

import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;



@RestController
@RequestMapping("/files")
@AllArgsConstructor
public class FileController {
    
    private FileService fileService;

    @PostMapping("/upload")
    public Arquivo uploadFile(@RequestParam("file") MultipartFile file){
        System.out.println(" Upload " + file);
        String fileName = fileService.fileSave(file);

        

        String fileDir = ServletUriComponentsBuilder.fromCurrentContextPath()
                                .path("files/downloadFile/")
                                .path(fileName)
                                .toUriString();

        return new Arquivo(fileName, fileDir, file.getContentType(), file.getSize());
    }

    @GetMapping("/downloadFile/{fileName}")
    public ResponseEntity<Resource> downloadFile(@PathVariable("fileName") String fileName, HttpServletRequest request){
        System.out.println("Download " + fileName);
        Resource resource = fileService.laodFiles(fileName);
        String contentType = fileService.getContentType(request, resource);
        System.out.println("Download " + resource);
        System.out.println("Download content " + contentType);
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment: filename\"" + resource.getFilename() + "\"")
                .body(resource);
    }

    @DeleteMapping("/delete/downloadFile/{fileName}")
    public ResponseEntity<String> deletFile(@PathVariable("fileName") String fileName, HttpServletRequest request){
        String response = fileService.fileDelete(fileName);

        return ResponseEntity.ok()  
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment: filename\"" + response + "\"")
                .body(response);
    }
    
    
}
