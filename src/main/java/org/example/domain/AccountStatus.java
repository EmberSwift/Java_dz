package org.example.domain;

import java.time.LocalDate;

//Sealed Interface для модерируемых статусов пользователей
sealed public interface AccountStatus permits
        AccountStatus.Active,
        AccountStatus.PendingConfirmation,
        AccountStatus.Blocked {

    record Active(LocalDate lastLoginDate) implements AccountStatus {}
    record PendingConfirmation(String confirmationToken) implements AccountStatus {}
    record Blocked(String reason, LocalDate blockedAt) implements AccountStatus {}
}



