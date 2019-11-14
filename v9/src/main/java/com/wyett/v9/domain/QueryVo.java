package com.wyett.v9.domain;

import java.util.List;

/**
 * @author : wyettLei
 * @date : Created in 2019/11/14 18:08
 * @description: TODO
 */

public class QueryVo {
    private User user;
    private List<Integer> ids;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Integer> getIds() {
        return ids;
    }

    public void setIds(List<Integer> ids) {
        this.ids = ids;
    }
}
