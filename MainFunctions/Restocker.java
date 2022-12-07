package MainFunctions;

import org.json.*;
import java.io.*;
import java.util.*;

class Restocker {
    static void checklist() throws Exception {
        VendingMachineFile vendingMachineSacramento = new VendingMachineFile("vendingMachine_Sacramento");
        int i = 1;
        char letter = 'A';
        do
        {
            while(i <= 8) {
                String str = String.valueOf(i);
                String let = String.valueOf(letter);
                String slot = let+i;
                int quantity = vendingMachineSacramento.getSlotQty(slot);
                String name = vendingMachineSacramento.getSlotProductName(slot);
                int refill = 15 - quantity;
                if (quantity == 15) {
                    i++;
                }
                else
                {
                    System.out.println(slot+" "+name+" x"+refill);
                    i++;
                }
            }
            letter++;
            i = 1;
        }while(letter <= 'E');
    }

    static void refill() throws Exception {
        VendingMachineFile vendingMachineSacramento = new VendingMachineFile("vendingMachine_Sacramento");
        int i = 1;
        char letter = 'A';
        do
        {
            while(i <= 8) {
                String str = String.valueOf(i);
                String let = String.valueOf(letter);
                String slot = let+i;
                int quantity = vendingMachineSacramento.getSlotQty(slot);
                String name = vendingMachineSacramento.getSlotProductName(slot);
                int refill = 15 - quantity;
                if (quantity == 15)
                    i++;
                else
                {
                    vendingMachineSacramento.setSlotQty(slot, 15);
                    int restocked = vendingMachineSacramento.getSlotQty(slot);
                    if(restocked == 15)
                        System.out.println(slot+" "+name+" fully stocked");
                    i++;
                }
            }
            letter++;
            i = 1;
        }while(letter <= 'E');
    }

    static void expired() throws Exception {
        VendingMachineFile vendingMachineSacramento = new VendingMachineFile("vendingMachine_Sacramento");
        int i = 1;
        char letter = 'A';
        System.out.println("\nExpired list");
        System.out.println("------------------------------------------------");
        do
        {
            while(i <= 8) {
                String str = String.valueOf(i);
                String let = String.valueOf(letter);
                String slot = let+i;
                int quantity = vendingMachineSacramento.getSlotQty(slot);
                String name = vendingMachineSacramento.getSlotProductName(slot);
                boolean exp = verify(slot);
                if (!exp)
                    i++;
                else {
                    System.out.println(slot+" "+name+" is expired");
                    i++;
                }
            }
            letter++;
            i = 1;
        }while(letter <= 'E');
    }

    static boolean verify(String slotNumber) throws Exception{
        VendingMachineFile vendingMachineSacramento = new VendingMachineFile("vendingMachine_Sacramento");
        Calendar localCalendar = Calendar.getInstance(TimeZone.getDefault());

        int currentMonth = localCalendar.get(Calendar.MONTH)+1;
        int currentYear = localCalendar.get(Calendar.YEAR) % 100;

        String slotExp = vendingMachineSacramento.getSlotExpDate(slotNumber);
        String slotMonth = slotExp.substring( 0, (slotExp.length()/2));
        String slotYear= slotExp.substring( (slotExp.length()/2));

        boolean isExpired = false;

        if ( Integer.parseInt(slotYear) < currentYear){
            isExpired = true;
        }
        else if(Integer.parseInt(slotMonth) < currentMonth && Integer.parseInt(slotYear) == currentYear){
            isExpired = true;
        }

        return isExpired;
    }

    public static void main(String args[]) throws Exception {
        VendingMachineFile vendingMachineSacramento = new VendingMachineFile("vendingMachine_Sacramento");
        Scanner input = new Scanner(System.in);
        Scanner dInput = new Scanner(System.in);
        expired();
        while(true) {
            System.out.println("\nReplace?");
            System.out.println("[1] Yes");
            System.out.println("[2] No");
            double decision = dInput.nextDouble();
            if(decision == 2)
                break;
            int qty = 15;
            System.out.println("Which slot?");
            String slot = input.nextLine();
            vendingMachineSacramento.setSlotQty(slot, qty);
            System.out.println("New product's name?");
            String name = input.nextLine();
            vendingMachineSacramento.setSlotProductName(slot, name);
            System.out.println("Price?");
            double price = dInput.nextDouble();
            vendingMachineSacramento.setSlotPrice(slot, price);
            System.out.println("Expiration date?");
            String date = input.nextLine();
            vendingMachineSacramento.setSlotExpDate(slot, date);
            System.out.println(slot+" replaced. Thank you!");
            expired();
        }
        System.out.println("\nChecklist");
        System.out.println("------------------------------------------------");
        checklist();
        System.out.println("\nRestock? Y/N");
        String decision = input.nextLine();
        switch(decision)
        {
            case "Y":
                refill();
                checklist();
                System.out.println("Items refilled! Thank you! Have a great day!");
                break;
            case "N":
                System.out.println("Good-bye!");
                break;
        }
    }
}