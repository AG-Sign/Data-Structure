package eg.edu.alexu.csd.datastructure.mailServer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class OthersGui extends JFrame {

	private JPanel contentPane;
	private JTextField name;
	private JTextField newname;
	private JScrollPane scrollPane;
	private JTable table;

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
	public OthersGui(App C) {
		this.setVisible(true);
		this.setTitle("My Mail");
		Image image = new ImageIcon(this.getClass().getResource("/blue-mail.png")).getImage();
		this.setIconImage(image);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		setBounds(100, 100, 450, 441);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel box = new JLabel("");
		Image img1 = new ImageIcon(this.getClass().getResource("/mailbox3.jpg")).getImage();

		JButton btnNewButton = new JButton("Return");
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setBackground(Color.BLACK);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				UserGui test = new UserGui(C);
				test.setVisible(true);
			}
		});

		JButton btnNewButton_1 = new JButton("Create New Folder");
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnNewButton_1.setForeground(Color.WHITE);
		btnNewButton_1.setBackground(new Color(0, 0, 128));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String folder = name.getText();
				Folder test = new Folder();
				if(test.makeFolder(folder, C))
				{
					DefaultTableModel model = (DefaultTableModel) table.getModel();
					model.setRowCount(0);
					File adding = new File("Accounts\\"+C.currentUser.getEmail()+"\\Others");
					File[] files = adding.listFiles();
					 int i=0;
						while(i<files.length)
						{
								model.addRow(new Object[] {files[i].getName()});
								i++;
						}
				}
				
			}
		});
		btnNewButton_1.setBounds(7, 139, 128, 23);
		contentPane.add(btnNewButton_1);
		btnNewButton.setBounds(17, 370, 89, 23);
		contentPane.add(btnNewButton);
		box.setIcon(new ImageIcon(img1));
		box.setBounds(264, 0, 180, 216);
		contentPane.add(box);

		JButton delete = new JButton("Delete Folder");
		delete.setForeground(Color.WHITE);
		delete.setFont(new Font("Tahoma", Font.BOLD, 12));
		delete.setBackground(new Color(139, 0, 0));
		delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Folder test = new Folder();
				int selectedrow = table.getSelectedRow();
			if(selectedrow>=0)
			{
				if(test.DeleteFolder(selectedrow,C))
				{
					DefaultTableModel model = (DefaultTableModel) table.getModel();
					model.removeRow(selectedrow);
				}
			}
			else {
				JOptionPane.showMessageDialog(null, "Nothing is Selected");
			}
				
			}
		});
		delete.setBounds(294, 370, 128, 23);
		contentPane.add(delete);

		JButton rename = new JButton("Rename Folder");
		rename.setFont(new Font("Tahoma", Font.BOLD, 12));
		rename.setForeground(Color.WHITE);
		rename.setBackground(new Color(0, 128, 0));
		rename.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Folder test = new Folder();
				
				int selectedrow = table.getSelectedRow();
				if(selectedrow>=0)
				{
					if(test.Rename(selectedrow, newname.getText(),C))
					{
						
						DefaultTableModel model = (DefaultTableModel) table.getModel();
						model.setRowCount(0);
						File adding = new File("Accounts\\"+C.currentUser.getEmail()+"\\Others");
						File[] files = adding.listFiles();
						int i=0;
							while(i<files.length)
							{
									model.addRow(new Object[] {files[i].getName()});
									i++;
							}
					}
				}
				else {
					JOptionPane.showMessageDialog(null, "Nothing is Selected");
				}
				
			}
		});
		rename.setBounds(7, 173, 128, 23);
		contentPane.add(rename);

		name = new JTextField();
		name.setBounds(168, 140, 86, 20);
		contentPane.add(name);
		name.setColumns(10);


		newname = new JPlaceholder("   New Name");
		newname.setBounds(168, 176, 86, 20);
		contentPane.add(newname);
		newname.setColumns(10);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(17, 207, 405, 160);
		contentPane.add(scrollPane);

		table = new JTable();
		table.setFont(new Font("Tahoma", Font.BOLD, 13));
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Other Folders"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		scrollPane.setViewportView(table);

		JLabel lblNewLabel = new JLabel("MyFolders");
		lblNewLabel.setFont(new Font("Snap ITC", Font.BOLD, 38));
		lblNewLabel.setBounds(0, 0, 254, 114);
		contentPane.add(lblNewLabel);
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		File adding = new File("Accounts\\"+C.currentUser.getEmail()+"\\Others");
		File[] files = adding.listFiles();
		    int i=0;
			while(i<files.length)
			{
					model.addRow(new Object[] {files[i].getName()});
					i++;
			}
		
	}

	}