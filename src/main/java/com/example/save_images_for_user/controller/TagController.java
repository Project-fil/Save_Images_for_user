package com.example.save_images_for_user.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface TagController {

    @GetMapping("tag/{id}")
    ResponseEntity<Object> getById(@PathVariable long id);

    @GetMapping("tag/view")
    void getByTag(@RequestParam(value = "tag") String tag, HttpServletResponse response) throws IOException;

    @PutMapping("tag/{id}")
    ResponseEntity<Object> updateTag(@PathVariable long id, @RequestParam(value = "tag") String tag);

}
