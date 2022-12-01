package GUI;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import java.io.File;
import java.io.IOException;
import java.lang.invoke.ConstantCallSite;
import java.text.NumberFormat;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.text.NumberFormatter;

public class KeyPadGUI extends JFrame implements ActionListener {
    public String order;

    public String getCustomerInput() {
        return this.order;
    }

    JButton createKeypadButton(String text) {
        JButton button = new JButton(text);

        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.setForeground(Color.WHITE);

        return button;
    }

    public KeyPadGUI() throws Exception {
        //Making the window 
        this.add(new JPanel());
        JFrame frame = new JFrame();
        frame.setVisible(true);
        frame.setSize(600,700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // Shut down the program when window is closed.
        
        //Layout Setup using GridBag 
        frame.setLayout(new GridBagLayout());
        GridBagConstraints w = new GridBagConstraints();
        w.insets = new Insets(2, 2, 2, 2);
        frame.getContentPane().setBackground(Color.DARK_GRAY);
        Font font = new Font("Verdana", Font.BOLD, 40);
        
        //All the Labels needed
        JLabel header = new JLabel("Select Item:");
        JLabel letter = new JLabel();
        JLabel num = new JLabel();
        
        //Header
        header.setHorizontalAlignment(SwingConstants.CENTER);
        header.setFont(new Font("Verdana", Font.BOLD, 30));
        header.setForeground(Color.WHITE);
        w.gridx = 1;
        w.gridy = 0;
        frame.add(header,w);
        
        //Letter in order
        w.gridx = 2;
        w.gridy = 0;
        w.ipadx = 50;
        w.ipady = 100;
        letter.setFont(new Font("Verdana", Font.BOLD, 30));
        letter.setForeground(Color.WHITE);
        frame.add(letter,w);
        
        //num in order
        w.gridx = 2;
        w.gridy = 0;
        w.ipadx = 1;
        w.ipady = 100;
        num.setFont(new Font("Verdana", Font.BOLD, 30));
        num.setForeground(Color.WHITE);
        frame.add(num,w);
        
        //Image
        BufferedImage myPicture = ImageIO.read(new File("GUI\\Five_GUIs-logos.jpeg"));
        JLabel picLabel = new JLabel(new ImageIcon(myPicture));
        w.gridx = 0;
        w.gridy = 0;
        frame.add(picLabel,w);

        //Buttons
        JButton a = createKeypadButton("A");
        JButton b = createKeypadButton("B");
        JButton c = createKeypadButton("C");
        JButton d = createKeypadButton("D");
        JButton e = createKeypadButton("E");
        JButton one = createKeypadButton("1");
        JButton two = createKeypadButton("2");
        JButton three = createKeypadButton("3");
        JButton four = createKeypadButton("4");
        JButton five = createKeypadButton("5");
        JButton six = createKeypadButton("6");
        JButton seven = createKeypadButton("7");
        JButton eight = createKeypadButton("8");
        JButton ok = createKeypadButton("OK");
        JButton back = createKeypadButton("CLR");
        
        w.ipadx = 15; w.ipady = 15;
        
        //ok.setBorderPainted(false);
        //ok.setFocusPainted(false);


        //X Location/ Y Location  /Adding to Frame  /Setting Font
        w.gridx = 0; w.gridy = 2; frame.add(a,w); a.setFont(font); 
        w.gridx = 0; w.gridy = 3; frame.add(b,w); b.setFont(font);
        w.gridx = 0; w.gridy = 4; frame.add(c,w); c.setFont(font);
        w.gridx = 0; w.gridy = 5; frame.add(d,w); d.setFont(font);
        w.gridx = 0; w.gridy = 6; frame.add(e,w); e.setFont(font);
        w.gridx = 1; w.gridy = 2; frame.add(one,w); one.setFont(font);
        w.gridx = 2; w.gridy = 2; frame.add(two,w); two.setFont(font);
        w.gridx = 1; w.gridy = 3; frame.add(three,w); three.setFont(font);
        w.gridx = 2; w.gridy = 3; frame.add(four,w); four.setFont(font);
        w.gridx = 1; w.gridy = 4; frame.add(five,w); five.setFont(font);
        w.gridx = 2; w.gridy = 4; frame.add(six,w); six.setFont(font);
        w.gridx = 1; w.gridy = 5; frame.add(seven,w); seven.setFont(font);
        w.gridx = 2; w.gridy = 5; frame.add(eight,w); eight.setFont(font);
        w.gridx = 1; w.gridy = 6; frame.add(back,w); back.setFont(font);
        w.gridx = 2; w.gridy = 6; frame.add(ok,w); ok.setFont(font);
        
        
        ActionListener buttonListener = new ActionListener() {
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
                    order = letter.getText() + num.getText();
                    if(order.length() != 2){
                        //new CustomerErrorGUI();
                        //frame.setVisible(false);
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

class CustomerOrderGUI {
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
        JLabel Your = new JLabel("Cash");
        JLabel change1 = new JLabel("-");
        JLabel is = new JLabel("Change");
        JLabel change = new JLabel("2.00");
        JLabel Your1 = new JLabel("Your ");
        JLabel order = new JLabel(" order");
        JLabel is1 = new JLabel("  is");
        JLabel slot1 = new JLabel(" " + slot);
        JLabel Divider = new JLabel("   --");
        
        //Buttons
        JButton back = new JButton("Back");
        JButton ok = new JButton("Ok");

        NumberFormat longFormat = NumberFormat.getIntegerInstance();

        NumberFormatter numberFormatter = new NumberFormatter(longFormat);
        numberFormatter.setValueClass(Double.class); 
        numberFormatter.setAllowsInvalid(true); //this is the key!!
        numberFormatter.setMinimum(0l); //Optional

        JFormattedTextField field = new JFormattedTextField(numberFormatter);
        
        //Grid format so having words seperate makes it easier to format
        w.gridx = 0; w.gridy = 0; frame.add(Your,w); Your.setFont(font);
        w.gridx = 1; w.gridy = 0; frame.add(change1,w); change1.setFont(font);
        w.gridx = 2; w.gridy = 0; frame.add(is,w); is.setFont(font);
        w.gridx = 0; w.gridy = 1; w.fill = GridBagConstraints.HORIZONTAL; frame.add(field,w); field.setFont(font);
        w.gridx = 2; w.gridy = 1; frame.add(change,w); change.setFont(font);
        
        w.gridx = 1; w.gridy = 2; frame.add(Divider,w); Divider.setFont(font);
        w.gridx = 0; w.gridy = 3; frame.add(Your1,w); Your1.setFont(font);
        w.gridx = 1; w.gridy = 3; frame.add(order,w); order.setFont(font);
        w.gridx = 2; w.gridy = 3; frame.add(is1,w); is1.setFont(font);
        w.gridx = 1; w.gridy = 4; frame.add(slot1,w); slot1.setFont(font);
        
        w.gridx = 0; w.gridy = 4; frame.add(back,w); back.setFont(button); back.setBackground(Color.RED);
        w.gridx = 2; w.gridy = 4; frame.add(ok,w); ok.setFont(button); ok.setBackground(Color.GREEN);
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

class CustomerErrorGUI {
    CustomerErrorGUI() {
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
    
