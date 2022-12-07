package ru.sergalas.FirstSecurity.entities.users.useCase.auth.dto;

import lombok.*;

import javax.validation.constraints.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthDto {

    @NotEmpty(message = "Имя не должно быть пустым")
    @Size(min = 2, max = 100, message ="Имя должно быть от 2 до 100 символов длиной")
    private String username;

    @NotEmpty(message = "Пароль должен быть указан")
    private String password;
}
