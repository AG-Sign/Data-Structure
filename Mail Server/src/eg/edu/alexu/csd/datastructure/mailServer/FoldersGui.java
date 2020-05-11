package eg.edu.alexu.csd.datastructure.mailServer;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class FoldersGui extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JButton exit;

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
	public FoldersGui(App C) {
		this.setVisible(false);
		this.setTitle("My Mail");
		Image image = new ImageIcon(this.getClass().getResource("/blue-mail.png")).getImage();
		this.setIconImage(image);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		 //this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setUndecorated(true);
		this.setLocation(400, 250);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 31, 430, 258);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Folders"
			}
		));
		scrollPane.setViewportView(table);
		
		exit = new JButton("");
		
		Image img1 = new ImageIcon(this.getClass().getResource("/close.png")).getImage();
		exit.setIcon(new ImageIcon(img1));
		exit.setOpaque(false);
		exit.setContentAreaFilled(false);
		exit.setBorderPainted(false);
		exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		exit.setBounds(406, 0, 34, 27);
		contentPane.add(exit);
		/*DefaultTableModel model = (DefaultTableModel) table.getModel();
		BufferedReader put;
		try {
			put = new BufferedReader(new FileReader("Accounts\\"+C.currentUser.getEmail()));
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
		}*/
		
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		File test = new File("Accounts\\"+C.currentUser.getEmail());
		File[] files = test.listFiles();
		Stacks A = new Stacks();
		int i=0;
		String[] split = null ;
		while(i<files.length)
		{
			split[i] = files[i].toString();
		}
		i=0;
		String end[] =null;
		String[] foldername = null;
		while(i<split.length)
		{
			end=split[i].split("\\");
			foldername[i]=end[end.length-1];
		}
			while(i<files.length)
			{
				
				model.addRow(new Object[] {foldername[i]});
				i++;
			}
	}

}