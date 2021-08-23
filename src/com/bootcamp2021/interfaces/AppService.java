package com.bootcamp2021.interfaces;

import java.util.Scanner;

public interface AppService {
    void start();

    void loginUser(Scanner scanner);

    void printOptionMenu();

    void evaluateOption(String option);

    void showLoggedInUserUI();
}
