//package Customer;
import MainFunctions.*;

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
        vendingMachineSacramento.setSlotProductName("E1", "Cheeto Chips");
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

        //Testing VendingMachine functions
        VendingMachine funcTest = new VendingMachine();

        System.out.println("Testing verifyStock function: " + funcTest.verifyStock("E1") + " units needed.");
        System.out.println("Testing verifyExpiration function: is the item expired? " + funcTest.verifyExpiration("E1"));

        //testing Customer functions
        Customer changeTest = new Customer();
        changeTest.customerOrder("E1", 2.50);

    }
}