package com.example.ornithology.controllers;

import java.io.IOException;
import java.util.Optional;

import com.example.ornithology.models.FileModel;
import com.example.ornithology.services.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import static com.example.ornithology.services.FileService.decompressZLib;
import static org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE;

@RestController
@RequestMapping(value = "/api/files")
@CrossOrigin(origins = "http://localhost:4200")

public class FilesController {

    private final FileService fileService;

    @Autowired
    public FilesController(FileService fileService)  {
        this.fileService = fileService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(consumes = MULTIPART_FORM_DATA_VALUE)

    public ResponseEntity<String> upload(@RequestParam(value = "file", required = false)
                                            MultipartFile file) {
        try {
            fileService.save(file);

            return ResponseEntity.status(HttpStatus.OK)
                .body(String.format("File uploaded successfully: %s", file.getOriginalFilename()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(String.format("Could not upload the file: %s!", file.getOriginalFilename()));
        }
    }



    @GetMapping("/{id}")
    public ResponseEntity<FileModel> getFile(@PathVariable Long id) throws IOException {

        Optional<FileModel> fileEntityOptional = fileService.getFile(id);
        if (!fileEntityOptional.isPresent()) {
            return ResponseEntity.notFound()
                    .build();
        }
        FileModel fileEntity = fileEntityOptional.get();
        return ResponseEntity.ok().body(fileEntity);

    }
}
