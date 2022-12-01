package MainFunctions;

import java.util.Calendar;
import java.util.TimeZone;

public class VendingMachine {



    //for using functions in other classes
    VendingMachineFile vendingMachineSacramento = new VendingMachineFile("vendingMachine_Sacramento");

    String location;

    void processOrder() {}

    //add item to desired slot using specified parameters
    public void addItem(String slotNumber, int slotQty, double slotPrice, String productName, String expDate) {

        //set quantity to desired int number
        vendingMachineSacramento.setSlotQty(slotNumber,slotQty);
        //set price to desired double number
        vendingMachineSacramento.setSlotPrice(slotNumber, slotPrice);
        //set product name to desired string name
        vendingMachineSacramento.setSlotProductName(slotNumber, productName);
        //set expiration date to desired string mmYY
        vendingMachineSacramento.setSlotExpDate(slotNumber, expDate);


    }

    //remove item from slot entirely
    public void removeItem(String slotNumber) {

        //set quantity to 0
        vendingMachineSacramento.setSlotQty(slotNumber,0);
        //set price to 0.00
        vendingMachineSacramento.setSlotPrice(slotNumber, 0.00);
        //set product name to blank string
        vendingMachineSacramento.setSlotProductName(slotNumber, " ");
        //set expiration date to blank string
        vendingMachineSacramento.setSlotExpDate(slotNumber, " ");

    }
    void changePrice() {}
    void getSaleData() {}

    //returns true if item in specified slot is expired, false if it is not
    public boolean verifyExpiration(String slotNumber) {

        //Set up calendar month and year to check against inventory
        Calendar localCalendar = Calendar.getInstance(TimeZone.getDefault());

        int currentMonth = localCalendar.get(Calendar.MONTH)+1;
        int currentYear = localCalendar.get(Calendar.YEAR) % 100;

        String slotExp = vendingMachineSacramento.getSlotExpDate(slotNumber);
        String slotMonth = slotExp.substring( 0, (slotExp.length()/2));
        String slotYear= slotExp.substring( (slotExp.length()/2));

        //check if item in slot is expired
        boolean isExpired = false;

        if ( Integer.parseInt(slotYear) < currentYear){
            isExpired = true;
        }
        else if(Integer.parseInt(slotMonth) < currentMonth && Integer.parseInt(slotYear) == currentYear){
            isExpired = true;
        }

        return isExpired;

    }

    void verifyRecall() {}

    //checks if item in slot is fully stocked (15) and returns units needed, and if nothing needs to be added returns 0
    public int verifyStock(String slotNumber) {
        int stockNeeded = 15;
        int stockQty = vendingMachineSacramento.getSlotQty(slotNumber);

        if (stockQty < 1) {

            return stockNeeded;

        } else if (stockQty < 15 && stockQty > 1) {

            stockNeeded = stockNeeded - stockQty;
            return stockNeeded;

        } else
            stockNeeded = 0;
        return stockNeeded;
    }
}
