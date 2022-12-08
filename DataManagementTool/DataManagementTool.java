package DataManagementTool;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Scanner;

import org.json.JSONObject;

import MainFunctions.SaleDataFile;
import MainFunctions.VendingMachineFile;

public class DataManagementTool {
    public static String menuItems[] = {
        "\t[1] View sale data\n",
        "\t[2] Check vending machine inventory\n",
        "\t[3] Send restocking instructions\n",
        "\t[4] EXIT\n"
    };

    public static String machineNames[] = {
        "Sacramento",
        "Folsom"
    };

    public static String userInput;
    public static String menuSelectColor = "\033[31;41;2m";     // Red background
    public static String menuSelectDefault = "\033[37;40;2m";   // Return to default console color
    public static Scanner scannerObj = new Scanner(System.in);

    public static void clearConsole() {
        System.out.print("\033\143");
    }

    public static void exitProgram() {
        scannerObj.close();
        System.exit(0);
    }

    public static void pressContinue() {
        System.out.print("\nPress ENTER to continue...");

        scannerObj.nextLine();
    }

    public static String convertToTitleCase(String text) {
        if (text == null || text.isEmpty()) {
            return text;
        }
    
        StringBuilder converted = new StringBuilder();
    
        boolean convertNext = true;
        for (char ch : text.toCharArray()) {
            if (Character.isSpaceChar(ch)) {
                convertNext = true;
            }
            else if (convertNext) {
                ch = Character.toTitleCase(ch);
                convertNext = false;
            }
            else {
                ch = Character.toLowerCase(ch);
            }
            converted.append(ch);
        }
    
        return converted.toString();
    }

    public static void sendRestockingInstructions() {
        BufferedWriter bw = null;
        try {
            File outfile = new File("DataManagementTool\\RestockerInstructions.txt");

            // Ensures that file is created if not present.
            if (!outfile.exists()) {
                outfile.createNewFile();
            }

            FileWriter fw = new FileWriter(outfile);
            bw = new BufferedWriter(fw);

            String menuChoice = "y";
            String slot = "NO INPUT";
            String[] slotsArr = {"A1", "A2", "A3", "A4", "A5", "A6", "A7", "A8",
                                "B1", "B2", "B3", "B4", "B5", "B6", "B7", "B8",
                                "C1", "C2", "C3", "C4", "C5", "C6", "C7", "C8",
                                "D1", "D2", "D3", "D4", "D5", "D6", "D7", "D8",
                                "E1", "E2", "E3", "E4", "E5", "E6", "E7", "E8"};
            
            // Verify that slot exists before accepting product inputs.
            do {
                boolean isSlot = false;
                
                while (!isSlot) {
                    clearConsole();
                    System.out.print("Enter slot number to replace: ");
                    slot = getInput().toUpperCase();                    

                    if (!Arrays.asList(slotsArr).contains(slot)) {
                        System.out.println("\nSlot doesn't exist. Try again.");
                        pressContinue();
                    }
                    else {
                        isSlot = true;
                    }
                }
    
                clearConsole();
                System.out.print("Enter product name to replace slot " + slot + ": ");
                String productName = convertToTitleCase(getInput());
    
                clearConsole();
                System.out.print("Enter price of " + productName + ": ");
                String price = getInput();

                clearConsole();
                System.out.print("Enter expiration date of " + productName + " (MM/YY): ");
                String expDate = getInput();

                // Remove '/' from expDate.
                String[] tempArr = expDate.split("/");
                String month = tempArr[0];
                String year = tempArr[1];
                expDate = month + year;
    
                String output = slot + "," + productName + "," + price + "," + expDate;
    
                bw.write(output + "\n");
                System.out.println("\nInstructions recorded successfully.\n");
                
                System.out.print("Do you have more instructions to add (Y/N)? ");
                menuChoice = getInput();
            }
            while (menuChoice.equals("y".toLowerCase()));

            clearConsole();
            System.out.println("Instructions sent to restocker.");
            pressContinue();
        }
        catch (IOException ioe) {
            ioe.printStackTrace();
        }
        finally {
            try {
                if (bw != null) {
                    bw.close();
                }
            }
            catch (Exception ex) {
                System.out.println("Error closing BufferedWriter " + ex);
            }
        }
    }

    public static void printMachineList() {
        clearConsole();
        System.out.println("SELECT VENDING MACHINE:\n");
        
        // Print list of vending machines.
        for (int i = 0; i < machineNames.length; i++) {
            System.out.println("\t[" + (i + 1) + "] " + machineNames[i] + "\n");
        }
    }

