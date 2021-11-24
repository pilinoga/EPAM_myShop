package com.epam.shop.dao;

import java.util.Arrays;

public enum Role {
    ADMIN(1, "admin"),
    CUSTOMER(2, "customer"),
    UNKNOWN(3, "unknown");

    private final Integer idRole;
    private final String name;


    Role(Integer idRole, String name){
        this.idRole = idRole;
        this.name = name;
    }
    public Integer getIdRole() {
        return idRole;
    }

    public String getName() {
        return name;
    }

    public static Role getRole(Integer idRole){
        return Arrays.stream(Role.values())
                .filter(role->role.getIdRole().equals(idRole))
                .findFirst().orElse(Role.UNKNOWN);
       }
    }


