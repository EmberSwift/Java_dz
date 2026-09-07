package org.example;

// Настройки фильтрации для поиска пользователей
public record UserFilter(
        String nameSearchQuery,
        Boolean onlyBlocked
) {}
