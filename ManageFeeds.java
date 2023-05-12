package feedform;

import java.awt.EventQueue;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;


public class ManageFeeds extends JFrame {

	private JPanel contentPane;
	private JTextField titleField;
	private JTextField rssField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ManageFeeds frame = new ManageFeeds();
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
	public ManageFeeds() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 981, 659);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("MANAGE FEEDS");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblNewLabel.setBounds(383, 22, 263, 64);
		contentPane.add(lblNewLabel);
		
		JLabel titleLabel = new JLabel("TITLE");
		titleLabel.setFont(new Font("Tahoma", Font.PLAIN, 19));
		titleLabel.setBounds(137, 143, 118, 31);
		contentPane.add(titleLabel);
		
		titleField = new JTextField();
		titleField.setBounds(379, 143, 281, 31);
		contentPane.add(titleField);
		titleField.setColumns(10);
		String website=titleField.getText();
		
		JLabel rssLabel = new JLabel("RSS SOURCE");
		rssLabel.setFont(new Font("Tahoma", Font.PLAIN, 19));
		rssLabel.setBounds(137, 241, 118, 31);
		contentPane.add(rssLabel);
		
		rssField = new JTextField();
		rssField.setBounds(379, 241, 281, 31);
		contentPane.add(rssField);
		rssField.setColumns(10);
		String rss=rssField.getText();
		
		MyConnection myconmng=new MyConnection();
		
		PreparedStatement psmng;
		ResultSet rsmng;
		
		JButton btnNewButton = new JButton("ADD");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String addQuery="INSERT INTO ? (TITLE, RSS) VALUES (?,?)";
				
				psmng=myconmng.createConnection().prepareStatement(addQuery);
				
				psmng.setString(1,user);
				psmng.setString(2,website);
				psmng.setString(3,rss);
									
				rsmng=psmng.executeQuery();
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton.setBounds(262, 401, 197, 64);
		contentPane.add(btnNewButton);
	}
}
