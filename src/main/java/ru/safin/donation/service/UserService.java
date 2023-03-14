package ru.safin.donation.service;

import ru.safin.donation.dto.UserDto;
import ru.safin.donation.entity.User;
import ru.safin.donation.entity.UserSettings;

public interface UserService extends CommonCrudService<User>{

    void createDefaultUserEnvironment(Long userId);
}
