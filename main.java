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

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;
import java.lang.invoke.ConstantCallSite;

import javax.imageio.ImageIO;
import javax.swing.*;


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




class KeyPadGUI extends JFrame implements ActionListener{
	
	KeyPadGUI() throws Exception{
	//Making the window 
	this.add(new JPanel());
	JFrame frame = new JFrame();
	frame.setVisible(true);
	frame.setSize(600,700);
	
	//Layout Setup using GridBag 
	frame.setLayout(new GridBagLayout());
	GridBagConstraints w = new GridBagConstraints();
	w.insets = new Insets(1,1,1,1);
	frame.getContentPane().setBackground(Color.GRAY);
	Font font = new Font("Verdana", Font.BOLD, 50);
	
	//All the Labels needed
	JLabel header = new JLabel("Select Item");
	JLabel letter = new JLabel();
	JLabel num = new JLabel();
	
	//Header
	header.setHorizontalAlignment(SwingConstants.CENTER);
	header.setFont(new Font("Verdana", Font.BOLD, 30));
	header.setForeground(Color.RED);
	w.gridx = 1;
	w.gridy = 0;
	frame.add(header,w);
	//Letter in order
	w.gridx = 2;
	w.gridy = 0;
	w.ipadx = 50;
	w.ipady = 100;
	letter.setFont(new Font("Verdana", Font.BOLD, 30));
	frame.add(letter,w);
	//num in order
	w.gridx =2;
	w.gridy = 0;
	w.ipadx = 1;
	w.ipady = 100;
	num.setFont(new Font("Verdana", Font.BOLD, 30));
	frame.add(num,w);
	
	//Image
	BufferedImage myPicture = ImageIO.read(new File("C:\\Users\\csus\\Downloads\\Five_GUIs-logos.jpeg"));
	JLabel picLabel = new JLabel(new ImageIcon(myPicture));
	w.gridx =0;
	w.gridy = 0;
	frame.add(picLabel,w);
	
	//Buttons
	JButton a = new JButton("A");
	JButton b = new JButton("B");
	JButton c = new JButton("C");
	JButton d = new JButton("D");
	JButton e = new JButton("E");
	JButton one = new JButton("1");
	JButton two = new JButton("2");
	JButton three = new JButton("3");
	JButton four = new JButton("4");
	JButton five = new JButton("5");
	JButton six = new JButton("6");
	JButton seven = new JButton("7");
	JButton eight = new JButton("8");
	JButton ok = new JButton("OK");
	JButton back = new JButton("Back");
	
	w.ipadx = 25; w.ipady = 25;    
	
	//X Location/ Y Location  /Adding to Frame  /Setting Font
	w.gridx = 0; w.gridy = 2; frame.add(back,w); back.setFont(font);
	w.gridx = 1; w.gridy = 2; frame.add(a,w); a.setFont(font); 
	w.gridx = 2; w.gridy = 2; frame.add(b,w); b.setFont(font);
	w.gridx = 0; w.gridy = 3; frame.add(c,w); c.setFont(font);
	w.gridx = 1; w.gridy = 3; frame.add(d,w); d.setFont(font);
	w.gridx = 2; w.gridy = 3; frame.add(e,w); e.setFont(font);
	w.gridx = 0; w.gridy = 4; frame.add(one,w); one.setFont(font);
	w.gridx = 1; w.gridy = 4; frame.add(two,w); two.setFont(font);
	w.gridx = 2; w.gridy = 4; frame.add(three,w); three.setFont(font);
	w.gridx = 0; w.gridy = 5; frame.add(four,w); four.setFont(font);
	w.gridx = 1; w.gridy = 5; frame.add(five,w); five.setFont(font);
	w.gridx = 2; w.gridy = 5; frame.add(six,w); six.setFont(font);
	w.gridx = 0; w.gridy = 6; frame.add(seven,w); seven.setFont(font);
	w.gridx = 1; w.gridy = 6; frame.add(eight,w); eight.setFont(font);
	w.gridx = 2; w.gridy = 6; frame.add(ok,w); ok.setFont(font);
	
	
	ActionListener buttonListener = new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent ae) {
				Object o = ae.getSource();
				
				if(o == a){
					letter.setText(a.getText());
				}
				if(o == b){
					letter.setText(b.getText());
				}
				if(o == c){
					letter.setText(c.getText());
				}
				if(o == d){
					letter.setText(d.getText());
				}
				if(o == e){
					letter.setText(e.getText());
				}
				if(o == one){
					num.setText(one.getText());
				}
				if(o == two){
					num.setText(two.getText());
				}
				if(o == three){
					num.setText(three.getText());
				}
				if(o == four){
					num.setText(four.getText());
				}
				if(o == five){
					num.setText(five.getText());
				}
				if(o == six){
					num.setText(six.getText());
				}
				if(o == seven){
					num.setText(seven.getText());
				}
				if(o == eight){
					num.setText(eight.getText());
				}
				if(o == ok){
					//Can be changed later making the second page
					String order = letter.getText() + num.getText();
					if(order.length() != 2){
						new CustomerErrorGUI();
						frame.setVisible(false);
					}
					else{
						new CustomerOrderGUI(order);
						frame.setVisible(false);
					}
				}
				if(o == back){
					if(num.getText() != ""){
						num.setText("");
					}
					else if(letter.getText() != ""){
						letter.setText("");
					}
				}
				
			}
			
		};
		a.addActionListener(buttonListener);
		b.addActionListener(buttonListener);
		c.addActionListener(buttonListener);
		d.addActionListener(buttonListener);
		e.addActionListener(buttonListener);
		one.addActionListener(buttonListener);
		two.addActionListener(buttonListener);
		three.addActionListener(buttonListener);
		four.addActionListener(buttonListener);
		five.addActionListener(buttonListener);
		six.addActionListener(buttonListener);
		seven.addActionListener(buttonListener);
		eight.addActionListener(buttonListener);
		ok.addActionListener(buttonListener);
		back.addActionListener(buttonListener);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}

