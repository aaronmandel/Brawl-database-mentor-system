package BrawlhallaClinic;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;
import java.awt.Color;


public class AddPlayer extends MainPage {

	private JPanel contentPane;
	private JTextField Name;
	private JTextField Elo;
	private JTextField DiscordID;
	private JTextField Characters;
	private JTable CharactersTable;
	private JCheckBox MentorValue;
	private JButton Save;
	private MainPage parent;
	private JButton Close;
	private JButton Close2;
	private JLabel lblNewLabel;


	/**
	 * Launch the application.
	 */

		
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddPlayer frame = new AddPlayer();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	private void search() { 
		try {

			Connection conn = DatabaseManager.getConnection();
			PreparedStatement rs;

			String query = "SELECT * FROM CharactersWeapons WHERE Characters LIKE ?";
			PreparedStatement pst = conn.prepareStatement(query);
			pst.setString(1, (Characters.getText() + "%"));
			ResultSet Rs = pst.executeQuery();

			CharactersTable.setModel(DatabaseManager.buildTableModel(Rs));
			// while(Rs.next())
			// {
			// Character.setString(1, Characters.getText());
			// }
			pst.close();

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	
	}
	
	public void MainPage() {
		this.setVisible(false);
		MainPage MP = new MainPage();
		MP.setVisible(true);
		this.getDefaultCloseOperation();
	}
	
	/**
	 * Create the frame.
	 */
	public AddPlayer() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel LName = new JLabel("Name");
		LName.setBounds(6, 6, 36, 16);
		
		Name = new JTextField();
		Name.setBounds(6, 28, 130, 26);
		Name.setColumns(10);
		
		JLabel LElo = new JLabel("Peak Elo");
		LElo.setBounds(6, 66, 52, 16);
		
		Elo = new JTextField();
		Elo.setBounds(6, 80, 130, 26);
		Elo.setColumns(10);
		
		JLabel LDiscordID = new JLabel("DiscordID");
		LDiscordID.setBounds(6, 113, 63, 16);
		
		DiscordID = new JTextField();
		DiscordID.setBounds(6, 142, 130, 26);
		DiscordID.setColumns(10);
		
		JCheckBox MentorValue = new JCheckBox("Mentor Value");
		MentorValue.setBounds(6, 186, 114, 23);
		MentorValue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (MentorValue.isSelected()) {
					try {
						Connection conn = DatabaseManager.getConnection();
						PreparedStatement ms;

						Statement stmnt = conn.createStatement();

						String sql2 = "Update Players SET Mentor = 1 WHERE PlayerID = ?";

					} catch (Exception Ee) {
						Ee.printStackTrace();
					}

				} else {

					try {
						Connection conn = DatabaseManager.getConnection();

						Statement stmnt = conn.createStatement();

						String sql = "Update Players SET Mentor = 0 WHERE PlayerID = ?";
						
						

					} catch (Exception E) {
						E.printStackTrace();
					}
				}}
				
				
			}
		);
		
		JLabel LCharacter = new JLabel("Character");
		LCharacter.setBounds(183, 48, 79, 16);
		
		Characters = new JTextField();
		Characters.setBounds(179, 60, 130, 26);
		Characters.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				search();
			}
		});
		Characters.setColumns(10);
		
		CharactersTable = new JTable();
		CharactersTable.setBounds(183, 92, 219, 76);
		
		
		
		Save = new JButton("Save");
		Save.setForeground(new Color(0, 128, 0));
		Save.setBounds(6, 233, 105, 29);
		Save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				close(true);
				MainPage();
			
			};
		
			
		
		
			
			
			

			private void MainPage() {
				MainPage MP = new MainPage();
				MP.setVisible(true);
				
				
			}
			
		
			
			  public void someMethod() {
			        int ID = MyClass.someInt;
			        System.out.println(ID);
			    }
			
			public void close(boolean save) {
		
				if (save == true) {
					
						Connection conn = DatabaseManager.getConnection();
						PreparedStatement rs;
						int ID = 0;
						while (ID < MyClass.someInt)
							ID = MyClass.someInt +1;
						System.out.println(ID);
						
						try {
							String sql = "INSERT INTO Players (PlayerID, Elo, Name, DiscordID, Characters, Mentor) " + "VALUES (?, ?, ?, ?, ?, ?);"; //SQL Method that adds a row into the table Players
							rs = conn.prepareStatement(sql);
							rs.setInt(1, ID);
							rs.setString(2, Elo.getText()); //Gets value in the text field Elo
							rs.setString(3, Name.getText()); //Gets the value in the text field Name
							rs.setString(4, DiscordID.getText());//Gets the value in the text field DiscordID
							rs.setString(5, Characters.getText()); //Gets the value in the text field Characters
						
							//Gets a value of 1 or 0  based on if the checkbox MentorValue is selected
							if (MentorValue.isSelected())
								rs.setString(6, "1");
							else
								rs.setString(6, "0");
							Statement stmnt = conn.createStatement();
							int rowsUpdated = rs.executeUpdate(); //Executes the Sql query

						} catch (Exception e) {
							e.printStackTrace();
						}

			

			}

			
			}
			});
		
		Close2 = new JButton("Close");
		Close2.setForeground(new Color(255, 0, 0));
		Close2.setBounds(117, 232, 105, 29);
		Close2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainPage();
			}
		});
		contentPane.setLayout(null);
		contentPane.add(LName);
		contentPane.add(Name);
		contentPane.add(LElo);
		contentPane.add(Elo);
		contentPane.add(LDiscordID);
		contentPane.add(CharactersTable);
		contentPane.add(Characters);
		contentPane.add(LCharacter);
		contentPane.add(DiscordID);
		contentPane.add(Save);
		contentPane.add(MentorValue);
		contentPane.add(Close2);
		
		lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("/Users/aaronmandel/Desktop/Screenshot 2022-11-21 at 2.30.03 PM.png"));
		lblNewLabel.setBounds(0, 0, 450, 272);
		contentPane.add(lblNewLabel);
	}
}
