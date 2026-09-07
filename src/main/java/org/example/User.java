package org.example;

//Records для моделей данных
public record User(
        Long id,
        String firstName,
        String lastName,
        AccountStatus status
) {}
