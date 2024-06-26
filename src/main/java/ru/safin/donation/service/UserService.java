package ru.safin.donation.service;

import ru.safin.donation.dto.UserDto;
import ru.safin.donation.entity.User;
import ru.safin.donation.entity.UserSettings;
//import ru.safin.donation.security.CustomUser;

public interface UserService extends CommonCrudService<User>{
    Long createDefaultUserEnvironment(User user);

    User findByEmail(String email);

    User findByUsername(String username);
}
