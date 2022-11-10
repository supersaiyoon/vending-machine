import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.JSONObject;
import org.json.JSONTokener;
import java.io.InputStream;

import java.awt.*;
import javax.swing.*;

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
    public static void main(String args[]) {

        VendingMachineFile vendingMachine = new VendingMachineFile();

        // Must set vending machine name before using methods.
        // This was added so that different vending machines can be selected.
        vendingMachine.setMachineName("vendingMachine_Sacramento");

        System.out.println(vendingMachine.getAddress());

        System.out.println(vendingMachine.getSlotQty("E1"));

        System.out.println(vendingMachine.getSlotPrice("E1"));

        System.out.println(vendingMachine.getSlotProductName("E1"));
       
        System.out.println(vendingMachine.getExpDate("E1"));

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