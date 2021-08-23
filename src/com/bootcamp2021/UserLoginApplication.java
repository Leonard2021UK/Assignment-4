package com.bootcamp2021;

import com.bootcamp2021.services.AppServiceImpl;

public class  UserLoginApplication {

    public static void main(String[] args) {

        // initiating an application object
        AppServiceImpl application = new AppServiceImpl();

        application.start();

    }
}
