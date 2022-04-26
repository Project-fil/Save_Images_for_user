package com.example.save_images_for_user.controller.impl;

import com.example.save_images_for_user.controller.FileController;
import com.example.save_images_for_user.entity.FileEntity;
import com.example.save_images_for_user.entity.TagEntity;
import com.example.save_images_for_user.entity.UserEntity;
import com.example.save_images_for_user.service.FileService;
import com.example.save_images_for_user.service.TagService;
import com.example.save_images_for_user.service.UserService;
import com.example.save_images_for_user.util.FileHandler;
import com.example.save_images_for_user.util.transfer_object.FileTransferObject;
import com.example.save_images_for_user.util.transfer_object.TagTransferObject;
import lombok.RequiredArgsConstructor;
import net.bytebuddy.utility.RandomString;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

@RestController
@CrossOrigin("*")
@RequiredArgsConstructor
@RequestMapping(path = "/api/")
public class FileControllerImpl implements FileController {

    private final TagService tagService;

    private final UserService userService;

    private final FileService fileService;

    private final FileHandler fileHandler;

    @Override
    @CrossOrigin("*")
    public ResponseEntity<Object> getById(long id) {
        return ResponseEntity.ok(FileTransferObject.fromFile(this.fileService.getById(id)));
    }

    @Override
    @CrossOrigin("*")
    public void getByPath(String path, HttpServletResponse response) throws IOException {
        FileEntity fileEntity = this.fileService.getByPath(path);
        Path get = Paths.get(fileEntity.getPath());
        byte[] arr = Files.readAllBytes(get);
        response.setContentType(fileEntity.getContentType());
        response.getOutputStream().write(arr);
        response.getOutputStream().close();
    }

    @Override
    @CrossOrigin("*")
    public ResponseEntity<Object> createFile(MultipartFile file, long userId) throws IOException {
        UserEntity userEntity = this.userService.getById(userId);
        FileEntity fileEntity = this.fileHandler.writeFile(file);
        String tag = "#" + fileEntity.getName();
        if (Objects.nonNull(this.tagService.checkIdentityTag(tag))) {
            tag = FileHandler.getFileName(tag) + RandomString.make(2) + "." + FileHandler.getExtension(tag);
        }
        TagEntity tagEntity = TagTransferObject.createTag(new TagEntity(tag), fileEntity);
        fileEntity.setTagEntity(tagEntity);
        fileEntity = this.fileService.create(fileEntity);
        userEntity.setFileEntity(fileEntity);
        this.userService.update(userEntity);
        return ResponseEntity.ok(FileTransferObject.fromFile(fileEntity));
    }

    @Override
    @CrossOrigin("*")
    public ResponseEntity<Object> delete(long id, long userId) {
        FileEntity fileEntity = this.fileService.getById(id);
        new File(fileEntity.getPath()).delete();
        UserEntity userEntity = this.userService.getById(userId);
        userEntity.setFileEntity(null);
        this.userService.update(userEntity);
        this.fileService.delete(fileEntity.getId());
        return ResponseEntity.ok("File " + fileEntity.getName() + " deleted.");
    }
}
