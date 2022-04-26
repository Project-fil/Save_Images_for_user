package com.example.save_images_for_user.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface FileController {

    @GetMapping("file/{id}")
    ResponseEntity<Object> getById(@PathVariable long id);

    @GetMapping("file/view")
    void getByPath(@RequestParam(value = "path") String path, HttpServletResponse response) throws IOException;

    @PostMapping(value = "file", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Object> createFile(@RequestPart(value = "file") MultipartFile file, @RequestPart(value = "user-id") long id) throws IOException;

    @DeleteMapping("file/{id}/{userId}")
    ResponseEntity<Object> delete(@PathVariable long id, @PathVariable long userId);

}
