package com.khalifa;

import java.util.Random;
import java.util.Scanner;

/**
 * @author Khalifa Fumo
 * @version 1.0
 * @since 2020
 */

class BankingApp {
    Scanner inp = new Scanner(System.in);

    //declaration of global variables
    String name;
    private int account_num;
    private String password;
    private int balance = 0;
    int amount;
    private String last_transaction;

    //constructor
    BankingApp(String n, int acc_num) {
        this.name = n;
        this.account_num = acc_num;
    }

    //checking if account number is valid
    boolean accountNumValid(int account_num) {
        int account_num_length = 0;

        while (account_num != 0) {
            account_num /= 10;
            ++account_num_length;
        }
        return (account_num_length == 8 || account_num_length == 0);
    }

    //checking if password is invalid
    boolean passwordNotValid(String pass){
        int pass_length = pass.length();
        boolean hasNum = false;

        char[] chars = pass.toCharArray();
        for (char c : chars){
            if(Character.isDigit(c))
                hasNum = true;
        }

        return (pass_length < 8 || !hasNum);
    }

    //checking do while exit condition
    boolean checkSwitchExit(int response) {
        return (response == 8);
    }

    //Account creation
    void createAccount() {
        Random random = new Random();
        account_num = random.nextInt(99999999 - 10000000) + 10000000;

        System.out.println("Please enter your new password and make sure you remember it");
        System.out.println("The password must have 8 or more characters and must contain at least one digit");
        password = inp.nextLine();

        while (passwordNotValid(password)) {
            System.out.println("Please ensure that the password has 8 or more characters and contains at least one digit");
            password = inp.nextLine();
        }

        System.out.println("Please confirm your new password");
        String confirm_pass = inp.nextLine();

        while (!confirm_pass.equals(password)) {
            System.out.println("Passwords entered do not match. Try again");
            confirm_pass = inp.nextLine();
        }

        System.out.println("You have successfully created a new account");
        System.out.println("Your account number is " + account_num);
        System.out.println("Your password is " + password);

    }


    //Balance check
    void checkBalance() {
        System.out.println("Your balance is " + this.balance);
    }


    //Last transaction check
    void checkLastTransaction() {
        System.out.println("Last transaction: " + last_transaction);
    }


    //Deposit function
    void deposit(int amount) {
        boolean successful = true;
        while (amount <= 0) {
            successful = false;
            System.out.println("Invalid amount entered. Please try again");
            amount = Integer.parseInt(inp.nextLine());
        }

        if (successful)
            System.out.println("You have successfully deposited " + amount);

        this.balance += amount;
        last_transaction = ("You deposited " + amount);
    }


    //Withdraw function
    void withdraw(int amount) {
        boolean successful = true;
        while (amount <= 0) {
            successful = false;
            System.out.println("Invalid amount entered. Please try again");
            amount = Integer.parseInt(inp.nextLine());
        }
        while (amount > balance) {
            successful = false;
            System.out.println("You have insufficient balance to withdraw. Please try again");
            amount = Integer.parseInt(inp.nextLine());
        }

        if (successful)
            System.out.println("You have successfully withdrawn " + amount);


        this.balance -= amount;
        last_transaction = ("You withdrew " + amount);
    }



    //Banking menu
    void runMenu() throws InterruptedException {


        while (!accountNumValid(account_num)) {
            System.out.println("Invalid account number. Please enter an 8-digit number.");
            System.out.println("If you would like to create an account enter 0 to see next menu");
            account_num = Integer.parseInt(inp.nextLine());
        }

        if (accountNumValid(account_num) && account_num != 0) {
            System.out.println("Please enter your password");
            password = inp.nextLine();

            while (passwordNotValid(password)) {
                System.out.println("Please ensure that the password has 8 or more characters and contains at least one digit");
                password = inp.nextLine();
            }

            System.out.println("===========================================");

            System.out.print("Validating");
            Thread.sleep(1000);
            for (int i = 0; i < 3; i++) {
                System.out.print('.');
                Thread.sleep(500);
            }
        }



        System.out.println("\n===========================================");

        System.out.println("WELCOME, " + name);
        System.out.println("===========================================");

        System.out.println("You have the following options:");
        System.out.println("1. Account Creation");
        System.out.println("2. Check balance");
        System.out.println("3. Deposit money");
        System.out.println("4. Withdraw money");
        System.out.println("5. Check previous transaction");
        System.out.println("6. Change password");
        System.out.println("7. Speak to an agent");
        System.out.println("8. Exit");

        int response;
        do {
            System.out.println("===========================================");
            System.out.println("What would you like to do?");
            response = Integer.parseInt(inp.nextLine());

            switch (response) {
                case 1:
                    createAccount();
                    break;

                case 2:
                    checkBalance();
                    break;

                case 3:
                    System.out.println("Enter the amount you would like to deposit");
                    amount = Integer.parseInt(inp.nextLine());
                    deposit(amount);


                    System.out.println("To check new balance enter 2");
                    break;

                case 4:
                    System.out.println("Enter the amount you would like to withdraw");
                    this.amount = Integer.parseInt(inp.nextLine());
                    withdraw(amount);

                    System.out.println("To check new balance enter 2");
                    break;

                case 5:
                    checkLastTransaction();
                    break;

                case 6:
                    System.out.println("===========================================");
                    System.out.println("Please confirm your previous password");
                    String confirm_pass = inp.nextLine();
                    while (!confirm_pass.equals(password)) {
                        System.out.println("Invalid Passcode. Please try again");
                        confirm_pass = inp.nextLine();
                    }
                    System.out.println("Please enter your new password");
                    password = inp.nextLine();
                    break;

                case 7:
                    System.out.println("===========================================");
                    System.out.println("Please wait as we connect you to the next available agent");
                    Thread.sleep(1000);
                    System.out.print("Ongoing call");
                    for (int j = 0; j < 5; j++) {
                        System.out.print('.');
                        Thread.sleep(1000);
                    }
                    System.out.println("\n===========================================");
                    System.out.print("\nRedirecting you to the menu");
                    for (int j = 0; j < 3; j++) {
                        System.out.print('.');
                        Thread.sleep(1000);
                    }
                    System.out.println("\n===========================================");
                    break;

                case 8:
                    System.out.println("Thank you for using Khalifa Banking Service!");
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid response! Please try again.");
                    break;

            }
        } while (!checkSwitchExit(response));
    }
}
