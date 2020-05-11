package eg.edu.alexu.csd.datastructure.mailServer;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import java.awt.Font;
import java.awt.Color;


public class Contacts_Gui extends JFrame {

	
	private JPanel contentPane;
	private JTable table;
	private JTextField add;

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
	public Contacts_Gui(App C) {
		this.setVisible(true);
		this.setResizable(false);
		setTitle("Contacts");
		Image image = new ImageIcon(this.getClass().getResource("/blue-mail.png")).getImage();
		this.setIconImage(image);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		this.setLocation(400, 200);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		JButton btnNewButton = new JButton("Return");
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setBackground(Color.BLACK);
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				UserGui test = new UserGui(C);
				test.setVisible(true);
			}
		});
		btnNewButton.setBounds(10, 227, 89, 23);
		contentPane.add(btnNewButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 45, 414, 171);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Contacts"
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
		table.getColumnModel().getColumn(0).setPreferredWidth(207);
		

		DefaultTableModel model = (DefaultTableModel) table.getModel();
		BufferedReader put;
		try {
			put = new BufferedReader(new FileReader("Accounts\\"+C.currentUser.getEmail()+"\\Contacts\\Index.txt"));
			String line =put.readLine();
			while(line!=null)
			{
				
				model.addRow(new Object[]{line});
				line = put.readLine();
			}
			put.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		JButton btnNewButton_1 = new JButton("Add");
		btnNewButton_1.setForeground(Color.WHITE);
		btnNewButton_1.setBackground(new Color(25, 25, 112));
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Contact cont = new Contact();
				if(cont.add(add.getText(), C))
				{
					model.addRow(new Object[] {add.getText()});
				}
				
				
			}
		});
		btnNewButton_1.setBounds(335, 11, 89, 23);
		contentPane.add(btnNewButton_1);
		/*ListSelectionModel model1 = table.getSelectionModel();
		model1.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				// TODO Auto-generated method stub
				/*if(model1.isSelectionEmpty())
				{
					JOptionPane.showMessageDialog(null, "Nothing is Selected");
				}
				else
				{
					int selectedrow = model1.getMinSelectionIndex();
					BufferedReader put2;
					try {
						put2 = new BufferedReader(new FileReader("Accounts\\"+C.currentUser.getEmail()+"\\Contacts\\Index.txt"));
						String line2 =put2.readLine();
						int i=0 ;
						while(i<selectedrow)
						{
							line2 = put2.readLine();
							i++;
						}
						line2.trim();
						put2.close();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
				if(!model1.isSelectionEmpty())
				{
					int i = model1.getMinSelectionIndex();
					
					int j=table.getSelectedRow();
					
				}
				
			}			
});*/
	
		
JButton btnNewButton_2 = new JButton("Delete");
btnNewButton_2.setForeground(Color.WHITE);
btnNewButton_2.setBackground(new Color(165, 42, 42));
btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedrow = table.getSelectedRow();
				if(selectedrow>=0)
				{
					model.removeRow(selectedrow);
					Contact cont = new Contact();
					cont.delete(selectedrow, C);
					JOptionPane.showMessageDialog(null, "Contact Removed");
				}
				else
				{
					JOptionPane.showMessageDialog(null, "No Contact Selected");
				}
				
			}
		});
		btnNewButton_2.setBounds(335, 227, 89, 23);
		contentPane.add(btnNewButton_2);
		
		add = new JTextField();
		add.setBounds(10, 11, 315, 23);
		contentPane.add(add);
		add.setColumns(10);
	}

}