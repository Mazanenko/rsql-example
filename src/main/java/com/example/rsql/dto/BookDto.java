package com.example.rsql.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;

@Getter
@RequiredArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class BookDto {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private final Long id;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private final Instant createdAt;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private final Instant modifiedAt;
    private final String name;
    private final String genre;
    private final String description;
    private final BigDecimal price;
    private final AuthorDto author;
}
