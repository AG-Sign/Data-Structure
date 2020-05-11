package eg.edu.alexu.csd.datastructure.mailServer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Document;
import javax.swing.text.Highlighter;
import javax.swing.text.JTextComponent;
import javax.swing.text.Highlighter.Highlight;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JComponent;

import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.File;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;

public class viewEmails extends JFrame {

	private JPanel contentPane;
	private IndexFile ind=new IndexFile();
	JList list;
	private String [] names;
	private String [] attachments;
	Search s=new Search();
	private JTextField textField;
	private JTextField textField_1;

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
	public viewEmails(App C,Mail email) {
		this.setVisible(true);
		setTitle("My Mail");
		Image image = new ImageIcon(this.getClass().getResource("/blue-mail.png")).getImage();
		this.setIconImage(image);
		this.setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 631, 394);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null );
		
		JLabel lblNewLabel_2 = new JLabel(email.getSubject());
		lblNewLabel_2.setBounds(95, 46, 294, 25);
		contentPane.add(lblNewLabel_2);
		if(email.getSubject().equals("NOSUBJECT")) {
			lblNewLabel_2.setText("");
		}
		else 
		lblNewLabel_2.setText(email.getSubject());
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setBounds(85, 11, 304, 25);
		contentPane.add(lblNewLabel_1);
		lblNewLabel_1.setText(email.getFrom());
		
		JLabel lblNewLabel = new JLabel(" From : ");
		lblNewLabel.setBounds(10, 11, 65, 25);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		contentPane.add(lblNewLabel);
		
		JLabel lblSubject = new JLabel("Subject : ");
		lblSubject.setBounds(10, 47, 75, 25);
		lblSubject.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		contentPane.add(lblSubject);
		
		JLabel lblPriority = new JLabel("Priority :");
		lblPriority.setBounds(10, 83, 66, 25);
		lblPriority.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		contentPane.add(lblPriority);
		String priority="";
		switch (email.getPriority()){
		case 1:
			priority="Urgent";
			break;
		case 2:
			priority="High";
			break;
		case 3:
			priority="Medium";
			break;
		case 4:
			priority="Low";
			break;
			
		}
		JLabel label = new JLabel("");
		label.setBounds(85, 83, 304, 25);
		contentPane.add(label);
		label.setText(priority);
		
		JLabel lblBody = new JLabel("Body :");
		lblBody.setBounds(10, 119, 66, 25);
		lblBody.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		contentPane.add(lblBody);
		contentPane.setBackground(Color.white);
		
		JLabel lblAttachments = new JLabel("Attachments");
		lblAttachments.setBounds(500, 11, 99, 25);
		lblAttachments.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		contentPane.add(lblAttachments);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(500, 47, 99, 199);
		contentPane.add(scrollPane);
		
		
		
		
		 attachments=new String [email.getAttachments().size()];
		for(int i=0;i<email.getAttachments().size();i++)
			attachments[i]=(String)email.getAttachments().get(i);
		 names=new String[attachments.length];
		for (int i=0;i<names.length;i++)
			names[i]=(attachments[i]).substring(attachments[i].lastIndexOf('\\')+1, attachments[i].length());
		
		
		
		
		
		
		list = new JList(names);
		scrollPane.setViewportView(list);
		
		JButton btnNewButton = new JButton("View");
		btnNewButton.setBounds(510, 278, 89, 23);
		contentPane.add(btnNewButton)	;
		JButton btnNewButton_3 = new JButton("Search Attachment");
		btnNewButton_3.setBounds(351, 16, 139, 21);
		contentPane.add(btnNewButton_3);
		btnNewButton_3.addActionListener(new ActionListener () {
			public void actionPerformed(ActionEvent e) {
				
				if(list.getModel().getSize()==0) {
					JOptionPane.showMessageDialog(null, "No Attachments to show");
				}
				else {
				String path=(s.searchAttachments(ind.getAttachments(email.getPath()),textField.getText()));

				if(path==null)
					JOptionPane.showMessageDialog(null, "Attachment Not found");
				else {
					File pathFile=new File(path);
					int searched=0;
					for(int i=0;i<names.length;i++) {
						if(pathFile.getName().equals(names[i])) {
							searched=i;
							break;
						}
					}
					list.setSelectedIndex(searched);
				}
			}
			}
			
		});
		
		
	;
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(list.getModel().getSize()==0) {
					JOptionPane.showMessageDialog(null, "No Attachments to show");
				}
				else if (list.isSelectionEmpty()) {
					JOptionPane.showMessageDialog(null, "Select an attachment");
				}
				else {
					ind.viewAttachment(attachments[list.getSelectedIndex()]);
				}
			}
			
			
		});
		
		
		JButton btnNewButton_2=new JButton("Delete");
		btnNewButton_2.setBounds(415, 279, 85, 21);
		btnNewButton_2.setVisible(true);
		contentPane.add(btnNewButton_2);
		btnNewButton_2.addActionListener(new ActionListener () {
			public void actionPerformed(ActionEvent e) {
				if(list.getModel().getSize()==0) {
					JOptionPane.showMessageDialog(null, "No Attachments to show");
				}
				else if (list.isSelectionEmpty()) {
					JOptionPane.showMessageDialog(null, "Select an attachment");
				}
				else {
					ind.deleteAttachment(attachments[list.getSelectedIndex()]);
					SLL newAttachments=ind.getAttachments(email.getPath());
					attachments=new String[newAttachments.size()];
					for(int i=0;i<attachments.length;i++) {
						attachments[i]=(String)newAttachments.get(i);
					}
					 names=new String[attachments.length];
					for(int i=0;i<attachments.length;i++)
						names[i]=(attachments[i]).substring(attachments[i].lastIndexOf('\\')+1, attachments[i].length());
					list=new JList(names);
					scrollPane.setViewportView(list);;
				}
			}
			
			
			
		});
		
		
		
		
		
		
		
		
		
		JButton btnNewButton_1 = new JButton("Return");
		btnNewButton_1.setBounds(526, 331, 89, 23);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				InboxGui test = new InboxGui(C);
				test.setVisible(true);
				
			}
		});
		contentPane.add(btnNewButton_1);
		textField = new JTextField();
		textField.setBounds(351, 63, 139, 19);
		textField.setText("     Attachment Name");
		textField.setForeground(Color.gray);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(25, 188, 368, 168);
		if(email.getBody().equals("NOTEXT"))
			textArea.setText("");
		else 
		textArea.setText(email.getBody());
		textArea.setEditable(false);
		textArea.setFont(new Font("Serif", Font.ITALIC, 16));
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		contentPane.add(textArea);
		
		JButton btnNewButton_4 = new JButton("Search Body");
		btnNewButton_4.setBounds(241, 118, 89, 21);
		contentPane.add(btnNewButton_4);
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				highlightSearched(textArea,textField_1.getText());
			}
		});
		
		
		textField_1 = new JTextField();
		textField_1.setBounds(234, 159, 185, 19);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		textField_1.setText("Search Text");
		textField_1.setForeground(Color.gray);
		textField_1.addFocusListener(new FocusAdapter() {
			@Override 
			public void focusGained(FocusEvent e ) {
				if(textField_1.getText().trim().toLowerCase().equals("search text")) {
					textField_1.setText("");
					textField_1.setForeground(Color.black);
				}
			}
			@Override 
			public void focusLost(FocusEvent e) {
				if(textField_1.getText().trim().equals("")||textField_1.getText().trim().toLowerCase().equals("search text")) {
					textField_1.setText("Search Text");
					textField_1.setForeground(Color.gray);
				}
			}
			
			
			
		});
			
		
		
		
		textField.addFocusListener(new FocusAdapter() {
			@Override 
			public void focusGained(FocusEvent e ) {
				if(textField.getText().trim().toLowerCase().equals("attachment name")) {
					textField.setText("");
					textField.setForeground(Color.black);
				}
			}
			@Override 
			public void focusLost(FocusEvent e) {
				if(textField.getText().trim().equals("")||textField.getText().trim().toLowerCase().equals("attachment name")) {
					textField.setText("     Attachment Name");
					textField.setForeground(Color.gray);
				}
			}
			
			
			
		});
			
		
		
	}
	class MyHighlighterPainter extends DefaultHighlighter.DefaultHighlightPainter{

		public MyHighlighterPainter(Color c) {
			super(c);
		}
		
		}
	Highlighter.HighlightPainter myHighlightPainter=new MyHighlighterPainter(Color.blue);
	public void highlightSearched(JTextComponent textComp,String search) {
		removeHighlight(textComp);
		try {
			Highlighter highlight=textComp.getHighlighter();
			Document Doc=textComp.getDocument();
			String text =Doc.getText(0, Doc.getLength());
			int pos=0;
			while((pos=text.toUpperCase().indexOf(search.toUpperCase(),pos))>=0) {
				highlight.addHighlight(pos, pos+search.length(), myHighlightPainter);
				pos+=search.length();
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void removeHighlight(JTextComponent textComp) {
		Highlighter highlight=textComp.getHighlighter();
		Highlighter.Highlight[] highlights=highlight.getHighlights();
		for(int i=0;i<highlights.length;i++) {
			if(highlights[i].getPainter() instanceof MyHighlighterPainter) {
				highlight.removeHighlight(highlights[i]);
			}
		}
		
		
	}
		

}