package org.example.infrastructure;

import org.example.application.AdminUserMapper;
import org.example.application.AdminUserView;
import org.example.domain.AccountStatus;
import org.example.domain.User;

//добавлен класс, реализующий указанный интерфейс
public class DefaultAdminUserMapper implements AdminUserMapper {

    @Override
    public AdminUserView map(User user) {
        String fullName = user.firstName() + " " + user.lastName();

        String statusDescription = formatStatus(user.status());
        boolean isActionRequired = checkActionRequired(user.status());

        return new AdminUserView(
                user.id(),
                fullName,
                statusDescription,
                isActionRequired
        );
    }
    // switch статусов вынесен в отдельный метод
    private String formatStatus(AccountStatus status) {
        return switch (status) {
            case AccountStatus.Active active ->
                    "Активен (Последний вход: " + active.lastLoginDate() + ")";
            case AccountStatus.PendingConfirmation pending ->
                    "Ожидает подтверждения (Токен: " + pending.confirmationToken() + ")";
            case AccountStatus.Blocked blocked ->
                    "Заблокирован [" + blocked.blockedAt() + "]. Причина: " + blocked.reason();
        };
    }
    //добавлен отдельный метод для определения, требует ли аккуант внимания админа
    private boolean checkActionRequired(AccountStatus status) {
        return switch (status) {
            case AccountStatus.PendingConfirmation _ -> true;
            case AccountStatus.Blocked _, AccountStatus.Active _ -> false;
        };
    }
}

