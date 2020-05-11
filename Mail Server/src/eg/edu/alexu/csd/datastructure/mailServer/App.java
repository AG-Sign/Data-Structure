package eg.edu.alexu.csd.datastructure.mailServer;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Scanner;

import javax.swing.JOptionPane; 

public class App implements IApp{
	Scanner reader=new Scanner(System.in);
	UserAccount currentUser= new UserAccount();
	static SLL originallist=new SLL();
	static SLL list=new SLL();
	static SLL listFilter = new SLL();
	String folder ;
		/**
		 * Used to check if the character is a number or an alphabet.
		 * @param  symbol .
		 * @return a boolean which determines whether the input character is an alphabetical character or numeral value .
		 * 
		 */
	public static void main(String[] args) throws IOException {
        App mm=new App();
        Folder f=new Folder();
    /* File myObj = new File("C:\\Users\\hp\\eclipse-workspace\\Mail Server\\Accounts\\f@mail.com\\Inbox\\new.txt");
        try{
            if(myObj.createNewFile()){
                System.out.println("File created : "+myObj.getName());
            }else{
                System.out.println("File already exists");
            }
        }catch (IOException e){
            System.out.println("an error occurred ");
        }
       */
        
        f.setPath("Accounts\\m@mail.com\\Inbox");
        Search ss=new Search();
		ss.setSr("math");
		Sort m=new Sort();
		m.setSo("date");
		mm.setViewingOptions(f, null,null);
       
    }
		private boolean isAlnum(char symbol) {
			
			if(Character.isDigit(symbol)||Character.isLetter(symbol)){
				return true;
			}
			return false;
		}
		/**
		 *Check if the given character is alphabetical or not
		 * @param  symbol
		 * @return a boolean to determine whether the input character is an alphabetical character
		 */
		
		private boolean isAlpha(char symbol) {
			
			if((symbol>=65&&symbol<=90)||(symbol>=97&&symbol<=122)) {
				return true;
			}
			return false;
		}
		/**Check if the given character is a digit or not
		 * @param symbol
		 * @return a boolean to determine whether the input character is a a digit or not 
		 */
		
		private boolean isNum(char symbol) {
			if((symbol>=48&symbol<=57)) {
				return true;
			}
			return false;
		}
		/**
		 * Checks if the given email is formed correctly or not 
		 * @param Email
		 * Check
		 * @return
		 */
	public boolean checkEmail(String Email) {
		if(Email.length()==0) {
			JOptionPane.showMessageDialog(null,"The Email field was left blank");
			return false;
		}
		else if(!Email.endsWith("@mail.com")) {
			JOptionPane.showMessageDialog(null, "The email must contain @mail.com");
			return false;
		}
		String EmailWOM=Email.substring(0,Email.lastIndexOf('@'));//The EmailWOM is without @mail.com
		if(EmailWOM.length()==0) {
			JOptionPane.showMessageDialog(null, "The email provided was empty and only contains @mail.com");
			return false;
		}
		if(EmailWOM.contains("@mail.com")) {
			JOptionPane.showMessageDialog(null, "You cannot add @mail.com more than once");
			return false;
		}
		if(!(isAlnum(EmailWOM.charAt(0)))) {
			JOptionPane.showMessageDialog(null, "The email must start with alphabets and numbers only");
			return false;
		}
		boolean containAlpha=false;
		for (int i=0;i<EmailWOM.length();i++) {
			if(isAlnum(EmailWOM.charAt(i))) {
				if(isAlpha(EmailWOM.charAt(i))) {
					containAlpha=true;
				}
			}
			if(!(isAlnum(EmailWOM.charAt(i)))) {
				JOptionPane.showMessageDialog(null, "The email must contain only alphabets and numbers and no spaces are allowed");
				return false;
			}
		}
		if(!containAlpha) {
			return false;
		}
		return true;
	}
	/**
	 * Takes the email address from the user for the sign up operation
	 * @param newAccount
	 * A new email address to be set in the sign up operation for the new user
	 * @return 
	 * A boolean to determine whether the email was created successfully or not 
	 */
	public boolean setUserEmail(String email) {
		boolean success=true;
		File newUser=new File("Accounts\\"+ email);
		if(newUser.exists()) {//Check if the email already exists
			JOptionPane.showMessageDialog(null, "Email already exists");
			return false;
		}
		else {
			
			newUser.mkdirs();
			String dirsarray[]=new String [] {"\\Inbox","\\Trash","\\Drafts","\\Sent","\\Contacts"};
			for(String dir:dirsarray) {//Make the directories Inbox ,trash , drafts and sent for the new email
				File newDirectory=new File(newUser.getPath() + dir);
				newDirectory.mkdirs();
				File indexFile=new File(newDirectory.getPath()+"\\Index.txt");
				File contactsFile=new File("Accounts\\"+email+"\\Contacts.txt");
				try {
				indexFile.createNewFile();
				contactsFile.createNewFile();
				}
				catch(IOException e) {
					e.printStackTrace();
				}
			}
			File other = new File("Accounts\\"+email+"\\Others");
			other.mkdir();
		}
		return success;
	}
	/**
	 * 
	 * @param newEmail
	 *  A new email address to be set in the sign up operation for the new user
	 * @return
	 * return a boolean to determine whether the email has been made or not
	 */

