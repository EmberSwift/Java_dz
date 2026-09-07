package org.example;

//отображение в админ-панели
public record AdminUserView(
        Long id,
        String fullName,
        String statusDescription,
        boolean isActionRequired
) {}
