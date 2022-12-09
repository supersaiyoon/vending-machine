package MainFunctions;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Restocker {
    static void checklist() throws Exception {
        VendingMachineFile vendingMachineSacramento = new VendingMachineFile("vendingMachine_Sacramento");
        int i = 1;
        char letter = 'A';
        System.out.println("Items needed to fill machine:");
        System.out.println("------------------------------------------------");
        
        do {
            while (i <= 8) {
                String let = String.valueOf(letter);
                String slot = let+i;
                int quantity = vendingMachineSacramento.getSlotQty(slot);
                String name = vendingMachineSacramento.getSlotProductName(slot);
                int refill = 15 - quantity;
                if (quantity == 15) {
                    i++;
                }
                else {
                    System.out.println(slot+" "+name+" x"+refill);
                    i++;
                }
            }
            letter++;
            i = 1;
        }
        while (letter <= 'E');
    }

    static void refill() throws Exception {
        VendingMachineFile vendingMachineSacramento = new VendingMachineFile("vendingMachine_Sacramento");
        int i = 1;
        char letter = 'A';
        do {
            while (i <= 8) {
                String let = String.valueOf(letter);
                String slot = let+i;
                int quantity = vendingMachineSacramento.getSlotQty(slot);
                String name = vendingMachineSacramento.getSlotProductName(slot);

                if (quantity == 15)
                    i++;
                else {
                    vendingMachineSacramento.setSlotQty(slot, 15);
                    int restocked = vendingMachineSacramento.getSlotQty(slot);
                    if(restocked == 15)
                        System.out.println(slot+" "+name+" fully stocked");
                    i++;
                }
            }
            letter++;
            i = 1;
        }
        while (letter <= 'E');
    }

    static void expired() throws Exception {
        VendingMachineFile vendingMachineSacramento = new VendingMachineFile("vendingMachine_Sacramento");
        int i = 1;
        char letter = 'A';
        System.out.println("Expired Items list");
        System.out.println("------------------------------------------------");
        do {
            while (i <= 8) {
                String let = String.valueOf(letter);
                String slot = let+i;
                String name = vendingMachineSacramento.getSlotProductName(slot);
                boolean exp = verify(slot);
                if (!exp)
                    i++;
                else {
                    System.out.println(slot+" "+name+" is expired");
                    i++;
                }
            }
            letter++;
            i = 1;
        }
        while (letter <= 'E');
    }

    static boolean verify(String slotNumber) throws Exception {
        VendingMachineFile vendingMachineSacramento = new VendingMachineFile("vendingMachine_Sacramento");
        Calendar localCalendar = Calendar.getInstance(TimeZone.getDefault());

        int currentMonth = localCalendar.get(Calendar.MONTH)+1;
        int currentYear = localCalendar.get(Calendar.YEAR) % 100;

        String slotExp = vendingMachineSacramento.getSlotExpDate(slotNumber);
        String slotMonth = slotExp.substring( 0, (slotExp.length()/2));
        String slotYear= slotExp.substring( (slotExp.length()/2));

        boolean isExpired = false;

        if ( Integer.parseInt(slotYear) < currentYear) {
            isExpired = true;
        }
        else if (Integer.parseInt(slotMonth) < currentMonth && Integer.parseInt(slotYear) == currentYear) {
            isExpired = true;
        }
        return isExpired;
    }

    public static void writeRestockerInstructions(Map<Integer, String> taskMap) {
        try (FileWriter file = new FileWriter("DataManagementTool/RestockerInstructions.txt")) {
            if (taskMap.isEmpty()) {
                file.write("");  // Integer is size of indent in JSON file.
            }
            else {
                for (Integer key : taskMap.keySet()) {
                    file.write(taskMap.get(key) + "\n");
                }
            }
            file.flush();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void clearConsole() {
        //System.out.print("\033\143");
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
                Runtime.getRuntime().exec("clear");    
            }
            catch (Exception e) {
                // TODO: handle exception
            }
        }            
    }

    public static void pressContinue() {
        System.out.print("\nPress ENTER to continue...");
        
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
    }

    public static void main(String args[]) throws Exception {
        VendingMachineFile vendingMachineSacramento = new VendingMachineFile("vendingMachine_Sacramento");
        Scanner input = new Scanner(System.in);
        Scanner Input2 = new Scanner(System.in);
        Scanner Input3 = new Scanner(System.in);


        int choice;
        while (true) {
            clearConsole();
            System.out.println("Restocker Interface Main Menu\n");
            System.out.println("\t[1] Check expired items\n");
            System.out.println("\t[2] Check if item quantities are full\n");
            System.out.println("\t[3] Restocking instructions from corporate\n");
            System.out.println("\t[4] Replace item in vending machine\n");
            System.out.println("\t[5] Exit Restocker Interface");
            System.out.print("\nEnter your choice: ");


            choice = input.nextInt();

            switch (choice) {
                case 1:
                    clearConsole();
                    expired();
                    pressContinue();
                    break;
                case 2:
                    clearConsole();
                    checklist();

                    int restockChoice;
                    System.out.println("\nRestock items to max quantity?\n");
                    System.out.println("\t[1] Yes\n");
                    System.out.println("\t[2] No\n");
                    System.out.print("> ");
                    restockChoice = input.nextInt();

                    if (restockChoice == 1) {
                        refill();
                        System.out.println("Items refilled, returning to Main Menu... \n");
                        break;
                    }
                    else if(restockChoice ==2) {
                        System.out.println("Returning to Main Menu... \n");
                        break;
                    }
                    else {
                        System.out.println("ERROR: Unaccepted input, please insert [1] or [2].");
                        break;
                    }
                case 3:
                    clearConsole();
                    String line = "------------------------------------------------------------------------------------";

                    // Print list of tasks from RestockerInstructions.txt
                    File fileObj = new File("DataManagementTool\\RestockerInstructions.txt");
                    
                    if (!fileObj.exists()) {
                        System.out.println("No instructions available at this time.");
                        pressContinue();
                        break;
                    }
                    Scanner fileScanner = new Scanner(fileObj);

                    String fileLine = "";
                    int taskNum = 1;
                    Map<Integer, String[]> taskMap = new HashMap<Integer, String[]>();  // Contains tasks for printing.
                    Map<Integer, String> stringMap = new HashMap<Integer, String>();    // Tracks unfinished tasks that need to be written to RestockerInstructions.txt.
                    
                    while (fileScanner.hasNextLine()) {
                        fileLine = fileScanner.nextLine();
                        stringMap.put(taskNum, fileLine);
                        String[] stringArr = fileLine.split(",");

                        taskMap.put(taskNum, stringArr);
                        taskNum++;
                    }

                    // Handles printing tasks and deleting tasks for restocker.
                    while (true) {
                        clearConsole();
                        System.out.println("New products to replace current products in vending machine:");
                        System.out.println(line);
                        System.out.println("| TASK | SLOT | PRODUCT NAME                                   | PRICE | EXP. DATE |");
                        System.out.println(line);

                        if (taskMap.isEmpty()) {
                            writeRestockerInstructions(stringMap);
                            System.out.println("\n                            ALL TASKS COMPLETE!");
                            pressContinue();
                            break;
                        }

                        // Print map containing tasks. Map key returns String array.
                        for (Integer key : taskMap.keySet()) {
                            System.out.print("|  " + key + "   ");
                            System.out.print("|  " + taskMap.get(key)[0] + "  ");
                            System.out.printf("| %-47s", taskMap.get(key)[1]);
                            System.out.printf("| $%-5.2f", Float.parseFloat(taskMap.get(key)[2]));
                            System.out.println("|   " + taskMap.get(key)[3].substring(0, 2) + "/" + taskMap.get(key)[3].substring(2) + "   |");
                            System.out.println(line);
                        }

                        // Ask restocker which task to delete.
                        System.out.print("\nInput task number completed (Q to Quit): ");
                        System.out.print("> ");
                        Scanner inputScanner = new Scanner(System.in);
                        String userInput = inputScanner.nextLine();
                        int keyNum = -1;

                        if (userInput.toUpperCase().equals("Q")) {
                            writeRestockerInstructions(stringMap);
                            break;
                        }
                        else {
                            keyNum = Integer.parseInt(userInput);
                        }

                        // Delete task from map and update JSON file.
                        String[] newProductArr = taskMap.remove(keyNum);
                        stringMap.remove(keyNum);

                        String slot = newProductArr[0];
                        vendingMachineSacramento.setSlotQty(slot, 15);
                        vendingMachineSacramento.setSlotProductName(slot, newProductArr[1]);
                        vendingMachineSacramento.setSlotPrice(slot, Double.parseDouble(newProductArr[2]));
                        vendingMachineSacramento.setSlotExpDate(slot, newProductArr[3]);
                    }
                    break;
                case 4:
                    clearConsole();
                    System.out.println("Replace item in Vending Machine?\n");
                    System.out.println("\t[1] Yes\n");
                    System.out.println("\t[2] No\n");
                    System.out.print("> ");
                    int replaceChoice = input.nextInt();

                    if (replaceChoice == 1) {
                        int qty = 15;
                        clearConsole();
                        System.out.println("Which slot?");
                        String slot = Input2.nextLine().toUpperCase();
                        vendingMachineSacramento.setSlotQty(slot, qty);

                        System.out.println("New product's name?");
                        String name = Input2.nextLine();
                        vendingMachineSacramento.setSlotProductName(slot, name);

                        System.out.println("Price?");
                        double price = Input2.nextDouble();
                        vendingMachineSacramento.setSlotPrice(slot, price);

                        System.out.println("Expiration date?");
                        String date = Input3.nextLine();
                        vendingMachineSacramento.setSlotExpDate(slot, date);

                        System.out.println(slot + " replaced. Thank you!\n");
                        break;
                    }
                    else if (replaceChoice == 2) {
                        System.out.println("Returning to Main Menu... \n");
                        break;
                    }
                    else {
                        System.out.println("ERROR: Unaccepted input, please press [1] or [2].\n");
                        break;
                    }
                case 5:
                    System.out.println("Exiting Restocker Interface...");
                    input.close();
                    Input2.close();
                    Input3.close();
                    System.exit(0);
                    break;

                default:
                    System.out.println("This is not a valid input, please select a valid input");
                    break;
            }
        }
    }
}