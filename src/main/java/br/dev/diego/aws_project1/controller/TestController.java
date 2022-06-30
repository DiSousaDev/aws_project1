package br.dev.diego.aws_project1.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/test")
public class TestController {

    private static final Logger LOG = LoggerFactory.getLogger(TestController.class);

    @GetMapping(value="/dog/{name}")
    public ResponseEntity<String> getDogName(@PathVariable String name) {
        LOG.info("Test controller - getDogName: {}", name);
        return ResponseEntity.ok("Name: " + name);
    }

    @GetMapping(value="/dogs")
    public ResponseEntity<List<String>> findAllDogs() {
        LOG.info("Test controller - findAllDogs");
        List<String> dogs = Arrays.asList("Lyli", "Rabbito", "Pandora", "Lobinho");
        return ResponseEntity.ok(dogs);
    }
    
}
