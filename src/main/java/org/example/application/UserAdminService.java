package org.example.application;

import org.example.domain.User;
import org.example.domain.UserFilter;
import org.example.domain.UserSpecific;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class UserAdminService {

    private final AdminUserMapper mapper;

    //зависимость передается через конструктор
    public UserAdminService(AdminUserMapper mapper) {
        this.mapper = Objects.requireNonNull(mapper, "Mapper must not be null");
    }

    public List<AdminUserView> prepareUsers(List<User> users, UserFilter filter) {
        UserSpecific specification = UserSpecific.fromFilter(filter);

        return users.stream()
                .filter(Objects::nonNull)
                .filter(specification)
                .sorted(Comparator.comparing(User::id))
                .map(mapper::map)
                .toList();
    }
}