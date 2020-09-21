package com.sysoiev.crud_jdbc_db.repository.impl;

import com.sysoiev.crud_jdbc_db.model.Account;
import com.sysoiev.crud_jdbc_db.repository.AccountRepository;
import com.sysoiev.crud_jdbc_db.util.ConnectionConfig;
import com.sysoiev.crud_jdbc_db.util.mappers.AccountMapper;

import java.sql.*;
import java.util.ArrayList;

public class JdbcAccountRepository implements AccountRepository {
    @Override
    public Account save(Account account) {
        try (Connection connection = ConnectionConfig.getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO accounts (id)" +
                    "VALUES (?)")) {
                preparedStatement.setLong(1, account.getId());
                preparedStatement.executeUpdate();
                connection.commit();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return account;
    }

    @Override
    public ArrayList<Account> getAll() {
        ArrayList<Account> listAccounts = null;
        try (Connection connection = ConnectionConfig.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM accounts");
            listAccounts = AccountMapper.mapToAccount(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listAccounts;
    }

    @Override
    public Account getById(Long aLong) {
        Account account = new Account();
        try (Connection connection = ConnectionConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM accounts WHERE Id=?")) {
            preparedStatement.setLong(1, aLong);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                account = AccountMapper.mapperAccount(resultSet);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return account;
    }

    @Override
    public void update(Long id, Account account) {
        try (Connection connection = ConnectionConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("UPDATE accounts SET " +
                     "account_status = ? WHERE Id = ?")) {
            preparedStatement.setString(1, account.getAccountStatus().name());
            preparedStatement.setLong(2, id);
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteById(Long id) {
        try (Connection connection = ConnectionConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM accounts WHERE Id = ?")) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
