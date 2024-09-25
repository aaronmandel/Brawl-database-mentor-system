package BrawlhallaClinic;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import BrawlhallaClinic.BrawlEditor.StudentClass;
import BrawlhallaClinic.MainPage.MyClass;
import BrawlhallaClinic.matchSystem.MentorClass;

import javax.swing.AbstractButton;
import javax.swing.ComboBoxEditor;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JDesktopPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.ImageIcon;
import java.awt.Color;

public class meetingPage extends MainPage {

	private static JTable MentorInfo;
	private JPanel contentPane;
	public matchSystem parent;
	private JButton MainPage;
	private JTable table;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JTable table_1;
	

	/**
	 * Launch the application.
	 */
	
	public meetingPage(matchSystem parent) {
		this();
		this.parent = parent;

	}
	
	public void setVisible(boolean visible) {
		super.setVisible(visible);
		if (visible == true) {
			loadMentor("Players");
			loadStudent("Players");
		}
	} 
	
	public void StudentID() {
        int StudentID = StudentClass.StudentID;
        System.out.println(StudentID);
    }
	
	public void MentorID() {
        int MentorID = MentorClass.MentorID;
        System.out.println(MentorID);
    }

	public void loadMentor(String Players) {
		String url = "jdbc:mysql://localhost/Brawlhalla";
		
		Connection conn = null;
		try {
			conn = DatabaseManager.getConnection();
			//int MentorID = MentorClass.MentorID;
		    Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM Players WHERE PlayerID = " + 3);
			
			table.setModel(DatabaseManager.buildTableModel(rs));
			stmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	public void loadStudent(String Players) {
		String url = "jdbc:mysql://localhost/Brawlhalla";
		
		Connection conn = null;
		try {
			conn = DatabaseManager.getConnection();
			//int MentorID = MentorClass.MentorID;
		    Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM Players WHERE PlayerID = " + 2);
			
			table_1.setModel(DatabaseManager.buildTableModel(rs));
			stmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	 



	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					meetingPage frame = new meetingPage();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		
	
	
	MentorInfo = new JTable() {
		public boolean isCellEditable(int row, int column) {
			return false;
		};
	};
	}
	
	

	public void matchSystem(matchSystem parent) {
		this.parent = parent;
	
	}
		
	public void setVisible1(boolean p) {
		// TODO Auto-generated method stub
		
	}
	
	private void MainPage() {
		this.setVisible(false);
		MainPage MP = new MainPage();
		MP.setVisible(true);
	}
	
	
	

	/**
	 * Create the frame.
	 */
	public meetingPage() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton Close = new JButton("Close");
		Close.setForeground(new Color(255, 0, 0));
		Close.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				close(true);
				
			}
		});
		Close.setBounds(327, 237, 117, 29);
		contentPane.add(Close);
		
		MainPage = new JButton("Return to Mainpage");
		MainPage.setForeground(new Color(0, 0, 255));
		MainPage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainPage();
			}
		});
		MainPage.setBounds(175, 237, 151, 29);
		contentPane.add(MainPage);
		
		table = new JTable();
		table.setBounds(74, 62, 310, 29);
		contentPane.add(table);
		
		table_1 = new JTable();
		table_1.setBounds(74, 147, 310, 35);
		contentPane.add(table_1);
		
		lblNewLabel_1 = new JLabel("Student");
		lblNewLabel_1.setBounds(192, 128, 61, 16);
		contentPane.add(lblNewLabel_1);
		
		lblNewLabel_2 = new JLabel("Mentor");
		lblNewLabel_2.setBounds(192, 46, 61, 16);
		contentPane.add(lblNewLabel_2);
		
		lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("/Users/aaronmandel/Desktop/bg copy 2.png"));
		lblNewLabel.setBounds(1, 0, 450, 270);
		contentPane.add(lblNewLabel);
	}
	public meetingPage(int iD2, String elo, String iGN, String characters, BrawlhallaClinic.MainPage mainPage2) {
		// TODO Auto-generated constructor stub
	}


	private void close(boolean Close) {
		// TODO Auto-generated method stub
		if (Close == true) {
		this.dispose();
		
		
			}			
		
		}
}
