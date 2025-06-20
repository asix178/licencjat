package com.app.dao;

import com.app.dbmodel.VolunteerEntity;
import com.app.model.Volunteer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VolunteerAdapter {
    private final VolunteerRepository volunteerRepository;

    public Volunteer findVolunteerById(Long id) {
        VolunteerEntity volunteerFetched = volunteerRepository.getReferenceById(id);
        return volunteerFetched.toDomain();
    }

    public Volunteer findVolunteerByName(String name) {
        Optional<VolunteerEntity> volunteerFetched = volunteerRepository.findByName(name);
        if (volunteerFetched.isPresent()) {
            return volunteerFetched.get().toDomain();
        } else {
            throw new NoSuchElementException();
        }
    }

    public void register(Volunteer volunteer) {
        volunteerRepository.save(VolunteerEntity.fromDomain(volunteer));
    }
}
