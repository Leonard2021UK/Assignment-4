package com.bootcamp2021.models;

import com.bootcamp2021.interfaces.User;

/**
 * User class - stores user information
 */
public class BaseUser implements Comparable<User> {

    private String username;
    private String password;
    private String name;
    protected String role;
    /**
     *
     * @param userInfo - User information from the file as an Array of strings
     */
    public BaseUser(String[] userInfo) {
        this.username = userInfo[0].trim();
        this.password = userInfo[1].trim();
        this.name = userInfo[2].trim();
        this.role = userInfo[3].trim();
    }


    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getRole() {
        return role;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @param that - the object what we want to compare
     * @return - int representation of the comparison result
     */
    @Override
    public int compareTo(User that) {
        //if roles are the same then compare using usernames
        if(that.getRole().compareTo(this.role) == 0){
            return (this.username.toUpperCase().compareTo(that.getUsername().toUpperCase()));
        }else{
            return that.getRole().compareTo(this.role);
        }
    }

    @Override
    public String toString() {
        return username + ", " + password + ", " + name + ", " + role;
    }


}
