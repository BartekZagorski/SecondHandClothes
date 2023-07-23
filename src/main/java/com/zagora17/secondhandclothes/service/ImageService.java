package com.zagora17.secondhandclothes.service;

import com.zagora17.secondhandclothes.dao.ImageRepository;
import com.zagora17.secondhandclothes.dao.ProductRepository;
import com.zagora17.secondhandclothes.entity.Image;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@Service
public class ImageService {
    private final String FOLDER_PATH="/work/second-hand-clothes-app/second-hand-clothes-frontend/second-hand-clothes" +
            "/src/";
    @Autowired
    private ImageRepository imageRepository;

    @Value("${spring.data.rest.base-path}")
    private String basePath;

    @Autowired
    private ServerProperties serverProperties;

    @Autowired
    private ProductRepository productRepository;
    @Transactional
    public String uploadImageToFileSystem(MultipartFile file, Long productId) throws IOException {
        String filePath=FOLDER_PATH+"assets/"+file.getOriginalFilename();
                imageRepository.save(Image.builder()
                        .product(productRepository.findById(productId).get())
                        .url("assets/"+file.getOriginalFilename()).build());


        Path path = Paths.get(filePath).toAbsolutePath();
        file.transferTo(path.toFile());

        return "file uploaded successfully : " + filePath;
    }
    @Transactional
    public String uploadMultipleImagesToFileSystem(List<MultipartFile> files, Long productId) throws IOException {
        StringBuilder messageBuilder = new StringBuilder();
        for (MultipartFile file: files
             ) {
            messageBuilder.append(this.uploadImageToFileSystem(file, productId));
        }
        return messageBuilder.toString();
    }

    public byte[] downloadImageFromFileSystem(Long id) throws IOException {
        Optional<Image> fileData = imageRepository.findById(id);

        var filePath = FOLDER_PATH + fileData.get().getUrl();

        try {
            return Files.readAllBytes(new File(filePath).toPath());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<String> getImages (Long productId) {

        return this.imageRepository.findByProductId(productId).stream()
                        .map(Image::getId)
                        .map(id -> {
                            String ip;
                            try {
                                ip = InetAddress.getLocalHost().getHostAddress();
                            } catch (UnknownHostException e) {
                                throw new RuntimeException(e);
                            }
                            String port = serverProperties.getPort() != null ? String.valueOf(serverProperties.getPort()) : "8080";
                            return "http://" + ip + ":" + port + "/api/images/" + id;
                        }).toList();
    }
}
