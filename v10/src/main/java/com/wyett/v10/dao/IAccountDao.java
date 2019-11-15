package com.wyett.v10.dao;


import com.wyett.v10.domain.Account;
import com.wyett.v10.domain.AccountUser;

import java.util.List;

public interface IAccountDao {
    /**
     * get all account
     * @return
     */
    List<Account> findAll();

    /**
     * 查询账户和用户名地址信息
     * @return
     */
    List<AccountUser> findAccountAndUser();
}
