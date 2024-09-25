package BrawlhallaClinic;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.Color;
import javax.swing.ImageIcon;



public class matchSystem extends JFrame {
	private static JTextField CharacterField;
	private static MainPage parent;
	static int ID;
	private static JTable MentorTable;
	private JRadioButton MatchOnCharacterRadio;

	private void doubleClick() {
		meetingPage MP;
		

		int row = MentorTable.getSelectedRow(); 
		if (row > -1) {
			int ID = ((int)MentorTable.getValueAt(row, 1));
			String Elo = MentorTable.getValueAt(row, 1).toString();
			String IGN = MentorTable.getValueAt(row, 2).toString();
			String Characters = MentorTable.getValueAt(row, 4).toString();
			System.out.println(MentorTable.getValueAt(row, 0).toString());
			System.out.println(MentorTable.getValueAt(row, 1).toString());
			System.out.println(MentorTable.getValueAt(row, 2).toString());
			System.out.println(MentorTable.getValueAt(row, 4).toString());
			
			MP = new meetingPage(this);
		}
		else {
			MP = new meetingPage(this);
		}
		
		MP.setVisible(true);
		this.setVisible(false);
			

	
	
	MentorTable.addMouseListener(new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent e) {
			if(e.getClickCount()>=2) {
				doubleClick();

			}
		}}
		);
	}
	
	public matchSystem(int ID, String Elo, String IGN, String Characters, MainPage parent) {
		this(parent);

		Connection conn = DatabaseManager.getConnection();
		try {
			this.ID = ID;
			Statement stmnt = conn.createStatement();
			ResultSet rs = stmnt.executeQuery("SELECT * FROM Players WHERE PlayerID = " + ID);
			rs.next();
			//this.Elo.setText(rs.getString("Elo"));
			//this.Characters.setText(rs.getString("Characters"));
			//this.mentorValue.setSelected(false);
			//if (rs.getInt("Mentor") == 1)
			//	this.mentorValue.setSelected(true);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void MainPage() {
		this.setVisible(false);
		MainPage MP = new MainPage();
		MP.setVisible(true);
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					matchSystem frame = new matchSystem();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
		});
}

		// TODO Auto-generated method stub
		
		
		public matchSystem(MainPage parent) {
			this();
			this.parent = parent;
			
	
	}
		
	
			
			
		
			
		private void launchMeetingPage() {
			this.setVisible(false);
			meetingPage mp = new meetingPage();
			mp.setVisible(true);
		}
		
		public static class MentorClass{
			static int Row = MentorTable.getSelectedRow();
			static int MentorID = ((int)MentorTable.getValueAt(Row, 1));

		    
		}
		
		
	public matchSystem () {
		getContentPane().setBackground(new Color(230, 230, 250));
		getContentPane().setForeground(new Color(230, 230, 250));
		
		//JFrame frame = new JFrame ("matchSystem");
		setBounds(400,400,400,400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		JLabel CharacterLabel = new JLabel("Search");
		CharacterLabel.setBounds(22, 22, 61, 16);
		getContentPane().add(CharacterLabel);
		
		JButton Close = new JButton("Close");
		Close.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				close(false);
				MainPage();
				

			}

		}
		);
		
	

			
			

		
		Close.setBounds(16, 300, 117, 29);
		getContentPane().add(Close);
		
		JButton Match = new JButton("Match");
		Match.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e)
			{
			
				launchMeetingPage();
				//newPage();
			        
			    
			}
			       
			});
	
		
		
			
		
		Match.setBounds(258, 300, 117, 29);
		getContentPane().add(Match);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(16, 102, 359, 176);
		getContentPane().add(scrollPane);
		
		MentorTable = new JTable();
		scrollPane.setViewportView(MentorTable);
		
		MatchOnCharacterRadio = new JRadioButton("Match on Character");
		MatchOnCharacterRadio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				search();
			}
		});
		
		CharacterField = new JTextField();
		CharacterField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				
				search();
			}
		});
		
		CharacterField.setBounds(18, 34, 130, 26);
		getContentPane().add(CharacterField);
		CharacterField.setColumns(10);
		MatchOnCharacterRadio.setBounds(203, 67, 172, 23);
		getContentPane().add(MatchOnCharacterRadio);
		MatchOnCharacterRadio.setSelected(true);
		
		JRadioButton MatchOnWeaponRadio = new JRadioButton("Match On weapon");
		MatchOnWeaponRadio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				search();
			}
		});
		MatchOnWeaponRadio.setBounds(6, 67, 158, 23);
		getContentPane().add(MatchOnWeaponRadio);
		
		ButtonGroup R = new ButtonGroup();
		R.add(MatchOnWeaponRadio);
		R.add(MatchOnCharacterRadio);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("/Users/aaronmandel/Desktop/background.png"));
		lblNewLabel.setBounds(-1, 0, 401, 370);
		getContentPane().add(lblNewLabel);
		
		setVisible(true);
	}
	
	private void search() {
		Connection conn = DatabaseManager.getConnection();
		PreparedStatement rs;
		
		try {
			//Uses the SQL statement that matches based on the character 
		if (MatchOnCharacterRadio.isSelected()) {
			String MentorSQL = "Select CharactersWeapons.Characters, Players.Name, Players.Elo FROM CharactersWeapons INNER JOIN Players ON Players.Characters = CharactersWeapons.Characters WHERE Players.Characters LIKE ? AND Players.Mentor = 1" ;
			rs = conn.prepareStatement(MentorSQL);
			rs.setString(1, (CharacterField.getText()+"%"));
			System.out.println("searching on character");
		}
		else {
			//TODO changes the sql statement for a match based on the weapon
			String MentorSQL = "Select CharactersWeapons.Characters, Players.Name, Players.Elo, CharactersWeapons.Weapons FROM CharactersWeapons INNER JOIN Players ON Players.Characters = CharactersWeapons.Characters WHERE CharactersWeapons.Weapons LIKE ? AND Players.Mentor = 1" ;
			rs = conn.prepareStatement(MentorSQL);
			rs.setString(1, (CharacterField.getText()+"%"));
			System.out.println("searching on weapon");
		}
		
		ResultSet ms=rs.executeQuery();
		
		
		MentorTable.setModel(DatabaseManager.buildTableModel(ms));
		
		
		rs.close();
		
		} 	catch (Exception r) {
			r.printStackTrace();
	
		}
	}
	
	private void close(boolean Close) {
		// TODO Auto-generated method stub
		if (Close == true) {
		
		}
			this.dispose();
			if (parent != null) {
				
			}		
			
	}
}

