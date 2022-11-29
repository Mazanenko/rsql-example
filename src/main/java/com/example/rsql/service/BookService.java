package com.example.rsql.service;

import com.example.rsql.dto.BookDto;
import com.example.rsql.dto.BookDtoPage;

public interface BookService {
    BookDto get(Long bookId);

    BookDtoPage getAll(String filter, Integer page, Integer size, String sort);

    BookDto create(BookDto bookDto);

    BookDto update(Long bookId, BookDto bookDto);

    void delete(Long bookId);
}
