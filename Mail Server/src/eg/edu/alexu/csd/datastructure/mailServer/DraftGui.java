package eg.edu.alexu.csd.datastructure.mailServer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import javax.swing.ListCellRenderer;
import java.awt.EventQueue;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JComboBox;
import javax.swing.JButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;

public class DraftGui extends JFrame {

	private JPanel contentPane;
	private JTable table;
	int page =1;
	private JTextField moved;
	private IndexFile ind=new IndexFile();
	Mail [] all=null;

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
	SLL allSLL=null;
	public DraftGui(App C) {
		this.setVisible(true);
		C.folder="Drafts";
		Folder deso=new Folder();
		deso.setPath("Accounts\\"+C.currentUser.getEmail()+"\\"+C.folder+"\\Index.txt");
		 allSLL=ind.getEmails(ind.readAll(deso));
		Stacks s=new Stacks();
		for(int i=0;i<allSLL.size();i++) {
			s.push(allSLL.get(i));
		}
			int size=allSLL.size();
		for(int i=0;i<size;i++) {

			allSLL.add(s.pop());
			
		}

		 all=new Mail[allSLL.size()];
		for(int i=0;i<all.length;i++) {
			all[i]=(Mail)allSLL.get(i);
		}

		setTitle("My Mail");
		
		Image image = new ImageIcon(this.getClass().getResource("/blue-mail.png")).getImage();
		this.setIconImage(image);
		this.setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 755, 474);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		 class MyComboBoxRenderer extends JLabel implements ListCellRenderer
		    {
		        private String _title;

		        public MyComboBoxRenderer(String title)
		        {
		            _title = title;
		        }

		        @Override
		        public Component getListCellRendererComponent(JList list, Object value,
		                int index, boolean isSelected, boolean hasFocus)
		        {
		            if (index == -1 && value == null) setText(_title);
		            else setText(value.toString());
		            return this;
		        }
		    }
			
			JButton btnNewButton_2 = new JButton("Show");
			btnNewButton_2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int flag=0;
					App ap =new App();
					int selectedmail=0;
					
					for(int i=0;i <table.getRowCount();i++)
					{
						if((boolean) table.getValueAt(i, 6))
						{
							
							selectedmail=(10*((ap.list.size()/10)-page+1)+(ap.list.size()%10)-i-1);
							flag++;
						}
					}
					
