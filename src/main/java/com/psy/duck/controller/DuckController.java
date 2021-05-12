package com.psy.duck.controller;

import com.psy.duck.entity.Dog;
import com.psy.duck.repository.DogRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class DuckController {

    private final DogRepository dogRepository;

        public DuckController(DogRepository dogRepository) {
                this.dogRepository = dogRepository;
    }

    @GetMapping(value = "/duck/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Dog> getDuck(@PathVariable Long id) {

        Optional<Dog> dogOptional = dogRepository.findById(id);

        if (dogOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "dog.not_found_error", null);
        }

        return ResponseEntity.ok(dogOptional.get());
    }

    @PostMapping(value = "/duck", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Dog> saveDuck(@RequestBody Dog newDog){

        Dog dog = dogRepository.save(newDog);
                return ResponseEntity.ok(dog);
    }

    @PutMapping(value = "/duck/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Dog> replaceDuck(@RequestBody Dog newDog, @PathVariable Long id){

        Dog dog = dogRepository.save(newDog);
        return ResponseEntity.ok(dog);

    }

}
