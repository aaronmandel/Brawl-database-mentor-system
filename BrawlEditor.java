package BrawlhallaClinic;

import java.awt.Frame;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.security.PublicKey;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JTable;
import javax.swing.border.BevelBorder;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.SystemColor;
import javax.swing.JCheckBox;
import java.util.Scanner;

public class BrawlEditor extends JFrame {

	private JPanel contentPane;
	private JTextField IGN;
	private static JTextField Elo;
	private JTextField Characters;
	ListModel<String> model = new DefaultListModel<>();
	DefaultListModel dlm = new DefaultListModel();
	static int ID;
	private MainPage parent;
	private JTextField CharacterList;
	private JTextField textField;
	private JTable CharacterTable;
	private JCheckBox mentorValue;
	private JTextField DiscordID;
	

	public BrawlEditor(MainPage parent) {
		this();
		this.parent = parent;

	}
	private void MainPage() {
		this.setVisible(false);
		MainPage MP = new MainPage();
		MP.setVisible(true);
	}

	private void search() { 
		try {

			Connection conn = DatabaseManager.getConnection();
			PreparedStatement rs;

			String query = "SELECT * FROM CharactersWeapons WHERE Characters LIKE ?"; //The SQL Statement that will be executed 
			PreparedStatement pst = conn.prepareStatement(query);
			pst.setString(1, (Characters.getText() + "%")); //This modifier tries to predict the users result based on what is available 
			ResultSet Rs = pst.executeQuery();

			CharacterTable.setModel(DatabaseManager.buildTableModel(Rs)); 
			pst.close();

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	/* private void loadTable(String Players) {

		Connection conn = null;
		try {
			conn = DatabaseManager.getConnection();
			PreparedStatement ms;

			String query2 = "SELECT Mentor FROM " + Players + " WHERE PlayerID = ?"; //The sql statement that will be executed
			PreparedStatement mst = conn.prepareStatement(query2);
			mst.setString(1, (Characters.getText() + "%"));
			ResultSet Ms = mst.executeQuery();

			mst.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void setVisible(boolean visible) {
		super.setVisible(visible);
		if (visible == true) {
			loadTable("Players"); //Loads the SQL Table players
		}
	}
	*/
	
	public static class StudentClass{
	
	    static int StudentID = ID;
	}

			

	public BrawlEditor(int ID, String Elo, String IGN, String Characters, String DiscordID, int PlayerID, MainPage parent) {
		this(parent);

		Connection conn = DatabaseManager.getConnection();
		try {
			this.ID = ID; //The row number 
			Statement stmnt = conn.createStatement();
			ResultSet rs = stmnt.executeQuery("SELECT * FROM Players WHERE PlayerID = " + ID);
			rs.next();
			this.IGN.setText(rs.getString("Name")); //Sets the text field IGN to the column of Name iin the table Players using the row number indicated in ID
			this.Elo.setText(rs.getString("Elo")); //Sets the text field Elo to the column of Elo in the table Players using the row number indicated in ID
			this.Characters.setText(rs.getString("Characters")); //Sets the text field Characters to the column of Characters in the table Players using the row number indicated in ID
			this.DiscordID.setText(rs.getString("DiscordID")); //Sets the text field DiscordID to the column of DiscordID in the table Players using the row number indicated in ID
			this.mentorValue.setSelected(false); //Sets the CheckBox to be false if the value of Mentor is 0
			if (rs.getInt("Mentor") == 1)
				this.mentorValue.setSelected(true); //Sets the CheckBox to be true if the value of mentor is 1
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 
	 */
	public BrawlEditor() {

		// initComponents();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(230, 230, 250));
		contentPane.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Name");
		lblNewLabel.setBounds(6, 6, 99, 16);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Peak Elo");
		lblNewLabel_1.setBounds(6, 49, 61, 16);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Characters");
		lblNewLabel_2.setBounds(246, 6, 81, 24);
		contentPane.add(lblNewLabel_2);

		IGN = new JTextField();
		IGN.setBounds(6, 22, 130, 26);
		contentPane.add(IGN);
		IGN.setColumns(10);

		Elo = new JTextField();
		Elo.setBounds(6, 65, 130, 26);
		contentPane.add(Elo);
		Elo.setColumns(10);
		DatabaseManager.setSize(400, 400);
		DatabaseManager.show();

		JButton Save = new JButton("Save"); 
		Save.setForeground(new Color(124, 252, 0));
		Save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (dataValidation() == true ) {
					close(true);
				}
				else {
					BackgroundImage Error = new BackgroundImage();
					Error.setVisible(true);
				}
				 

			}
		});
		Save.setBounds(6, 237, 111, 29);
		contentPane.add(Save);
		
		

		JButton CloseButton = new JButton("Close");
		CloseButton.setForeground(new Color(255, 127, 80));
		CloseButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				close(false); //Runs the method Close to exit BrawlEditor
				MainPage(); //Runs the method MainPage to return the user to the MainPage
			}

		});
		
		
		CloseButton.setBounds(114, 237, 117, 29);
		contentPane.add(CloseButton);

		Characters = new JTextField();
		Characters.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				search(); //Runs the method search on each keypress

			}
		});
		Characters.setBounds(246, 26, 130, 26);
		contentPane.add(Characters);
		Characters.setColumns(10);

		CharacterTable = new JTable();
		CharacterTable.setBounds(246, 87, 188, 70);
		contentPane.add(CharacterTable);

		JLabel Characters_Weapons = new JLabel("Characters and Weapons");
		Characters_Weapons.setBounds(246, 70, 175, 16);
		contentPane.add(Characters_Weapons);

		JButton MatchButton = new JButton("Match");
		MatchButton.setForeground(new Color(0, 0, 255));
		MatchButton.setBounds(333, 237, 117, 29);
		contentPane.add(MatchButton);

		mentorValue = new JCheckBox("Mentor Value");
		mentorValue.setBounds(6, 154, 128, 23);
		contentPane.add(mentorValue);
		
		JLabel Discord = new JLabel("Discord ID");
		Discord.setBounds(6, 94, 99, 16);
		contentPane.add(Discord);
		
		DiscordID = new JTextField();
		DiscordID.setBounds(6, 113, 130, 26);
		contentPane.add(DiscordID);
		DiscordID.setColumns(10);
		
		JButton Delete = new JButton("Delete");
		Delete.setForeground(new Color(255, 0, 0));
		Delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				close(true);
				Connection conn = DatabaseManager.getConnection();
				PreparedStatement rs;
				try {
					String sql = "Delete FROM Players WHERE PlayerID = " + ID; //SQL Query that removes the selected row
					rs = conn.prepareStatement(sql);
					int rowsUpdated = rs.executeUpdate();
					

				} catch (Exception E) {
					E.printStackTrace();
				}
			}
		});
		Delete.setBounds(231, 237, 104, 29);
		contentPane.add(Delete);
		

		if (mentorValue.isSelected()) {
			try {
				Connection conn = DatabaseManager.getConnection();
				PreparedStatement ms;

				Statement stmnt = conn.createStatement();

				String sql2 = "Update Players SET Mentor = 1 WHERE PlayerID =" + ID; ; //SQL query that is ran to change the status of the player to a mentor

			} catch (Exception e) {
				e.printStackTrace();
			}

		} else {

			try {
				Connection conn = DatabaseManager.getConnection();

				Statement stmnt = conn.createStatement();

				String sql = "Update Players SET Mentor = 0 WHERE PlayerID =" + ID;; //SQL query that is ran to change the status of the player to a non mentor

			} catch (Exception e) {
				e.printStackTrace();
			}

			mentorValue.setBounds(8, 167, 128, 23);
			contentPane.add(mentorValue);

			MatchButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					launchMatchSystem();

				
				}
			});

		}
	}

	private void launchMatchSystem() {
		this.dispose();
		matchSystem m = new matchSystem(parent);
		m.setVisible(true);
	}

	private void close(boolean save) {
		if (save == true) {

			// do what you need to do here to save

			this.setVisible(false);
			if (parent != null) {

				Connection conn = DatabaseManager.getConnection();
				PreparedStatement rs;
				this.ID = ID;
				try {

					Statement stmnt = conn.createStatement();

					String sql = "Update Players SET Elo = ?, Name = ?, Characters = ?, DiscordID = ?, Mentor = ? WHERE PlayerID = " + ID;
							
					rs = conn.prepareStatement(sql);
					
					rs.setString(1, Elo.getText());  //Gets the value from the text Field Elo
					rs.setString(2, IGN.getText()); //Gets the value from the text Field IGN
					rs.setString(3, Characters.getText()); //Gets the value from the text Field Characters
					rs.setString(4, DiscordID.getText()); //Gets the value from the text field DiscordID
					if (mentorValue.isSelected()) //Gets if the CheckBox MentorValue has been selected
						rs.setString(5, "1");
					else
						rs.setString(5, "0");
					

					int rowsUpdated = rs.executeUpdate(); //updates values within the database

				} catch (Exception e) {
					e.printStackTrace();
				}
				parent.setVisible(true);

			}
		} else
			this.setVisible(false);

	}

	public boolean dataValidation() {
		int value = 0;
		value = Elo.getText().length(); //The length of the variable Elo
		System.out.println(value);
		
		int EloLength = Elo.getText().length();
		int CharacterLength = Characters.getText().length();
		
		if(EloLength > 3) {
			
			return true;
		} else {
			return false;
		}
		
		
	}

	
	
	
			
			
		
		
		
	
	
	
	public BrawlEditor(MouseAdapter mouseAdapter, String iGN2, String elo2, int iD2) {
		// TODO Auto-generated constructor stub
	}
}
