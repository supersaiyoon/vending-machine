package MainFunctions;

import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;

public class SaleDataFile {

    public String dateSold;   // Multiple dates will need to be accessed
    public JSONObject tokenObj;  // Access nested JSON objects through this.
    public String fileNameJSON = "MainFunctions/SaleData.json";

    public SaleDataFile() {
        String fileName = "SaleData.json";
        InputStream is = SaleDataFile.class.getResourceAsStream(fileName);

        if (is == null) {
            throw new NullPointerException("Cannot find JSON file.");
        }

        JSONTokener tokener = new JSONTokener(is);
        tokenObj = new JSONObject(tokener);
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

    public void setSaleDate(String saleDate) {  // Need to do this before using any methods.
        this.dateSold = saleDate;
        System.out.println(dateSold);

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

    public Double getPriceSold() {
        //Get product price sold on particular date/time

        JSONObject saleDataObj = tokenObj.getJSONObject(dateSold);

        Double priceSold = saleDataObj.getDouble("price");
        return priceSold;
    }

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
