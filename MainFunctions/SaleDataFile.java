package MainFunctions;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.json.JSONArray;

import java.io.FileWriter;
import java.io.IOException;
import java.io.*;

import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

import java.text.DecimalFormat;

public class SaleDataFile {

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

        //set up classes from other files/formatters
        VendingMachineFile vendingMachineSacramento = new VendingMachineFile("vendingMachine_Sacramento");
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss");
        JSONArray arr = initArray("MainFunctions/SaleData.json");
        DecimalFormat df = new DecimalFormat("$#,##0.00");


        LocalDateTime now = LocalDateTime.now();
        String currentDate = String.valueOf(dtf.format(now));

        //put values and keys into new arrays
        JSONObject saleDataObj = new JSONObject();
        JSONObject valuesObject = new JSONObject();

        valuesObject.put("price", df.format(vendingMachineSacramento.getSlotPrice(slot)));
        valuesObject.put("slot", slot);
        valuesObject.put("productName", vendingMachineSacramento.getSlotProductName(slot));

        saleDataObj.put(currentDate, valuesObject);

        arr.put(saleDataObj);
        saveArray(arr, "MainFunctions/SaleData.json");

    }

    public String getDateSold() throws IOException {
        //Get product name sold on particular date

        JSONArray arr = initArray("MainFunctions/SaleData.json");

        String result = "";

        for(int i=0; i<arr.length(); i++) {

            String price = arr.get(i).toString();

            final Pattern pattern = Pattern.compile("\\d\\d/\\d\\d/\\d\\d\\d\\d\\s\\d\\d:\\d\\d:\\d\\d", Pattern.CASE_INSENSITIVE);
            final Matcher matcher = pattern.matcher(price);

            if (matcher.find()) {

                result +=  matcher.group(0) +"," ;
            }
        }
        return result;
    }

    public String getProductNameSold() throws IOException {
        //Get product name sold on particular date

        JSONArray arr = initArray("MainFunctions/SaleData.json");

        String result ="";

        for(int i=0; i<arr.length(); i++) {

            String price = arr.get(i).toString();

            final Pattern pattern = Pattern.compile("me\":\"(.*?)\"\\}", Pattern.CASE_INSENSITIVE);
            final Matcher matcher = pattern.matcher(price);



            if (matcher.find()) {

                result +=  matcher.group(1) + ",";

            }

        }
        return result;
    }

    public String getPriceSold() throws IOException {
        //Get product price sold on particular date/time

        JSONArray arr = initArray("MainFunctions/SaleData.json");

        String result ="";

        for(int i=0; i<arr.length(); i++) {

            String price = arr.get(i).toString();

            final Pattern pattern = Pattern.compile("\\d\\.\\d\\d", Pattern.CASE_INSENSITIVE);
            final Matcher matcher = pattern.matcher(price);

            if (matcher.find()) {

                result +=  matcher.group(0) + " ";
            }
        }
        return result;
    }


    public String getSlotSold() throws IOException {
        //Get product slot that was sold on particular date/time

        JSONArray arr = initArray("MainFunctions/SaleData.json");

        String result = "";

        for(int i=0; i<arr.length(); i++) {

            String price = arr.get(i).toString();

            final Pattern pattern = Pattern.compile("[a-zA-Z][0-9]+", Pattern.CASE_INSENSITIVE);
            final Matcher matcher = pattern.matcher(price);

            if (matcher.find()) {

                result +=  matcher.group(0) + " ";
            }
        }
        return result;
    }



}
