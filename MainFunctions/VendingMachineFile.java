package MainFunctions;

import EventHandlers.*;

import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;

public class VendingMachineFile {
    public String fileNameJSON = "MainFunctions/VendingMachineFile.json";    // Just in case we need to change the file name.
    public JSONObject tokenObj;             // Initializing here so writeFile() has access to the highest level JSON object.
    public JSONObject vendingMachineObj;    // Used by constructor only. Do not touch.
    public JSONObject addressObj;           // Use methods on this to modify vending machine address info.
    public JSONObject slotObj;              // Use methods on this to modify vending machine slot.

    public VendingMachineFile() {};

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

    public String getAddress() {
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

    public void setAddressCountry(String country) {
        addressObj.put("country", country);
        writeFile();
    }

    public void setAddressStreet(String street) {
        addressObj.put("street", street);
        writeFile();
    }

    public void setAddressCity(String city) {
        addressObj.put("city", city);
        writeFile();
    }

    public void setAddressState(String state) {
        addressObj.put("state", state.toUpperCase());
        writeFile();
    }

    public void setAddressZipCode(String zipCode) {
        addressObj.put("zipCode", zipCode);
        writeFile();
    }

    public int getSlotQty(String slotNum) {
        JSONObject slotNumObj = slotObj.getJSONObject(slotNum);

        return slotNumObj.getInt("quantity");
    }

    public void setSlotQty(String slotNum, int quantity) {
        JSONObject slotNumObj = slotObj.getJSONObject(slotNum);

        slotNumObj.put("quantity", quantity);
        writeFile();
    }

    public double getSlotPrice(String slotNum) {
        JSONObject slotNumObj = slotObj.getJSONObject(slotNum);

        return slotNumObj.getDouble("price");
    }

    public void setSlotPrice(String slotNum, double price) {
        JSONObject slotNumObj = slotObj.getJSONObject(slotNum);

        slotNumObj.put("price", price);
        writeFile();
    }

    public String getSlotProductName(String slotNum) {
        JSONObject slotNumObj = slotObj.getJSONObject(slotNum);

        return slotNumObj.getString("productName");
    }

    public void setSlotProductName(String slotNum, String productName) {
        JSONObject slotNumObj = slotObj.getJSONObject(slotNum);

        slotNumObj.put("productName", productName);
        writeFile();
    }

    public String getSlotExpDate(String slotNum) {
        JSONObject slotNumObj = slotObj.getJSONObject(slotNum);

        return slotNumObj.getString("expDate");
    }

    public void setSlotExpDate(String slotNum, String expDate) {
        JSONObject slotNumObj = slotObj.getJSONObject(slotNum);

        slotNumObj.put("expDate", expDate);
        writeFile();
    }
}