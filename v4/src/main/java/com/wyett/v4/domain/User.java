package com.wyett.v4.domain;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

/**
 * @author : wyettLei
 * @date : Created in 2019/11/9 14:01
 * @description: TODO
 */

public class User implements Serializable {
    private BigInteger id;
    private String username;
    private Date birthday;
    private String address;
    private String gender;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", birthday=" + birthday +
                ", address='" + address + '\'' +
                ", gender='" + gender + '\'' +
                '}';
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
