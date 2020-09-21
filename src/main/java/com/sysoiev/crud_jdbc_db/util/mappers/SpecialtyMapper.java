package com.sysoiev.crud_jdbc_db.util.mappers;

import com.sysoiev.crud_jdbc_db.model.Specialty;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SpecialtyMapper {

    public static ArrayList<Specialty> mapToSpecialties(ResultSet resultSet) throws SQLException {
        ArrayList<Specialty> specialtyList = new ArrayList<>();
        while (resultSet.next()) {
            Specialty specialty = new Specialty();
            specialty.setId(resultSet.getLong("id"));
            specialty.setSpecialty(resultSet.getString("specialty"));
            specialtyList.add(specialty);
        }
        return specialtyList;
    }

    public static Specialty mapperSpecialty(ResultSet resultSet) throws SQLException {
        Specialty specialty = new Specialty();
        specialty.setId(resultSet.getLong("Id"));
        specialty.setSpecialty(resultSet.getString("Specialty"));
        return specialty;
    }
}
