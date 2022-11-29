package com.example.rsql.util.rsql;

import cz.jirutka.rsql.parser.ast.ComparisonOperator;
import io.github.perplexhub.rsql.RSQLCustomPredicate;
import lombok.experimental.UtilityClass;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;


@UtilityClass
public final class CustomPredicate {

    public static RSQLCustomPredicate<String> likeForDigits() {
        return new RSQLCustomPredicate<>(new ComparisonOperator("=digitLike="), String.class, input -> {
            Class<?> inputClass = input.getPath().getJavaType();

            if (!inputIsDigit(inputClass)) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                        "Фильтр digitLike применим только к числовым значениям.");
            }
            return input.getCriteriaBuilder()
                    .like(input.getPath().as(String.class),
                            input.getArguments().get(0).toString().replace("*", "%"));
        });
    }

    private static boolean inputIsDigit(Class<?> inputClass) {
        return Number.class.isAssignableFrom(inputClass)
                || int.class.isAssignableFrom(inputClass)
                || long.class.isAssignableFrom(inputClass)
                || double.class.isAssignableFrom(inputClass)
                || float.class.isAssignableFrom(inputClass);
    }
}
