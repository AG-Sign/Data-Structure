package eg.edu.alexu.csd.datastructure.mailServer;

import java.awt.EventQueue;
import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;

public class MyMail {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MyMail window = new MyMail();
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
	public MyMail() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		Image img = new ImageIcon(this.getClass().getResource("/mail2.png")).getImage();
		frame.setIconImage(img);
		frame.setTitle("My Mail");
		frame.getContentPane().setLayout(null);
		//frame.setResizable(false);
		
		JLabel lblNewLabel = new JLabel("");
		Image img1 = new ImageIcon(this.getClass().getResource("/Email-Newsletters.png")).getImage();
		
		JButton btnNewButton_1 = new JButton("Sign Up");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*frame.dispose();
				second test = new second();
				test.setVisible(true);*/
				
			}
		});
		Image img4 = new ImageIcon(this.getClass().getResource("/Sign.png")).getImage();
		
		JButton btnNewButton = new JButton("Login");
		Image image = new ImageIcon(this.getClass().getResource("/ok-icon (1).png")).getImage();
		
		JLabel lblNewLabel_5 = new JLabel("PASSWORD");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_5.setForeground(Color.LIGHT_GRAY);
		lblNewLabel_5.setBounds(510, 164, 73, 14);
		frame.getContentPane().add(lblNewLabel_5);
		btnNewButton.setIcon(new ImageIcon(image));
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnNewButton.setBounds(494, 222, 96, 23);
		frame.getContentPane().add(btnNewButton);
		btnNewButton_1.setIcon(new ImageIcon(img4));
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		btnNewButton_1.setBounds(497, 298, 96, 23);
		frame.getContentPane().add(btnNewButton_1);
		
		JLabel lblNewLabel_7 = new JLabel("New Account ?... ");
		lblNewLabel_7.setForeground(new Color(51, 51, 102));
		lblNewLabel_7.setBackground(Color.LIGHT_GRAY);
		lblNewLabel_7.setBounds(497, 273, 96, 14);
		frame.getContentPane().add(lblNewLabel_7);
		Image img3 = new ImageIcon(this.getClass().getResource("/ok-icon (1).png")).getImage();
		
		JLabel lblNewLabel_6 = new JLabel("_________________________");
		lblNewLabel_6.setBounds(449, 197, 178, 14);
		frame.getContentPane().add(lblNewLabel_6);
		lblNewLabel.setIcon(new ImageIcon(img1));
		lblNewLabel.setBounds(0, 0, 439, 338);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_2 = new JLabel("");
		Image img2 = new ImageIcon(this.getClass().getResource("/person-icon (big).png")).getImage();
		lblNewLabel_2.setIcon(new ImageIcon(img2));
		lblNewLabel_2.setBounds(510, 24, 48, 54);
		frame.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("________________________");
		lblNewLabel_3.setBounds(449, 77, 178, 14);
		frame.getContentPane().add(lblNewLabel_3);
		
		textField_1 = new JTextField();
		textField_1.setBounds(459, 156, 157, 30);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Email");
		lblNewLabel_4.setForeground(Color.LIGHT_GRAY);
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_4.setBounds(523, 123, 38, 14);
		frame.getContentPane().add(lblNewLabel_4);
		
		textField = new JTextField();
		textField.setBounds(459, 115, 157, 30);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setForeground(new Color(0, 0, 255));
		lblNewLabel_1.setBackground(Color.BLUE);
		
		lblNewLabel_1.setBounds(439, 0, 198, 338);
		frame.getContentPane().add(lblNewLabel_1);
		
		
		
		
	}
}
