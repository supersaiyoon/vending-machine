import GUI.*;

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
import java.text.SimpleDateFormat;
import java.text.DateFormat;

class VendingMachine {
    String location;

    void processOrder() {}
    void addItem() {}
    void removeItem() {}
    void changePrice() {}
    void getSaleData() {}
    void verifyExpiration() {}
    void verifyRecall() {}
    void verifyStock() {}
}


class Customer {



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
    public JSONObject tokenObj;             // Initializing here so writeFile() has access to highest level JSON object.
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

        System.out.println("\nAddress: " + vendingMachineSacramento.getAddress());
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
        System.out.println("Exp. Date: " + vendingMachineSacramento.getSlotExpDate("E1"));

        // new KeyPadGUI();

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



    }
}