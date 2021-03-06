package com.onur.spring.implementation;

import com.onur.spring.domain.Auditorium;
import com.onur.spring.service.AuditoriumService;

import java.util.Set;

public class AuditoriumImpl implements AuditoriumService {

    public Set<Auditorium> auditoriumSet;

    public AuditoriumImpl(Set<Auditorium> auditoriumSet) {
        this.auditoriumSet = auditoriumSet;
    }

    @Override
    public Set<Auditorium> getAll() {
        return auditoriumSet;
    }

    @Override
    public Auditorium getByName(String name) {
        for (Auditorium auditorium : auditoriumSet){
            if(auditorium.getName().equalsIgnoreCase(name)){
                return auditorium;
            }
        }
        return null;
    }

}
