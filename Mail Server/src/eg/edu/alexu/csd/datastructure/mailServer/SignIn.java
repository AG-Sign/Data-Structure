package eg.edu.alexu.csd.datastructure.mailServer;

import java.awt.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JPasswordField;

public class SignIn extends JFrame {

	private JPanel contentPane;
	private JTextField Email;
	private JPasswordField password;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SignIn frame = new SignIn();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public SignIn() {
		this.setVisible(true);
		this.setTitle("My Mail");
		Image img = new ImageIcon(this.getClass().getResource("/blue-mail.png")).getImage();
		this.setIconImage(img);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(100, 100, 631, 508);
		this.setLocation(350, 150);
		this.setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel bigimage = new JLabel("");
		
		bigimage.setBounds(0, 151, 437, 328);
		Image img1 =new ImageIcon(this.getClass().getResource("/Email-Newsletters.png")).getImage();
		 
		 JLabel welcome = new JLabel("");
		 Image imgy =new ImageIcon(this.getClass().getResource("/w7.jpg")).getImage();
		 
		 JLabel lblNewLabel = new JLabel("");
	
		 lblNewLabel.setBackground(Color.WHITE);
		 lblNewLabel.setBounds(398, 0, 39, 153);
		 contentPane.add(lblNewLabel);
		 welcome.setIcon(new ImageIcon(imgy));
		 welcome.setBounds(0, 0, 400, 153);
		 contentPane.add(welcome);
		 bigimage.setIcon(new ImageIcon(img1));
		contentPane.add(bigimage);
		
		JLabel label_1 = new JLabel("_______________________");
		label_1.setBounds(447, 139, 178, 14);
		contentPane.add(label_1);
		
		JLabel label_2 = new JLabel("_______________________");
		label_2.setBounds(447, 274, 178, 14);
		contentPane.add(label_2);
		
		JLabel smallimage = new JLabel("");
		Image img2 =new ImageIcon(this.getClass().getResource("/b.png")).getImage();
		smallimage.setIcon(new ImageIcon(img2));
		smallimage.setBounds(457, 11, 134, 130);
		contentPane.add(smallimage);
		
		JButton login = new JButton("Login");
		login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				App C = new App();
				String s = String.valueOf(password.getPassword());
				if(C.signin(Email.getText(), s))
				{
					JOptionPane.showMessageDialog(null, "Successfully Logged In");	
					dispose();
					UserGui test=new UserGui(C);
					test.setVisible(true);
				}
				
			}
		});
		Image img3 = new ImageIcon(this.getClass().getResource("/ok-icon (1).png")).getImage();
		login.setIcon(new ImageIcon(img3));
		login.setFont(new Font("Tahoma", Font.BOLD, 13));
		login.setBounds(469, 311, 112, 23);
		contentPane.add(login);
		
		JLabel label_5 = new JLabel("New Account ?... ");
		label_5.setForeground(new Color(51, 51, 102));
		label_5.setBackground(Color.LIGHT_GRAY);
		label_5.setBounds(473, 377, 96, 14);
		contentPane.add(label_5);
		
		JButton signup = new JButton("Sign Up");
		signup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				SignUp test =new SignUp();
				test.setVisible(true);
			}
		});
		Image img4 = new ImageIcon(this.getClass().getResource("/Sign.png")).getImage();
		signup.setIcon(new ImageIcon(img4));
		signup.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		signup.setBounds(473, 402, 96, 23);
		contentPane.add(signup);
				
						Email = new JPlaceholder("              Email");
						Email.setFont(new Font("Tahoma", Font.BOLD, 13));
								
						Email.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
							}
						});
						Email.setToolTipText("");
						Email.setColumns(10);
						Email.setBounds(446, 177, 157, 30);
						contentPane.add(Email);
						
						password = new JPlaceholder2("           Password");
								
						password.setToolTipText("");
						password.setFont(new Font("Tahoma", Font.BOLD, 13));
						password.setBounds(447, 233, 156, 30);
						contentPane.add(password);
						Image img5 = new ImageIcon(this.getClass().getResource("/white.jpg")).getImage();
						 lblNewLabel.setIcon(new ImageIcon(img5));
		
		 
		
	}
}
