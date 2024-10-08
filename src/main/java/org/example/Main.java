package org.example;

import org.example.Model.BookExchange;
import org.example.Model.Client;
import org.example.Model.User;
import org.example.Utils.MenuOperations;
import org.example.Utils.ReadWriteOperation;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.example.Utils.MenuOperations.generateUserMenu;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        BookExchange bookExchange = ReadWriteOperation.readFromFile();
        if (bookExchange == null) {bookExchange = new BookExchange();}
       // BookExchange bookExchange = new BookExchange();
        String cmd = "";
        while(!cmd.equals("Q")) {
            System.out.println("""
                    Choose an option:\s
                    U - Work with Users.
                    P - Work with Publications.
                    W - Write to file
                    R - Read from file
                    Q - Exit""");
            cmd = scanner.nextLine();
            switch(cmd) {
                case "U":
                    MenuOperations.generateUserMenu(scanner, bookExchange);
                    break;
                case "P":
                    MenuOperations.generatePublicationsMenu(scanner);
                    break;
                case "Q":
                    System.out.println("Goodbye!");
                    break;
                case "W":
                    ReadWriteOperation.writeToFileAsText(bookExchange.getUsers());
                    break;
                case "R":
                    System.out.println(ReadWriteOperation.getUsersFromFile());
                    break;
                default:
                    break;
            }
        }

    }
}