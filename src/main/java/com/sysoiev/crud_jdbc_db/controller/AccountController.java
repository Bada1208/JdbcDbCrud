package com.sysoiev.crud_jdbc_db.controller;

import com.sysoiev.crud_jdbc_db.model.Account;
import com.sysoiev.crud_jdbc_db.repository.AccountRepository;
import com.sysoiev.crud_jdbc_db.repository.impl.JdbcAccountRepository;

import java.util.List;

public class AccountController {
    private AccountRepository accountRepository = new JdbcAccountRepository();


    public List<Account> printAll() {
        return accountRepository.getAll();
    }

    public void saveAccount(Account newAccount) {
        accountRepository.save(newAccount);
    }

    public void deleteAccount(Long index) {
        accountRepository.deleteById(index);
    }

    public void updateAccount(Long id, Account updateAccount) {
        accountRepository.update(id, updateAccount);

    }

    public Account getValueByIndex(Long index) {
        return accountRepository.getById(index);
    }
}
