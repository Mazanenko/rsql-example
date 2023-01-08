package com.example.rsql.service.impl;

import com.example.rsql.dto.BookDto;
import com.example.rsql.dto.BookDtoPage;
import com.example.rsql.model.Book;
import com.example.rsql.repository.BookRepository;
import com.example.rsql.service.BookService;
import com.example.rsql.util.mapstructMapper.BookMapper;
import io.github.perplexhub.rsql.RSQLJPASupport;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static com.example.rsql.util.rsql.CustomPredicate.likeForDigits;

@Service
@RequiredArgsConstructor
@Slf4j
public class BookServiceImpl implements BookService {
    @Value("${rsql.defaultSort}")
    private String defaultSort;
    @Value("${rsql.defaultPageSize}")
    private Integer defaultPageSize;

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    @Override
    public BookDto get(@NonNull Long bookId) {
        return bookMapper.bookToDto(getFromDb(bookId));
    }

    @Override
    public BookDtoPage getAll(String filter, Integer page, Integer size, String sort) {
        BookDtoPage bookDtoPage = new BookDtoPage();

        //pages start from 1 for user
        if (page == null || page < 1) {
            log.info("Invalid page value (page = {}). Set default page value = 0", page);
            page = 0; //default page
        } else --page;

        if (sort == null || sort.isBlank()) {
            log.info("Invalid sort value (sort = {}). Set default sort value = {}", sort, defaultSort);
            sort = defaultSort;    //default sort
        }
        if (size == null || size <= 0) {
            log.info("Invalid size value (size = {}). Set default size  = {}", size, defaultPageSize);
            size = defaultPageSize;  //default size
        }

        bookDtoPage.setFilter(filter);
        bookDtoPage.setSort(sort);
        bookDtoPage.setPage(page + 1);
        bookDtoPage.setElementsOnPage(size);

        try {
            Specification<?> specification;
            if (filter == null || filter.isBlank()) {
                bookDtoPage.setFilter(null);
                specification = RSQLJPASupport.toSort(sort);

            } else specification = RSQLJPASupport
                    .toSpecification(filter, List.of(likeForDigits())).and(RSQLJPASupport.toSort(sort));

            @SuppressWarnings("unchecked")
            Page<Book> bookPage = bookRepository
                    .findAll((Specification<Book>) specification, PageRequest.of(page, size));

            bookDtoPage.setTotalAmount(bookPage.getTotalElements());
            bookDtoPage.setTotalPages(bookPage.getTotalPages());
            bookDtoPage.setBooks(bookPage.stream().map(bookMapper::bookToDto).toList());
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return bookDtoPage;
    }

    @Override
    @Transactional
    public BookDto create(@NonNull BookDto bookDto) {
        Book book = bookMapper.bookDtoToEntity(bookDto);
        return bookMapper.bookToDto(bookRepository.save(book));
    }

    @Override
    public BookDto update(@NonNull Long bookId, @NonNull BookDto bookDto) {
        Book book = getFromDb(bookId);
        bookMapper.updateBookFromDto(bookDto, book);
        return bookMapper.bookToDto(bookRepository.save(book));
    }

    @Override
    @Transactional
    public void delete(@NonNull Long bookId) {
        bookRepository.delete(bookRepository.getReferenceById(bookId));
    }

    private Book getFromDb(@NonNull Long bookId) {
        return bookRepository.findById(bookId).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "В бд нет книги с id: " + bookId));
    }
}
