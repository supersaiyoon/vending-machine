import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


import org.json.JSONObject;
import org.json.JSONTokener;

import java.util.Date;

class VendingMachineFile {

}

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

class Main {
    public static void main(String arg[]) {
        // JSON parser object to parse read file
        JSONParser jsonParser = new JSONParser();
        
        try (FileReader reader = new FileReader("VendingMachineFile.json")) {
            // Read JSON file
            Object obj = jsonParser.parse(reader);

            JSONObject vendingMachineList = (JSONObject) obj;
            String prettyJson = vendingMachineList.toJSONString();
            System.out.println(prettyJson);
            
            // Grab value from nested field in JSON
            JSONObject testMachine = (JSONObject) vendingMachineList.get("vendingMachineTest");
            JSONObject testMachineAddress = (JSONObject) testMachine.get("address");
            String streetName = (String) testMachineAddress.get("streetName");
            System.out.println(streetName);

            // Accessing product name in slot A1
            JSONObject testMachineA1Slot = (JSONObject) testMachine.get("slot");
            JSONObject slot = (JSONObject) testMachineA1Slot.get("A1");
            String productName = (String) slot.get("productName");
            System.out.println(productName);

            slot.put("productName","Pringles");
            productName = (String) slot.get("productName");
            System.out.println(productName);
            
            // Write JSON file
            try (FileWriter file = new FileWriter("VendingMachineFile.json")) {
                file.write(vendingMachineList.toString());
                file.flush();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        catch (ParseException e) {
            e.printStackTrace();
        }
    }
}