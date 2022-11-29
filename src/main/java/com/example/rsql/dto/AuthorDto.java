package com.example.rsql.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.Instant;
import java.util.Set;

@Getter
@RequiredArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class AuthorDto {
    private final Long id;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private final Instant createdAt;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private final Instant modifiedAt;
    private final String firstName;
    private final String middleName;
    private final String lastName;
    private final Set<BookDto> books;
}
