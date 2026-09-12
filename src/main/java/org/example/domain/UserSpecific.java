package org.example.domain;

import java.util.function.Predicate;

//Добавлен предикат для фильтрации
@FunctionalInterface
public interface UserSpecific extends Predicate<User> {

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
