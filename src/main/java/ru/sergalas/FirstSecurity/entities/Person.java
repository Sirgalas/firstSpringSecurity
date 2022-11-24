package ru.sergalas.FirstSecurity.entities;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name = "Person")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty(message = "Имя не должно быть пустым")
    @Size(min = 2, max = 100, message ="Имя должно быть от 2 до 100 символов длиной")
    @Column(name="username")
    private String username;

    @Min(value = 1940, message = "Год рождения больше чем 1900")
    @Column(name = "year_of_birth")
    private int yearOfBirth;

    @Column(name = "password")
    private String password;

}
