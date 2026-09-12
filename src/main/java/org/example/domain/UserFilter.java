package org.example.domain;

// Настройки фильтрации для поиска пользователей
public record UserFilter(
        String nameSearchQuery,
        Boolean onlyBlocked
) {}
