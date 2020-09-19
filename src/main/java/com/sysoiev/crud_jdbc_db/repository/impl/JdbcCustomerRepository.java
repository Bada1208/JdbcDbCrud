package com.sysoiev.crud_jdbc_db.repository.impl;

import com.sysoiev.crud_jdbc_db.model.Customer;
import com.sysoiev.crud_jdbc_db.repository.CustomerRepository;

import java.util.ArrayList;

public class JdbcCustomerRepository implements CustomerRepository {
    @Override
    public Customer save(Customer data) {
        return null;
    }

    @Override
    public ArrayList<Customer> getAll() {
        return null;
    }

    @Override
    public Customer getById(Long aLong) {
        return null;
    }

    @Override
    public void update(Long aLong, Customer data) {

    }

    @Override
    public void deleteById(Long aLong) {

    }
}
