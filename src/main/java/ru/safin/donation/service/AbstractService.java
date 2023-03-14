package ru.safin.donation.service;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.safin.donation.entity.AbstractEntity;
import ru.safin.donation.repository.CommonRepository;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
public abstract class AbstractService<E extends AbstractEntity, R extends CommonRepository<E>>
        implements CommonCrudService<E> {

    protected final R repository;

    protected abstract String getEntityName();

    @Override
    public E get(Long id) {
        log.info("Getting {} by id={}", getEntityName(), id);

        return repository.findById(id).orElseThrow(RuntimeException::new);
    }

    @Override
    public E create(E obj) {
        log.info("Creating new {} entity {}", getEntityName(), obj);

        return repository.save(obj);
    }

    @Override
    public List<E> getAll() {
        log.info("Getting all {}", getEntityName());

        return repository.findAll();
    }

    @Override
    public E update(E obj) {
        log.info("Updating existing {} entity {}", getEntityName(), obj);

        return repository.save(obj);
    }

    @Override
    public void delete(Long id) {
        log.info("Deleting entity with id={}", id);

        repository.deleteById(id);
    }
}
