package com.example.ornithology.controllers;

import com.example.ornithology.models.BirdModel;
import com.example.ornithology.services.BirdService;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BirdControllerTest {

    @InjectMocks
    private BirdController birdController;

    @Mock
    private BirdService birdService;

    private BirdModel birdModel;

    @Test
    void postBird() throws IOException {
        birdModel = new BirdModel(
                null,
                null,
                "Canário",
                "Blue-and-yellow Macaw",
                "Ara ararauna",
                "90 cm",
                "Can",
                "Amarelo",
                "Psittacidae",
                "Floresta");

        when(birdService.save(birdModel)).thenReturn(birdModel);

        var response = assertDoesNotThrow(() -> birdController.upload(birdModel));
        assertNotNull(response);
        assertEquals(ResponseEntity.status(HttpStatus.CREATED).body(birdService.save(birdModel)), response);
    }

    @Test
    void getAllBird() throws IOException {

        var response = assertDoesNotThrow(() -> birdController.getAllBird());
        assertNotNull(response);
        assertEquals(ResponseEntity.status(HttpStatus.OK).body(birdService.findAll()), response);
    }


}
