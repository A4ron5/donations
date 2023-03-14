package ru.safin.donation.service;

import ru.safin.donation.entity.AbstractEntity;

import java.util.List;

public interface CommonCrudService<E extends AbstractEntity> {
    E get(Long id);

    E create(E obj);

    List<E> getAll();

    E update(E obj);

    void delete(Long id);
}
