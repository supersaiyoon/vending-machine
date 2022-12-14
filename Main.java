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
        String machineName = "vendingMachine_Sacramento";
        VendingMachineFile file = new VendingMachineFile(machineName);
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
            System.out.println("|                                              |");
            if(file.getActive() == 0) {
                System.out.println("|  NOTE: Customer interface currently inactive |");
                System.out.println("|        please correctly close restocker tool |");
                System.out.println("+----------------------------------------------+");

            }
            else
                System.out.println("+----------------------------------------------+");

            System.out.print("Make a selection: ");
            choice = input.nextLine();

            boolean loop = true;

            do {

                if (choice.equals("1")  && file.getActive() == 1) {//Customer
                    System.out.println("Opening Customer Interface...\n");
                    customerActive = "active";
                    new KeyPadGUI();
                    while (customerActive.equals("active")) ;//keeps program from opening infinite KeyPadGUI

                } else if (choice.equals("2") || choice.equalsIgnoreCase("Restocker")) {//Restocker

                    file.setInActive();
                    System.out.println("Opening Restocker Interface...\n");
                    new Restocker();
                    Restocker.main(args);
                    System.out.println("Closing Restocker Interface...\n");
                    file.setActive();
                } else if (choice.equals("3") || choice.equalsIgnoreCase("Exit")) {//exit
                    System.out.println("Closing machine...\n");
                    System.out.println("Have a nice day!");
                    input.close();
                    loop = false;
                    file.setActive();
                    System.exit(0);
                } else {
                    System.out.println("Not a valid option. Please re-enter.\n");
                    input.nextLine();
                }
            }while(loop);
        }
    }
}