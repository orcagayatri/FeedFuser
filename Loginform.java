package feedform;
import java.awt.EventQueue;
import javax.swing.*;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.border.Border;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Logger;

public class Loginform {

	private JFrame frame;
	private JTextField textUserName;
	private JPasswordField passwordField;

	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Loginform window = new Loginform();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Loginform() {
		initialize();
		//Border jpanel_border=BorderFactory.createMatteBorder(0,1,1,1,Color.yellow);
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 972, 538);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 128, 192));
		panel.setBounds(10, 11, 940, 129);
		frame.getContentPane().add(panel);
		
		JLabel lblNewLabel = new JLabel("Login form");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 43));
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("User name");
		lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 25));
		lblNewLabel_1.setBounds(132, 228, 140, 42);
		frame.getContentPane().add(lblNewLabel_1);
		
		textUserName = new JTextField();
		textUserName.setBounds(415, 228, 223, 42);
		frame.getContentPane().add(textUserName);
		textUserName.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Password");
		lblNewLabel_2.setFont(new Font("Arial", Font.BOLD, 25));
		lblNewLabel_2.setBounds(132, 310, 133, 42);
		frame.getContentPane().add(lblNewLabel_2);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(415, 314, 223, 38);
		frame.getContentPane().add(passwordField);
		
		JButton loginButton = new JButton("LOGIN");
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				PreparedStatement ps;
				ResultSet rs;
				
				String userName=textUserName.getText();
				String password=String.valueOf(passwordField.getPassword());
				
				if(userName.trim().equals(""))
				{
					JOptionPane.showMessageDialog(loginButton,"Enter Your Username to Login","Empty Username",2);
				}
				else if(password.trim().equals(""))
				{
					JOptionPane.showMessageDialog(loginButton,"Enter Your Password to Login","Empty password",2);
				}
				else {
					MyConnection myconnection=new MyConnection();
					String selectQuery="SELECT * FROM 'users' WHERE 'username'=? AND 'password'=?";
					try {
					ps=myconnection.createConnection().prepareStatement(selectQuery);
					
					ps.setString(1, userName);
					ps.setString(2, password);
					
					rs=ps.executeQuery();
					
					if(rs.next())
					{
						MainForm mainform=new MainForm();
						mainform.setVisible(true);
						mainform.pack();
						mainform.setLocationRelativeTo(null);
					}
					else
					{
						JOptionPane.showMessageDialog(loginButton,"Incorect username or password","login error",2);
					}
					
					}catch(SQLException ex) {
						Logger.getLogger(Loginform.class.getName()).log(level.SEVERE,null,ex);				}
					
				}
				
			}
		});
		loginButton.setFont(new Font("Arial", Font.BOLD, 20));
		loginButton.setBounds(308, 373, 120, 42);
		frame.getContentPane().add(loginButton);
	}
}
