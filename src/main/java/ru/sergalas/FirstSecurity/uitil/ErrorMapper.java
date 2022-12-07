package ru.sergalas.FirstSecurity.uitil;

import org.springframework.validation.FieldError;

import java.util.List;

public class ErrorMapper {

    public static String map(List<FieldError> errors) {
        StringBuilder stringBuilder = new StringBuilder();
        for(FieldError error: errors){
            stringBuilder.append(error.getField())
                    .append(" - ")
                    .append(error.getDefaultMessage())
                    .append(";");
        }
        return stringBuilder.toString();
    }
}
