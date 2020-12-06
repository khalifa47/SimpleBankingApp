package com.khalifa;

import java.util.Scanner;

public class Main {
    static Scanner inp = new Scanner(System.in);

    public static void main(String[] args) throws InterruptedException {

        System.out.println("+=========================================+");
        System.out.println("|\tWELCOME TO KHALIFA BANKING SERVICE!   |");
        System.out.println("+=========================================+");

        System.out.println("Please enter your name");
        String name = inp.nextLine();
        name = name.toUpperCase();


        System.out.println("Please enter your 8-digit account number.");
        int account_num = Integer.parseInt(inp.nextLine());

        BankingApp app = new BankingApp(name, account_num);
        app.runMenu();

    }
}




/*
Account creation
Deposit
Withdraw
Balance
Passcode
Speak to agent
Previous transaction -> reverse
Exit
 */