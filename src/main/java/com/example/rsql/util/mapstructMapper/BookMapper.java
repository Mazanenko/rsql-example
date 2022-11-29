package com.example.rsql.util.mapstructMapper;

import com.example.rsql.dto.AuthorDto;
import com.example.rsql.dto.BookDto;
import com.example.rsql.model.Author;
import com.example.rsql.model.Book;
import org.mapstruct.*;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface BookMapper {

    @Named("bookToDto")
    @Mapping(target = "author", qualifiedByName = "attachedAuthorToDto")
    BookDto bookToDto(Book book);

    @Named("bookDtoToEntity")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "modifiedAt", ignore = true)
    @Mapping(target = "author", qualifiedByName = "attachedAuthorDtoToEntity")
    Book bookDtoToEntity(BookDto bookDto);

    @Named("updateBookFromDto")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "modifiedAt", ignore = true)
    @Mapping(target = "author", qualifiedByName = "attachedAuthorDtoToEntity")
    void updateBookFromDto(BookDto bookDto, @MappingTarget Book bookForUpdate);

    @Named("attachedAuthorDtoToEntity")
    @Mapping(target = "books", ignore = true)
    Author attachedAuthorDtoToEntity(AuthorDto authorDto);

    @Named("attachedAuthorToDto")
    @Mapping(target = "books", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "modifiedAt", ignore = true)
    AuthorDto attachedAuthorToDto(Author author);
}
