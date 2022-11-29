package com.example.rsql.controller;

import com.example.rsql.dto.BookDto;
import com.example.rsql.dto.BookDtoPage;
import com.example.rsql.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/books")
public class BookController {
    private final BookService bookService;

    @GetMapping("/{id}")
    public ResponseEntity<BookDto> get(@PathVariable("id") Long id) {
        return ResponseEntity.ok(bookService.get(id));
    }

    @GetMapping()
    public ResponseEntity<BookDtoPage> getAll(@RequestParam(value = "filter", required = false) String filter,
                                              @RequestParam(value = "page", required = false) Integer page,
                                              @RequestParam(value = "pageSize", required = false) Integer pageSize,
                                              @RequestParam(value = "sort", required = false) String sort) {
        return ResponseEntity.ok(bookService.getAll(filter, page, pageSize, sort));
    }

    @PostMapping()
    public ResponseEntity<BookDto> create(@RequestBody BookDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(bookService.create(dto));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<BookDto> update(@PathVariable("id") Long id, @RequestBody BookDto dto) {
        return ResponseEntity.ok(bookService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") Long id) {
        bookService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
