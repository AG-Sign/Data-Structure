package eg.edu.alexu.csd.datastructure.mailServer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.swing.JOptionPane;

public class Contact implements IContact {
	public boolean add(String a,App C)
	{
		boolean b=false;
		File check = new File("Accounts\\"+a);
		if(a.contains(" ")||(a.isEmpty()))
		{
			JOptionPane.showMessageDialog(null, "Email Doesnot Exist");
			
		}
		
		
		else if(a.equals(C.currentUser.getEmail()))
		{
			JOptionPane.showMessageDialog(null, "Email Doesnot Exist");
			
		}
		else if(check.exists())
		{
			
					boolean exist = false;
						BufferedReader read;
						try {
							read = new BufferedReader(new FileReader("Accounts\\"+C.currentUser.getEmail()+"\\Contacts\\Index.txt"));
							String line = read.readLine();
							while(line != null)
							{
								if(line.equals(a))
								{
									JOptionPane.showMessageDialog(null, "Contact Already Exists");
									exist=true;
								}
								line = read.readLine();
							}
							read.close();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					
					
					if(exist==false)
					{
					
				File Fwrite = new File("Accounts\\"+C.currentUser.getEmail() +"\\Contacts\\Index.txt");
				try {
					FileWriter write = new FileWriter(Fwrite,true);
					write.write(a);
					write.append("\n");
					write.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				JOptionPane.showMessageDialog(null, "Successfully Added Email");
				b=true;
				}
		}
		else
		{
			JOptionPane.showMessageDialog(null, "Email Doesnot Exist");
		}
		
		return b;
	}
	
	public void delete(int selectedrow,App C)
	{
		
		BufferedReader put;
		try {
			put = new BufferedReader(new FileReader("Accounts\\"+C.currentUser.getEmail()+"\\Contacts\\Index.txt"));
			File tempFile = new File("Accounts\\"+C.currentUser.getEmail()+"\\Contacts\\MyTempFile.txt");
			
			BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
			String line =put.readLine();
			int i =0;
			while(line!=null)
			{		
				if(i==selectedrow)
				{
					i++;
					line =put.readLine();
				}
				else
					{
					writer.write(line);
					writer.write("\n");
					i++;
					line =put.readLine();
					}
				
			}
			put.close();
			writer.close();
			Files.deleteIfExists(Paths.get("Accounts\\"+C.currentUser.getEmail()+"\\Contacts\\Index.txt"));
			File temp =new File("Accounts\\"+C.currentUser.getEmail()+"\\Contacts\\Index.txt");
			tempFile.renameTo(temp);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
	}
	
	}

