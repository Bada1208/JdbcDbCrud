package com.sysoiev.crud_jdbc_db.util.mappers;

import com.sysoiev.crud_jdbc_db.model.Account;
import com.sysoiev.crud_jdbc_db.model.AccountStatus;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AccountMapper {
    public static ArrayList<Account> mapToAccount(ResultSet resultSet) throws SQLException {
        ArrayList<Account> accountList = new ArrayList<>();
        while (resultSet.next()) {
            Account account = new Account();
            account.setId(resultSet.getLong("id"));
            account.setAccountStatus(AccountStatus.valueOf(resultSet.getString("account_status")));
            accountList.add(account);
        }
        return accountList;
    }
    public static Account mapperAccount(ResultSet resultSet) throws SQLException {
        Account account = new Account();
        account.setId(resultSet.getLong("id"));
        account.setAccountStatus(AccountStatus.valueOf(resultSet.getString("account_status")));
        return account;
    }
}
