package com.example.save_images_for_user.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

public interface TagController {

    @GetMapping("tag/{id}")
    ResponseEntity<Object> getById(@PathVariable long id);

    @GetMapping("tag/view/{tag}")
    ResponseEntity<Object> getByTag(@PathVariable String tag);

    @PutMapping("tag/{id}/{tag}")
    ResponseEntity<Object> updateTag(@PathVariable long id, @PathVariable String tag);

    @DeleteMapping("tag/{id}")
    ResponseEntity<Object> delete(@PathVariable long id);

}
