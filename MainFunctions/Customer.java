package MainFunctions;


import java.text.DecimalFormat;
import java.util.Scanner;

public class Customer {

    //for using functions in other classes
    VendingMachine classObj = new VendingMachine();
    VendingMachineFile vendingMachineSacramento = new VendingMachineFile("vendingMachine_Sacramento");

    public double customerOrder(String slotNumber, double moneyInsert){

        //for returning proper rounded change format #.## instead of massive doubles
        DecimalFormat df = new DecimalFormat("#.##");

        int newItemQty = vendingMachineSacramento.getSlotQty(slotNumber);
        double itemPrice = vendingMachineSacramento.getSlotPrice(slotNumber);
        double moneyDifference = itemPrice - moneyInsert;


        //while expiration date has not passed
        if (!classObj.verifyExpiration(slotNumber) && newItemQty > 0){

            System.out.println("Item costs $" + itemPrice +" please insert money.");

            while ( itemPrice > moneyInsert) {

                System.out.println("Not enough money inserted, please insert at least $" + df.format(moneyDifference) + " more.");
                moneyInsert = additionalChange(moneyInsert);
                moneyDifference = itemPrice - moneyInsert;

            }

            vendingMachineSacramento.setSlotQty(slotNumber, newItemQty-1);
            double changeReturn = moneyInsert - itemPrice;
            System.out.println("Thank you, your change today is: $" + df.format(changeReturn));
            return changeReturn;

            /*//attempting to print proper change increments, wasn't working so will try later
            StringBuilder change = new StringBuilder();

            while (changeReturn > 0.01f) {
                if (changeReturn >= 100.0f) {
                    change.append("ONE HUNDRED");
                    changeReturn -= 100.0f;
                } else if (changeReturn >= 50.0f) {
                    change.append("FIFTY");
                    changeReturn -= 50.0f;
                } else if (changeReturn >= 20.0f) {
                    change.append("TWENTY");
                    changeReturn -= 20.0f;
                } else if (changeReturn >= 10.0f) {
                    change.append("TEN");
                    changeReturn -= 10.0f;
                } else if (changeReturn >= 5.0f) {
                    change.append("FIVE");
                    changeReturn -= 5.0f;
                } else if (changeReturn >= 2.0f) {
                    change.append("TWO");
                    changeReturn -= 2.0f;
                } else if (changeReturn >= 1.0f) {
                    change.append("ONE");
                    changeReturn -= 1.0f;
                } else if (changeReturn >= 0.25f) {
                    change.append("QUARTER");
                    changeReturn -= 0.25f;
                } else if (changeReturn >= 0.1f) {
                    change.append("DIME");
                    changeReturn -= 0.1f;
                } else if (changeReturn >= 0.05f) {
                    change.append("NICKEL");
                    changeReturn -= 0.05f;
                } else {
                    change.append("PENNY");
                    changeReturn -= 0.01f;
                }
                change.append(",");
            }
            change.setLength(change.length() - 1);

            return change.toString();*/

        }

        //if product is expired or no quantity
        else  {

            System.out.println("ERROR: Item is unavailable, please choose a different item.");
            return -1.00;

        }

    }

    public double additionalChange(double money){

        Scanner getMoney = new Scanner(System.in);
        System.out.println("Enter additional change: ");

        double setMoney = Double.parseDouble(getMoney.nextLine());
        double newChange;

        newChange = setMoney+money;
        return newChange;

    }


}