package ru.sergalas.FirstSecurity.entities.users.useCase.update.dto;

import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserUpdateDto {

    @NotEmpty(message = "Имя не должно быть пустым")
    @Size(min = 2, max = 100, message ="Имя должно быть от 2 до 100 символов длиной")
    private String username;

    @Min(value = 1940, message = "Год рождения больше чем 1900")
    private int yearOfBirth;

    private String role;
}
