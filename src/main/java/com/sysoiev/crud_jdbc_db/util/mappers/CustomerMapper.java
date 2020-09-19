package com.sysoiev.crud_jdbc_db.util.mappers;

import com.sysoiev.crud_jdbc_db.model.Account;
import com.sysoiev.crud_jdbc_db.model.Customer;
import com.sysoiev.crud_jdbc_db.model.Specialty;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class CustomerMapper {
    public static void mapToCustomer(ResultSet resultSet, Customer customer) throws SQLException {
        while (resultSet.next()) {
            long id = resultSet.getLong("id");
            customer.setId(id);
        }
    }

    public static ArrayList<Customer> mapToCustomer(ResultSet resultSet) throws SQLException {
        ArrayList<Customer> customersList = new ArrayList<>();
        while (resultSet.next()) {
            long customerId = resultSet.getLong("customers.id");
            long accountId = resultSet.getLong("customers.account_id");

            String customer_name = resultSet.getString("name");
            String customer_surname = resultSet.getString("surname");

            Customer customer = new Customer();
            Account account = new Account();
            account.setId(accountId);
            customer.setId(customerId);
            customer.setName(customer_name);
            customer.setSurname(customer_surname);
            customer.setCustomerAccount(account);
            Set<Specialty> specialtySet = new HashSet<>();
            while (resultSet.next()) {
                long customerSpecialtyId = resultSet.getLong("customer_specialties.customer_id");
                long specialtyId = resultSet.getLong("customer_specialties.specialty_id");
                if (customerSpecialtyId == customer.getId()) {
                    specialtySet.add(new Specialty(specialtyId));
                }
            }
            customer.setCustomerSpecialtiesSet(specialtySet);
            customersList.add(customer);
        }
        return customersList;
    }
}
