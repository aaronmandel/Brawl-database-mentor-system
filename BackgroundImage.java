package BrawlhallaClinic;
import java.awt.*;
import javax.swing.*;

public class BackgroundImage extends JFrame {
	JLabel Error;
	public BackgroundImage() {
		
		setTitle("ErrorMessage");
		setSize(600, 600);
		 setLocationRelativeTo(null);
		 /*setDefaultCloseOperation(JFrame.CENTER_ALIGNMENT);*/
		 setVisible(true);
		 
		 
		 
				 
		 getContentPane().setLayout(new BorderLayout());
		 setContentPane(new JLabel(new ImageIcon("/Users/aaronmandel/eclipse-workspace/BrawlhallaClinic/img/error_message.png")));
		 
		
		 
		 
		 getContentPane().setLayout(new FlowLayout());
		 Error = new JLabel();
		// Error.setText("Error Data is invalid ");
		 getContentPane().add(Error);
		 
		 
		 
		 setSize(210,270);
	}
	
	public static void main(String args[]) {
		new BackgroundImage();
		
		
		
	}
}