	public boolean signup(UserAccount newEmail) {
			boolean success=checkEmail(newEmail.getEmail())&&setUserEmail(newEmail.getEmail());
			if(newEmail.getPassword()==" ")
			{
				return false;
			}
			if(!success) {
				return success;
			}
			else {
				currentUser=newEmail;
				String emailPath="Accounts\\"+newEmail.getEmail();
				File emailInfo=new File(emailPath +"\\EmailInfo.txt");
				try {
					FileWriter write=new FileWriter(emailInfo);
					write.write(newEmail.getName());
					write.write("\n");
					write.append(newEmail.getEmail());
					write.append("\n");
					write.append(newEmail.getPassword()+"\n");
					write.close();
					
				}
				catch(IOException e) {
					e.printStackTrace();
				}
			}
		return success;
	}

		
	public void moving(String target,ILinkedList mails)
	{
		String current=target;
		File test=new File(target);
				if(test.exists())
				{
		target+="\\";
		int i=0;
		int flag=1;
		IndexFile index = new IndexFile();
		while(i<mails.size())
			{ Path temp;
			String source=(String)mails.get(i);
			Path emailpath=Paths.get(source);
			String name=emailpath.getFileName().toString();
			target +=name;
			System.out.println(target);
			try {
				temp=Files.move(Paths.get(source),Paths.get(target));
				flag=1;
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				flag=0;
			} 
			
			i++;
			target=current;
			} 
		if(flag==1)
		{
			JOptionPane.showMessageDialog(null, "Successfully Moved files");
		}
		if(flag==0)
		{
			JOptionPane.showMessageDialog(null, "Error Occurred");
		}
				}
				else
				{
					JOptionPane.showMessageDialog(null, "No such Folder");
				}
		 		  
	}

	@Override
	public boolean signin(String email, String password) {
		// TODO Auto-generated method stub
		if(email.length()==0 || password.length()==0)
		{
			JOptionPane.showMessageDialog(null,"Either the Email or Password fields is left blank");
			return false;
		}
		File existingEmail=new File("Accounts\\"+ email);
		boolean rightEmail=true;//A boolean which check if the given email and password are right or not
		if(existingEmail.exists()) {//Check if the email exists
			
			
			String pass="";//The password that is stored in the email directory
			
			try {
			FileReader emailInfo=new FileReader(existingEmail.getPath() + "\\EmailInfo.txt");
			BufferedReader read=new BufferedReader(emailInfo);
			for(int i=0;i<2;i++) {//Skip the first 2 lines which are the name and email of the user
				pass=read.readLine();
			}
			pass=read.readLine();
			read.close();
			}
			
			
			catch (IOException e) {
				e.printStackTrace();
			}
			if(!(pass.equals(password))) {//If the entered password doesn't match the stored password
				JOptionPane.showMessageDialog(null, "Wrong password");
				rightEmail=false;
			}
		
		}
		
		else {//The email doesn't exist
			JOptionPane.showMessageDialog(null, "The entered email doesn't exist");
			rightEmail=false;
		}
		if(rightEmail) {
			currentUser.setEmail(email);
		}
		return rightEmail;
		
	}
	/**
	 * 
	 * @param newEmail
	 * The email being composed
	 * @return A boolean to determine if the body or the receiver of the email have not been specified in case of composing the email and not sending as draft
	 */

