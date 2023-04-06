package com.psy.duck.controller;

import com.psy.duck.entity.Dog;
import com.psy.duck.repository.DogRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
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

    @GetMapping(value = "/duck/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Dog>> getAllDucks() {

        List<Dog> dogList = dogRepository.findAll();

        if (dogList.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "dog.not_found_error", null);
        }

        return ResponseEntity.ok(dogList);
    }

    @GetMapping(value = "/duck/all/{age}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Dog>> getAllDucksByAge(@PathVariable Integer age) {

        List<Dog> dogListById = dogRepository.findAllByAge(age);

        if (dogListById.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "dog.not_found_error", null);
        }

        return ResponseEntity.ok(dogListById);
    }

    @PostMapping(value = "/duck", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Dog> saveDuck(@RequestBody Dog newDog){

        Dog dog = dogRepository.save(newDog);

        return ResponseEntity.ok(dog);
    }

    @PutMapping(value = "/duck/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Dog> replaceDuck(@RequestBody Dog newDog, @PathVariable Long id){

        Optional<Dog> dogOptional = dogRepository.findById(id);

        if (dogOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "dog.not_found_error", null);
        }

        Dog dog = dogOptional.get();
        dog.setAge(newDog.getAge());
        dog.setName(newDog.getName());
        Dog save = dogRepository.save(dog);

        return ResponseEntity.ok(save);

    }

    @DeleteMapping(value = "/duck/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> deleteDuck(@PathVariable long id) {
         Optional<Dog> dogOptional = dogRepository.findById(id);

        if (dogOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "dog.not_found_error", null);
        }

        dogRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
