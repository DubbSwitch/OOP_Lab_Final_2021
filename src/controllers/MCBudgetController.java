package controllers;

import models.Budget;
import models.User;
import lib.ConsoleIO;

import java.util.ArrayList;

public class MCBudgetController {

    static User currentUser;

    public static void run() {
        int choice = homeMenu();
        homeSwitch(choice);
    }

    private static int homeMenu() {
        String[] options = {"Login", "Create New User", "Reset Password"};
        return ConsoleIO.promptForMenuSelection("", options, true);
    }

    private static void homeSwitch(int choice) {
        switch (choice) {
            case 1:
                login();
                break;
            case 2:
                createUser();
                break;
            case 3:
                resetPassword();
                break;
            case 0:
                System.out.println("Goodbye");
                break;
            default:
                System.out.println("I don't know how you did that. Your input =" + choice);
        }
    }

    //TODO write method
    private static void login() {
        String username = ConsoleIO.promptForString("Enter your Username: ", false);
        // added a try catch because if their was no users created it would cause a NulLPointerError
        String password = ConsoleIO.promptForString("Enter your password: ",false);
        try{
        if(currentUser.getUserName().equals(username) && currentUser.getPassword().equals(password)){
            int choice = userMenu();
            userSwitch(choice);
        }else{
            System.out.println("Password was incorrect: ");
            run();
        }}catch (NullPointerException nfe){
            System.out.println("User: '" + username + "', does not exist.");
            run();
        }

    }

    private static void createUser() {
        String pED = "Please enter desired";
        String userName = ConsoleIO.promptForString(pED + " username: ", false);
        String password = ConsoleIO.promptForString(pED + " password: ", true);
        String question = ConsoleIO.promptForString("Security Question for password reset\n" + pED + "Question: ", false);
        String answer = ConsoleIO.promptForString(pED + " answer: ", false);
        String displayName = ConsoleIO.promptForString(pED + " display name: ", false);
        // user is a place holder strictly for testing
        // the end of this method should create a new file with the user's information.
        currentUser = new User(userName, displayName, password, question, answer);
        run();
    }

    //TODO write method
    private static void resetPassword() {
        // prompts the user for their username
        String username = ConsoleIO.promptForString("Enter your username: ", false);
        try{
            if(username.equals(currentUser.getUserName())){
                String answer = ConsoleIO.promptForString(currentUser.getSecQuestion() + " : ",false);
                if(answer.equals(currentUser.getSecAnswer())){
                    String password = ConsoleIO.promptForString("Enter your new password: ",false);
                    currentUser.setPassword(password);
                }else {
                    System.out.println("The answer to your security question was incorrect.");
                }
            }
            else {
                System.out.println("User '"+ username+"' not found.");

            }
        }catch (NullPointerException nfe){
            System.out.println("User '" + username + "' does not exist.");
            //run();
        }
        run();
        //checks if that user exists
        //IF user exists
            // Prompt their security question
                // IF user input is true
                    // Prompt user for new password and set it as the users new password
                //else go back to homeMenu
    }

    //      //
    // USER //
    //      //

    //TODO write method
    private static int userMenu() {
        String[] menu = {"Budgeting","Account Settings","Logout"};
        return ConsoleIO.promptForMenuSelection("", menu,false);
    }

    //TODO write method
    private static void userSwitch(int choice) {
        int input;
        switch (choice) {
            case 1:
                input = budgetingMenu();
                budgetingSwitch(input);
                break;
            case 2:
                input = accountSettingsMenu();
                accountSettingsSwitch(input);
                break;
            case 3:
                logout();
        }
    }

    //TODO write method
    private static void logout() {
        //This should refresh the app and start from run();
    }

    //           //
    // BUDGETING //
    //           //

    //TODO write method
    private static int budgetingMenu() {
        String[] menu = {"Select Budget","Create Budget","Savings"};
        return ConsoleIO.promptForMenuSelection("", menu,true);
    }

    //TODO write method
    private static void budgetingSwitch(int choice) {
        int input;
        switch (choice){
            case 1:
                chooseBudget();
                break;
            case 2:
                String name = ConsoleIO.promptForString("Enter name of budget: ", false);
                int funds = ConsoleIO.promptForInt("Amount of money allocated: ",1,1000000000);
                createBudget(funds,name);
                userSwitch(1);
                break;
            case 3:
                savingsMenu();
                break;
            case 0:
                input = userMenu();
                userSwitch(input);
        }
    }

    private static void chooseBudget() {
        //Creates an array containing the names of all the user's budgets, for later menu display
        String[] menu = new String[currentUser.getBudgetList().size()];
        for (int i = 0; i < menu.length; i++) {
            menu[i] = currentUser.getBudgetList().get(i).getName();
        }

        int input = ConsoleIO.promptForMenuSelection("Please select the budget you'd like to view:", menu,false);
        budgetOptionsMenu(currentUser.getBudgetList().get(input - 1));

    }

    //TODO write method
    private static void createBudget(double funds, String name) {

    }

    //         //
    // SAVINGS //
    //         //

    //TODO write method
    private static void savingsMenu() {


    }

    //TODO write method
    private static void modifySavingsAmount(double amount, int choice) {

    }

    //       //
    // MENUS //
    //       //

    //TODO write method
    private static int budgetOptionsMenu(Budget budget) {
        return 0;
    }

    //TODO write method
    private static void budgetOptionsSwitch(int choice) {

    }

    //TODO write method
    private static void modifyBudgetMenu() {

    }

    //TODO write method
    private static void viewTransactionHistory(Budget budget) {

    }

    //TODO write method
    private static void deleteBudget(Budget budget) {

    }

    //TODO write method
    private static void renameBudget(Budget budget, String newName) {

    }

    //         //
    // ACCOUNT //
    //         //

    //TODO write method
        private static int accountSettingsMenu() {
        String[] menu = {"Change Display Name","Change Password","Change Security Question and Answer"};
        return ConsoleIO.promptForMenuSelection("", menu,true);
    }

    //TODO write method
    private static void accountSettingsSwitch(int choice) {
        switch (choice){
            case 1:
                String displayName = ConsoleIO.promptForString("Please enter new name: ",false);
                changeDisplayName(displayName);
                break;
            case 2:
                String password = ConsoleIO.promptForString("Please enter new password: ",false);
                changePassword(password);
                break;
            case 3:
                String question = ConsoleIO.promptForString("Enter your new security question: ",false);
                String answer = ConsoleIO.promptForString("Enter the answer to your new security question: ",false);
                changeSecQnA(question,answer);
                break;
            default:
                // brings the user back to the previous menu
                int input = userMenu();
                userSwitch(input);
                break;
        }
    }

    //TODO write method
    private static void changeDisplayName(String newName) {
        // Sets the new display name for the user and brings them back to the account settings menu
            currentUser.setDisplayName(newName);
            int choice = accountSettingsMenu();
            accountSettingsSwitch(choice);
    }

    //TODO write method
    private static void changePassword(String newPassword) {
        // Sets the new password for the user and brings them back to the account settings menu
            currentUser.setPassword(newPassword);
        int choice = accountSettingsMenu();
        accountSettingsSwitch(choice);
    }

    //TODO write method
    private static void changeSecQnA(String question, String answer) {
        //Sets the new Security QnA for the user and brings them back to the account settings
        currentUser.setSecQuestion(question);
        currentUser.setSecAnswer(answer);
        int choice = accountSettingsMenu();
        accountSettingsSwitch(choice);
    }
}