    public static void checkInventory() {
        printMachineList();
        System.out.print("> ");

        int userChoice = Integer.parseInt(getInput());
        String machineName = "vendingMachine_" + machineNames[userChoice - 1];

        VendingMachineFile file = new VendingMachineFile(machineName);

        // Determine longest product name to get width of name column.
        JSONObject slotObj = file.slotObj;
        Iterator<String> keys = slotObj.keys();
        int longestName = 0;

        while (keys.hasNext()) {
            String key = keys.next();

            if (slotObj.get(key) instanceof JSONObject) {
                JSONObject slotNumObj = slotObj.getJSONObject(key);
                String productName = slotNumObj.getString("productName");
                if (longestName < productName.length()) {
                    longestName = productName.length();
                }
            }
        }

        // Print quantity, price, and name of products in a vending machine.
        String[] letterArr = {"A", "B", "C", "D", "E"};
        String labelSlot = "SLOT";
        String labelName = "PRODUCT NAME                                   ";
        String labelQty = "QTY.";
        String labelPrice = "PRICE";
        String line = "-------------------------------------------------------------------------";

        System.out.println("Checking inventory in " + machineNames[userChoice - 1] + "...");

        // Print column titles
        System.out.println(line);
        String columnNames = "| " + labelSlot + " | " + labelName + " | " + labelQty + " | " + labelPrice + " |";
        System.out.println(columnNames);
        System.out.println(line);

        for (int i = 0; i < 5; i++) {
            for (int j = 1; j <= 8; j++) {
                String slotNum = letterArr[i] + Integer.toString(j);
                System.out.print("|  " + slotNum + "  |");
                System.out.printf(" %-48s", file.getSlotProductName(slotNum));
                System.out.printf("|  %02d  ", file.getSlotQty(slotNum));
                System.out.printf("| $%1.2f |%n", file.getSlotPrice(slotNum));
                System.out.println(line);
            }
        }
        pressContinue();
    }

    public static void viewSaleDataFolsom() {
        System.out.println("Under construction!\n");
        System.out.print("Press ENTER to continue...");
        getInput();
    }

    public static void viewSaleDataSacramento() throws Exception {
        SaleDataFile saleData = new SaleDataFile();

        String slots = saleData.getSlotSold();
        String[] slotsArr = slots.split(" ");

        String products = saleData.getProductNameSold();
        String[] productsArr = products.split(",");

        String prices = saleData.getPriceSold();
        String[] pricesArr = prices.split(" ");

        String dates = saleData.getDateSold();
        String[] datesArr = dates.split(",");
        
        clearConsole();
        System.out.println("Sales for Sacramento...");
        String line = "---------------------------------------------------------------------------------------";
        System.out.println(line);
        System.out.println("| SLOT | DATE & TIME         | PRODUCT NAME                                   | PRICE |");
        System.out.println(line);
        
        for (int i = 0; i < slotsArr.length; i++) {
            System.out.print("|  " + slotsArr[i]);
            System.out.print("  | " + datesArr[i]);
            System.out.printf(" | %-46s", productsArr[i]);
            System.out.println(" | $" + pricesArr[i] + " |");
            System.out.println(line);
        }

        pressContinue();
    }

    public static void viewSaleData() {
        printMachineList();

        System.out.print("> ");
        int userChoice = Integer.parseInt(getInput());

        try {
            switch (userChoice) {
                case 1:
                    viewSaleDataSacramento();
                    break;
                case 2:
                    viewSaleDataFolsom();
                    break;
            }    
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        

    }

    public static String getInput() {
        String input = scannerObj.nextLine();

        return input;
    }

    public static void displayMainMenu() {
        clearConsole();
        
        System.out.println("MAIN MENU OPTIONS:\n");
        for (String item : menuItems) {
            System.out.println(item);
        }
        System.out.print("> ");
    }

    public static void executeDataManagementTool() {
        do {
            displayMainMenu();
            userInput = getInput();

            switch (userInput) {
                case "1":
                    viewSaleData();
                    break;
                case "2":
                    checkInventory();
                    break;
                case "3":
                    sendRestockingInstructions();
                    break;
                case "4":
                    exitProgram();
            }
        }
        while (userInput != "4");
    }

    public static void main(String[] args) {
        executeDataManagementTool();
    }
}