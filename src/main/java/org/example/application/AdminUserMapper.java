package org.example.application;

import org.example.domain.User;

//добавлен интерфейс для сопоставления
public interface AdminUserMapper {
    AdminUserView map(User user);
}
