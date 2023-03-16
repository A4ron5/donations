package ru.safin.donation.repository;

import org.springframework.stereotype.Repository;
import ru.safin.donation.entity.Donate;

import java.util.List;

@Repository
public interface DonateRepository extends CommonRepository<Donate> {

    List<Donate> findAllByUserId(Long userId);

    List<Donate> findAllByUserIdAndFromUser(Long userId, String fromUser);
}
