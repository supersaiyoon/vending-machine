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

    public SaleDataFile() {
        String fileName = "SaleData.json";
        InputStream is = SaleDataFile.class.getResourceAsStream(fileName);

        if (is == null) {
            throw new NullPointerException("Cannot find JSON file.");
        }

        JSONTokener tokener = new JSONTokener(is);
        tokenObj = new JSONObject(tokener);
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

    Double getPriceSold() {
        //Get product price sold on particular date/time

        JSONObject saleDataObj = tokenObj.getJSONObject(dateSold);

        Double priceSold = saleDataObj.getDouble("price");
        return priceSold;
    }

    String getSlotSold() {
        //Get product slot that was sold on particular date/time

        JSONObject saleDataObj = tokenObj.getJSONObject(dateSold);

        String slotSold = saleDataObj.getString("slot");
        return slotSold;
    }

}

class Error {

}

class VendingMachineFile {
    public String machineName;   // There will be multiple vending machines in the JSON file.
    public JSONObject tokenObj;  // Access nested JSON objects through this.

    public VendingMachineFile() {
        String fileName = "VendingMachineFile.json";
        InputStream is = VendingMachineFile.class.getResourceAsStream(fileName);

        if (is == null) {
            throw new NullPointerException("Cannot find JSON file.");
        }

        JSONTokener tokener = new JSONTokener(is);
        tokenObj = new JSONObject(tokener);
    }

    void setMachineName(String machineName) {  // Need to do this before using any methods.
        this.machineName = machineName;
    }

    String getAddress() {
        // Return full address.
        // streetName, city, country
        JSONObject vendingMachineObj = tokenObj.getJSONObject(machineName);
        JSONObject addressObj = vendingMachineObj.getJSONObject("address");

        String street = addressObj.getString("street");
        String city = addressObj.getString("city");
        String state = addressObj.getString("state");
        String zipCode = addressObj.getString("zipCode");

        String address = String.join(", ", street, city, state);
        address = String.join(" ", address, zipCode);  // Joined ZIP code separately to not include comma.

        return address;
    }

    int getSlotQty(String slotNum) {
        JSONObject vendingMachineObj = tokenObj.getJSONObject(machineName);
        JSONObject slotObj = vendingMachineObj.getJSONObject("slot");
        JSONObject slotName = slotObj.getJSONObject(slotNum);

        return slotName.getInt("quantity");
    }

    double getSlotPrice(String slotNum) {
        JSONObject vendingMachineObj = tokenObj.getJSONObject(machineName);
        JSONObject slotObj = vendingMachineObj.getJSONObject("slot");
        JSONObject slotName = slotObj.getJSONObject(slotNum);

        return slotName.getDouble("price");
    }

    String getSlotProductName(String slotNum) {
        JSONObject vendingMachineObj = tokenObj.getJSONObject(machineName);
        JSONObject slotObj = vendingMachineObj.getJSONObject("slot");
        JSONObject slotName = slotObj.getJSONObject(slotNum);

        return slotName.getString("productName");
    }

    String getExpDate(String slotNum) {
        JSONObject vendingMachineObj = tokenObj.getJSONObject(machineName);
        JSONObject slotObj = vendingMachineObj.getJSONObject("slot");
        JSONObject slotName = slotObj.getJSONObject(slotNum);

        return slotName.getString("expDate");
    }
}






class Main {
    public static void main(String args[]) throws Exception{

        VendingMachineFile vendingMachine = new VendingMachineFile();

        // Must set vending machine name before using methods.
        // This was added so that different vending machines can be selected.
        vendingMachine.setMachineName("vendingMachine_Sacramento");

        System.out.println(vendingMachine.getAddress());

        System.out.println(vendingMachine.getSlotQty("E1"));

        System.out.println(vendingMachine.getSlotPrice("E1"));

        System.out.println(vendingMachine.getSlotProductName("E1"));

        System.out.println(vendingMachine.getExpDate("E1"));
        new KeyPadGUI();
        
        //Buffer for SaleData
        System.out.println();


        //Testing SaleData JSON
        SaleDataFile saleData = new SaleDataFile();
        //Set date of sale for specific data
        saleData.setSaleDate("11.14.22.10.31.22");

        System.out.println("Sale date set.");

        System.out.println(saleData.getProductNameSold());
        System.out.println("Product name retrieved.");

        System.out.println(saleData.getPriceSold());
        System.out.println("Product price retrieved.");

        System.out.println(saleData.getSlotSold());
        System.out.println("Product location retrieved.");


        //System.out.println(addressObj.getString("streetName"));

        //addressObj.put("streetName", "789 Main St");

        // Include the below in setter functions.
        /*try (FileWriter file = new FileWriter("VendingMachineFile.json")) {
            file.write(tokenObj.toString(2));
            file.flush();
        }
        catch (IOException e) {
            e.printStackTrace();
        }*/
    }
}