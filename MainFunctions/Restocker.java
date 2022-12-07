package MainFunctions;

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
        System.out.println("\nExpired Items list");
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
        Scanner Input2 = new Scanner(System.in);
        Scanner Input3 = new Scanner(System.in);


        int choice;
        while(true) {

            System.out.println("Restocker Interface Main Menu");
            System.out.println("[1] Check expired items ");
            System.out.println("[2] Check if item quantities are full ");
            System.out.println("[3] Replace item in vending machine ");
            System.out.println("[4] Exit Restocker Interface ");
            System.out.println("Enter your choice: ");


            choice = input.nextInt();

            switch (choice) {

                case 1:
                    int menuChoice;
                    expired();
                    System.out.println("\nReturn to Main Menu?");
                    System.out.println("[1] Yes");

                    menuChoice = input.nextInt();

                    if(menuChoice ==1){
                        System.out.println("Returning to Main Menu...\n");
                        break;
                    }
                    else{
                        System.out.println("ERROR: Unaccepted input, please insert [1] to return.");
                    }

                    System.out.println();
                    break;

                case 2:

                    System.out.println("\nItems below max quantity checklist:");
                    System.out.println("------------------------------------------------");
                    checklist();

                    int restockChoice;
                    System.out.println("\nRestock items to max quantity?");
                    System.out.println("[1] Yes");
                    System.out.println("[2] No");
                    restockChoice = input.nextInt();

                    if (restockChoice == 1) {
                            refill();
                            checklist();
                            System.out.println("Items refilled, returning to Main Menu... \n");
                            break;

                        }
                    else if(restockChoice ==2) {
                            System.out.println("Returning to Main Menu... \n");
                            break;
                        }

                    else
                            System.out.println("ERROR: Unaccepted input, please insert [1] or [2].");
                        //break;


                case 3:
                    int replaceChoice;

                    System.out.println("\nReplace item in Vending Machine?");
                    System.out.println("[1] Yes");
                    System.out.println("[2] No");
                    replaceChoice = input.nextInt();

                    if(replaceChoice == 1) {
                        int qty = 15;

                        System.out.println("Which slot?");
                        String slot = Input2.nextLine();
                        vendingMachineSacramento.setSlotQty(slot, qty);

                        System.out.println("New product's name?");
                        String name = Input2.nextLine();
                        vendingMachineSacramento.setSlotProductName(slot, name);

                        System.out.println("Price?");
                        double price = Input2.nextDouble();
                        vendingMachineSacramento.setSlotPrice(slot, price);

                        System.out.println("Expiration date?");
                        String date = Input3.nextLine();
                        vendingMachineSacramento.setSlotExpDate(slot, date);

                        System.out.println(slot + " replaced. Thank you!\n");
                        break;
                    }

                    else if(replaceChoice == 2) {
                        System.out.println("Returning to Main Menu... \n");
                        break;
                    }
                    else
                        System.out.println("ERROR: Unaccepted input, please insert [1] or [2].");


                case 4:
                    System.out.println("Exiting Restocker Interface...");
                    System.exit(0);
                    break;

                default:
                    System.out.println("This is not a valid input, please select a valid input");
                    break;


            }

        }

    }
}