package back.com.example.demo.services;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.nio.file.*;


import back.com.example.demo.config.FileStorageProperties;
import back.com.example.demo.excepation.NotFoundFileException;
import back.com.example.demo.excepation.UploadFileException;
import org.springframework.util.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class FileService {

    private final Path fileStorageLocation;

    public FileService(FileStorageProperties fileStorageProperties){
        this.fileStorageLocation =  Paths.get(fileStorageProperties.getUploadDir())
        .toAbsolutePath().normalize();
        
        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception e) {
            throw new UploadFileException("Algo deu errado ao fazer upload");
        }
    }

    public String getContentType(HttpServletRequest request, Resource resource){
        String contentType = null;

        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (Exception e) {
            log.error("Não foi possível determinar o tipo do arquivo", e);
        }
        if (contentType == null) {
            contentType = "application/octet-stream";
        }
        return contentType;
    }

    public String fileSave(MultipartFile file){
        String fileName = org.springframework.util.StringUtils.cleanPath(file.getOriginalFilename());

        try {
            if (fileName.contains("..")) {
                throw new UploadFileException("Arquivo invalido");
            }
            Path targetLocation = this.fileStorageLocation.resolve(fileName);
            System.out.println("Local:" + targetLocation);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            return fileName;
        } catch (Exception e) {
            throw new UploadFileException("Erro ao tentar salvar arquivo");
        }
    }



    public Resource laodFiles(String fileName){
        System.out.println("local load " + fileName);
        try {
            Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
            System.out.println("local load " + filePath);
            Resource resource = new UrlResource(filePath.toUri());
            if (resource.exists()) {
                return resource;
            }else{
                throw new NotFoundFileException("Arquivo não encontrado");
            }
        } catch (Exception e) {
            throw new NotFoundFileException("Arquivo não encontrado", e);
        }
    }

    public String fileDelete(String fileName){
        System.out.println("local delete " + fileName);
        try {
            Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
            System.out.println("local delete " + filePath);
            Resource resource = new UrlResource(filePath.toUri());
            if (resource.exists()) {
                Files.delete(filePath);
                return "Arquivo deletado com sucesso";
            }else{
                throw new NotFoundFileException("Arquivo não encontrado");
            }
        } catch (Exception e) {
            throw new NotFoundFileException("Arquivo não encontrado", e);
        }
    }
}
