package eg.edu.alexu.csd.datastructure.mailServer;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;


public class test extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					test frame = new test();
				} catch (Exception e) {
					e.printStackTrace();
				} 
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public test() {
		this.setVisible(true);
		this.setTitle("My Mail");
		Image img = new ImageIcon(this.getClass().getResource("/mail2.png")).getImage();
		this.setIconImage(img);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		getContentPane().setLayout(null);
		contentPane.setLayout(null);
		
		textField = new JTextField("");
	
		textField.setBounds(162, 88, 175, 29);
		textField.setForeground(Color.black);
		contentPane.add(textField);
		textField.setColumns(10);
		
		
		
	}
}
