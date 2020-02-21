package com.btp400;

import java.util.Scanner;
import com.seneca.accounts.*;
import com.seneca.business.*;
import org.omg.Messaging.SYNC_WITH_TRANSPORT;

public class FinancialApp {

    public static void loadBank( Bank bank ){
        bank.addAccount(new Chequing("John Doe","1234C",123.45,0.25,3));
        bank.addAccount(new Chequing("Mary Ryan","5678C",678.90,0.12,3));
        //todo:Add GIC accounts
    }
    public static void displayMenu( String bankName ){
        StringBuffer title = new StringBuffer("\nWelcome to").append(bankName).append(" Bank!");
        StringBuffer choice1 = new StringBuffer("1. Open an account.");
        StringBuffer choice2 = new StringBuffer("2. Close an account.");
        StringBuffer choice3 = new StringBuffer("3. Deposit money.");
        StringBuffer choice4 = new StringBuffer("4. Withdraw money.");
        StringBuffer choice5 = new StringBuffer("5. Display accounts.");
        StringBuffer choice6 = new StringBuffer("6. Display a tax statement.");
        StringBuffer choice7 = new StringBuffer("7. Exit.");

        System.out.println(title);
        System.out.println(choice1);
        System.out.println(choice2);
        System.out.println(choice3);
        System.out.println(choice4);
        System.out.println(choice5);
        System.out.println(choice6);
        System.out.println(choice7);
    }
    public static int menuChoice( ){
        Scanner s = new Scanner(System.in);
        System.out.println("Please enter your choice> ");
        return s.nextInt();
    }

    public static void displayAccount( Account account){

    }

    public static Account openAcc(){//todo: add the option of exiting the function prematurely
        Scanner in= new Scanner(System.in);
        Account newAccount = null;

        boolean valid_args = false;

        while(!valid_args){
            System.out.println("Please enter the account type(CHQ/GIC)> ");
            String acc_type = in.nextLine();

            System.out.println("Please enter account information at one line");

            if(acc_type.equals("CHQ")||acc_type.equals("chq")){
                System.out.println("Format: Name;Account Number; Starting Balance; Service Charge; Max number of Transactions");
                System.out.println("ex. (John Doe; 1234; 567.89; 0.25; 3)");

                String chq_valuesString = in.nextLine();
                String[] chq_args = chq_valuesString.split(";");

                if(chq_args.length!= 5){
                    System.out.println("Invalid input. Please follow the format shown on screen");
                }else{
                    //this loop takes out the spaces for all the other constructor arguments with an exception to the name
                    for (int i = 1 ; i<chq_args.length;++i){// it would be nice to put this into a lambda
                        chq_args[i] = chq_args[i].trim();
                    }
                    newAccount = new Chequing(chq_args[0],chq_args[1],Double.parseDouble(chq_args[2]),Double.parseDouble(chq_args[3]),Integer.parseInt(chq_args[4]));
                    valid_args=true;

                }

            }else if (acc_type.equals("GIC")|| acc_type.equals("gic")){//todo:Include the creation of GIC accounts
                System.out.println("This is GIC account type input");
                valid_args = true;
            }else{
                System.out.println("invalid account type");
            }
        }

        return newAccount;


    }

    public static void closeAcc(){
        System.out.println("This is the closeAcc() method");
    }

    public static void depositMoney(){
        System.out.println("This is the depositMoney() method");
    }

    public static void withdrawMoney(){
        System.out.println("This is the withdrawMoney() method");
    }

    public static void displayAccountChoice(){
        System.out.println("This is the displayAccountChoice() method");

    }

    public static void displayTax(){
        System.out.println("This is the displayTax() method");

    }



    public static void main(String[] args) {

        Bank myBank=new Bank();
        loadBank(myBank);

        int choice = 0;

        while (choice != 7){
            displayMenu(myBank.getBankName());
            choice=menuChoice();
            switch (choice){

                case 1: //Open an account
                    if(myBank.addAccount(openAcc())){
                        System.out.println("Account successfully included");
                    }else{
                        System.out.println("Unable to add Account");
                    }
                    break;

                case 2: //Close an account
                    closeAcc();
                    break;
                case 3://Deposit money
                  depositMoney();
                    break;
                case 4://Withdraw money
                    withdrawMoney();
                    break;
                case 5://Display accounts
                    displayAccountChoice();
                    break;
                case 6://Display a tax statement
                    displayTax();
                    break;
                case 7://Exit
                    break;
                default:
                    System.out.println("invalid choice");
                    choice= menuChoice();

            }


        }
        System.out.println("Thank you for using our app!");

    }






}
