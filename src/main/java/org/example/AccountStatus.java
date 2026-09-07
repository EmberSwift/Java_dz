package org.example;

import java.time.LocalDate;
import java.util.function.Predicate;

//Sealed Interface для модерируемых статусов пользователей
sealed public interface AccountStatus permits
        AccountStatus.Active,
        AccountStatus.PendingConfirmation,
        AccountStatus.Blocked {

    record Active(LocalDate lastLoginDate) implements AccountStatus {}
    record PendingConfirmation(String confirmationToken) implements AccountStatus {}
    record Blocked(String reason, LocalDate blockedAt) implements AccountStatus {}
}


/*
В исходном коде класс UserAdminService совмещал в себе несколько обязанностей:
фильтрацию данных, бизнес-логику форматирования статусов и сопоставление объектов
 */

//Добавлен предикат для фильтрации
@FunctionalInterface
interface UserSpecific extends Predicate<User> {

    static UserSpecific isBlockedOnly(Boolean onlyBlocked) {
        if (onlyBlocked == null || !onlyBlocked) {
            return user -> true;
        }
        return user -> user.status() instanceof AccountStatus.Blocked;
    }

    static UserSpecific fromFilter(UserFilter filter) {
        return user -> isBlockedOnly(filter.onlyBlocked()).test(user);
    }
}

