package com.example.jdbcdemo;

import com.example.jdbcdemo.domain.Account;
import com.example.jdbcdemo.repository.AccountRepository;

/* Example to demonstrate how we can write repository and domain classes and
* do the translations manually without the help of ORM frameworks like JPA */

public class App {
    public static void main(String[] args) throws Exception {
        AccountRepository accountRepository = new AccountRepository();

        Account account1 = accountRepository.findById(1);

        System.out.println("Account details");
        System.out.println("id - " + account1.getId());
        System.out.println("name - " + account1.getName());
        System.out.println("balance - " + account1.getBalance());

        account1.setBalance(9999);

        accountRepository.save(account1);
    }
}
