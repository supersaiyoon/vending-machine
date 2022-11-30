import java.awt.*;
import javax.swing.*;
import java.util.*;
import java.io.*;

class Checklist extends JFrame {

	static JFrame f;

   	static JButton c1;

	static JLabel l;

	public static void main(String[] args) throws Exception
	{
		f = new JFrame("panel");

		l = new JLabel("panel label");
      
		JPanel p = new JPanel();

		p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));      
      
      	File input = new File("input.txt");
      	Scanner sc = new Scanner(input);
      	String data = "";
      	while(sc.hasNextLine()){
         	data = sc.nextLine();
         	JCheckBox c1 = new JCheckBox(data);
         	p.add(c1);
      	}

	f.add(p);

	f.setSize(300, 300);

	f.show();
	}
}