class CustomerErrorGUI {
	CustomerErrorGUI(){
		JFrame frame = new JFrame();
		frame.setVisible(true);
		frame.setSize(600,700);
		
		//Layout Setup using GridBag 
		frame.setLayout(new GridBagLayout());
		GridBagConstraints w = new GridBagConstraints();
		w.insets = new Insets(2,2,2,2);
		frame.getContentPane().setBackground(Color.GRAY);
		Font font = new Font("Verdana", Font.BOLD, 70);
		
		JLabel error = new JLabel("Error");
		JLabel oops = new JLabel("Oops");
		JLabel something = new JLabel("Something");
		JLabel happened = new JLabel("Happened");
		
		JButton ok = new JButton("OK");
		
		 w.gridy = 0; frame.add(error,w); error.setFont(new Font("Verdana", Font.BOLD, 60)); error.setForeground(Color.RED);
		 w.gridy = 1; frame.add(oops,w); oops.setFont(font); 
		 w.gridy = 2; frame.add(something,w); something.setFont(font);
		 w.gridy = 3; frame.add(happened,w); happened.setFont(font);
		 w.gridy = 5; frame.add(ok,w); ok.setFont(new Font("Verdana", Font.BOLD, 60));
		 
		 ActionListener buttonListener = new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent ae) {
					frame.setVisible(false);
			}
		 };
			
		 ok.addActionListener(buttonListener);
		
		
	}
}

class CustomerOrderGUI{
	CustomerOrderGUI(String slot){
		//New Frame called when ok is pressed
		JFrame frame = new JFrame();
		frame.setVisible(true);
		frame.setSize(600, 700);
		//Layout
		frame.setLayout(new GridBagLayout());
		GridBagConstraints w = new GridBagConstraints();
		w.insets = new Insets(1,1,1,1);
		frame.getContentPane().setBackground(Color.GRAY);
		Font font = new Font("Verdana", Font.BOLD, 55);
		Font button = new Font("Verdana", Font.BOLD, 25);
		//Labels
		JLabel Your = new JLabel(" Your ");
		JLabel change1 = new JLabel("change");
		JLabel is = new JLabel("  is  ");
		JLabel change = new JLabel("2.00");
		JLabel Your1 = new JLabel("Your");
		JLabel order = new JLabel("order");
		JLabel is1 = new JLabel("is");
		JLabel slot1 = new JLabel(slot);
		//Buttons
		JButton back = new JButton("Back");
		JButton ok = new JButton("Ok");
		
		//Grid format so having words seperate makes it easier to format
		w.gridx = 0; w.gridy = 0; frame.add(Your,w); Your.setFont(font);
		w.gridx = 1; w.gridy = 0; frame.add(change1,w); change1.setFont(font);
		w.gridx = 2; w.gridy = 0; frame.add(is,w); is.setFont(font);
		w.gridx = 1; w.gridy = 1; frame.add(change,w); change.setFont(font);
		
		w.gridx = 0; w.gridy = 2; frame.add(Your1,w); Your1.setFont(font);
		w.gridx = 1; w.gridy = 2; frame.add(order,w); order.setFont(font);
		w.gridx = 2; w.gridy = 2; frame.add(is1,w); is1.setFont(font);
		w.gridx = 1; w.gridy = 3; frame.add(slot1,w); slot1.setFont(font);
		
		w.gridx = 0; w.gridy = 3; frame.add(back,w); back.setFont(button); back.setBackground(Color.RED);
		w.gridx = 2; w.gridy = 3; frame.add(ok,w); ok.setFont(button); ok.setBackground(Color.GREEN);
		//Buttons
		ActionListener buttonListener = new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent ae) {
				Object o = ae.getSource();
				
				if(o == back){
					try {
						new KeyPadGUI();
						frame.setVisible(false);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				else if(o == ok){
					frame.setVisible(false);
				}
			}
		};
		ok.addActionListener(buttonListener);
		back.addActionListener(buttonListener);
		
		
		
		 
 	}
}

class Main {
    public static void main(String args[]) throws Exception{

        VendingMachineFile vendingMachine = new VendingMachineFile();

        // Must set vending machine name before using methods.
        // This was added so that different vending machines can be selected.
        vendingMachine.setMachineName("vendingMachine_Sacramento");

        System.out.println(vendingMachine.getAddress());

        System.out.println(vendingMachine.getSlotQty("E1"));

        System.out.println(vendingMachine.getSlotPrice("E1"));

        System.out.println(vendingMachine.getSlotProductName("E1"));
       
        System.out.println(vendingMachine.getExpDate("E1"));
        new KeyPadGUI();
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