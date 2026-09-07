package org.example;

//добавлен интерфейс для сопоставления
public interface AdminUserMapper {
    AdminUserView map(User user);
}
