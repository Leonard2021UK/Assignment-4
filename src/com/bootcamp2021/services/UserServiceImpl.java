package com.bootcamp2021.services;

import com.bootcamp2021.interfaces.User;
import com.bootcamp2021.interfaces.UserService;
import com.bootcamp2021.models.NormalUserImpl;
import com.bootcamp2021.models.SuperUserImpl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UserServiceImpl implements UserService {

    ArrayList<User> users = new ArrayList<>();

    // Stores the actual logged in user
    User loggedInUser;

    // Tracks the number of invalid logins
    private int remainingLoginAttempts;

    public UserServiceImpl( ) {
        this.remainingLoginAttempts = 5;
        this.loggedInUser = null;
    }

    public User getLoggedInUser() {
        return this.loggedInUser;
    }

    /**
     *
     * @param data - Transforms user data String into User object
     * @return Returns a new user object created from the provided user data String.
     */
    public User createUser(String data){
        String[] userDetails = data.split(",");

        String userType = userDetails[3].trim();

        if (userType.equals("normal_user")){
            return new NormalUserImpl(userDetails);
        }else{
            return new SuperUserImpl(userDetails);
        }

    }

    /**
     * Checks whether the user has more log in attempt
     * @return boolean
     */
    public boolean hasMoreLoginAttempts() {
        return this.remainingLoginAttempts > 0;
    }

    /**
    * Iterates and transforms user data Strings into a list of User objects.
     */
    public void setUsers(List<String> userData){
        for (String userInfo:userData){
            this.users.add(this.createUser(userInfo));
        }
    }

    public List<User> getUsers(){
        return this.users;
    }


    /**
     * Checks user credentials and sets the logged in user and its index in the users ArrayList
     * @param userName - user's username
     * @param password - user's password
     */
    public void authenticateUser(String userName, String password){
        int index = 0;
        for (User user : this.users){

            if (user.getUsername().equalsIgnoreCase(userName) && user.getPassword().equals(password)) {
                // store logged in user

                this.loggedInUser = user;
                this.loggedInUser.setAuthenticatedUserIndex(index);
                break;
            }
            index++;
        }

        // if the authentication is unsuccessful reduce the remaining attempt
        this.remainingLoginAttempts--;
    }

    /**
     * Checks user credentials and sets the logged in user and its index in the users ArrayList
     * @param userName - user's username
     */
    public boolean switchUser(String userName){
        if(getLoggedInUser().isSuperUser()){
            int index = 0;
            for (User user : this.users){

                if (user.getUsername().equalsIgnoreCase(userName)) {
                    // store logged in user
                    this.loggedInUser = user;
                    this.loggedInUser.setAuthenticatedUserIndex(index);
                    return true;
                }
                index++;
            }
        }else{
            System.out.println("You should be logged with Super user privileges!");
        }
        return false;
    }

    public void upDateUsersRecord(){
        // update users record
        this.users.set(this.loggedInUser.getAuthenticatedUserIndex(),this.loggedInUser);
        Collections.sort(this.users);
    }


}
