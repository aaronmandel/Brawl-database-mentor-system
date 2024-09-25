package BrawlhallaClinic;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.*;
import java.sql.*;

public class AddQueue extends JFrame {

	private JPanel contentPane;
	private JTable QueueTable;
	private JButton AddPlayer;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddQueue frame = new AddQueue();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public AddQueue() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JComboBox PlayerOptions = new JComboBox();
		contentPane.add(PlayerOptions, BorderLayout.NORTH);
		
		QueueTable = new JTable();
		contentPane.add(QueueTable, BorderLayout.CENTER);
		
		AddPlayer = new JButton("Add Player to Queue");
		contentPane.add(AddPlayer, BorderLayout.SOUTH);
		
		Connection conn = null;
		try {
			conn = DatabaseManager.getConnection();
			
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT Names  FROM Players" );
			while(rs.next())
			{
				PlayerOptions.addItem(rs.getString(1));
			}
	
			
			stmt.close();
			conn.close();
		} catch (Exception e) {
			PlayerOptions.showPopup();
			
			e.printStackTrace();
		}
	}
		
		
		
	}


