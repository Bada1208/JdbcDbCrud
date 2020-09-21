package com.sysoiev.crud_jdbc_db.repository.impl;

import com.sysoiev.crud_jdbc_db.model.Specialty;
import com.sysoiev.crud_jdbc_db.repository.SpecialtyRepository;
import com.sysoiev.crud_jdbc_db.util.ConnectionConfig;
import com.sysoiev.crud_jdbc_db.util.mappers.SpecialtyMapper;

import java.sql.*;
import java.util.ArrayList;
import java.util.Optional;

public class JdbcSpecialtyRepository implements SpecialtyRepository {
    @Override
    public Specialty save(Specialty specialty) {
        try (Connection connection = ConnectionConfig.getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO specialties (id,specialty)" +
                    "VALUES (?,?)")) {
                preparedStatement.setLong(1, specialty.getId());
                preparedStatement.setString(2, specialty.getSpecialty());
                preparedStatement.executeUpdate();

            }
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return specialty;
    }

    @Override
    public ArrayList<Specialty> getAll() {
        ArrayList<Specialty> listSpecialties = null;
        try (Connection connection = ConnectionConfig.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM specialties");
            listSpecialties = SpecialtyMapper.mapToSpecialties(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listSpecialties;
    }

    @Override
    public Specialty getById(Long id) {
        Specialty specialty = new Specialty();
        try (Connection connection = ConnectionConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM specialties WHERE Id=?")) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                specialty = SpecialtyMapper.mapperSpecialty(resultSet);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (specialty == null) {
            Optional<Specialty> empty = Optional.empty();
            return empty.orElseThrow(NullPointerException::new);
        } else return specialty;
    }

    @Override
    public void update(Long id, Specialty specialty) {
        try (Connection connection = ConnectionConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("UPDATE specialties SET " +
                     "specialty = ? WHERE id = ?")) {
            preparedStatement.setString(1, specialty.getSpecialty());
            preparedStatement.setLong(2, specialty.getId());
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteById(Long id) {
        try (Connection connection = ConnectionConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM specialties WHERE Id = ?")) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
