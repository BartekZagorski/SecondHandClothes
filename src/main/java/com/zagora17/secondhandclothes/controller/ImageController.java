package com.zagora17.secondhandclothes.controller;

import com.zagora17.secondhandclothes.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/images")
class ImageController {

    @Autowired
    private ImageService imageService;

    public ResponseEntity<String> uploadImageToFileSystem(@RequestParam("image") MultipartFile file,
                                                          @RequestParam("productId") Long productId) throws IOException {
        String uploadImage = imageService.uploadImageToFileSystem(file, productId);
        return ResponseEntity.status(HttpStatus.OK)
                .body(uploadImage);
    }

    @PostMapping
    public ResponseEntity<String> uploadMultipleImages(@RequestParam("images") List<MultipartFile> files,
                                                       @RequestParam("productId") Long productId) throws IOException {
        String message = imageService.uploadMultipleImagesToFileSystem(files, productId);
        return ResponseEntity.status(HttpStatus.OK).body(message);
    }

    @GetMapping("/{id}")
    public ResponseEntity<byte[]> downloadImageFromFileSystem(@PathVariable Long id) throws IOException {
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf("image/png"))
                .body(imageService.downloadImageFromFileSystem(id));
    }

    @GetMapping
    public ResponseEntity<List<String>> getResourceUrl(@RequestParam Long productId) {
        return ResponseEntity.ok(this.imageService.getImages(productId));
    }
}
