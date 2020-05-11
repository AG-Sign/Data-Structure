package eg.edu.alexu.csd.datastructure.mailServer;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.Toolkit;
import javax.swing.JPasswordField;

public class SignUp extends JFrame {

	private JPanel contentPane;
	private JTextField Email;
	private JButton Signin;
	private JButton Signup;
	private JTextField FirstName;
	private JTextField LastName;
	private JPasswordField Password;
	private JPasswordField ConfirmPassword;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SignUp frame = new SignUp();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public SignUp() {
		this.setVisible(true);
		this.setTitle("My Mail");
		Image image = new ImageIcon(this.getClass().getResource("/blue-mail.png")).getImage();
		this.setIconImage(image);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(100, 100, 638, 365);
		this.setLocation(350, 150);
		this.setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		Image img =new ImageIcon(this.getClass().getResource("/mail3.png")).getImage();
		
		Signup = new JButton("Register");
		Image login = new ImageIcon(this.getClass().getResource("/login1.png")).getImage();
		Signup.setIcon(new ImageIcon(login));
		Signup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				App C=new App();
				UserAccount acc=new UserAccount();
				acc.setEmail(Email.getText());
				String Name = FirstName.getText()+" "+LastName.getText();
				acc.setName(Name);
				String s1=String.valueOf(ConfirmPassword.getPassword());
				String s2 =String.valueOf(Password.getPassword());
				acc.setPassword(s1);
				if(!String.valueOf(s1).equals(String.valueOf(s2))) {
					
					JOptionPane.showMessageDialog(null, "Password and confirm password fields are different");	
				}
				else if(FirstName.getText().trim().equals("First Name")){
			JOptionPane.showMessageDialog(null, "Enter Your First Name");
				}
				else if (LastName.getText().trim().equals("Last Name"))
					JOptionPane.showMessageDialog(null, "Enter Your Last Name");
				else if(Password.getText().trim().equals("Password")||ConfirmPassword.getText().trim().equals("Confirm Password")) {
					JOptionPane.showMessageDialog(null, "Enter Password and Confirm it");
				}
				else if(C.signup(acc)) {
					JOptionPane.showMessageDialog(null, "You have created your account successfully");
					dispose();
					UserGui test =new UserGui(C);
					test.setVisible(true);
				}
				else {
					JOptionPane.showMessageDialog(null,"");//TODO Show a message that includes the rules of the email address of the user or show that the email exists
				}
				
				
			}
				
		});
		
		ConfirmPassword =  new JPlaceholder2("                       Confirm Password");
		ConfirmPassword.setFont(new Font("Tahoma", Font.BOLD, 13));
		ConfirmPassword.setBounds(44, 220, 306, 30);
		contentPane.add(ConfirmPassword);
		
		Password = new JPlaceholder2("                              Password");
		Password.setFont(new Font("Tahoma", Font.BOLD, 13));
		Password.setBounds(44, 179, 306, 30);
		contentPane.add(Password);
		
		FirstName = new JPlaceholder("          First Name");
		FirstName.setFont(new Font("Tahoma", Font.BOLD, 13));
		FirstName.setBounds(44, 82, 150, 30);
		contentPane.add(FirstName);
		FirstName.setColumns(10);
		
		LastName = new JPlaceholder("          Last Name");
		LastName.setFont(new Font("Tahoma", Font.BOLD, 13));
		LastName.setColumns(10);
		LastName.setBounds(200, 82, 150, 30);
		contentPane.add(LastName);
		Signup.setFont(new Font("Tahoma", Font.BOLD, 13));
		Signup.setBounds(234, 273, 116, 30);
		contentPane.add(Signup);
		
		Signin = new JButton("Sign In");
		Signin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				SignIn test =new SignIn();
				test.setVisible(true);
				
			}
				
			
		});
		Image img5 =new ImageIcon(this.getClass().getResource("/sign-left-icon.png")).getImage();
		Signin.setIcon(new ImageIcon(img5));
		Signin.setFont(new Font("Tahoma", Font.BOLD, 13));
		Signin.setBounds(44, 273, 98, 30);
		contentPane.add(Signin);
		
		Email = new JPlaceholder("                                 Email");
		Email.setFont(new Font("Tahoma", Font.BOLD, 13));
		Email.setColumns(10);
		Email.setBounds(44, 138, 306, 30);
		contentPane.add(Email);
		
		JLabel label_1 = new JLabel("________________________");
		label_1.setBounds(313, 44, 178, 14);
		contentPane.add(label_1);
		
		JLabel label = new JLabel("________________________");
		label.setBounds(42, 44, 178, 14);
		contentPane.add(label);
		
		JLabel lblNewLabel_1 = new JLabel("Sign Up");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 25));
		lblNewLabel_1.setBounds(212, 21, 108, 48);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel.setIcon(new ImageIcon(img));
		lblNewLabel.setBounds(0, -13, 622, 343);
		contentPane.add(lblNewLabel);
	}

}
