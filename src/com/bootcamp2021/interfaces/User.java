package com.bootcamp2021.interfaces;

import java.util.List;

public interface User extends Comparable<User>{
    List<String> getAuthorities();

    boolean isSuperUser();

    String getName();

    String getUsername();

    String getPassword();

    String getRole();

    void setUsername(String username);

    void setPassword(String password);

    void setName(String name);
}