	private boolean checkEmpty(Mail newEmail) {
		boolean isEmpty=false;
		if(newEmail.getFolder()=="Sent") {
			if(newEmail.getBody().length()==0||newEmail.getTo()==null) {
				isEmpty=true;
			}
		}
		else {
			isEmpty=false;
		}
		return isEmpty;
	}
	private boolean setTo(SLL to) {
		for(int i=0;i<to.size();i++) {
		File receiver=new File("Accounts\\"+(String)to.get(i));
		if(!receiver.exists()) {
			return false;
		}
	}
	return true;
}
/**
 * Sets the subject of the composed email
 * @param newEmail
 * The email which is being composed 
 */
private boolean setSubject(String subject) {
	if(subject.contains("===")) {
		return false;
	}
	return true;
}

/**
 * Sets the priority of the composed email
 * @param newEmail
 * The email which is being composed 
 */
//TODO Combobox to choose the priority of the email
private boolean setPriority(String priority,Mail newEmail) {
	if(priority.length()==0) {
		newEmail.setPriority(4);
		return true;
	}
	try {
	switch(Integer.parseInt(priority)) {
	case 1:
	case 2:
	case 3:
	case 4:
		return true;
	default:
		
		return false;//TODO Message to say that the priority must be 1,2,3 or 4
	}
	}
	catch(Exception e) {
		return false;//TODO Message to say that the priority must be 1,2,3 or 4 
	}
	}
/**
 * Sets the attachments of the Email being composed
 * @param newEmail
 * The email being composed
 */
private void setAttachments(SLL attachments) {
	
}

	@Override
	public boolean signup(IContact newIEmail) {
		// TODO Auto-generated method stub
		UserAccount newEmail =(UserAccount) newIEmail;
		return false;
	}

