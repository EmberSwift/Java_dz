import org.example.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Тестирование DefaultAdminUserMapper")
class UserMapperTests {

    public final AdminUserMapper mapper = new DefaultAdminUserMapper();

    @Test
    @DisplayName("Активный пользователь")
    void shouldMapActiveUserCorrectly() {
        //given
        LocalDate lastLogin = LocalDate.of(2026, 9, 1);
        User user = new User(1L, "Иван", "Иванов",  new AccountStatus.Active(lastLogin));
        //when
        AdminUserView view = mapper.map(user);
        //then
        assertAll(
                () -> assertEquals(1L, view.id()),
                () -> assertEquals("Иван Иванов", view.fullName()),
                () -> assertEquals("Активен (Последний вход: 2026-09-01)", view.statusDescription()),
                () -> assertFalse(view.isActionRequired(), "Активный пользователь не требует действий админа")
        );
    }

    @Test
    @DisplayName("Пользователь, ожидающий подтверждения")
    void shouldMapPendingUserCorrectly() {
        //given
        User user = new User(2L, "Анна", "Смирнова",
                new AccountStatus.PendingConfirmation("TOKEN_123"));
        //when
        AdminUserView view = mapper.map(user);
        //then
        assertAll(
                () -> assertEquals("Ожидает подтверждения (Токен: TOKEN_123)", view.statusDescription()),
                () -> assertTrue(view.isActionRequired(), "Pending-пользователь требует внимания админа")
        );
    }

    @Test
    @DisplayName("Заблокированный пользователь")
    void shouldMapBlockedUserCorrectly() {
        //given
        LocalDate blockedAt = LocalDate.of(2026, 8, 15);
        User user = new User(3L, "Петр", "Петров",
                new AccountStatus.Blocked("Spam", blockedAt));
        //when
        AdminUserView view = mapper.map(user);
        //then
        assertAll(
                () -> assertEquals("Заблокирован [2026-08-15]. Причина: Spam", view.statusDescription()),
                () -> assertFalse(view.isActionRequired(), "Заблокированный пользователь не требует действия")
        );
    }
}
