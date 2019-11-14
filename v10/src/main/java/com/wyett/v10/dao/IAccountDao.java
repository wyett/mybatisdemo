package com.wyett.v10.dao;


import com.wyett.v10.domain.Account;

import java.util.List;

public interface IAccountDao {
    /**
     * get all account
     * @return
     */
    List<Account> findAll();
}
