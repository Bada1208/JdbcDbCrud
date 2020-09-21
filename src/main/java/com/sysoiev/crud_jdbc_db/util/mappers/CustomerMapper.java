package com.sysoiev.crud_jdbc_db.util.mappers;

import com.sysoiev.crud_jdbc_db.model.Account;
import com.sysoiev.crud_jdbc_db.model.Customer;
import com.sysoiev.crud_jdbc_db.model.Specialty;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerMapper {

    public static ArrayList<Customer> mapToCustomer(ResultSet resultSet) throws SQLException {
        ArrayList<Customer> customersList = new ArrayList<>();
        while (resultSet.next()) {
            long customerId = resultSet.getLong("customers.id");
            long accountId = resultSet.getLong("accounts.id");

            String customer_name = resultSet.getString("name");
            String customer_surname = resultSet.getString("surname");
            long specialtyId = resultSet.getLong("customer_specialties.specialty_id");

            Customer customer = new Customer();
            Account account = new Account();
            Specialty specialty=new Specialty();
            account.setId(accountId);
            customer.setId(customerId);
            customer.setName(customer_name);
            customer.setSurname(customer_surname);
            customer.setCustomerAccount(account);
            specialty.setId(specialtyId);
            customer.setCustomerSpecialties(specialty);
            customersList.add(customer);
        }
        return customersList;
    }
    public static Customer mapperCustomer(ResultSet resultSet) throws SQLException {
        long customerId = resultSet.getLong("customers.id");
        long accountId = resultSet.getLong("accounts.id");

        String customer_name = resultSet.getString("name");
        String customer_surname = resultSet.getString("surname");
        long specialtyId = resultSet.getLong("customer_specialties.specialty_id");

        Customer customer = new Customer();
        Account account = new Account();
        Specialty specialty=new Specialty();
        account.setId(accountId);
        customer.setId(customerId);
        customer.setName(customer_name);
        customer.setSurname(customer_surname);
        customer.setCustomerAccount(account);
        specialty.setId(specialtyId);
        customer.setCustomerSpecialties(specialty);
        return customer;
    }
}
