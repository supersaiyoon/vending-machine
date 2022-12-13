import GUI.*;
import MainFunctions.*;
import java.util.*;

class Main {
    public static void clearConsole() {
        final String os = System.getProperty("os.name");
        if (os.contains("Windows")) {
            try {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();                
            }
            catch (Exception e) {
                // TODO: handle exception
            }
        }
        else {
            try {
                Runtime.getRuntime();
            }
            catch (Exception e) {
                // TODO: handle exception
            }
        }            
    }
    
    public static void main(String args[]) throws Exception {
        Scanner input = new Scanner(System.in);
        String choice = "";
        String customerActive = "";

        clearConsole();
        while (!choice.equals("3") || !choice.equalsIgnoreCase("Exit")){
            clearConsole();
            System.out.println("+----------------------------------------------+");
            System.out.println("|           Five GUIS Vending Machine          |");
            System.out.println("|----------------------------------------------|");
            System.out.println("|                     Menu                     |");
            System.out.println("|  [1] Customer                                |");
            System.out.println("|  [2] Restocker                               |");
            System.out.println("|  [3] Exit                                    |");
            System.out.println("+----------------------------------------------+");

            if(customerActive.equals("active")){
                System.out.println("Customer is active; don't make a new selection");
            }
            
            else{
                System.out.print("Make a selection: ");
                choice = input.nextLine();
            }

            if(choice.equals("1") || choice.equalsIgnoreCase("Customer")){//Customer
                System.out.println("Opening Customer Interface...\n");
                customerActive = "active";
                new KeyPadGUI();
                while(customerActive.equals("active"));//keeps program from opening infinite KeyPadGUI
            }
            
            else if(choice.equals("2") || choice.equalsIgnoreCase("Restocker")){//Restocker
                System.out.println("Opening Restocker Interface...\n");
                new Restocker();
                Restocker.main(args);
                System.out.println("Closing Restocker Interface...\n");
            }

            else if(choice.equals("3") || choice.equalsIgnoreCase("Exit")){//exit
                System.out.println("Closing machine...\n");
                System.out.println("Have a nice day!");
                input.close();
                System.exit(0);
            }
            
            else{
                System.out.println("Not a valid option. Please re-enter.\n");
                input.nextLine();
            }
        }
    }
}