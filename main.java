import GUI.*;

import java.io.*;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.JSONObject;
import org.json.JSONTokener;
import java.io.InputStream;

import java.awt.*;
import javax.swing.*;
import javax.swing.text.NumberFormatter;

import java.util.Date;
import java.util.Calendar;
import java.util.TimeZone;

import java.text.DecimalFormat;
import java.util.Scanner;



class VendingMachine {



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


class Customer {

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

class Restocker {

}

// Similar to VendingMachineFile class. Getter and setter functions.
// Write to SaleData.json after each transaction. Verify there are no errors.
class SaleDataFile {

    public String dateSold;   // Multiple dates will need to be accessed
    public JSONObject tokenObj;  // Access nested JSON objects through this.
    public String fileNameJSON = "SaleData.json";

    public SaleDataFile() {
        String fileName = "SaleData.json";
        InputStream is = SaleDataFile.class.getResourceAsStream(fileName);

        if (is == null) {
            throw new NullPointerException("Cannot find JSON file.");
        }

        JSONTokener tokener = new JSONTokener(is);
        tokenObj = new JSONObject(tokener);
    }

    void writeFile() {
        try (FileWriter file = new FileWriter(fileNameJSON)) {
            file.write(tokenObj.toString(2));  // Integer is size of indent in JSON file.
            file.flush();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    void setSaleDate(String saleDate) {  // Need to do this before using any methods.
        this.dateSold = saleDate;
        System.out.println(dateSold);

    }

    String getProductNameSold() {
        //Get product name sold on particular date

        JSONObject saleDataObj = tokenObj.getJSONObject(dateSold);

        String productName = saleDataObj.getString("productName");
        return productName;
    }

    void setProductNameSold(String productName) {

        JSONObject saleDataObj = tokenObj.getJSONObject(dateSold);

        saleDataObj.put("productName", productName);
        writeFile();
    }

    Double getPriceSold() {
        //Get product price sold on particular date/time

        JSONObject saleDataObj = tokenObj.getJSONObject(dateSold);

        Double priceSold = saleDataObj.getDouble("price");
        return priceSold;
    }

    void setPriceSold(double priceSold) {

        JSONObject saleDataObj = tokenObj.getJSONObject(dateSold);

        saleDataObj.put("price", priceSold);
        writeFile();
    }


    String getSlotSold() {
        //Get product slot that was sold on particular date/time

        JSONObject saleDataObj = tokenObj.getJSONObject(dateSold);

        String slotSold = saleDataObj.getString("slot");
        return slotSold;
    }

    void setSlotSold(String slotSold) {

        JSONObject saleDataObj = tokenObj.getJSONObject(dateSold);

        saleDataObj.put("slot", slotSold);
        writeFile();
    }

}

class Error {

}

class VendingMachineFile {
    public String fileNameJSON = "VendingMachineFile.json";    // Just in case we need to change the file name.
    public JSONObject tokenObj;             // Initializing here so writeFile() has access to the highest level JSON object.
    public JSONObject vendingMachineObj;    // Used by constructor only. Do not touch.
    public JSONObject addressObj;           // Use methods on this to modify vending machine address info.
    public JSONObject slotObj;              // Use methods on this to modify vending machine slot.

    public VendingMachineFile(String vendingMachineName) {
        String fileName = "VendingMachineFile.json";
        InputStream is = VendingMachineFile.class.getResourceAsStream(fileName);

        if (is == null) {
            throw new NullPointerException("Cannot find JSON file.");
        }

        JSONTokener tokener = new JSONTokener(is);
        tokenObj = new JSONObject(tokener);
        vendingMachineObj = tokenObj.getJSONObject(vendingMachineName);
        addressObj = vendingMachineObj.getJSONObject("address");
        slotObj = vendingMachineObj.getJSONObject("slot");
    }

    // Call this method every time you replace any values in VendingMachineFile.json.
    void writeFile() {
        try (FileWriter file = new FileWriter(fileNameJSON)) {
            file.write(tokenObj.toString(4));  // Integer is size of indent in JSON file.
            file.flush();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    String getAddress() {
        // Return full address.
        // streetName, city, country
        String street = addressObj.getString("street");
        String city = addressObj.getString("city");
        String state = addressObj.getString("state");
        String zipCode = addressObj.getString("zipCode");

        String address = String.join(", ", street, city, state);
        address = String.join(" ", address, zipCode);  // Joined ZIP code separately to not include comma.

        return address;
    }

    void setAddressCountry(String country) {
        addressObj.put("country", country);
        writeFile();
    }

    void setAddressStreet(String street) {
        addressObj.put("street", street);
        writeFile();
    }

    void setAddressCity(String city) {
        addressObj.put("city", city);
        writeFile();
    }

    void setAddressState(String state) {
        addressObj.put("state", state.toUpperCase());
        writeFile();
    }

    void setAddressZipCode(String zipCode) {
        addressObj.put("zipCode", zipCode);
        writeFile();
    }

    int getSlotQty(String slotNum) {
        JSONObject slotNumObj = slotObj.getJSONObject(slotNum);

        return slotNumObj.getInt("quantity");
    }

    void setSlotQty(String slotNum, int quantity) {
        JSONObject slotNumObj = slotObj.getJSONObject(slotNum);

        slotNumObj.put("quantity", quantity);
        writeFile();
    }

    double getSlotPrice(String slotNum) {
        JSONObject slotNumObj = slotObj.getJSONObject(slotNum);

        return slotNumObj.getDouble("price");
    }

    void setSlotPrice(String slotNum, double price) {
        JSONObject slotNumObj = slotObj.getJSONObject(slotNum);

        slotNumObj.put("price", price);
        writeFile();
    }

    String getSlotProductName(String slotNum) {
        JSONObject slotNumObj = slotObj.getJSONObject(slotNum);

        return slotNumObj.getString("productName");
    }

    void setSlotProductName(String slotNum, String productName) {
        JSONObject slotNumObj = slotObj.getJSONObject(slotNum);

        slotNumObj.put("productName", productName);
        writeFile();
    }

    String getSlotExpDate(String slotNum) {
        JSONObject slotNumObj = slotObj.getJSONObject(slotNum);

        return slotNumObj.getString("expDate");
    }

    void setSlotExpDate(String slotNum, String expDate) {
        JSONObject slotNumObj = slotObj.getJSONObject(slotNum);

        slotNumObj.put("expDate", expDate);
        writeFile();
    }
}


class Main {
    public static void main(String args[]) throws Exception{

        VendingMachineFile vendingMachineSacramento = new VendingMachineFile("vendingMachine_Sacramento");

        /*System.out.println("\nAddress: " + vendingMachineSacramento.getAddress());
        System.out.println("Product Name: " + vendingMachineSacramento.getSlotProductName("E1"));
        System.out.println("Price: $" + vendingMachineSacramento.getSlotPrice("E1"));
        System.out.println("Quantity: " + vendingMachineSacramento.getSlotQty("E1"));
        System.out.println("Exp. Date: " + vendingMachineSacramento.getSlotExpDate("E1"));


        System.out.println("\nData AFTER writing to file:");
        // Testing address setter functions.
        vendingMachineSacramento.setAddressStreet("23 Bulls Ln");
        vendingMachineSacramento.setAddressCity("Chicago");
        vendingMachineSacramento.setAddressState("IL");
        vendingMachineSacramento.setAddressZipCode("90210");

        // Testing specific slot setter functions.
        vendingMachineSacramento.setSlotProductName("E1", "Chips Ahoy Cookies");
        vendingMachineSacramento.setSlotPrice("E1", 4.99);
        vendingMachineSacramento.setSlotQty("E1", 8);
        vendingMachineSacramento.setSlotExpDate("E1", "0214");

        // Printing after changing values in VendingMachineFile.json.
        System.out.println("Address: " + vendingMachineSacramento.getAddress());
        System.out.println("Product Name: " + vendingMachineSacramento.getSlotProductName("E1"));
        System.out.println("Price: $" + vendingMachineSacramento.getSlotPrice("E1"));
        System.out.println("Quantity: " + vendingMachineSacramento.getSlotQty("E1"));
        System.out.println("Exp. Date: " + vendingMachineSacramento.getSlotExpDate("E1"));*/

        Process theProcess = null;
        theProcess = Runtime.getRuntime().exec("java KeyPadGUI");

        KeyPadGUI keypad = new KeyPadGUI();

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