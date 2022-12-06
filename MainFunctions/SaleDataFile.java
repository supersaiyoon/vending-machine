package MainFunctions;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.json.JSONArray;

import java.io.FileWriter;
import java.io.IOException;
import java.io.*;

import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

public class SaleDataFile {

    public String dateSold;   // Multiple dates will need to be accessed
    public JSONObject tokenObj;  // Access nested JSON objects through this.
    public String fileNameJSON = "MainFunctions/SaleData.json";

    public SaleDataFile() {

        String fileName = "MainFunctions/SaleData.json";

        String json = "";
        File jsonFile = new File(fileName);
        if (!jsonFile.exists()) {
            throw new NullPointerException("Cannot find JSON file.");
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(jsonFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                json += line;
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        JSONTokener tokener = new JSONTokener(json);
        //tokenObj = new JSONObject(tokener);
        JSONArray readArr = new JSONArray(tokener);

    }

    public void writeFile() {
        try (FileWriter file = new FileWriter(fileNameJSON)) {
            file.write(tokenObj.toString(2));  // Integer is size of indent in JSON file.
            file.flush();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static JSONArray initArray(String jsonFileName) throws IOException {
        String json = "";
        File jsonFile = new File(jsonFileName);
        if (!jsonFile.exists()) {
            return new JSONArray();
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(jsonFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                json += line;
            }
        }

        JSONTokener tokener = new JSONTokener(json);
        JSONArray readArr = new JSONArray(tokener);
        return readArr;
    }

    private static JSONArray saveArray(JSONArray arr, String jsonFileName) throws IOException {
        try (FileWriter Data = new FileWriter(jsonFileName)) {
            Data.write(arr.toString(4)); // setting spaces for indent
        }
        return arr;
    }


    public void newSaleDate(String slot) throws IOException {  // Need to do this before using any methods.

        VendingMachineFile vendingMachineSacramento = new VendingMachineFile("vendingMachine_Sacramento");
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        JSONArray arr = initArray("MainFunctions/SaleData.json");

        LocalDateTime now = LocalDateTime.now();
        String currentDate = String.valueOf(dtf.format(now));

        JSONObject saleDataObj = new JSONObject();
        JSONObject valuesObject = new JSONObject();

        valuesObject.put("price", vendingMachineSacramento.getSlotPrice(slot));
        valuesObject.put("slot", slot);
        valuesObject.put("productName", vendingMachineSacramento.getSlotProductName(slot));

        saleDataObj.put(currentDate, valuesObject);

        arr.put(saleDataObj);
        saveArray(arr, "MainFunctions/SaleData.json");

    }

    public String getProductNameSold() {
        //Get product name sold on particular date

        JSONObject saleDataObj = tokenObj.getJSONObject(dateSold);

        String productName = saleDataObj.getString("productName");
        return productName;
    }

    public void setProductNameSold(String productName) {

        JSONObject saleDataObj = tokenObj.getJSONObject(dateSold);

        saleDataObj.put("productName", productName);
        writeFile();
    }

    /*public Double getPriceSold(String date) throws IOException {
        //Get product price sold on particular date/time

        JSONArray arr = initArray("MainFunctions/SaleData.json");

        Double priceSold = arr.getJSONObject(0).getString(date);
        return priceSold;
    }*/

    public void setPriceSold(double priceSold) {

        JSONObject saleDataObj = tokenObj.getJSONObject(dateSold);

        saleDataObj.put("price", priceSold);
        writeFile();
    }


    public String getSlotSold() {
        //Get product slot that was sold on particular date/time

        JSONObject saleDataObj = tokenObj.getJSONObject(dateSold);

        String slotSold = saleDataObj.getString("slot");
        return slotSold;
    }

    public void setSlotSold(String slotSold) {

        JSONObject saleDataObj = tokenObj.getJSONObject(dateSold);

        saleDataObj.put("slot", slotSold);
        writeFile();
    }

}
