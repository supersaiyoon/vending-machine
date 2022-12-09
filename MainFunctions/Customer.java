package MainFunctions;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Scanner;

public class Customer {

    //for using functions in other classes
    VendingMachine classObj = new VendingMachine();
    VendingMachineFile vendingMachineSacramento = new VendingMachineFile("vendingMachine_Sacramento");

    public double customerOrder(String slotNumber, double moneyInsert){

        //for returning proper rounded change format #.## instead of massive doubles
        DecimalFormat df = new DecimalFormat("$#,##0.00");

        int newItemQty = vendingMachineSacramento.getSlotQty(slotNumber);
        double itemPrice = vendingMachineSacramento.getSlotPrice(slotNumber);
        double moneyDifference = itemPrice - moneyInsert;

            while ( itemPrice > moneyInsert) {

                System.out.println();
                System.out.println("ERROR: Not enough money inserted, please insert at least " + df.format(moneyDifference) + " more.");
                moneyInsert = additionalChange(moneyInsert);
                moneyDifference = itemPrice - moneyInsert;

            }

            vendingMachineSacramento.setSlotQty(slotNumber, newItemQty-1);
            double changeReturn = moneyInsert - itemPrice;

            if (changeReturn ==0){
                System.out.println("Thank you for your purchase!");

            }

            else{
                System.out.println("Thank you, your change today is: " + df.format(changeReturn));

            }
            return changeReturn;


    }

    public double additionalChange(double money){

        Scanner getMoney = new Scanner(System.in);
        System.out.println("Enter additional change: ");

        double setMoney = Double.parseDouble(getMoney.nextLine());
        double newChange;

        newChange = setMoney+money;
        return newChange;

    }

    public void newSaleData(String slot) throws IOException {

        SaleDataFile sale = new SaleDataFile();

        sale.newSaleDate(slot);

    }


}