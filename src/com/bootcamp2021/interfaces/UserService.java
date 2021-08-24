package com.bootcamp2021.interfaces;


public interface UserService {
    void authenticateUser(String userName, String password);

    boolean switchUser(String userName);

    boolean hasMoreLoginAttempts();

    User createUser(String data);

    void removeCurrentLoggedInUser();
}
