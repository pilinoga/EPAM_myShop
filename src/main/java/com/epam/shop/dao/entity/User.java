package com.epam.shop.dao.entity;

import java.util.Objects;

public class User implements Entity {
    private long id;
    private Integer roleId;
    private String login;
    private String password;

    public User(){}

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public User(Integer roleId, String login, String password) {
        this.roleId = roleId;
        this.login = login;
        this.password = password;
    }

    public User(long id, Integer roleId, String login, String password) {
        this.id = id;
        this.roleId = roleId;
        this.login = login;
        this.password = password;
    }

    @Override
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id &&
                roleId.equals(user.roleId) &&
                Objects.equals(login, user.login) &&
                Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {
        final int hashFactor = 31;
        int hash = 1;
        hash = hashFactor * hash + (int) id;
        hash = hashFactor * hash + (int) roleId;
        hash = hashFactor * hash + (login == null ? 0 : login.hashCode());
        hash = hashFactor * hash + (password == null ? 0 : password.hashCode());
        return hash;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", roleId=" + roleId +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}

