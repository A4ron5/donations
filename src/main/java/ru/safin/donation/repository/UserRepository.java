package ru.safin.donation.repository;

import ru.safin.donation.entity.User;

public interface UserRepository extends  CommonRepository<User> {
    User findByEmail(String email);
}
