package eg.edu.alexu.csd.datastructure.mailServer;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JLabel;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class FriendsGui extends JFrame {

	private JPanel contentPane;
	private JTextArea newName;
	private JTextField name;
	private JTextField email;
	private JList list;
	private JList list_1;
	private JTextField AddEmailText;
	private JScrollPane scrollPane_1;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FriendsGui frame = new FriendsGui();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the frame.
	 */
	public FriendsGui(App ap) {
		this.setVisible(true);
		this.setTitle("My Mail");
		Image img = new ImageIcon(this.getClass().getResource("/blue-mail.png")).getImage();
		this.setIconImage(img);
		this.setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1317, 499);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(71, 36, 186, 341);
		contentPane.add(scrollPane);
		Friends f=new Friends();
		String friendslist_1[]=f.getFriends(ap);
		if(friendslist_1==null) {
			list_1=new JList();
		}
		else {
			list_1=new JList(friendslist_1);
		}
		list_1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(list_1);
		JButton Rename = new JButton("Rename");
		Rename.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(list_1.getModel().getSize()==0) {
					JOptionPane.showMessageDialog(null, "You have no contacts");
				}
				else if(list_1.isSelectionEmpty()) {
					JOptionPane.showMessageDialog(null, "Select a contact");
				}
				else {
				if(newName.getText().trim().equals("Rename With")) {
					JOptionPane.showMessageDialog(null, "Enter the new name");
				}
				else {
				f.rename(newName.getText(),list_1.getSelectedIndex(),ap);
				list_1=new JList(f.getFriends(ap));
				scrollPane.setViewportView(list_1);
				newName.setText("                     Rename With");
				newName.setForeground(Color.gray);
				}
				}
			}
		});
		Rename.setFont(new Font("Tahoma", Font.PLAIN, 20));
		Rename.setBounds(308, 132, 199, 37);
		contentPane.add(Rename);
		
		newName = new JTextArea();
		newName.setBounds(542, 132, 209, 37);
		contentPane.add(newName);
		
		JButton btnAddNewContact = new JButton("Add New Contact");
		btnAddNewContact.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(name.getText().trim().equals("New Friend Name")){
					JOptionPane.showMessageDialog(null, "Enter The new Contact name");
				}
				else if(email.getText().trim().equals("New Friend Email")) {
					JOptionPane.showMessageDialog(null, "Enter The new Contact email");
				}
				else {
				f.setName(name.getText());
				String [] emails=email.getText().split(" ");
				f.setEmails(emails);
				if(!f.addContact(f, ap)) {
					JOptionPane.showMessageDialog(null, "Email Doesn't Exist");
				}
				else {
				list_1=new JList(f.getFriends(ap));
				scrollPane.setViewportView(list_1);
				email.setText("                  New Friend Email");
				email.setForeground(Color.gray);
				name.setText("                  New Friend Name");
				name.setForeground(Color.gray);
				}
				
				}
			}
		});
		btnAddNewContact.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnAddNewContact.setBounds(308, 35, 199, 37);
		contentPane.add(btnAddNewContact);
		
		JButton btnRemoveContact = new JButton("Remove Contact");
		btnRemoveContact.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(list_1.getModel().getSize()==0) {
						JOptionPane.showMessageDialog(null, "You have no Contacts");
				}
			else if(list_1.isSelectionEmpty()) {
				JOptionPane.showMessageDialog(null, "Select a contact");
			}
			else {
			
			f.deleteContact(list_1.getSelectedIndex(), ap);
			if(f.getFriends(ap)==null) {
				list_1=new JList();
			}
			else 
			list_1=new JList(f.getFriends(ap));
			scrollPane.setViewportView(list_1);
			list=new JList();
			scrollPane_1.setViewportView(list);
			}
			}
		});
		
		btnRemoveContact.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnRemoveContact.setBounds(308, 340, 199, 37);
		contentPane.add(btnRemoveContact);		
		JButton btnNewButton = new JButton("View Emails");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(list_1.getModel().getSize()==0) {
					JOptionPane.showMessageDialog(null,"You have no contacts");
				}
				else if(list_1.isSelectionEmpty()) {
					JOptionPane.showMessageDialog(null, "Select a contact");
				}
				else {
				String [] friendEmails=f.getFriendEmails(list_1.getSelectedIndex(), ap);
				list=new JList(friendEmails);
				scrollPane_1.setViewportView(list);
				}
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton.setBounds(308, 235, 199, 39);
		contentPane.add(btnNewButton);
		
		name = new JTextField();
		name.setText("                  New Friend Name");
		name.setForeground(Color.gray);
		name.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if(name.getText().trim().toLowerCase().equals("new friend name")){
			name.setText("");
			name.setForeground(Color.black);
		}
			}
			public void focusLost(FocusEvent e) {
				if(name.getText().trim().equals("")||name.getText().trim().toLowerCase().equals("new friend name")) {
					name.setText("                  New Friend Name");
					name.setForeground(Color.gray);
				}
			}
		});
		
		newName.setText("                     Rename With");
		newName.setForeground(Color.gray);
		newName.addFocusListener(new FocusAdapter() {
			@Override 
			public void focusGained(FocusEvent e) {
				if(newName.getText().trim().toLowerCase().equals("rename with")) {
					newName.setText("");
					newName.setForeground(Color.black);
				}
			}
			@Override 
			public void focusLost(FocusEvent e) {
				if(newName.getText().trim().equals("")||newName.getText().trim().toLowerCase().contentEquals("rename with")) {
					newName.setText("                     Rename With");
					newName.setForeground(Color.gray);
				}
			}
		});
		name.setBounds(542, 36, 209, 36);
		contentPane.add(name);
		name.setColumns(10);
		
		email = new JTextField();
		email.setForeground(Color.gray);
		email.setColumns(10);
		email.setBounds(794, 40, 209, 36);
		contentPane.add(email);
		email.setText("                  New Friend Email");
		
		 scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(1067, 36, 209, 357);
		contentPane.add(scrollPane_1);
		
		list = new JList();
		scrollPane_1.setViewportView(list);
		
		JButton AddEmail = new JButton("Add New Email");
		AddEmail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(list_1.getModel().getSize()==0) {
					JOptionPane.showMessageDialog(null, "You have no contacts");
				}
				else if(list_1.isSelectionEmpty()) {
					JOptionPane.showMessageDialog(null, "Select A contact");
				}
				else if(AddEmailText.getText().equals("                     New Friend Email")) {
					JOptionPane.showMessageDialog(null,"Enter The new email");
				}
				else if(!f.checkExist(AddEmailText.getText())) {
					JOptionPane.showMessageDialog(null,"Email doesn't exist");
				}
				else {
					f.addNewEmail(AddEmailText.getText(), list_1.getSelectedIndex(), ap);
					list=new JList(f.getFriendEmails(list_1.getSelectedIndex(), ap));
					scrollPane_1.setViewportView(list);
					AddEmailText.setText("                     New Friend Email");
					AddEmailText.setForeground(Color.gray);
				}
			}
		});
		AddEmail.setFont(new Font("Tahoma", Font.PLAIN, 20));
		AddEmail.setBounds(757, 354, 245, 37);
		contentPane.add(AddEmail);
		
		AddEmailText = new JTextField();
		AddEmailText.setBounds(757, 403, 245, 49);
		contentPane.add(AddEmailText);
		AddEmailText.setColumns(10);
		AddEmailText.setText("                     New Friend Email");
		AddEmailText.setForeground(Color.gray);
		AddEmailText.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if(AddEmailText.getText().trim().toLowerCase().equals("new friend email")) {
					AddEmailText.setText("");
					AddEmailText.setForeground(Color.black);
				}
			}
			@Override
			public void focusLost(FocusEvent e) {
				if(AddEmailText.getText().trim().equals("")||AddEmailText.getText().trim().toLowerCase().equals("new friend email")) {
					AddEmailText.setText("                     New Friend Email");
					AddEmailText.setForeground(Color.gray);
				}
			}
		});
		
		JButton RemoveEmail = new JButton("Remove Email");
		RemoveEmail.setFont(new Font("Tahoma", Font.PLAIN, 20));
		RemoveEmail.setBounds(1067, 403, 209, 49);
		RemoveEmail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(list_1.getModel().getSize()==0) {
					JOptionPane.showMessageDialog(null, "You have no contacts");
				}
				else if(list_1.isSelectionEmpty()) {
					JOptionPane.showMessageDialog(null, "Select a contact");
				}
					else if(list.isSelectionEmpty()) {
						JOptionPane.showMessageDialog(null, "Select an email");
					}
					else {
					f.deleteOldEmail(ap, list_1.getSelectedIndex(), list.getSelectedIndex());
					list=new JList(f.getFriendEmails(list_1.getSelectedIndex(), ap));
					scrollPane_1.setViewportView(list);
					}
				}
		});
		contentPane.add(RemoveEmail);
		
		JButton btnNewButton_1 = new JButton("Return");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				UserGui test=new UserGui(ap);
				test.setVisible(true);
				
			}
			
		});
		btnNewButton_1.setBounds(71, 416, 89, 23);
		contentPane.add(btnNewButton_1);
		email.addFocusListener(new FocusAdapter() {
			@Override 
			public void focusGained(FocusEvent e) {
				if(email.getText().trim().toLowerCase().equals("new friend email")) {
					email.setText("");
					email.setForeground(Color.BLACK);
				}
			}
			@Override 
			public void focusLost(FocusEvent e) {
				if(email.getText().trim().equals("")||email.getText().trim().toLowerCase().contentEquals("new friend email")) {
					email.setText("                  New Friend Email");
					email.setForeground(Color.gray);
				}
			}
		});
		this.setVisible(true);
	}
}