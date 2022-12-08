package GUI;

import EventHandlers.OrderInputPerformed;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.*;

public class KeyPadGUI extends JFrame implements ActionListener {
    JFrame keypadFrame;

    JButton createKeypadButton(String text) {
        JButton button = new JButton(text);

        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.setForeground(Color.WHITE);

        return button;
    }

    class PaymentMessage {
        PaymentMessage() {
            JFrame paymentFrame = new JFrame();
            paymentFrame.setLocation(keypadFrame.getX() + 50, keypadFrame.getY() + 300);
            paymentFrame.setVisible(true);
            paymentFrame.setSize(500,200);
    
            //Layout Setup using GridBag
            paymentFrame.setLayout(new GridBagLayout());
            GridBagConstraints w = new GridBagConstraints();
            w.insets = new Insets(2,2,20,2);
            paymentFrame.getContentPane().setBackground(Color.DARK_GRAY);
    
            JLabel payment = new JLabel("Thank you!");
            JButton ok = new JButton("OK");
            ok.setContentAreaFilled(false);
            ok.setFocusPainted(false);
            ok.setForeground(Color.WHITE);
    
            w.gridy = 0;
            paymentFrame.add(payment, w);
            payment.setFont(new Font("Verdana", Font.BOLD, 30));
            payment.setForeground(Color.WHITE);
            
            w.gridy = 1;
            paymentFrame.add(ok, w);
            ok.setFont(new Font("Verdana", Font.BOLD, 30));
    
            ActionListener buttonListener = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ae) {
                    paymentFrame.setVisible(false);
                }
            };
    
