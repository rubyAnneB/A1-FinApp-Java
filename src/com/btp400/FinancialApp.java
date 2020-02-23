package com.btp400;

import java.util.Arrays;
import java.util.Scanner;
import com.seneca.accounts.*;
import com.seneca.business.*;

/**
 * This class prints out the menu and outputs the messages in accordance to the
 * user's input
 * @author Ruby Anne Bautista, Daniel Derich
 * @since 202/02/22
 * @version 1.0
 */
public class FinancialApp {

	/**
	 * @param bank the bank that the accounts are added to
	 */
	public static void loadBank(Bank bank) {
		bank.addAccount(new Chequing("John Doe", "1234C", 123.45, 0.25, 3));
		bank.addAccount(new Chequing("Mary Ryan", "5678C", 678.90, 0.12, 3));
		bank.addAccount(new GIC("John Doe", "9999G", 6000, 2, .0150));
		bank.addAccount(new GIC("Mary Ryan", "888G", 15000, 4, .0250));
		bank.addAccount(new GIC("Mary Ryan", "778G", 12222, 4, .0250));

	}

	/**
	 *
	 * @param bankName The bank's name to be displayed on the menu
	 */
	public static void displayMenu(String bankName) {
		StringBuffer title = new StringBuffer("\nWelcome to ").append(bankName).append(" Bank!");

		System.out.println(title);
		System.out.println("1. Open an account.");
		System.out.println("2. Close an account.");
		System.out.println("3. Deposit money.");
		System.out.println("4. Withdraw money.");
		System.out.println("5. Display accounts.");
		System.out.println("6. Display a tax statement.");
		System.out.println("7. Exit.");
	}

	/**
	 * Takes in an int from user's input
	 * 
	 * @return the user's menu choice
	 */
	public static int menuChoice() {
		Scanner s = new Scanner(System.in);
		System.out.print("Please enter your choice> ");
		return s.nextInt();
	}

	/**
	 *
	 * @param account Account to be printed out
	 */
	public static void displayAccount(Account account) {
		System.out.println(account);
	}

	/**
	 * Creates an account based on what the user inputs. Different account choices:
	 * Chequing and GIC
	 * 
	 * @return An account built from the user's input.
	 */
	public static Account openAcc() {
		Scanner in = new Scanner(System.in);
		Account newAccount = null;

		boolean valid_args = false;

		while (!valid_args) {
			System.out.print("Please enter the account type(CHQ/GIC)> ");
			String acc_type = in.nextLine();

			System.out.println("Please enter account information in one line.\n");

			if (acc_type.equals("CHQ") || acc_type.equals("chq")) {
				System.out.println(
						"Format: Name;Account Number; Starting Balance; Service Charge; Max number of Transactions");
				System.out.println("ex. (John Doe; 1234; 567.89; 0.25; 3)");

				String chq_valuesString = in.nextLine();
				String[] chq_args = chq_valuesString.split(";");

				if (chq_args.length != 5) {
					System.out.println("Invalid input. Please follow the format shown on screen");
				} else {
					// this is vulnerable to type mismatch
					newAccount = new Chequing(chq_args[0], chq_args[1].trim(), Double.parseDouble(chq_args[2].trim()),
							Double.parseDouble(chq_args[3].trim()), Integer.parseInt(chq_args[4].trim()));
					valid_args = true;

				}

			} else if (acc_type.equals("GIC") || acc_type.equals("gic")) {
				System.out.println("This is GIC account type input.");
				System.out.println(
						"Format: Name; Account Number; Starting Balance; Period of Investment in year(s); Interest Rate (15.5% would be 15.5)");
				System.out.println("Example: John M. Doe;A1234;1000.00; 1; 15.5");
				System.out.print(">");
				String gic_valuesString = in.nextLine();
				String[] gic_args = gic_valuesString.split(";");

				if (gic_args.length != 5) {
					System.out.println("Invalid input. Please follow the format shown on screen.");
				} else {
					newAccount = new GIC(gic_args[0], gic_args[1].trim(), Double.parseDouble(gic_args[2].trim()),
							Integer.parseInt(gic_args[3].trim()), (Double.parseDouble(gic_args[4].trim()) / 100.00));
				}
				valid_args = true;
			} else {
				System.out.println("Invalid account type");
			}
		}

		return newAccount;

	}

	/**
	 * Asks user for account number and closes account if found
	 * 
	 * @param bank The bank from which the account will be deleted.
	 */
	public static void closeAcc(Bank bank) {// can expand to search by name
		Scanner in = new Scanner(System.in);

		System.out.println("Please enter the Account Number: ");
		String delAccNum = in.nextLine();
		StringBuffer confirm = new StringBuffer("Confirm delete of account with the number:").append(delAccNum).append(" (Y/N)");
		System.out.println(confirm);

		String res = in.nextLine();

		boolean valid_res = false;
		while (!valid_res) {

			switch (res) {
			case "Y":
			case "y":
				Account a = bank.removeAccount(delAccNum);
				if (a != null) {
					System.out.println("Account successfully deleted");
				} else {
					System.out.println("Account not found");
				}
				valid_res = true;
				break;

			case "N":
			case "n":
				valid_res = true;
				System.out.println("Delete cancelled");
				break;
			default:
				System.out.println("Invalid response, please enter \"Y\" or \"N\" ");

			}

		}

	}

