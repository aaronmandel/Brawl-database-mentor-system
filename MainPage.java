package BrawlhallaClinic;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.BevelBorder;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.LinkedList;
import java.util.Queue;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.ImageIcon;


public class MainPage extends JFrame {

	private JPanel contentPane;
	private JScrollPane scrollPane;
	private static JTable PlayerList;
	private JTextField queue;

	/**
	 * Launch the application.
	 */
	
	
	
	public static void main(String[] args) {
		
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainPage frame = new MainPage();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
		});
	}
	
	private void matchSystem() {
		this.setVisible(false);
		matchSystem MP = new matchSystem();
		MP.setVisible(true);
	}
	
	private void AddPlayer() {
		
		this.setVisible(false);
		AddPlayer AP = new AddPlayer();
		AP.setVisible(true);
		
	}
	
	
	private void DeletePlayer() {
		this.setVisible(true);
		DeletePlayer DP = new DeletePlayer();
		DP.setVisible(true);
	}
	
	
	
	public void loadTable(String Players) {
		//String url = "jdbc:mysql://localhost/Brawlhalla";
		
		Connection conn = null;
		try {
			conn = DatabaseManager.getConnection();
			
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT *  FROM " + Players + ";" );
			
			PlayerList.setModel(DatabaseManager.buildTableModel(rs));
			
			stmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void RowNumber() {
		int Rows = PlayerList.getRowCount();
		System.out.println(Rows);
	}	
	
	
	/**
	 * Create the frame.
	 * @return 
	 */
	
	public void Queue() {
		
		Queue<String> PlayerQue = new LinkedList<String>();
		PlayerQue.add("Player");
		
		System.out.println(PlayerQue.peek());
		System.out.println(PlayerQue.size());
		
		
		
	}
	
	
	public MainPage() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 458, 339);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(6, 34, 446, 239);
		contentPane.add(scrollPane);
		
		PlayerList = new JTable() {
			public boolean isCellEditable(int row, int column) {
				return false;
			};
		};
		
		
		
		PlayerList.addMouseListener(new MouseAdapter() { 
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount()>=2) { //If the mouse is pressed twice then the method double click is run
					doubleClick();

				}
			}}
			);
			
			
		

		PlayerList.setColumnSelectionAllowed(true);
		PlayerList.setCellSelectionEnabled(true);
		PlayerList.setFillsViewportHeight(true);
		scrollPane.setViewportView(PlayerList);
		
		JButton DeletePlayer = new JButton("Delete Player");
		DeletePlayer.setForeground(Color.RED);
		DeletePlayer.setBackground(Color.RED);
		DeletePlayer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//DeletePlayer();
				int Selected = PlayerList.getSelectedRow();
				Connection conn = DatabaseManager.getConnection();
				PreparedStatement rs;
				try {
					String sql = "Delete FROM Players WHERE PlayerID = " + Selected;
					rs = conn.prepareStatement(sql);
					int rowsUpdated = rs.executeUpdate();

				} catch (Exception E) {
					E.printStackTrace();
				}
			
			}});
			
		DeletePlayer.setBounds(0, 276, 117, 29);
		contentPane.add(DeletePlayer);
		
		
		
		JButton AddPlayer = new JButton("Add Player");
		AddPlayer.setForeground(new Color(46, 139, 87));
		AddPlayer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddPlayer();
			}
		});
		AddPlayer.setBounds(111, 276, 117, 29);
		contentPane.add(AddPlayer);
		
		JButton matchbutton = new JButton("Match");
		matchbutton.setForeground(new Color(0, 0, 255));
		matchbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				matchSystem();
				
				
			}
		});
		matchbutton.setBounds(335, 276, 117, 29);
		contentPane.add(matchbutton);
		
		queue = new JTextField();
		queue.setEditable(false);
		queue.setBounds(6, 6, 130, 26);
		contentPane.add(queue);
		queue.setColumns(10);
		
		JButton Nqueue = new JButton("Next in queue");
		Nqueue.setForeground(new Color(65, 105, 225));
		Nqueue.setBounds(135, 6, 117, 29);
		contentPane.add(Nqueue);
		
		JButton AddQueue = new JButton("Add to Queue");
		AddQueue.setForeground(new Color(255, 140, 0));
		AddQueue.setBounds(224, 276, 113, 29);
		contentPane.add(AddQueue);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("/Users/aaronmandel/Desktop/Screenshot 2022-11-21 at 2.10.18 PM.png"));
		lblNewLabel.setBounds(3, 0, 455, 308);
		contentPane.add(lblNewLabel);
		
		
		
		
	}
	
	public static class MyClass {
	    static int someInt = PlayerList.getRowCount(); //gets the value for number of rows for AddPlayers
	}
	
	@Override
	public void setVisible(boolean visible) {
		super.setVisible(visible);
		if (visible == true) {
			loadTable("Players"); //Loads the SQL Table "Players"
		}
	} 
	
	
	
	

	private void doubleClick() { 
			BrawlEditor BE;

			int row = PlayerList.getSelectedRow(); //Gets all values at the row selected
			// If statement to make sure the row selected is valid
			if (row > -1) {
				int ID = ((int)PlayerList.getValueAt(row, 0)); //Assigns a value to the variable ID
				String Elo = PlayerList.getValueAt(row, 1).toString(); //Assigns a value to the variable Elo
				String IGN = PlayerList.getValueAt(row, 2).toString(); //Assigns a value to the variable IGN
				String DiscordID = PlayerList.getValueAt(row, 3).toString(); //Assigns a value to the variable DiscordID
				String Characters = PlayerList.getValueAt(row, 4).toString(); //Assigns a value to the variable Characters
				int PlayerID = ((int)PlayerList.getValueAt(row, 0)); //Assigns a value to the variable PlayerID
				int PlayerRow = PlayerList.getRowCount(); //gets the value for number of rows
				
				
				//Outputs the value in console in order to monitor the values
				System.out.println(PlayerList.getValueAt(row, 0).toString()); 
				System.out.println(PlayerList.getValueAt(row, 1).toString()); 
				System.out.println(PlayerList.getValueAt(row, 2).toString());
				System.out.println(PlayerList.getValueAt(row, 4).toString());
				
				
				
				
				BE = new BrawlEditor(ID, Elo, IGN, Characters, DiscordID, PlayerID, this); //Opens a new page with variables of ID, Elo, Characters, DiscordID, PlayerID
			}
			else {
				BE = new BrawlEditor(this);
			}
			
			BE.setVisible(true);
			this.setVisible(false);
				
	
	}
}

