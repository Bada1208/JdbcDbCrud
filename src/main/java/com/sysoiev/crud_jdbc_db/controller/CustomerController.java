package com.sysoiev.crud_jdbc_db.controller;

import com.sysoiev.crud_jdbc_db.model.Customer;
import com.sysoiev.crud_jdbc_db.repository.CustomerRepository;
import com.sysoiev.crud_jdbc_db.repository.impl.JdbcCustomerRepository;

import java.util.List;

public class CustomerController {
    private CustomerRepository customerRepository = new JdbcCustomerRepository();

    public List<Customer> printAll() {
        return customerRepository.getAll();
    }

    public void saveCustomer(Customer newCustomer) {
        customerRepository.save(newCustomer);
    }

    public void deleteCustomer(Long index) {
        customerRepository.deleteById(index);
    }

    public void updateCustomer(Long id, Customer updateCustomer) {
        customerRepository.update(id, updateCustomer);
    }

    public Customer getValueByIndex(Long index) {
        return customerRepository.getById(index);
    }
}
