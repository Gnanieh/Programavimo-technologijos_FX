package org.example.Utils;

import java.util.Scanner;

public class UserMenuOperations {
    public static void createUserMenu(Scanner sc){
        int userCmd = 0;
        while(userCmd != 50){
            System.out.println("""
                    1. - Input Login
                    2. - Choose privileges
                    50. - Exit Program
                    """);

            userCmd = sc.nextInt();
            sc.nextLine();
            switch(userCmd){
                case 1:
                    System.out.print("Enter Username: ");
                    break;
                case 2:
                    System.out.println("Choose privileges");
                    break;
                case 50:
                    break;
                default:
                    break;
            }
        }
    }

    public static void viewUserMenu(Scanner sc){
        int userCmd = 0;
        while(userCmd != 50){
            System.out.println("""
                    SHOWING USERS
                    """);
        }
    }
}
