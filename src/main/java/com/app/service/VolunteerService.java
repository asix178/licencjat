package com.app.service;

import com.app.controller.request.VolunteerRequest;
import com.app.dao.VolunteerAdapter;
import com.app.model.Volunteer;
import com.app.security.Bcrypt;
import com.password4j.Password;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class VolunteerService {
    private final VolunteerAdapter volunteerAdapter;

    public Boolean login(VolunteerRequest volunteerRequest) {
        try {
            Volunteer volunteer = volunteerAdapter.findVolunteerByName(volunteerRequest.getName());
            return Password.check(volunteerRequest.getPassword(), volunteer.getPassword())
                    .with(Bcrypt.getBcryptFunction());
        } catch (Exception e) {
            return false;
        }
    }

    public Boolean register(VolunteerRequest volunteerRequest) {
        try {
            volunteerAdapter.findVolunteerByName(volunteerRequest.getName());
        } catch (NoSuchElementException e) {
            Volunteer volunteer = new Volunteer(UUID.randomUUID(), volunteerRequest.getName()
                    , Password.hash(volunteerRequest.getPassword()).with(Bcrypt.getBcryptFunction()).getResult());
            volunteerAdapter.register(volunteer);
            return true;
        }
        return false;
    }
}
