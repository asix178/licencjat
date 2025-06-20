package com.app.dao;

import com.app.dbmodel.AdministratorEntity;
import com.app.model.Administrator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdministratorAdapter {
    private final AdministratorRepository administratorRepository;

    public Administrator findAdministratorById(Long id) {
        AdministratorEntity administratorFetched = administratorRepository.getReferenceById(id);
        return administratorFetched.toDomain();
    }

    public Administrator findAdministratorByLogin(String login) {
        Optional<AdministratorEntity> administratorFetched = administratorRepository.findByLogin(login);
        if (administratorFetched.isPresent()) {
            return administratorFetched.get().toDomain();
        } else {
            throw new NoSuchElementException();
        }
    }

    public void register(Administrator administrator) {
        administratorRepository.save(AdministratorEntity.fromDomain(administrator));
    }
}
