package org.example;

public final class AppContext {
    private static AppContext instance;

    private final AdminUserMapper adminUserMapper;
    private final UserAdminService userAdminService;

    // Закрытый конструктор: сборка всех зависимостей происходит внутри
    private AppContext() {
        this.adminUserMapper = createAdminUserMapper();
        this.userAdminService = createUserAdminService(this.adminUserMapper);
}

    /**
     * Возвращает единственный экземпляр AppContext
     */
    public static synchronized AppContext getInstance() {
        if (instance == null) {
            instance = new AppContext();
        }
        return instance;
    }

    // Фабричные методы для внутренней сборки компонентов
    private AdminUserMapper createAdminUserMapper() {
        return new DefaultAdminUserMapper();
    }

    private UserAdminService createUserAdminService(AdminUserMapper mapper) {
        return new UserAdminService(mapper);
    }

    // Геттеры для предоставления компонентов системе
    public AdminUserMapper getAdminUserMapper() {
        return adminUserMapper;
    }

    public UserAdminService getUserAdminService() {
        return userAdminService;
    }

    /**
     * Метод для сброса контекста
     */
    public static synchronized void reset() {
        instance = null;
    }
}
