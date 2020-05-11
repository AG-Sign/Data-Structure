package eg.edu.alexu.csd.datastructure.mailServer;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.swing.JOptionPane;

public class Folder implements IFolder{

	private String path;
	public void setPath(String p)
	{
		path=p;
	}
	public String getPath()
	{
		return path;
	}
	public boolean makeFolder(String old,App C)
	{
		boolean test = false;
		File folder =new File("Accounts\\"+C.currentUser.getEmail()+"\\Others\\"+old);
		if((old.equals("Inbox")) || (old.equals("Drafts")) || (old.equals("Contacts")) ||(old.equals("Sent")) || (old.equals("Trash"))|| (old.equals("trash"))|| (old.equals("sent"))|| (old.equals("inbox"))|| (old.equals("contacts"))|| (old.equals("drafts")))
		{
			JOptionPane.showMessageDialog(null, "Cannot Create Folder with this Name");
		}
		else if(old.isEmpty())
		{
			JOptionPane.showMessageDialog(null, "Please Enter Folder Name ");
		}
		
		else if(!folder.exists())
		{
			if(folder.mkdir())
			{
				JOptionPane.showMessageDialog(null, "Created Folder Successfully");
				File indexFile=new File(folder.getPath()+"\\Index.txt");
				try {
				indexFile.createNewFile();
				}
				catch(IOException e) {
					e.printStackTrace();
				}
				test=true;
			}
			else {
				JOptionPane.showMessageDialog(null, "Failed To Create Folder");
			}
		}
		else {
			JOptionPane.showMessageDialog(null, "Folder already exists");
		}
		return test;
	}
	public boolean Rename(int selectedrow,String rename,App C)
	{
		boolean current = false;
		File test = new File("Accounts\\"+C.currentUser.getEmail()+"\\Others");
		File[] files = test.listFiles();
		String old = files[selectedrow].getName();
	
			File oldfile = new File("Accounts\\"+C.currentUser.getEmail()+"\\Others\\"+old);
		File newfile = new File("Accounts\\"+C.currentUser.getEmail()+"\\Others\\"+rename);
		if(!oldfile.exists())
		{
			JOptionPane.showMessageDialog(null, "Folder Doesnot Exist");
		}
		else if(old.isEmpty())
		{
			JOptionPane.showMessageDialog(null, "Please Enter Folder New Name ");
		}
		else if(newfile.exists())
		{
			JOptionPane.showMessageDialog(null, "There is another folder with the same name");
		}
		else
			{
			oldfile.renameTo(newfile);
			JOptionPane.showMessageDialog(null, "Folder Renamed Successfully");
			current=true;
			}
		
		return current;
	}
	static public boolean deleteDirectory(File path) {
		
	    if( path.exists() ) {
	      File[] files = path.listFiles();
	      for(int i=0; i<files.length; i++) {
	         if(files[i].isDirectory()) {
	           deleteDirectory(files[i]);
	         }
	         else {
	           files[i].delete();
	         }
	      }
	    }
	    return( path.delete() );
	  }
	public boolean DeleteFolder(int selectedrow,App C )
	{
		boolean current = false;
		File test = new File("Accounts\\"+C.currentUser.getEmail()+"\\Others");
		File[] files = test.listFiles();
		String old = files[selectedrow].getName();
			 test=new File("Accounts\\"+C.currentUser.getEmail()+"\\Others\\"+old);
				
				if(test.exists())
				{
						if(deleteDirectory(test))
					{
						JOptionPane.showMessageDialog(null, "Folder Deleted Successfully");
						current=true;
					}	
				}
				else {
					JOptionPane.showMessageDialog(null, "Folder Doesnot exist");
				}
		return current;
}
	}
