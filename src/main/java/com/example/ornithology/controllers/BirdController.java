package com.example.ornithology.controllers;

import com.example.ornithology.models.BirdModel;
import com.example.ornithology.models.FileModel;
import com.example.ornithology.services.BirdService;
import com.example.ornithology.services.FileService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/bird")
public class BirdController {

    final BirdService birdService;
    final FileService fileService;

    public BirdController(BirdService birdService, FileService fileService) {
        this.birdService = birdService;
        this.fileService = fileService;
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public ResponseEntity<BirdModel> upload(@RequestBody BirdModel bird)
            throws IOException {
        return ResponseEntity.status(HttpStatus.CREATED).body(birdService.save(bird));

    }

    @GetMapping
    public ResponseEntity<List<BirdModel>> getAllBird() throws IOException {
        return ResponseEntity.status(HttpStatus.OK).body(birdService.findAll());
    }

}
