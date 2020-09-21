package com.sysoiev.crud_jdbc_db.repository.impl;

import com.sysoiev.crud_jdbc_db.model.Customer;
import com.sysoiev.crud_jdbc_db.model.Specialty;
import com.sysoiev.crud_jdbc_db.repository.CustomerRepository;
import com.sysoiev.crud_jdbc_db.util.ConnectionConfig;
import com.sysoiev.crud_jdbc_db.util.mappers.CustomerMapper;

import java.sql.*;
import java.util.ArrayList;
import java.util.Optional;

public class JdbcCustomerRepository implements CustomerRepository {
    @Override
    public Customer save(Customer customer) {
        try (Connection connection = ConnectionConfig.getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO customers (id,name,surname,account_id)" +
                    "VALUES (?,?,?,?)")) {
                preparedStatement.setLong(1, customer.getId());
                preparedStatement.setString(2, customer.getName());
                preparedStatement.setString(3, customer.getSurname());
                preparedStatement.setLong(4, customer.getCustomerAccount().getId());
                preparedStatement.executeUpdate();
            }
            ArrayList<Specialty> customerSpecialties = new ArrayList<>(customer.getCustomerSpecialtiesSet());
            for (int i = 0; i < customerSpecialties.size(); i++) {
                try (PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO customer_specialties(customer_id, specialty_id) VALUES (?, ?);")) {
                    preparedStatement.setLong(1, customer.getId());
                    preparedStatement.setLong(2, customerSpecialties.get(i).getId());
                    preparedStatement.executeUpdate();
                }
                connection.commit();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customer;
    }

    @Override
    public ArrayList<Customer> getAll() {
        ArrayList<Customer> listCustomers = null;
        try (Connection connection = ConnectionConfig.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("select customers.id,\n" +
                    "       customers.name,\n" +
                    "       customers.surname,\n" +
                    "       customer_specialties.specialty_id,\n" +
                    "       accounts.id\n" +
                    "from customers\n" +
                    "         join customer_specialties on customer_specialties.customer_id = customers.id\n" +
                    "         join specialties on customer_specialties.specialty_id = specialties.id\n" +
                    "         join accounts on customers.account_id = accounts.id;");
            listCustomers = CustomerMapper.mapToCustomer(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listCustomers;
    }

    @Override
    public Customer getById(Long id) {
        Customer customer = new Customer();
        try (Connection connection = ConnectionConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("select customers.id,\n" +
                     "       customers.name,\n" +
                     "       customers.surname,\n" +
                     "       customer_specialties.specialty_id,\n" +
                     "       accounts.id\n" +
                     "from customers\n" +
                     "         join customer_specialties on customer_specialties.customer_id = customers.id\n" +
                     "         join specialties on customer_specialties.specialty_id = specialties.id\n" +
                     "         join accounts on customers.account_id = accounts.id where customers.id=?")) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                customer = CustomerMapper.mapperCustomer(resultSet);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (customer == null) {
            Optional<Customer> empty = Optional.empty();
            return empty.orElseThrow(NullPointerException::new);
        } else return customer;
    }

    @Override
    public void update(Long id, Customer customer) {
        try (Connection connection = ConnectionConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("UPDATE customers SET name = ?,surname=?  WHERE id = ?;")) {
            preparedStatement.setString(1, customer.getName());
            preparedStatement.setString(2, customer.getSurname());
            preparedStatement.setLong(3, id);
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteById(Long id) {
        try (Connection connection = ConnectionConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM customers WHERE Id = ?")) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
