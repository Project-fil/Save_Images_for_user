package com.example.save_images_for_user.service.impl;

import com.example.save_images_for_user.entity.FileEntity;
import com.example.save_images_for_user.exception.EntityNotFoundException;
import com.example.save_images_for_user.repository.FileRepository;
import com.example.save_images_for_user.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {

    private final FileRepository fileRepository;

    @Override
    public FileEntity getById(long id) {
        return this.fileRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("File not found"));
    }

    @Override
    public FileEntity getByPath(String path) {
        return this.fileRepository.findByPath(path)
                .orElseThrow(() -> new EntityNotFoundException("File not found"));
    }

    @Override
    public FileEntity create(FileEntity fileEntity) {
        return this.fileRepository.save(fileEntity);
    }

    @Override
    public void delete(long id) {
        this.fileRepository.deleteById(id);
    }
}
