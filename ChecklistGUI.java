import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.awt.TextField;
 
public class ChecklistGUI extends JPanel {
   public static ArrayList<AddUsers> users = new ArrayList<>();
 
   private JLabel addNewUserLabel;
   private JTextField addNewUserTextField;
 
   private JButton addButton;
   private JButton deleteButton;
   private JPanel namePanel;
 
   public ChecklistGUI() {
      addNewUserLabel = new JLabel ("Add Item:");
      addNewUserTextField = new JTextField (0);
      addButton = new JButton ("Add");
      deleteButton = new JButton ("Delete");
      namePanel = new JPanel();
      namePanel.setLayout(new BoxLayout(namePanel, BoxLayout.Y_AXIS));
 
      setPreferredSize (new Dimension (350, 500));
      setLayout (null);

      add (addNewUserLabel);
      add (addNewUserTextField);
      add (namePanel);
      add (addButton);
      add (deleteButton);

      addNewUserLabel.setBounds (25, 25, 100, 25);
      addNewUserTextField.setBounds (100, 25, 100, 25);
      addButton.setBounds (225, 25, 100, 25);
      namePanel.setBounds(25, 75, 250, 0);
      deleteButton.setBounds (225, 50, 100, 25);
 
      addButton.addActionListener(new AddButtonListener());
 
      deleteButton.addActionListener(new DeleteButtonListener());
 
 
   }
 
   private class AddButtonListener implements ActionListener {
      public void actionPerformed(ActionEvent e) {
         String text = addNewUserTextField.getText();
         users.add(new AddUsers(text));
 
         JCheckBox nameCheckBox = new JCheckBox();
         nameCheckBox.setText(addNewUserTextField.getText());
         namePanel.add(nameCheckBox);
         namePanel.setBounds(25, 75, 250, namePanel.getHeight() + 25);
         JFrame frame = (JFrame) getRootPane().getParent();
         frame.setSize(frame.getWidth(), frame.getHeight() + 25);
         frame.pack();
      }
   }
 
   private class DeleteButtonListener implements ActionListener {
      @Override
      public void actionPerformed(ActionEvent e) {
         for(Component component : namePanel.getComponents()) {
            if(((JCheckBox)component).isSelected())
               namePanel.remove(component);
         }        
         revalidate();
         repaint(); 
      }
   }      
 
 
   public static void main (String[] args) {
      JFrame frame = new JFrame ("ChecklistGUI");
      frame.setTitle("Checklist");
      frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
      frame.getContentPane().add (new ChecklistGUI());
      frame.pack();
      frame.setVisible (true);
   }
}

class AddUsers{
 
   private String userName;
 
   public AddUsers(String userName) {
      this.userName = userName;
   }
 
   public AddUsers() {
      userName = "";
   }   
 
   public void setUserName(String userName) {
      this.userName = userName;   
   }
 
   public String getUserName() {
      return userName;
   }
 
   public String toString() {
      return userName + "\n";
   }
 
}