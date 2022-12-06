package EventHandlers;

import MainFunctions.*;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Scanner;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

public class OrderInputPerformed implements ActionListener {
    String slotLetter = null;
    String slotNum = null;

    // Read the button pressed.
    public String getInput(JButton o) {
        String letter = "NO INPUT";

        if (o.getText() == "A") {
            letter = "A";
        }
        else if (o.getText() == "B") {
            letter = "B";
        }
        else if (o.getText() == "C") {
            letter = "C";
        }
        else if (o.getText() == "D") {
            letter = "D";
        }
        else if (o.getText() == "E") {
            letter = "E";
        }
        else if (o.getText() == "1") {
            letter = "1";
        }
        else if (o.getText() == "2") {
            letter = "2";
        }
        else if (o.getText() == "3") {
            letter = "3";
        }
        else if (o.getText() == "4") {
            letter = "4";
        }
        else if (o.getText() == "5") {
            letter = "5";
        }
        else if (o.getText() == "6") {
            letter = "6";
        }
        else if (o.getText() == "7") {
            letter = "7";
        }
        else if (o.getText() == "8") {
            letter = "8";
        }
        else if (o.getText() == "OK") {
            letter = "OK";
        }
        else if (o.getText() == "CLR") {
            letter = "CLR";
        }

        return letter;
    }

    void clearConsole() {
        System.out.print("\033\143");
    }

    // Order processing logic goes here. Update files, compute change, etc.
    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();

        
        String letter = getInput((JButton) o);

        String[] letterArr = {"A", "B", "C", "D", "E"};
        String[] numArr = {"1", "2", "3", "4", "5", "6", "7", "8"};

        for (String l : letterArr) {
            if (letter == l) {
                slotLetter = l;
                break;
            }
        }

        for (String n : numArr) {
            if (letter == n) {
                slotNum = n;
                break;
            }
        }

        if (letter == "CLR") {
            slotLetter = null;
            slotNum = null;
        }

        if (slotLetter != null && slotNum != null && letter == "OK") {

            //for returning proper rounded change format #.## instead of massive doubles
            DecimalFormat df = new DecimalFormat("$#,##0.00");

            String slot = slotLetter + slotNum;
            clearConsole();
            System.out.println("\nProcessing customer order for slot " + slot);

            VendingMachineFile vendingMachineSacramento = new VendingMachineFile("vendingMachine_Sacramento");
            Customer custObj = new Customer();
            VendingMachine classObj = new VendingMachine();

            int newItemQty = vendingMachineSacramento.getSlotQty(slot);

            //while expiration date has not passed
            if (!classObj.verifyExpiration(slot) && newItemQty > 0) {
                System.out.println("You want to purchase " + vendingMachineSacramento.getSlotProductName(slot));
                System.out.println("Please enter " + df.format(vendingMachineSacramento.getSlotPrice(slot)));
                System.out.println();

                Scanner getMoney = new Scanner(System.in);
                System.out.println("Please insert money: ");
                double moneyInsert = Double.parseDouble(getMoney.nextLine());

                //get slot from keypad, money from terminal
                custObj.customerOrder(slot, moneyInsert);

                //save sale data in SaleData.json
                try {
                    custObj.newSaleData(slot);
                    slotLetter = null;
                    slotNum = null;
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }

            else {
                System.out.println("ERROR: Item is unavailable, please choose a different item.");
            }

            /*
            //Buffer for SaleData
            System.out.println();

            //Testing SaleData JSON
            SaleDataFile saleData = new SaleDataFile();
            //Set date of sale for specific data
            System.out.println("Setting Sale Date: ");
            saleData.setSaleDate("11.14.22.10.31.22");
            System.out.println("Sale Date set.\n");

            //Testing get functions
            System.out.println("Product name retrieved: " + saleData.getProductNameSold());
            System.out.println("Product price retrieved: " + saleData.getPriceSold());
            System.out.println("Product location retrieved: " + saleData.getSlotSold()+"\n");

            //Testing set functions
            System.out.println("Changing data, values after set functions: ");
            saleData.setProductNameSold("Cheetos");
            saleData.setPriceSold(4.99);
            saleData.setSlotSold("E8");

            System.out.println("New product name: " + saleData.getProductNameSold());
            System.out.println("New price: "  + saleData.getPriceSold());
            System.out.println("New slot: " + saleData.getSlotSold());

            //Testing VendingMachine functions
            VendingMachine funcTest = new VendingMachine();

            System.out.println("Testing verifyStock function: " + funcTest.verifyStock("E1") + " units needed.");
            System.out.println("Testing verifyExpiration function: is the item expired? " + funcTest.verifyExpiration("E1"));

            //testing Customer functions
            Customer changeTest = new Customer();
            changeTest.customerOrder("E1", 2.50);
            */
        }
    }
}