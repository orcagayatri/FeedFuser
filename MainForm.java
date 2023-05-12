package feedform;

import java.awt.EventQueue;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URI;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JMenuItem;
import javax.swing.event.MenuKeyListener;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;


import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import java.io.IOException;

import javax.swing.event.MenuKeyEvent;

public class MainForm extends JFrame {

	private JPanel contentPane, westSubPanel, centerSubPanel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainForm frame = new MainForm();
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
	public MainForm() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1050, 738);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("Manage Feeds");
		mnNewMenu.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		menuBar.add(mnNewMenu);
		
		JMenuItem addRssButton = new JMenuItem("Add RSS");
		addRssButton.addMenuKeyListener(new MenuKeyListener() {
			public void menuKeyPressed(MenuKeyEvent e) {
				ManageFeeds manage=new ManageFeeds();
				manage.setVisible(true);
				manage.pack();
				manage.setLocationRelativeTo(null);
				manage.setExtendedState(JFrame.MAXIMIZED_BOTH);
			}
			public void menuKeyReleased(MenuKeyEvent e) {
			}
			public void menuKeyTyped(MenuKeyEvent e) {
			}
		});
		mnNewMenu.add(addRssButton);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		MyConnection myconnectionm;
		Loginform login;
		
		PreparedStatement psm;
		ResultSet rsm;
		//for items in table source : create Buttons with NameOfWebsite in west;
		String selectQueryforWest="SELECT * FROM ? ";
		
		psm=myconnectionm.createConnection().prepareStatement(selectQueryforWest);
		
		psm.setString(1, login.userName);
		
		rsm=psm.executeQuery();
		
		JButton[] myFixedListOfButtons,myFixedListOfTitleButtons ;

		myFixedListOfButtons = new JButton[10];
		myFixedListOfTitleButtons=new JButton[10];
		JButton eastButton=new JButton();
		int count=0;
		
		westSubPanel=new JPanel();
		centerSubPanel=new JPanel();

		while(rsm.next())
		{
		    String gotit = rsm.getString(2);
		    if (count < myFixedListOfButtons.length) 
		    {
		        myFixedListOfButtons[count].setText(gotit);
		        westSubPanel.add(myFixedListOfButtons[count]);
		        myFixedListOfButtons[count].addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
						
					        DocumentBuilderFactory f = DocumentBuilderFactory.newInstance();
					      //to parse the home page
					        DocumentBuilder b = f.newDocumentBuilder();
					        Document doc = b.parse(rsm.getString(3));

					        PreparedStatement statement = null;

					        doc.getDocumentElement().normalize();
					        
					      //fetch all the elements by the name item into nodeList datastructure
					        NodeList items = doc.getElementsByTagName("item");

					        
					        //for (int i = 0; i < items.getLength() ; i++) {
					        
					        for (int i = 0; i < 10 ; i++) {
					            Node n = items.item(i);
					            if (n.getNodeType() != Node.ELEMENT_NODE) {
					                continue;
					            }
					            
					            Element el = (Element) n;

					            // get the "link" in this item (only one)
					            NodeList titl = el.getElementsByTagName("title");
					            NodeList desc = el.getElementsByTagName("description");
					            NodeList link = el.getElementsByTagName("link");
					            myFixedListOfTitleButtons[i].setText(titl.toString());
						        centerSubPanel.add(myFixedListOfTitleButtons[i]);
						        eastButton=desc.toString();
						        
						        myFixedListOfTitleButtons[i].addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent e) {
										contentPane.add(eastButton,BorderLayout.EAST);
										 
										eastButton.addActionListener(new ActionListener(){
											public void actionPerformed(ActionEvent e) {
												
												URI uri= new URI(link.toString());
												   
												   java.awt.Desktop.getDesktop().browse(uri);
											}
										});
										
									}
						        });
									}
						        
					        }
					  
						}catch (ParserConfigurationException | SAXException | IOException | DOMException e) {
					        e.printStackTrace();
					    } 
					}
					});
		    }
		    count++;
		}
		contentPane.add(westSubPanel, BorderLayout.WEST);
		contentPane.add(centerSubPanel, BorderLayout.CENTER);
		//each button clicked on east should give cards in center
		
		finally {
	        //finally block used to close resources
	        myconnectionm.close();
	    }       //end finally
		
		//each card clicked should give the article on east
	}

}
