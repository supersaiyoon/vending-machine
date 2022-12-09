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

            int itemQty = vendingMachineSacramento.getSlotQty(slot);

            //while expiration date has not passed
            if (!classObj.verifyExpiration(slot) && itemQty > 0) {
                System.out.println("You want to purchase " + vendingMachineSacramento.getSlotProductName(slot));
                System.out.println("Please enter " + df.format(vendingMachineSacramento.getSlotPrice(slot)));
                System.out.println();

                Scanner getMoney = new Scanner(System.in);
                System.out.println("Please insert money: ");
                double moneyInsert = 0;
                while (true) {
                    System.out.println("Please follow ##.## format:");
                    try {
                        moneyInsert = Double.parseDouble(getMoney.nextLine());
                        break; // will only get to here if input was a double
                    } catch (NumberFormatException ignore) {
                        System.out.println("ERROR: Invalid input");
                    }
                }

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
        }
    }
}