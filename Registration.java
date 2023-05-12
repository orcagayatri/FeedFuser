package feedform;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;

public class Registration extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JPasswordField pass1;
	private JPasswordField confirm1;
	private JButton RegisterButton;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Registration frame = new Registration();
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
	public Registration() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 948, 658);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("REGISTRATION");
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 25));
		lblNewLabel.setBounds(356, 27, 198, 53);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("EMAIL");
		lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 17));
		lblNewLabel_1.setBounds(114, 112, 192, 25);
		contentPane.add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setBounds(363, 112, 350, 25);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("USER NAME");
		lblNewLabel_2.setFont(new Font("Arial", Font.BOLD, 17));
		lblNewLabel_2.setBounds(114, 179, 192, 25);
		contentPane.add(lblNewLabel_2);
		
		JLabel passwordLabel = new JLabel("PASSWORD");
		passwordLabel.setFont(new Font("Arial", Font.BOLD, 17));
		passwordLabel.setBounds(114, 252, 192, 25);
		contentPane.add(passwordLabel);
		
		JLabel confirmLabel = new JLabel("CONFIRM PASSWORD");
		confirmLabel.setFont(new Font("Arial", Font.BOLD, 17));
		confirmLabel.setBounds(114, 331, 192, 25);
		contentPane.add(confirmLabel);
		
						
		textField_1 = new JTextField();
		textField_1.setBounds(363, 179, 350, 25);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		pass1 = new JPasswordField();
		pass1.setBounds(363, 252, 350, 25);
		contentPane.add(pass1);
		
		confirm1 = new JPasswordField();
		confirm1.setBounds(363, 331, 350, 25);
		contentPane.add(confirm1);
		String user=textField.getText();
		String email=textField_1.getText();
		String pass=String.valueOf(pass1.getPassword());
		String confirm=String.valueOf(confirm1.getPassword());
		
		PreparedStatement ps1,ps2;
		ResultSet rs1,rs2;
		
		RegisterButton = new JButton("REGISTER");
		RegisterButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				if(pass.trim().equals(confirm.trim()))
				{
				MyConnection myconn=new MyConnection();
				
				//insert the user into the mysql database
				
				String insertQuery="INSERT INTO feed_user_db (email, username, password) VALUES (?,?,?)";
				
				ps1=myconn.createConnection().prepareStatement(insertQuery);
				
				ps1.setString(1,user);
				ps1.setString(2,email);
				ps1.setString(3,pass);
									
				rs1=ps1.executeQuery();
				 
				//create a table to store the feed source
				//create table source{ index int, NameOfWebsite varchar(100), url varchar(500) };
				String feedTable="CREATE TABLE ? (INDEX INT AUTO_INCREMENT , TITLE VARCHAR(200), RSS VARCHAR(500))";
				
				ps2=myconn.createConnection().prepareStatement(feedTable);
				ps2.setString(1,user);
				rs2=ps2.executeQuery();
				
			}
				else
				{
					JOptionPane.showMessageDialog(RegisterButton,"Confirm with the same password","registration error",2);
				}
			}
		});
		RegisterButton.setFont(new Font("Tahoma", Font.BOLD, 20));
		RegisterButton.setBounds(252, 428, 265, 75);
		contentPane.add(RegisterButton);
		
	
		
		
		
	}

}
