package com.epam.shop.service.dto;

import com.epam.shop.dao.Role;
import java.util.Objects;

public class UserDto implements Dto{
    private long id;
    private String login;
    private String password;
    private Role role;

    public UserDto() {}

    public UserDto( String login, String password, Role role) {
        this.login = login;
        this.password = password;
        this.role= role;
    }

    public UserDto( String login, String password) {
        this.login = login;
        this.password = password;}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDto userDto = (UserDto) o;
        return id == userDto.id &&
                login.equals(userDto.login) &&
                password.equals(userDto.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login, password);
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                '}';
    }
}
