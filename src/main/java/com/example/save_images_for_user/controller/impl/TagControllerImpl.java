package com.example.save_images_for_user.controller.impl;

import com.example.save_images_for_user.controller.TagController;
import com.example.save_images_for_user.entity.TagEntity;
import com.example.save_images_for_user.service.TagService;
import com.example.save_images_for_user.util.FileHandler;
import com.example.save_images_for_user.util.transfer_object.TagTransferObject;
import lombok.RequiredArgsConstructor;
import net.bytebuddy.utility.RandomString;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

@RestController
@CrossOrigin("*")
@RequiredArgsConstructor
@RequestMapping(path = "/api/")
public class TagControllerImpl implements TagController {

    private final TagService tagService;

    @Override
    public ResponseEntity<Object> getById(long id) {
        return ResponseEntity.ok(TagTransferObject.fromTag(this.tagService.getById(id)));
    }

    @Override
    public void getByTag(String tag, HttpServletResponse response) throws IOException {
        TagEntity tagEntity = this.tagService.getByTag(tag);
        Path get = Paths.get(tagEntity.getFileEntity().getPath());
        byte[] arr = Files.readAllBytes(get);
        response.setContentType(tagEntity.getFileEntity().getContentType());
        response.getOutputStream().write(arr);
        response.getOutputStream().close();
    }

    @Override
    public ResponseEntity<Object> updateTag(long id, String tag) {
        TagEntity tagEntity = this.tagService.getById(id);
        if (Objects.nonNull(this.tagService.checkIdentityTag(tag))){
            tag = FileHandler.getFileName(tag) + RandomString.make(2) + "." + FileHandler.getExtension(tag);
        }
        tagEntity.setTag(tag);
        return ResponseEntity.ok(TagTransferObject.fromTag(this.tagService.update(tagEntity)));
    }

}
