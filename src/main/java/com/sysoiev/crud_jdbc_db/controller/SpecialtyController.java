package com.sysoiev.crud_jdbc_db.controller;

import com.sysoiev.crud_jdbc_db.model.Specialty;
import com.sysoiev.crud_jdbc_db.repository.SpecialtyRepository;
import com.sysoiev.crud_jdbc_db.repository.impl.JdbcSpecialtyRepository;

import java.util.List;

public class SpecialtyController {
    private SpecialtyRepository specialtyRepository = new JdbcSpecialtyRepository();


    public List<Specialty> printAll() {
        return specialtyRepository.getAll();
    }

    public void saveSpecialty(Specialty newSpecialty) {
        specialtyRepository.save(newSpecialty);
    }

    public void deleteSpecialty(Long index) {
        specialtyRepository.deleteById(index);
    }

    public void updateSpecialty(Long id, Specialty updateSpecialty) {
        specialtyRepository.update(id, updateSpecialty);

    }

    public Specialty getValueByIndex(Long index) {
        return specialtyRepository.getById(index);
    }
}
