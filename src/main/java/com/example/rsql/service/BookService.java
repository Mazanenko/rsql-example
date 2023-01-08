package com.example.rsql.service;

import com.example.rsql.dto.BookDto;
import com.example.rsql.dto.BookDtoPage;
import org.springframework.lang.NonNull;

public interface BookService {
    BookDto get(@NonNull Long bookId);

    BookDtoPage getAll(String filter, Integer page, Integer size, String sort);

    BookDto create(@NonNull BookDto bookDto);

    BookDto update(@NonNull Long bookId, @NonNull BookDto bookDto);

    void delete(@NonNull Long bookId);
}
