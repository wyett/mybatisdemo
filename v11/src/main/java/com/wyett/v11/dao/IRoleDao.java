package com.wyett.v11.dao;

import com.wyett.v11.domain.Role;

import java.util.List;

public interface IRoleDao {

    /**
     * get all roles
     * @return
     */
    List<Role> findAll();
}
