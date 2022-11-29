package com.example.rsql.util;

import lombok.experimental.UtilityClass;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@UtilityClass
public final class ValidationUtils {

    public static void checkId(Long id) {
        if (id == null || id < 1L) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Передан невалидный id. Должен быть больше 0.");
        }
    }
}