					if(flag==0)
					{
						JOptionPane.showMessageDialog(null, "Nothing is Selected");
					}
					else if (flag==1)
					{
						dispose();
						ViewEmailsDraft test = new ViewEmailsDraft(C,all[selectedmail]);
						test.setVisible(true);
					}
					else
					{
						JOptionPane.showMessageDialog(null, "There is more than one Email selected");
					}
					
				}
			});
			btnNewButton_2.setBounds(10, 11, 89, 23);
			contentPane.add(btnNewButton_2);
			
			JComboBox comboBox_1 = new JComboBox();
			comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"Default", "Date", "From", "Subject"}));
			comboBox_1.setRenderer(new MyComboBoxRenderer("Sort By"));
	        comboBox_1.setSelectedIndex(-1);
			comboBox_1.setBounds(653, 11, 76, 22);
			contentPane.add(comboBox_1);
		
			JButton btnNewButton_3 = new JButton("Compose");
			btnNewButton_3.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(allSLL==null||allSLL.size()==0) {
						JOptionPane.showMessageDialog(null, "No Emails");
					}
					else {
					int flag=0;
					App ap =new App();
					int selectedmail=0;
					
					for(int i=0;i <table.getRowCount();i++)
					{
						if((boolean) table.getValueAt(i, 6))
						{
							
							selectedmail=(10*((ap.list.size()/10)-page+1)+(ap.list.size()%10)-i-1);
							flag++;
						}
					}
					
					if(flag==0)
					{
						JOptionPane.showMessageDialog(null, "Nothing is Selected");
					}
					else if(flag==1)
					{
						dispose();
						DraftComposeGui test = new DraftComposeGui(C,all[selectedmail]);
						test.setVisible(true);
					}
					else
					{
						JOptionPane.showMessageDialog(null, "There is more than one Email selected");
					}
					}
				}
			});
			btnNewButton_3.setBounds(215, 11, 89, 23);
			contentPane.add(btnNewButton_3);
			btnNewButton_2.setBounds(10, 11, 89, 23);
			contentPane.add(btnNewButton_2);
		JButton delete = new JButton("Delete");
		delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int flag=0;
				for(int i=0;i<table.getRowCount();i++)
				{
					if((boolean) table.getValueAt(i, 6))
					{
						flag=1;
					}
				}if(flag==1)
				{
					
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				SLL mails = new SLL();
				App ap =new App();
				Folder fold=new Folder();
				Folder destination=new Folder();
				IndexFile index= new IndexFile();
				fold.setPath("Accounts\\"+C.currentUser.getEmail()+"\\"+C.folder+"\\Index.txt");
				destination.setPath("Accounts\\"+C.currentUser.getEmail()+"\\Trash\\Index.txt");
				Mail[] emi=(Mail[]) ap.listEmails(page);
				
				for(int i=0;i <table.getRowCount();i++)
				{
					if((boolean) table.getValueAt(i, 6))
					{
		
						//emi a5rha 10
						//formula lw aktr mn 10 ---> (10*((list.size/10)-page+1)+(list.size%10)-i-1)
						
						mails.add(all[(10*((ap.list.size()/10)-page+1)+(ap.list.size()%10)-i-1)].getPath());
						index.deleteLine(fold, destination,(all[(10*((ap.list.size()/10)-page+1)+(ap.list.size()%10)-i-1)].getPath()));
					}
				}
				C.deleteEmails(mails);
				Folder deso=new Folder();
				deso.setPath("Accounts\\"+C.currentUser.getEmail()+"\\"+C.folder+"\\Index.txt");
				 allSLL=ind.getEmails(ind.readAll(deso));
				allSLL.size();
				Stacks s=new Stacks();
				for(int i=0;i<allSLL.size();i++) {
					s.push(allSLL.get(i));
				}
				
					
					int size=allSLL.size();
				for(int i=0;i<size;i++) {
					allSLL.add(s.pop());
				}
				 all=new Mail[allSLL.size()];
				for(int i=0;i<all.length;i++) {
					all[i]=(Mail)allSLL.get(i);
				}
				int selectedsort =comboBox_1.getSelectedIndex();
				Sort sort=new Sort();
			switch(selectedsort)
			{
			case 1 :
				sort.setSo("date");
				break;
				
			case 2 :
				sort.setSo("from");
				break;
				
			case 3 :
				sort.setSo("subject");
				break;
				
			
			
			}
			DefaultTableModel model1 = (DefaultTableModel) table.getModel();
			model1.setRowCount(0);
			App ap1 = new App();
			Folder des = new Folder();
			des.setPath("Accounts\\"+C.currentUser.getEmail()+"\\"+C.folder);
			if(selectedsort==0 ||selectedsort==-1)
			{
				ap.setViewingOptions(des, null, null);
				
			}
			else
			{
				ap.setViewingOptions(des,null, sort);
			}
			Mail[] emi1=(Mail[]) ap1.listEmails(page);
			int i=0;
			//System.out.println("size iszzzz "+emi.length);
			while(i<emi1.length)
			{
				model1.addRow(new Object[] {emi1[i].getDate(),emi1[i].getPriority(),emi1[i].getFrom(),emi1[i].getSubject(),emi1[i].getBody(),emi1[i].getAttachments(),false});
				i++;
			}
				}
				else {
					JOptionPane.showMessageDialog(null, "No Emails Selected");
				}
			}
		});
		
		JButton btnNewButton_1 = new JButton("Move");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int flag=0;
				for(int i=0;i<table.getRowCount();i++)
				{
					if((boolean) table.getValueAt(i, 6))
					{
						flag=1;
					}
				}if(flag==1)
				{
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				SLL mails = new SLL();
				App ap =new App();
				Folder fold=new Folder();
				Folder trash=new Folder();
				Folder destination=new Folder();
				IndexFile index= new IndexFile();
				fold.setPath("Accounts\\"+C.currentUser.getEmail()+"\\"+C.folder+"\\Index.txt");
				trash.setPath("Accounts\\"+C.currentUser.getEmail()+"\\Others\\"+moved.getText());
				destination.setPath("Accounts\\"+C.currentUser.getEmail()+"\\Others\\"+moved.getText());
				Mail[] emi=(Mail[]) ap.listEmails(page);
				if(moved.getText().equals("Drafts")||moved.getText().equals("Contacts")||moved.getText().equals("Inbox")||moved.getText().equals("Trash")||moved.getText().equals("Sent")||moved.getText().equals("Others"))
				{
					JOptionPane.showMessageDialog(null, "Can not move to this folder");
				}
				else if(moved.getText().isBlank())
				{
				JOptionPane.showMessageDialog(null, "Please Enter Name of folder");
				}
				else
				{
				for(int i=0;i<table.getRowCount();i++)
				{
					if((boolean) table.getValueAt(i, 6))
					{
						//emi a5rha 10
						//formula lw aktr mn 10 ---> (10*((list.size/10)-page+1)+(list.size%10)-i-1)
						mails.add(all[(10*((ap.list.size()/10)-page+1)+(ap.list.size()%10)-i-1)].getPath());
						index.deleteLine2(fold, trash, (10*((ap.list.size()/10)-page+1)+(ap.list.size()%10)-i-1));
					}
				}
				C.moveEmails(mails, destination);
				Folder deso=new Folder();
				deso.setPath("Accounts\\"+C.currentUser.getEmail()+"\\"+C.folder+"\\Index.txt");
				 allSLL=ind.getEmails(ind.readAll(deso));
				allSLL.size();
				Stacks s=new Stacks();
				for(int i=0;i<allSLL.size();i++) {
					s.push(allSLL.get(i));
				}
				
					
					int size=allSLL.size();
				for(int i=0;i<size;i++) {
					allSLL.add(s.pop());
				}
				 all=new Mail[allSLL.size()];
				for(int i=0;i<all.length;i++) {
					all[i]=(Mail)allSLL.get(i);
				}
				int selectedsort =comboBox_1.getSelectedIndex();
				Sort sort=new Sort();
			switch(selectedsort)
			{
			case 1 :
				sort.setSo("date");
				break;
				
			case 2 :
				sort.setSo("from");
				break;
				
			case 3 :
				sort.setSo("subject");
				break;
			
			}
			DefaultTableModel model1 = (DefaultTableModel) table.getModel();
			model1.setRowCount(0);
			App ap1 = new App();
			Folder des = new Folder();
			des.setPath("Accounts\\"+C.currentUser.getEmail()+"\\"+C.folder);
			if(selectedsort==0 ||selectedsort==-1)
			{
				ap.setViewingOptions(des, null, null);
				
			}
			else
			{
				ap.setViewingOptions(des,null, sort);
			}
			Mail[] emi1=(Mail[]) ap1.listEmails(page);
			int i=0;
			//System.out.println("size iszzzz "+emi.length);
			while(i<emi1.length)
			{
				model1.addRow(new Object[] {emi1[i].getDate(),emi1[i].getPriority(),emi1[i].getFrom(),emi1[i].getSubject(),emi1[i].getBody(),emi1[i].getAttachments(),false});
				i++;
			}
				}
				}
				else
				{
					JOptionPane.showMessageDialog(null, "No Emails Selected");
				}
			}
			
		});
		
		moved = new JTextField();
		moved.setBounds(413, 402, 86, 20);
		contentPane.add(moved);
		moved.setColumns(10);
		btnNewButton_1.setBounds(314, 401, 89, 23);
		contentPane.add(btnNewButton_1);
		delete.setBounds(215, 401, 89, 23);
		contentPane.add(delete);
		
	
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 44, 719, 346);
		contentPane.add(scrollPane);
		
		table = new JTable() {

            private static final long serialVersionUID = 1L;
            @Override
            public Class getColumnClass(int column) {
                switch (column) {
                     
                    case 6:
                        return Boolean.class;
                    
                    default:
                        return String.class;
                }
            }
        };
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Date", "Priority", "From", "Subject", "Body", "Attachment","Select"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false, false,true
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(0).setPreferredWidth(70);
		table.getColumnModel().getColumn(1).setPreferredWidth(30);
		table.getColumnModel().getColumn(2).setPreferredWidth(130);
		table.getColumnModel().getColumn(3).setPreferredWidth(85);
		table.getColumnModel().getColumn(4).setPreferredWidth(150);
		table.getColumnModel().getColumn(5).setPreferredWidth(125);
		scrollPane.setViewportView(table);
		class MyComboBoxRenderer1 extends JLabel implements ListCellRenderer
	    {
			private String _title;

	        public MyComboBoxRenderer1(String title)
	        {
	            _title = title;
	        }

	        @Override
	        public Component getListCellRendererComponent(JList list, Object value,
	                int index, boolean isSelected, boolean hasFocus)
	        {
	            if (index == -1 && value == null) setText(_title);
	            else setText(value.toString());
	            return this;
	        }
	    }
		JButton btnNewButton = new JButton("Refresh");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Search filter = new Search();
				//filter.setSr(Filter.getText());
				int selectedsort =comboBox_1.getSelectedIndex();
					Sort sort=new Sort();
				switch(selectedsort)
				{
				case 1 :
					sort.setSo("date");
					break;
					
				case 2 :
					sort.setSo("from");
					break;
					
				case 3 :
					sort.setSo("subject");
					break;
			
				}
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				model.setRowCount(0);
				App ap = new App();
				Folder des = new Folder();
				des.setPath("Accounts\\"+C.currentUser.getEmail()+"\\"+C.folder);
				if(selectedsort==0 ||selectedsort==-1)
				{
					ap.setViewingOptions(des, null, null);
					
				}
				else
				{
					ap.setViewingOptions(des,null, sort);
				}
				Mail[] emi=(Mail[]) ap.listEmails(page);
				int i=0;
				//System.out.println("size iszzzz "+emi.length);
				while(i<emi.length)
				{
					model.addRow(new Object[] {emi[i].getDate(),emi[i].getPriority(),emi[i].getFrom(),emi[i].getSubject(),emi[i].getBody(),emi[i].getAttachments(),false});
					i++;
				}
				
			}
		});
		btnNewButton.setBounds(554, 11, 89, 23);
		contentPane.add(btnNewButton);
		
		JButton Previous = new JButton("Previous");
		Previous.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(page==1)
				{
					JOptionPane.showMessageDialog(null,"This is The First Page");
				}
				else
					{
					
				int selectedsort =comboBox_1.getSelectedIndex();
					Sort sort=new Sort();
				switch(selectedsort)
				{
				case 1 :
					sort.setSo("date");
					break;
					
				case 2 :
					sort.setSo("from");
					break;
					
				case 3 :
					sort.setSo("subject");
					break;
		
				}
				page--;
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				model.setRowCount(0);
				App ap = new App();
				Folder des = new Folder();
				des.setPath("Accounts\\"+C.currentUser.getEmail()+"\\"+C.folder);
				if(selectedsort==0 ||selectedsort==-1)
				{
					ap.setViewingOptions(des, null, null);
				}
				else
					{
					ap.setViewingOptions(des,null, sort);
					}
				Mail[] emi=(Mail[]) ap.listEmails(page);
				int i=0;
				//System.out.println("size iszzzz "+emi.length);
				while(i<emi.length)
				{
					model.addRow(new Object[] {emi[i].getDate(),emi[i].getPriority(),emi[i].getFrom(),emi[i].getSubject(),emi[i].getBody(),emi[i].getAttachments(),false});
					i++;
				}
					}
			}
		});
		Previous.setBounds(541, 401, 89, 23);
		contentPane.add(Previous);
		
		JButton Next = new JButton("Next");
		Next.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int selectedsort =comboBox_1.getSelectedIndex();
					Sort sort=new Sort();
				switch(selectedsort)
				{					
				case 1 :
					sort.setSo("date");
					break;
					
				case 2 :
					sort.setSo("from");
					break;
					
				case 3 :
					sort.setSo("subject");
					break;
		
				
				}
				App ap = new App();
				page++;
				int pagenum=(ap.originallist.size()/10)+1;
				if(page>pagenum)
				{
					JOptionPane.showMessageDialog(null, "No More Pages");
					page--;
				}
				else{
					DefaultTableModel model = (DefaultTableModel) table.getModel();
				
				model.setRowCount(0);
				Folder des = new Folder();
				des.setPath("Accounts\\"+C.currentUser.getEmail()+"\\"+C.folder);
				if(selectedsort==0 || selectedsort==-1)
				{
					ap.setViewingOptions(des, null, null);
				}
				else
					{
					ap.setViewingOptions(des, null, sort);
					}
				Mail[] emi=(Mail[]) ap.listEmails(page);
				int i=0;
				//System.out.println("size iszzzz "+emi.length);
				while(i<emi.length)
				{
					model.addRow(new Object[] {emi[i].getDate(),emi[i].getPriority(),emi[i].getFrom(),emi[i].getSubject(),emi[i].getBody(),emi[i].getAttachments(),false});
					i++;
				}
				}
			}
		});
		Next.setBounds(640, 401, 89, 23);
		contentPane.add(Next);
		
		JButton Return = new JButton("Return");
		Return.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dispose();
				UserGui test=new UserGui(C);
				test.setVisible(true);
			}
		});
		Return.setBounds(10, 401, 89, 23);
		contentPane.add(Return);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(0, 0, 745, 435);
		contentPane.add(lblNewLabel);
		Image img =new ImageIcon(this.getClass().getResource("/maildesign.jpg")).getImage();
		lblNewLabel.setIcon(new ImageIcon(img));
		
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		App ap = new App();
		Folder des = new Folder();
		des.setPath("Accounts\\"+C.currentUser.getEmail()+"\\"+C.folder);
		ap.setViewingOptions(des, null, null);
		Mail[] emi=(Mail[]) ap.listEmails(page);
		int i=0;
		//System.out.println("size is "+emi.length);
		while(i<emi.length)
		{
			model.addRow(new Object[] {emi[i].getDate(),emi[i].getPriority(),emi[i].getFrom(),emi[i].getSubject(),emi[i].getBody(),emi[i].getAttachments(),false});
			i++;
		}
		
		
	}

}
