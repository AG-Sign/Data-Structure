package eg.edu.alexu.csd.datastructure.mailServer;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.io.File;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import java.awt.Color;

public class ComposeGui extends JFrame {
	

	enum PRIORITY{
		Urgent,High,Medium,Low;
	}
	private JPanel contentPane;
	private JTextField subject;
	private JTextArea receivers;
	private JTextArea body;
	private SLL filesSLL=new SLL();
	private JList attachList;
	private Mail newEmail;
	private App c=new App();
	DefaultListModel dlm=new DefaultListModel();
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
	public ComposeGui(App C) {
		this.setVisible(true);
		setTitle("My Mail");
		Image image = new ImageIcon(this.getClass().getResource("/blue-mail.png")).getImage();
		this.setIconImage(image);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	//	Dimension fullscreen=Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(100, 100, 750,568);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		subject = new JTextField();
		subject.setColumns(10);
		subject.setBounds(136, 265, 354, 31);
		contentPane.add(subject);
		
		JLabel lblNewLabel = new JLabel("Recievers :");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		lblNewLabel.setBounds(10, 205, 120, 43);
		contentPane.add(lblNewLabel);
		
		JLabel lblSubject = new JLabel("Subject :");
		lblSubject.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		lblSubject.setBounds(27, 260, 99, 43);
		contentPane.add(lblSubject);
		
		JComboBox priority = new JComboBox(PRIORITY.values());
		priority.setSelectedIndex(3);
		priority.setFont(new Font("Tahoma", Font.BOLD, 12));
		priority.setToolTipText("");
		priority.setMaximumRowCount(4);
		
		priority.setBounds(302, 451, 74, 23);
		contentPane.add(priority);
		
		JLabel lblPriority = new JLabel("Priority :");
		lblPriority.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblPriority.setBounds(212, 440, 80, 43);
		contentPane.add(lblPriority);
		
		JLabel lblBody = new JLabel("Body : ");
		lblBody.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		lblBody.setBounds(48, 315, 74, 43);
		contentPane.add(lblBody);
		
		receivers = new JTextArea();
		receivers.setBounds(136, 210, 354, 31);
		contentPane.add(receivers);
		
		 body = new JTextArea();
			body.setBounds(136, 320, 354, 120);
			body.setLineWrap(true);
			body.setWrapStyleWord(true);
			contentPane.add(body);
		
		
		
		
		JButton btnNewButton_1 = new JButton("Add Attachments");
		btnNewButton_1.setForeground(Color.WHITE);
		btnNewButton_1.setBackground(new Color(0, 100, 0));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser= new JFileChooser();
				fileChooser.setMultiSelectionEnabled(true);
				int r=fileChooser.showOpenDialog(null);
				if(r==JFileChooser.APPROVE_OPTION) {
					File[] filesArray=fileChooser.getSelectedFiles();
					for(int i=0;i<filesArray.length;i++) {
						filesSLL.add(filesArray[i].getAbsolutePath().replace("\\", "\\\\"));
					}
					for(int i=0;i<filesArray.length;i++) {
						dlm.addElement(filesArray[i].getName());
					}
				}
			}
			
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnNewButton_1.setBounds(547, 490, 163, 31);
		contentPane.add(btnNewButton_1);
		
		 attachList = new JList();
		 attachList.setModel(dlm);
		attachList.setBounds(580, 205, 99, 232);
		contentPane.add(attachList);
		
