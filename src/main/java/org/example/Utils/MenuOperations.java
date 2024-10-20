package org.example.Utils;

import org.example.Model.Client;
import org.example.Model.Admin;
import org.example.Model.User;
import org.example.Model.BookExchange;

import java.time.LocalDate;
import java.util.Scanner;

public class MenuOperations
{
    public static void generateUserMenu(Scanner sc, BookExchange bookExchange){
        int userCmd = 0;
        int i = 1;
        String consoleInput = "";
        while(userCmd != 50){
            System.out.println("""
                    1 - create
                    2 - update
                    3 - read
                    4 - read all
                    5 - delete
                50. - Exit Program
                """);
            userCmd = sc.nextInt();
            sc.nextLine();
            switch(userCmd){
                case 1:

                    System.out.println("Which type? C/A");
                    consoleInput = sc.nextLine();
                    if (consoleInput.equals("C")){
                        System.out.println("Enter data: login;psw;name;surname");
                        consoleInput = sc.nextLine();
                        String[] info = consoleInput.split(";");
                        Client client = new Client(i,info[0],info[1],info[2],info[3]);
                        bookExchange.getUsers().add(client);
                        System.out.println(client.toString());
                    ++i;
                    }
                    else if (consoleInput.equals("A")){
                        System.out.println("Enter data: login;psw;name;surname;phoneNumber");
                        consoleInput = sc.nextLine();
                        String[] info = consoleInput.split(";");
                        Admin admin = new Admin(01,info[0],info[1],info[2],info[3], info[4]);
                        System.out.println(admin.toString());
                        bookExchange.getUsers().add(admin);
                    }
                    else
                    {
                        System.out.println("Invalid input");
                    }
                    //UserMenuOperations.createUserMenu(sc);
                    break;
                case 2:
                    System.out.println("Enter login");
                    consoleInput = sc.nextLine();
                    for (User u : bookExchange.getUsers())
                        if (u.getLogin().equals(consoleInput)) {
                            System.out.println("Enter name");
                            consoleInput = sc.nextLine();
                            u.setName(consoleInput);
                        }
                    break;
                case 3:
                    System.out.println("Enter login: ");
                    consoleInput = sc.nextLine();
                    for (User u : bookExchange.getUsers()) {
                        if (u.getLogin().equals(consoleInput))
                            System.out.println(u);
                        System.out.println("Enter new name: ");
                        consoleInput = sc.nextLine();
                        u.setName(consoleInput);
                    }
                    break;
                case 4:
                    for (User u : bookExchange.getUsers()){
                        System.out.println(u);
                    }
                    break;
                default:
                    break;
            }
        }
    }


    public static void generatePublicationsMenu(Scanner sc){
        int publicationCmd = 0;
        while(publicationCmd != 50){
            System.out.println(
                    """
                    1. - Create Publication
                    2. - View Publications
                    3. - Edit Publication
                    50. - Exit Program
                    """
            );
            switch(publicationCmd){
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 50:
                    break;
                default:
                    break;
            }
            publicationCmd = sc.nextInt();
            sc.nextLine();
        }

    }
}
