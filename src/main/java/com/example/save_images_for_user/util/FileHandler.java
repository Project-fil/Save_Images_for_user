package com.example.save_images_for_user.util;

import com.example.save_images_for_user.entity.FileEntity;
import com.example.save_images_for_user.exception.EntityNotFoundException;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class FileHandler {

    @Value("${app.image-path}")
    private String savePath;

    private static final List<String> formatAvailable = new ArrayList<>() {{
        addAll(List.of("image/png",
                "image/jpg",
                "image/jpeg",
                "image/bmp",
                "image/jpe",
                "image/pdn"));
    }};

    public FileEntity writeFile(MultipartFile file) throws IOException {
        if (Objects.isNull(file)
                || !checkFormat(file.getContentType())) {
            throw new EntityNotFoundException("Wrong file");
        }
        String fileName = getFileName(file.getOriginalFilename()) + RandomString.make(4) + "." + getExtension(file.getOriginalFilename());
        String filePath = savePath += "/" + fileName;
        byte[] bytes = file.getBytes();
        Path path = Paths.get(filePath);
        Files.write(path, bytes);
        return new FileEntity(
                fileName,
                filePath,
                file.getContentType(),
                file.getSize()
        );
    }

    private static String getFileName(String fileName) {
        String[] split = Objects.requireNonNull(fileName).split("\\.");
        return split[0];
    }

    private static String getExtension(String fileExtension) {
        String[] split = Objects.requireNonNull(fileExtension).split("\\.");
        return split[1];
    }

    private boolean checkFormat(String format) {
        if (formatAvailable.contains(format)) {
            return true;
        }
        return false;
    }

}