		JButton btnNewButton_2 = new JButton("Delete Attachment");
		btnNewButton_2.setForeground(Color.WHITE);
		btnNewButton_2.setBackground(new Color(139, 0, 0));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int [] indices =attachList.getSelectedIndices();
				for(int i:indices) {
					filesSLL.remove(i);
					dlm.remove(i);
				}
			}
		});
		btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		btnNewButton_2.setBounds(547, 448, 163, 31);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton = new JButton("Send");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setBackground(new Color(0, 0, 128));
		Image img2 =new ImageIcon(this.getClass().getResource("/send.png")).getImage();
		btnNewButton.setIcon(new ImageIcon(img2));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				newEmail=new Mail();
				String toString =receivers.getText();
				if(toString.trim().length()!=0) {
					String [] toArray =toString.split(",");
					SLL toSLL= new SLL();
					for(int i=0;i<toArray.length;i++) {
						toSLL.add(toArray[i]);
					}
					newEmail.setTo(toSLL);
				}
				newEmail.setSubject(subject.getText());
				switch( (PRIORITY)priority.getSelectedItem()){
				case Urgent :
					newEmail.setPriority(1);
					break;
				case High:
					newEmail.setPriority(2);
					break;
				case Medium:
					newEmail.setPriority(3);
					break;
				case Low :
					newEmail.setPriority(4);
					break;
				}
				newEmail.setBody(body.getText());
				newEmail.setAttachments(filesSLL);
				DateTimeFormatter dtf=DateTimeFormatter.ofPattern("yyyy-MM-dd HH,mm,ss");
				LocalDateTime now=LocalDateTime.now();
				newEmail.setDate(dtf.format(now));
				newEmail.setFrom(C.currentUser.getEmail());
				
				
				newEmail.setFolder("Sent");
				if(c.compose(newEmail)) {
					JOptionPane.showMessageDialog(null,"The email has been sent successfully");
				dispose();
				UserGui test = new UserGui(C);
				test.setVisible(true);}
				
			}
		});
		btnNewButton.setBounds(580, 35, 110, 43);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_3 = new JButton("Save As Draft");
		btnNewButton_3.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 newEmail=new Mail();
					String toString =receivers.getText();
					if(toString.trim().length()!=0) {
						String [] toArray =toString.split(",");
						SLL toSLL= new SLL();
						for(int i=0;i<toArray.length;i++) {
							toSLL.add(toArray[i]);
						}
						newEmail.setTo(toSLL);
					}
					newEmail.setSubject(subject.getText());
					switch( (PRIORITY)priority.getSelectedItem()){
					case Urgent :
						newEmail.setPriority(1);
						break;
					case High:
						newEmail.setPriority(2);
						break;
					case Medium:
						newEmail.setPriority(3);
						break;
					case Low :
						newEmail.setPriority(4);
						break;
					}
					newEmail.setBody(body.getText());
					newEmail.setAttachments(filesSLL);
					DateTimeFormatter dtf=DateTimeFormatter.ofPattern("yyyy-MM-dd HH,mm,ss");
					LocalDateTime now=LocalDateTime.now();
					newEmail.setDate(dtf.format(now));
					newEmail.setFrom(C.currentUser.getEmail());
					newEmail.setPath("Accounts\\"+newEmail.getFrom()+"\\"+newEmail.getFrom()+newEmail.getDate());
					newEmail.setFolder("Drafts");
					newEmail.setEmailFolder("Drafts");
					 if (c.compose(newEmail))
						{JOptionPane.showMessageDialog(null,"Saved as draft");
					 dispose();
						UserGui test = new UserGui(C);
						test.setVisible(true);
						}
			}
		});
		btnNewButton_3.setBounds(562, 114, 140, 31);
		contentPane.add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("Discard");
		btnNewButton_4.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton_4.setForeground(Color.WHITE);
		btnNewButton_4.setBackground(Color.BLACK);
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				UserGui test = new UserGui(C);
				test.setVisible(true);
			}
		});
		btnNewButton_4.setBounds(10, 493, 89, 23);
		contentPane.add(btnNewButton_4);
		
		JLabel background = new JLabel("");
		Image imeg = new ImageIcon(this.getClass().getResource("/mailbirds1.png")).getImage();
		background.setIcon(new ImageIcon(imeg));
		background.setBounds(0, 0, 528, 179);
		contentPane.add(background);
		
		JLabel lblNewLabel_1 = new JLabel("__________________________________________________________________________________________________________");
		lblNewLabel_1.setBounds(-14, 178, 762, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Please Seperate between each reciever by a comma");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_2.setBounds(139, 190, 351, 14);
		contentPane.add(lblNewLabel_2);
		Image imege = new ImageIcon(this.getClass().getResource("/gos.jpg")).getImage();
		
		
	}
}

