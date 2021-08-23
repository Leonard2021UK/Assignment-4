package com.bootcamp2021.models;

import com.bootcamp2021.interfaces.User;

import java.util.ArrayList;
import java.util.List;

public class NormalUserImpl extends BaseUser implements User {


    public NormalUserImpl(String[] userInfo) {

        super(userInfo);

    }

    /**
     * Provides the graphical user interface for the user.
     * @return - list of available options based on user type
     */
    public List<String> getAuthorities(){

        ArrayList<String> userOption = new ArrayList<>();

        userOption.add("(1) Update username");
        userOption.add("(2) Update password");
        userOption.add("(3) Update name");
        userOption.add("(4) Exit");

        return userOption;
    }

    public boolean isSuperUser(){
        return false;
    }


}