	@Override
	public void setViewingOptions(IFolder folder, IFilter filter, ISort sort) {
		// TODO Auto-generated method stub
		
		Folder obj3=(Folder)folder;
    	obj3.setPath(obj3.getPath()+"\\Index.txt");
        Sort obj = (Sort)sort;
        Search obj2 =(Search)filter;
        if(!originallist.isEmpty())
        {
        	originallist.clear();
        }
        if(!list.isEmpty())
        {
        	list.clear();
        }
        	try {
                FileReader ki=new FileReader(obj3.getPath());
                
                Scanner scan=new Scanner(ki);
                while (scan.hasNextLine()){
                    String data =scan.nextLine();
                    originallist.add(data);
                }
                
                scan.close();
            }catch (IOException e){
               e.printStackTrace();
            }
               
                for(int i=0;i<originallist.size();i++) {
                	list.add(originallist.get(i));
                }

             if(originallist.size()==0)
             {
            	 JOptionPane.showMessageDialog(null, "No Emails");
             }
             else
            {
		                
		                	if(filter!=null) 
		                	{
		        	String seq = obj2.getSr();
		        	String type="";
		        		if(seq.contains("/")) {
		        			type="date";
		        		}else if(seq.contains("@")) {
		        			type="from";
		        		}else {
		        			type="subject";
		        		}
		        	try {
					    obj.quick(list, type);
						
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		        	int[] indexx=obj2.binary(list, obj2.getSr());
		        	
		        	for(int j=0;j<indexx.length;j++) {
		        		listFilter.add(list.get(indexx[j]));
		        	}
		        
		        	
		        	if(sort!=null) {
		        		try {
		    			    obj.quick(listFilter,obj.getSo());
		    			    
		    			} catch (ParseException e) {
		    				// TODO Auto-generated catch block
		    				e.printStackTrace();
		    			}
		        	}
		        }else if(sort!=null) {
		        	try {
					    obj.quick(list,obj.getSo());
					    
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		        }
        	
        }
		
	}

	@Override
	public IMail[] listEmails(int page) {
		// TODO Auto-generated method stub
			Stacks st=new Stacks();
	    	for(int i=0;i<list.size();i++) {
	    		st.push(list.get(i));
	    	}
	    	for(int i=0;i<list.size();i++) {
	    		list.set(i, st.pop());
	    	}
	    	int start=page*10-10;
	    	int end=start+9;
	    	int j=0;
	    	int last=end;
	    	if(list.size()<end) {
	    		 last=list.size()-1;
	    	}
	    	Mail[] em=new Mail[(last+1)-start];
	    	int i=0;
	    	while(start<=last) {
	    		String tt=(String) list.get(start);
	    		Mail m=new Mail();  		
	    		String[] ss=tt.split("===");
	    		m.setPath(ss[0]);
	    		m.setDate(ss[1]);
	    		m.setFrom(ss[2]);
	    		SLL to=new SLL();
	    		to.add(ss[3]);
	    		m.setTo(to);
	    		m.setSubject(ss[4]);
	    		m.setPriority(Integer.parseInt(ss[5]));
	    		//HHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHH
	    		//HHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHH
	    		//HHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHH
	    		//HHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHH
	    		IndexFile hah=new IndexFile();
	            try {
	                File f=new File(m.getPath()+"\\Body.txt");
	                FileReader fr=new FileReader(f);
	                BufferedReader reader=new BufferedReader(fr);
	                String line;
	                String Body="";
	                Folder des =new Folder();
	                des.setPath(f.toString());
	                if((hah.countLines(des)==0)){
	                    Body="NOTEXT";//TODO In GUI SEE how are you going to display NOTEXT on the screen to the User
	                }
	                else {
	                    while((line=reader.readLine())!=null){
	                        Body+=line;
	                        //Body+="\n";
	                    }
	                }
	                m.setBody(Body);
	                reader.close();
	            }

	            catch(IOException e) {
	                e.printStackTrace();
	            }
	            //reading the paths of the attachments and storing them into the email object
	            MyFileVisitor visitor=new MyFileVisitor();
	            try {
	                Files.walkFileTree(Paths.get(m.getPath()),visitor);
	                m.setAttachments(visitor.getAttachments());//TODO in GUI attachments SLL is equal to null if there are no attachments in the email folder
	            } catch (IOException e) {
	                e.printStackTrace();
	                e.printStackTrace();
	            }
	            em[j]=m;
	    		start++;
	    		j++;
	    	}
	        return em;
		}
    
	
	
	
	
	
	
	
	public Mail[] listFilteredEmails(int page) {
		Stacks st=new Stacks();
    	for(int i=0;i<listFilter.size();i++) {
    		st.push(listFilter.get(i));
    	}
    	for(int i=0;i<listFilter.size();i++) {
    		listFilter.set(i, st.pop());
    	}
    	int start=page*10-10;
    	int end=start+9;
    	int j=0;
    	int last=end;
    	if(listFilter.size()<end) {
    		 last=listFilter.size()-1;
    	}
    	Mail[] em=new Mail[(last+1)-start];
    	int i=0;
    	while(start<=last) {
    		String tt=(String) listFilter.get(start);
    		Mail m=new Mail();  		
    		String[] ss=tt.split("===");
    		m.setPath(ss[0]);
    		m.setDate(ss[1]);
    		m.setFrom(ss[2]);
    		SLL to=new SLL();
    		to.add(ss[3]);
    		m.setTo(to);
    		m.setSubject(ss[4]);
    		m.setPriority(Integer.parseInt(ss[5]));
    		//HHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHH
    		//HHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHH
    		//HHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHH
    		//HHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHH
    		IndexFile hah=new IndexFile();
            try {
                File f=new File(m.getPath()+"\\Body.txt");
                FileReader fr=new FileReader(f);
                BufferedReader reader=new BufferedReader(fr);
                String line;
                String Body="";
                Folder des =new Folder();
                des.setPath(f.toString());
                if((hah.countLines(des)==0)){
                    Body="NOTEXT";//TODO In GUI SEE how are you going to display NOTEXT on the screen to the User
                }
                else {
                    while((line=reader.readLine())!=null){
                        Body+=line;
                        //Body+="\n";
                    }
                }
                m.setBody(Body);
                reader.close();
            }

            catch(IOException e) {
                e.printStackTrace();
            }
            //reading the paths of the attachments and storing them into the email object
            MyFileVisitor visitor=new MyFileVisitor();
            try {
                Files.walkFileTree(Paths.get(m.getPath()),visitor);
                m.setAttachments(visitor.getAttachments());//TODO in GUI attachments SLL is equal to null if there are no attachments in the email folder
            } catch (IOException e) {
                e.printStackTrace();
                e.printStackTrace();
            }
            em[j]=m;
    		start++;
    		j++;
    	}
        return em;
    	}

    	
    	
    	
    	
    	
    	
    	
    	
    	
	@Override
	public void deleteEmails(ILinkedList mails) {
		
		// TODO Auto-generated method stub
		/*loop over LL takes every node and put in a file then get
		   path of source and move it to path of trash and name it as it is 
		 */
		int i=0;
		int flag=1;
		IndexFile index = new IndexFile();
		while(i<mails.size())
			{ Path temp;
			String source=(String)mails.get(i);
			Path emailpath=Paths.get(source);
			String name=emailpath.getFileName().toString();
			String target="Accounts\\"+currentUser.getEmail()+"\\Trash\\";
			target +=name;
			
			try {
				
				temp=Files.move(Paths.get(source),Paths.get(target));
				flag=1;
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				
				flag=0;
				e.printStackTrace();
			} 
			
			i++;
			}  
		 		  
		   if(flag==1)
		   {
			   JOptionPane.showMessageDialog(null, "Successfully Deleted files");
		   }
		   if(flag==0)
		   {
			   JOptionPane.showMessageDialog(null, "Error Occurred");
		   }
		
		
	}

	@Override
	public void moveEmails(ILinkedList mails, IFolder des)  {
		// TODO Auto-generated method stub
		Folder obj1 = (Folder) des;
		String target=obj1.getPath();
		
		moving(target,mails);
		
	}

	@Override
	public boolean compose(IMail newIEmail) {
		boolean isRight=true;
		Mail newEmail = (Mail) newIEmail;
		if(checkEmpty(newEmail)) {
			JOptionPane.showMessageDialog(null,"The Body or Receivers is left blank");
			return false;
		}
		else if (newEmail.getFolder().equals("")&&!setTo(newEmail.getTo())) {
			JOptionPane.showMessageDialog(null,"One of the receivers of the email doesn't exist");
			return false;
		}
		else if(!setSubject(newEmail.getSubject())) {
			JOptionPane.showMessageDialog(null,"You cannot enter === inside the subject");
			return false;
		}
		else {
			for(int i=0;i<newEmail.getTo().size();i++) {
				if(!checkEmail((String)newEmail.getTo().get(i)))
				return false;
			}
			if(newEmail.getFolder().equals("Sent")){
				//Write the email information to the index file  of the sender
				IndexFile i=new IndexFile();
				Folder senderDes =new Folder();
				String senderPath="Accounts\\"+newEmail.getFrom()+"\\"+"Sent"+"\\Index.txt";
				senderDes.setPath(senderPath);
				newEmail.setEmailFolder("Sent");
				newEmail.setPath();
				i.write(newEmail, senderDes);
				
				//Make the email folder for the sender
				String senderEmailPath="Accounts\\"+newEmail.getFrom()+"\\"+"Sent"+"\\"+newEmail.getFrom()+newEmail.getDate();
				File senderEmail=new File(senderEmailPath);
				senderEmail.mkdirs();
				
				//make the email folder for the receivers
				for(int j=0;j<newEmail.getTo().size();j++) {
					String receiverEmailPath ="Accounts\\"+(String)newEmail.getTo().get(j)+"\\"+"Inbox"+"\\"+newEmail.getFrom()+newEmail.getDate();
					File receiverEmail=new File(receiverEmailPath);
					receiverEmail.mkdirs();
				}
				//Write the body of the email on the Body.txt file
				//1-Make the body.txt file
				String dirPath="Accounts\\"+newEmail.getFrom()+"\\"+"Sent"+"\\"+newEmail.getFrom()+newEmail.getDate();
				String bodyPath=dirPath+"\\"+"Body.txt";
				File body=new File(bodyPath);
				try {
					body.createNewFile();
				} catch (IOException e) {
					e.printStackTrace();
				}
				//2-Write the body on the file 
					try {
						FileWriter writer=new FileWriter(bodyPath,true);
						writer.write(newEmail.getBody());
						writer.close();
					}
					catch(IOException e) {
						e.printStackTrace();
					}
				
				//3-Copy the body text file to the receivers directories 
				for(int j=0;j<newEmail.getTo().size();j++) {
					String receiver="Accounts\\"+(String)newEmail.getTo().get(j)+"\\"+"Inbox\\"+newEmail.getFrom()+newEmail.getDate();
					
					File emailReceiverDirectory= new File(receiver);
					emailReceiverDirectory.mkdirs();
					Path receiverPath=Paths.get(receiver+"\\Body.txt");
					try {
						Files.copy(Paths.get(bodyPath), receiverPath);
					} catch (IOException e) {
						
						e.printStackTrace();
					}
				}
				
				//Copy the attachments to the email folder and to the receiver email folder 
				
				if(newEmail.getAttachments()!=null) {
				Folder sender=new Folder();
				sender.setPath(senderEmailPath);
				i.copyAttachments(newEmail.getAttachments(), sender);//Copy to the sender
				
				
				for(int l=0;l<newEmail.getTo().size();l++) {//Copy to the receivers
					Folder receiver=new Folder();
					receiver.setPath("Accounts\\"+(String)newEmail.getTo().get(l)+"\\"+"Inbox"+"\\"+newEmail.getFrom()+newEmail.getDate());
					i.copyAttachments(newEmail.getAttachments(), receiver);
					
				}
				
				}
				//Write on the index file of the receiver
				
				for(int j=0;j<newEmail.getTo().size();j++) {
					String receiverPath="Accounts\\"+(String)newEmail.getTo().get(j)+"\\"+"Inbox"+"\\Index.txt";
					newEmail.setPath("Accounts\\"+(String)newEmail.getTo().get(j)+"\\"+"Inbox\\"+newEmail.getFrom()+newEmail.getDate());
					Folder des=new Folder();
					des.setPath(receiverPath);
					i.write(newEmail, des);
				}
				
			}
			else {// if it is saved as a draft
				IndexFile i=new IndexFile();
				Folder senderDes=new Folder();
				String senderPath="Accounts\\"+newEmail.getFrom()+"\\"+"Drafts"+"\\Index.txt";
				senderDes.setPath(senderPath);
				newEmail.setEmailFolder("Drafts");
				newEmail.setPath();
				i.write(newEmail, senderDes);
				
				
				
				//Make the folder for the email in the drafts folder
				String senderEmailPath="Accounts\\"+newEmail.getFrom()+"\\"+"Drafts"+"\\"+newEmail.getFrom()+newEmail.getDate();
				File draftEmail=new File(senderEmailPath);
				draftEmail.mkdirs();
				
				//Write the body of the email on the Body.txt file
				//1-Make the body.txt file
				String bodyPath="Accounts\\"+newEmail.getFrom()+"\\"+"Drafts"+"\\"+newEmail.getFrom()+newEmail.getDate()+"\\"+"Body.txt";
				File body=new File(bodyPath);
				try {
					body.createNewFile();
				} catch (IOException e) {
					e.printStackTrace();
				}
				//2-Write the body on the file if exists
					if(newEmail.getBody().length()!=0) {
					try {
						FileWriter writer=new FileWriter(bodyPath,true);
						writer.write(newEmail.getBody());
						writer.close();
					}
					catch(IOException e) {
						e.printStackTrace();
					}
					}
					
				//Copy the attachments to the email folder
				if(newEmail.getAttachments()!=null) {
				Folder sender=new Folder();
				sender.setPath(senderEmailPath);
				i.copyAttachments(newEmail.getAttachments(), sender);//Copy to the sender
				}
				
			}
		}
	return isRight;	
	}
	}