            ok.addActionListener(buttonListener);
        }
    }

    class CustomerErrorGUI {
        CustomerErrorGUI(){
            JFrame errorFrame = new JFrame();
            errorFrame.setLocation(keypadFrame.getX() + 100, keypadFrame.getY() + 275);
            errorFrame.setVisible(true);
            errorFrame.setSize(400,250);
    
            //Layout Setup using GridBag
            errorFrame.setLayout(new GridBagLayout());
            GridBagConstraints w = new GridBagConstraints();
            w.insets = new Insets(2,2,20,2);
            errorFrame.getContentPane().setBackground(Color.DARK_GRAY);
            Font font = new Font("Verdana", Font.BOLD, 30);
    
            JLabel error = new JLabel("ERROR");
            JLabel oops = new JLabel("Oops! Input error...");
            oops.setForeground(Color.WHITE);
    
            JButton ok = new JButton("OK");
            ok.setContentAreaFilled(false);
            ok.setFocusPainted(false);
            ok.setForeground(Color.WHITE);
    
            w.gridy = 0;
            errorFrame.add(error, w);
            error.setFont(font);
            error.setForeground(Color.RED);
            
            w.gridy = 1;
            errorFrame.add(oops,w);
            oops.setFont(font);
            
            w.gridy = 2;
            errorFrame.add(ok,w);
            ok.setFont(font);
    
            ActionListener buttonListener = new ActionListener(){
    
                @Override
                public void actionPerformed(ActionEvent ae) {
                    errorFrame.setVisible(false);
                }
            };
    
            ok.addActionListener(buttonListener);
        }
    }

    public KeyPadGUI() throws Exception{

        // Making the window
        this.add(new JPanel());
        keypadFrame = new JFrame("Vending Machine");

        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - keypadFrame.getWidth()) / 2.25);
        int y = (int) ((dimension.getHeight() - keypadFrame.getHeight()) / 4);
        keypadFrame.setLocation(x, y);

        keypadFrame.setVisible(true);
        keypadFrame.setSize(600,800);
        keypadFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Layout Setup using GridBag
        keypadFrame.setLayout(new GridBagLayout());
        GridBagConstraints w = new GridBagConstraints();
        w.insets = new Insets(2, 2, 2, 2);
        keypadFrame.getContentPane().setBackground(Color.DARK_GRAY);
        Font font = new Font("Verdana", Font.BOLD, 40);

        // All the Labels needed
        JLabel header = new JLabel("Select Item:");
        JLabel letter = new JLabel();
        JLabel num = new JLabel();

        // Header
        header.setHorizontalAlignment(SwingConstants.CENTER);
        header.setFont(new Font("Verdana", Font.BOLD, 25));
        header.setForeground(Color.WHITE);
        w.gridx = 1;
        w.gridy = 0;
        keypadFrame.add(header,w);
        
        // Letter in order
        w.gridx = 2;
        w.gridy = 0;
        w.ipadx = 50;
        w.ipady = 100;
        letter.setFont(new Font("Verdana", Font.BOLD, 30));
        letter.setForeground(Color.WHITE);
        keypadFrame.add(letter,w);
        
        // num in order
        w.gridx = 2;
        w.gridy = 0;
        w.ipadx = 1;
        w.ipady = 100;
        num.setFont(new Font("Verdana", Font.BOLD, 30));
        num.setForeground(Color.WHITE);
        keypadFrame.add(num,w);

        // Image
        BufferedImage myPicture = ImageIO.read(new File("GUI\\Five_GUIs-logos.jpeg"));
        JLabel picLabel = new JLabel(new ImageIcon(myPicture));
        w.gridx = 0;
        w.gridy = 0;
        keypadFrame.add(picLabel,w);

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
        JButton clear = createKeypadButton("CLR");

        w.ipadx = 15; w.ipady = 15;

        //X Location/ Y Location  /Adding to Frame  /Setting Font
        w.gridx = 0; w.gridy = 2; keypadFrame.add(a,w); a.setFont(font); 
        w.gridx = 0; w.gridy = 3; keypadFrame.add(b,w); b.setFont(font);
        w.gridx = 0; w.gridy = 4; keypadFrame.add(c,w); c.setFont(font);
        w.gridx = 0; w.gridy = 5; keypadFrame.add(d,w); d.setFont(font);
        w.gridx = 0; w.gridy = 6; keypadFrame.add(e,w); e.setFont(font);
        w.gridx = 1; w.gridy = 2; keypadFrame.add(one,w); one.setFont(font);
        w.gridx = 2; w.gridy = 2; keypadFrame.add(two,w); two.setFont(font);
        w.gridx = 1; w.gridy = 3; keypadFrame.add(three,w); three.setFont(font);
        w.gridx = 2; w.gridy = 3; keypadFrame.add(four,w); four.setFont(font);
        w.gridx = 1; w.gridy = 4; keypadFrame.add(five,w); five.setFont(font);
        w.gridx = 2; w.gridy = 4; keypadFrame.add(six,w); six.setFont(font);
        w.gridx = 1; w.gridy = 5; keypadFrame.add(seven,w); seven.setFont(font);
        w.gridx = 2; w.gridy = 5; keypadFrame.add(eight,w); eight.setFont(font);
        w.gridx = 1; w.gridy = 6; keypadFrame.add(clear,w); clear.setFont(font);
        w.gridx = 2; w.gridy = 6; keypadFrame.add(ok,w); ok.setFont(font);


        ActionListener orderListener = new OrderInputPerformed();
        ActionListener buttonListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                Object o = ae.getSource();

                if (o == a){
                    letter.setText(a.getText());
                }
                if (o == b){
                    letter.setText(b.getText());
                }
                if (o == c){
                    letter.setText(c.getText());
                }
                if (o == d){
                    letter.setText(d.getText());
                }
                if (o == e){
                    letter.setText(e.getText());
                }
                if (o == one){
                    num.setText(one.getText());
                }
                if (o == two){
                    num.setText(two.getText());
                }
                if (o == three){
                    num.setText(three.getText());
                }
                if (o == four){
                    num.setText(four.getText());
                }
                if (o == five){
                    num.setText(five.getText());
                }
                if (o == six){
                    num.setText(six.getText());
                }
                if (o == seven){
                    num.setText(seven.getText());
                }
                if (o == eight){
                    num.setText(eight.getText());
                }
                if (o == ok){
                    //Can be changed later making the second page
                    String order = letter.getText() + num.getText();
                    
                    if (order.length() != 2) {
                        new CustomerErrorGUI();
                    }
                    else {
                        new PaymentMessage();  // Message pops up at incorrect times. Removed for now.
                        letter.setText(null);
                        num.setText(null);
                    }
                }
                if (o == clear){
                    num.setText(null);
                    letter.setText(null);
                }

            }

        };
        a.addActionListener(buttonListener);
        a.addActionListener(orderListener);
        b.addActionListener(buttonListener);
        b.addActionListener(orderListener);
        c.addActionListener(buttonListener);
        c.addActionListener(orderListener);
        d.addActionListener(buttonListener);
        d.addActionListener(orderListener);
        e.addActionListener(buttonListener);
        e.addActionListener(orderListener);
        one.addActionListener(buttonListener);
        one.addActionListener(orderListener);
        two.addActionListener(buttonListener);
        two.addActionListener(orderListener);
        three.addActionListener(buttonListener);
        three.addActionListener(orderListener);
        four.addActionListener(buttonListener);
        four.addActionListener(orderListener);
        five.addActionListener(buttonListener);
        five.addActionListener(orderListener);
        six.addActionListener(buttonListener);
        six.addActionListener(orderListener);
        seven.addActionListener(buttonListener);
        seven.addActionListener(orderListener);
        eight.addActionListener(buttonListener);
        eight.addActionListener(orderListener);
        ok.addActionListener(buttonListener);
        ok.addActionListener(orderListener);
        clear.addActionListener(buttonListener);
        clear.addActionListener(orderListener);
    }


    @Override
    public void actionPerformed(ActionEvent arg0) {
        // TODO Auto-generated method stub

    }

}