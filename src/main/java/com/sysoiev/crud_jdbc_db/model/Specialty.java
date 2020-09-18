package com.sysoiev.crud_jdbc_db.model;

public class Specialty {
    private Long id;
    private String specialty;

    public Specialty() {
    }

    public Specialty(Long id) {
        this.id = id;
    }

    public Specialty(Long id, String specialty) {
        this.id = id;
        this.specialty = specialty;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    @Override
    public String toString() {
        return id + " " + specialty;
    }
}
