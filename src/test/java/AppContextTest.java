import org.example.application.AdminUserMapper;
import org.example.AppContext;
import org.example.infrastructure.DefaultAdminUserMapper;
import org.example.application.UserAdminService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Тестирование контекста приложения AppContext")
class AppContextTest {

    @BeforeEach
    @AfterEach
    void resetContext() {
        // Гарантируем чистый контекст перед и после каждого теста
        AppContext.reset();
    }

    @Test
    @DisplayName("Метод getInstance всегда возвращает один и тот же экземпляр (Singleton)")
    void shouldReturnSameInstance() {
        AppContext instance1 = AppContext.getInstance();
        AppContext instance2 = AppContext.getInstance();

        assertNotNull(instance1, "Экземпляр контекста не должен быть null");
        assertSame(instance1, instance2, "Повторные вызовы getInstance должны возвращать один и тот же объект");
    }

    @Test
    @DisplayName("Метод reset создаёт новый экземпляр при следующем вызове getInstance")
    void shouldCreateNewInstanceAfterReset() {
        AppContext instanceBeforeReset = AppContext.getInstance();

        AppContext.reset();

        AppContext instanceAfterReset = AppContext.getInstance();

        assertNotSame(instanceBeforeReset, instanceAfterReset, "После сброса должен создаваться новый экземпляр контекста");
    }

    @Test
    @DisplayName("Контекст правильно инициализирует и предоставляет сервисы")
    void shouldProvideInitializedBeans() {
        AppContext context = AppContext.getInstance();

        AdminUserMapper mapper = context.getAdminUserMapper();
        UserAdminService service = context.getUserAdminService();

        assertAll(
                () -> assertNotNull(mapper, "AdminUserMapper должен быть проинициализирован"),
                () -> assertInstanceOf(DefaultAdminUserMapper.class, mapper, "Маппер должен быть реализацией DefaultAdminUserMapper"),
                () -> assertNotNull(service, "UserAdminService должен быть проинициализирован")
        );
    }

    @Test
    @DisplayName("Компоненты внутри контекста являются Singleton-экземплярами")
    void shouldReturnSameBeanInstances() {
        AppContext context = AppContext.getInstance();

        UserAdminService service1 = context.getUserAdminService();
        UserAdminService service2 = context.getUserAdminService();

        assertSame(service1, service2, "Контекст должен возвращать один и тот же экземпляр сервиса");
    }
}