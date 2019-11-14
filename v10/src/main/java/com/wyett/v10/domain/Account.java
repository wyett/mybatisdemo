package com.wyett.v10.domain;

import java.math.BigInteger;

/**
 * @author : wyettLei
 * @date : Created in 2019/11/14 19:28
 * @description: TODO
 */

public class Account {
    private BigInteger id;
    private BigInteger uid;
    private double money;

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public BigInteger getUid() {
        return uid;
    }

    public void setUid(BigInteger uid) {
        this.uid = uid;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", uid=" + uid +
                ", money=" + money +
                '}';
    }
}
