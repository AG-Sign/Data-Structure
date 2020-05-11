package eg.edu.alexu.csd.datastructure.mailServer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Insets;

import javax.swing.*;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class UserGui extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * Create the frame.
	 */
	public  UserGui(App C) {
		
		
		this.setVisible(true);
		this.setTitle("My Mail");
		this.setResizable(false);
		Image image = new ImageIcon(this.getClass().getResource("/blue-mail.png")).getImage();
		this.setIconImage(image);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 560);
		this.setLocation(200, 75);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		Image img =new ImageIcon(this.getClass().getResource("/branch2.png")).getImage();
		Image img1= new ImageIcon(this.getClass().getResource("/back1.png")).getImage();
		
		JButton back = new JButton("");
		back.setBackground(Color.WHITE);
		back.setIcon(new ImageIcon(img1));
		back.setOpaque(false);
		back.setContentAreaFilled(false);
		back.setBorderPainted(false);
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				SignIn test=new SignIn();
				test.setVisible(true);
			}
		});
		
		JButton inbox = new JButton("");
		Image img2= new ImageIcon(this.getClass().getResource("/bestbox.png")).getImage();
		inbox.setIcon(new ImageIcon(img2));
		inbox.setOpaque(false);
		inbox.setContentAreaFilled(false);
		inbox.setBorderPainted(false);
		inbox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				C.folder="Inbox";
				dispose();
				InboxGui test = new InboxGui(C);
				test.setVisible(true);
			}
		});
		
		JButton plus = new JButton("");
		Image img4= new ImageIcon(this.getClass().getResource("/green.png")).getImage();
		plus.setIcon(new ImageIcon(img4));
		plus.setOpaque(false);
		plus.setContentAreaFilled(false);
		plus.setBorderPainted(false);
		plus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				ComposeGui test = new ComposeGui(C);
				test.setVisible(true);
			}
		});
		
		JButton trash = new JButton("");
		trash.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				C.folder="Trash";
				dispose();
				InboxGui test = new InboxGui(C);
				test.setVisible(true);
			}
		});
		Image img6= new ImageIcon(this.getClass().getResource("/trash1.png")).getImage();
		
		JButton btnNewButton = new JButton("");
		btnNewButton.setOpaque(false);
		btnNewButton.setContentAreaFilled(false);
		btnNewButton.setBorderPainted(false);
		Image img7 = new ImageIcon(this.getClass().getResource("/contact.png")).getImage();
		btnNewButton.setIcon(new ImageIcon(img7));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				FriendsGui test = new FriendsGui(C);
				test.setVisible(true);
			}
			
		});
		
		JButton others = new JButton("");
		Image img8 = new ImageIcon(this.getClass().getResource("/mark.png")).getImage();
		others.setIcon(new ImageIcon(img8));
		others.setOpaque(false);
		others.setContentAreaFilled(false);
		others.setBorderPainted(false);
		others.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				OthersGui test = new OthersGui(C);
				test.setVisible(true);
			}
		});
		others.setBounds(905, 87, 72, 79);
		contentPane.add(others);
		btnNewButton.setBounds(895, 11, 89, 73);
		contentPane.add(btnNewButton);
		
		JLabel lblTrash = new JLabel("TRASH");
		lblTrash.setFont(new Font("Snap ITC", Font.ITALIC, 29));
		lblTrash.setBackground(Color.RED);
		lblTrash.setBounds(748, 54, 125, 38);
		contentPane.add(lblTrash);
		
		JLabel lblInbox = new JLabel("INBOX");
		lblInbox.setFont(new Font("Snap ITC", Font.ITALIC, 29));
		lblInbox.setBackground(Color.RED);
		lblInbox.setBounds(112, 52, 134, 38);
		contentPane.add(lblInbox);
		
		JLabel lblSent = new JLabel("SENT");
		lblSent.setFont(new Font("Snap ITC", Font.ITALIC, 29));
		lblSent.setBackground(Color.RED);
		lblSent.setBounds(15, 340, 110, 38);
		contentPane.add(lblSent);
		
		JLabel lblCompose = new JLabel("Compose");
		lblCompose.setFont(new Font("Snap ITC", Font.ITALIC, 29));
		lblCompose.setBackground(Color.RED);
		lblCompose.setBounds(828, 340, 147, 38);
		contentPane.add(lblCompose);
		
		JLabel lblNewLabel = new JLabel("DRAFT");
		lblNewLabel.setBackground(Color.RED);
		lblNewLabel.setFont(new Font("Snap ITC", Font.ITALIC, 29));
		lblNewLabel.setBounds(421, 136, 134, 38);
		contentPane.add(lblNewLabel);
		trash.setIcon(new ImageIcon(img6));
		trash.setOpaque(false);
		trash.setContentAreaFilled(false);
		trash.setBorderPainted(false);
		trash.setBounds(738, 87, 140, 143);
		contentPane.add(trash);
		
		JButton draft = new JButton("");
		draft.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				C.folder="Drafts";
				dispose();
				DraftGui test = new DraftGui(C);
				test.setVisible(true);
			}
		});
		
		draft.setOpaque(false);
		draft.setContentAreaFilled(false);
		draft.setBorderPainted(false);
		draft.setBounds(423, 0, 134, 137);
		contentPane.add(draft);
		plus.setBounds(832, 377, 140, 143);
		contentPane.add(plus);
		
		JButton sent = new JButton("");
		sent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				C.folder="Sent";
				dispose();
				InboxGui test = new InboxGui(C);
				test.setVisible(true);
			}
		});
		
		sent.setOpaque(false);
		sent.setContentAreaFilled(false);
		sent.setBorderPainted(false);
		sent.setBounds(0, 366, 140, 154);
		contentPane.add(sent);
		inbox.setBounds(99, 87, 144, 137);
		contentPane.add(inbox);
		back.setBounds(15, 11, 72, 64);
		contentPane.add(back);
		
		JLabel background = new JLabel("");
		background.setBounds(-13, 0, 997, 560);
		background.setIcon(new ImageIcon(img));
		contentPane.add(background);
		Image img3= new ImageIcon(this.getClass().getResource("/bestsemt.png")).getImage();
		sent.setIcon(new ImageIcon(img3));
		Image img5= new ImageIcon(this.getClass().getResource("/bestdraft.png")).getImage();
		draft.setIcon(new ImageIcon(img5));
		
		
	
	}
}