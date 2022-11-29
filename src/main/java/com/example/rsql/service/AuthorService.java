package com.example.rsql.service;

import com.example.rsql.dto.AuthorDto;

import java.util.List;

public interface AuthorService {
    AuthorDto get(Long authorId);

    List<AuthorDto> getAll();

    AuthorDto create(AuthorDto authorDto);

    AuthorDto update(Long authorId, AuthorDto authorDto);

    void delete(Long authorId);
}
