import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.JSONObject;
import org.json.JSONTokener;
import java.io.InputStream;

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

class Transaction {
    
}

class Error {

}

class VendingMachineFile {
    public String slotNum;       // Must store value within class for functions to work.
    public JSONObject tokenObj;  // Access nested fields through this.

    public VendingMachineFile() {
        String fileName = "VendingMachineFile.json";
        InputStream is = Main.class.getResourceAsStream(fileName);

        if (is == null) {
            throw new NullPointerException("Cannot find JSON file.");
        }

        JSONTokener tokener = new JSONTokener(is);
        tokenObj = new JSONObject(tokener);
    }

    void setSlot(String slotNum) {
        this.slotNum = slotNum;
    }

    public static String getAddress(JSONObject tokenObj) {
        // Return full address.
        // streetName, city, country
        String address = "";
    
        return address;
    }

    int getSlotQty() {
        JSONObject vendingMachineObj = tokenObj.getJSONObject("vendingMachine_Sacramento");
        JSONObject slotObj = vendingMachineObj.getJSONObject("slot");
        JSONObject slotName = slotObj.getJSONObject(slotNum);

        return slotName.getInt("quantity");
    }

    double getSlotPrice() {
        double price = 0;

        return price;
    }

    String getSlotProductName() {

    }

    String getExpDate() {

    }
}


class Main {
    public static void main(String args[]) {

        VendingMachineFile testFile = new VendingMachineFile();
        
        System.out.println(testFile.getSlotQty());
       

        // Leap frog to get to the specific nested level you want.
        // These should become functions.


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