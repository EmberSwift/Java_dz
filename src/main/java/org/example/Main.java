package org.example;

import java.time.LocalDate;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<User> users = List.of(
                new User(
                        3L, "Иван", "Иванов",
                        new AccountStatus.Active(LocalDate.now().minusDays(1))
                ),
                new User(
                        1L, "Анна", "Смирнова",
                        new AccountStatus.PendingConfirmation("CONFIRM_KEY_123")
                ),
                new User(
                        2L, "Петр", "Петров",
                        new AccountStatus.Blocked("Spam activities", LocalDate.now().minusDays(10))
                )
        );

        // Сервис собирается из компонентов
        AdminUserMapper mapper = new DefaultAdminUserMapper();
        UserAdminService service = new UserAdminService(mapper);

        System.out.println("Все пользователи для админ-панели:");
        UserFilter noFilter = new UserFilter(null, false);
        List<AdminUserView> result = service.prepareUsers(users, noFilter);
        result.forEach(System.out::println);

        System.out.println("\nТолько заблокированные:");
        UserFilter blockedFilter = new UserFilter(null, true);
        List<AdminUserView> blockedResult = service.prepareUsers(users, blockedFilter);
        blockedResult.forEach(System.out::println);
    }
}

