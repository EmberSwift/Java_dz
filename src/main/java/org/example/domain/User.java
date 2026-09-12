package org.example.domain;

//Records для моделей данных
public record User(
        Long id,
        String firstName,
        String lastName,
        AccountStatus status
) {}
