package com.example.rsql.controller;

import com.example.rsql.dto.AuthorDto;
import com.example.rsql.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/authors")
public class AuthorController {
    private final AuthorService authorService;

    @GetMapping("/{id}")
    public ResponseEntity<AuthorDto> get(@PathVariable("id") Long id) {
        return ResponseEntity.ok(authorService.get(id));
    }

    @GetMapping()
    public ResponseEntity<List<AuthorDto>> getAll() {
        return ResponseEntity.ok(authorService.getAll());
    }

    @PostMapping()
    public ResponseEntity<AuthorDto> create(@RequestBody AuthorDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(authorService.create(dto));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<AuthorDto> update(@PathVariable("id") Long id, @RequestBody AuthorDto dto) {
        return ResponseEntity.ok(authorService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<AuthorDto> delete(@PathVariable("id") Long id) {
        authorService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
