package pl.pawelskrzypkowski.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.pawelskrzypkowski.storage.StorageFileNotFoundException;
import pl.pawelskrzypkowski.storage.StorageService;

import java.io.IOException;
import java.nio.file.Files;

@Controller
@RequestMapping("/blog")
public class BlogController {
    @Autowired
    @Qualifier("blogFileStorageService")
    StorageService blogStorageService;

    @GetMapping("/image/{id}")
    public HttpEntity<byte[]> getImage(@PathVariable("id") Long id){
        Resource resource;
        MediaType mediaType;
        try {
            resource = blogStorageService.loadAsResource(id + ".jpeg");
            mediaType = MediaType.IMAGE_JPEG;
        } catch (StorageFileNotFoundException e){
            try {
                resource = blogStorageService.loadAsResource(id + ".png");
                mediaType = MediaType.IMAGE_PNG;
            } catch (StorageFileNotFoundException e2){
                resource = null;
                mediaType = MediaType.TEXT_HTML;
            }
        }
        byte[] image;
        long contentLength;
        try {
            image = Files.readAllBytes(resource.getFile().toPath());
            contentLength = image.length;
        } catch (IOException e) {
            image = null;
            contentLength = 0;
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(mediaType);
        headers.setContentLength(contentLength);
        return new HttpEntity<>(image, headers);
    }
}