	/**
	 * Asks user for account number of the account to be deposited into
	 * 
	 * @param bank The bank the account to be deposited into belongs to
	 */
	public static void depositMoney(Bank bank) {

		Scanner in = new Scanner(System.in);

		System.out.println("Please enter your account number: ");
		String account_num = in.nextLine();
		Account depAcc = bank.searchByAccountNumber(account_num);

		if (depAcc != null) {
			System.out.println("Please enter the amount you would like to deposit:");
			double depositMoney = in.nextDouble();
			depAcc.deposit(depositMoney);
			System.out.println("Deposit Successful");

		} else {
			System.out.println("Error account not found.");
		}

	}

	/**
	 * Asks user for account number of the account to be withdrawn from
	 * 
	 * @param bank the bank that the account to be withdrawn from belongs to
	 */
	public static void withdrawMoney(Bank bank) {
		Scanner in = new Scanner(System.in);

		System.out.println("Please enter your account number: ");
		String account_num = in.nextLine();
		Account depAcc = bank.searchByAccountNumber(account_num);

		if (depAcc != null) {
			System.out.println("Please enter the amount you would like to withdraw");
			double withdrawAmount = in.nextDouble();

			if (depAcc.withdraw(withdrawAmount)) {
				System.out.println("Withdraw successful");
			} else {
				System.out.println("Withdraw failed");
			}

		} else {
			System.out.println("Error account not found.");
		}

	}

	/**
	 * Displays the options to display accounts, takes user's choice and prints out
	 * the accounts accordingly
	 * 
	 * @param bank The bank that the accounts displayed belong to
	 */
	public static void displayAccountChoice(Bank bank) {
		Scanner in = new Scanner(System.in);
		boolean valid = false;

		while (!valid) {
			System.out.println("Please choose one of the following options: ");
			System.out.println("a) display all accounts with the same account name");
			System.out.println("b) display all accounts with the same final balance");
			System.out.println("c) display all accounts opened at the bank");
			System.out.println("d) display a specific account");
			System.out.println("x) Return to main menu");
			System.out.print(">");
			String option = in.nextLine();

			switch (option) {
			case "a":
			case "A":
				System.out.println("Please enter the name to search by: ");
				String nameSearch = in.nextLine();
				System.out.println(Arrays.toString(bank.searchByAccountName(nameSearch)));
				valid = true;
				break;
			case "b":
			case "B":
				System.out.println("Please enter the balance to search by: ");
				double balance = in.nextDouble();
				System.out.println(Arrays.toString(bank.searchByBalance(balance)));
				valid = true;
				break;
			case "c":
			case "C":
				System.out.println(Arrays.toString(bank.getAllAccounts()));
				valid = true;
				break;

			case "d":
			case "D":
				System.out.println("Please enter the account number:");
				String accNum = in.nextLine();
				Account displayAcc = bank.searchByAccountNumber(accNum);
				if (displayAcc != null) {
					System.out.println(displayAcc);
				} else {
					System.out.println("Account not found!");
				}
				break;
			case "x":
			case "X":
				System.out.println("Operation cancelled, returning to main menu");
				valid = true;
				break;
			default:
				System.out.println("Invalid response, enter a valid response (a-d) or \"x\" to exit");
				break;
			}
		}

	}

	/**
	 * Displays all the GIC accounts belonging to a particular person(identified by
	 * their name)
	 * 
	 * @param accounts Takes in an array of accounts. Any taxable accounts are
	 *                 printed out
	 */
	public static void displayTax(Account[] accounts) {
		Scanner in = new Scanner(System.in);
		System.out.println("Which person would you like a tax statement for?");
		System.out.print("Name: ");
		String n = in.nextLine();
		boolean start = false;
		int count = 1;

		for (Account a : accounts) {

			if (a instanceof Taxable && a.getFullName().equals(n)) {
				if (!start) {
					System.out.println("Tax rate: " + ((int) (((Taxable) a).tax_rate * 100.00)) + "%\n");

					start = true;
				}
				System.out.println("[" + count++ + "]");
				System.out.print(((GIC) a).getTax());

			}
		}
	}

	public static void main(String[] args) {

		Bank myBank = new Bank();
		loadBank(myBank);

		int choice = 0;

		while (choice != 7) {
			displayMenu(myBank.getBankName());
			choice = menuChoice();

			switch (choice) {

			case 1: // Open an account - add GIC
				if (myBank.addAccount(openAcc())) {
					System.out.println("Account successfully included");
				} else {
					System.out.println("Unable to add Account");
				}
				break;

			case 2: // Close an account - Complete
				closeAcc(myBank);
				break;
			case 3:// Deposit money-Complete
				depositMoney(myBank);
				break;
			case 4:// Withdraw money-Complete
				withdrawMoney(myBank);
				break;
			case 5:// Display accounts - Formatting needs work
				displayAccountChoice(myBank);
				break;
			case 6:// Display a tax statement
				displayTax(myBank.getAllAccounts());
				break;
			case 7:// Exit
				break;
			default:
				System.out.println("invalid choice");
				choice = menuChoice();

			}

		}
		System.out.println("Thank you for using our app!");

	}
}
