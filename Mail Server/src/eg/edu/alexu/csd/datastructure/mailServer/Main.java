package eg.edu.alexu.csd.datastructure.mailServer;

import java.io.*;
import java.nio.file.*;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.LocalDateTime;    

public class Main {
	
	public static void main(String[] args)
	{
		App app = new App();
		UserAccount user =new UserAccount();
		Scanner scan =new Scanner(System.in);
		String mail = scan.nextLine();
		if(app.checkEmail(mail))
		{
			
		}
		
	}
	
}























/*public static void makeDir()
	{
		File sys =new File("C:\\System");
		File trash =new File("C:\\System\\Trash");
		File inbox =new File("C:\\System\\Inbox");
		File draft =new File("C:\\System\\Draft");
		File sent =new File("C:\\System\\Sent");
		if(!sys.exists())
		{
			if(sys.mkdir())
			{
				System.out.println("System Directory created");
			}
			else {
				System.out.println("Failed to create Directory");
			}
		}
		else {
			System.out.println("Failed to create Directory");
		}
		if(!inbox.exists())
		{
			if(inbox.mkdir())
			{
				System.out.println("Inbox Directory created");
			}
			else {
				System.out.println("Failed to create Directory");
			}
		}
		else {
			System.out.println("Failed to create Directory");
		}
		if(!trash.exists())
		{
			if(trash.mkdir())
			{
				System.out.println("Trash Directory created");
			}
			else {
				System.out.println("Failed to create Directory");
			}
		}
		else
		{
			System.out.println("Failed to create Directory");
		}
		if(!draft.exists())
		{
			if(draft.mkdir())
			{
				System.out.println("Draft Directory created");
			}
			else {
				System.out.println("Failed to create Directory");
			}
		}
		else
		{
			System.out.println("Failed to create Directory");
		}
		if(!sent.exists())
		{
			if(sent.mkdir())
			{
				System.out.println("Sent Directory created");
			}
			else {
				System.out.println("Failed to create Directory");
			}
		}
		else
		{
			System.out.println("Failed to create Directory");
		}
	}
	public static File makeFile()
	{
		Scanner scan=new Scanner(System.in);
		Date now = new Date();
		SimpleDateFormat simpleDateformat = new SimpleDateFormat("E"); // the day of the week abbreviated
	    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
	    LocalDateTime now1 = LocalDateTime.now();  
	    System.out.println("Please Enter name of mail");
	    String name =scan.nextLine();
		System.out.println("Please Enter Sender mail");
		String sender =scan.nextLine();
		System.out.println("Please Enter Reciever mail");
		String reciever =scan.nextLine();
		System.out.println("Please Enter Body");
		String body=scan.nextLine();
	    String dat =simpleDateformat.format(now);
	    dat +=" ";
	    dat += dtf.format(now1);
		Mail email1=new Mail();
		email1.sender=sender;
		email1.reciever=reciever;
		email1.Body=body;
		email1.now=dat;
		 File email = new File("C:\\System\\Inbox\\"+name+".txt");
			try {
			     
			      if (email.createNewFile()) {
			        System.out.println("File created");
			      } else {
			        System.out.println("File already exists.");
			      }
			    } catch (IOException e) {
			      System.out.println("An error occurred.");
			      e.printStackTrace();
			    }
			try {
			      FileWriter myWriter = new FileWriter("C:\\System\\Inbox\\"+name+".txt");
			      myWriter.write(email1.sender+"\n");
			      myWriter.write(email1.now+"\n");
			      myWriter.write(email1.reciever+"\n");
			      myWriter.write(email1.Body+"\n");
			      myWriter.write("C:\\System\\Inbox\\"+name+".txt");
			      myWriter.close();
			      System.out.println("Successfully wrote to the file.");
			    } catch (IOException e) {
			      System.out.println("An error occurred.");
			      e.printStackTrace();
			    }
			return email;
	}
public static void main(String[] args)
{
	  //makeDir();
	Folder des = new Folder();
	   Folder obj1 = (Folder) des;
	  //File email = makeFile();
	  //File email2 = makeFile();
	 // File email3=makeFile();
	   //SLL mails =new SLL();
	   //mails.add(email);
	   //mails.add(email2);
	   //mails.add(email3);
	   App test =new App();  
	//test.deleteEmails(mails);
	//File email4=makeFile();
	//mails.add(email4);
	   obj1.DeleteFolder("Inbox");
	Scanner scan = new Scanner(System.in);
	System.out.println("Folder name");
	String fold = scan.nextLine();
	obj1.makeFolder(fold);
	System.out.println("Choose a Destination File : 1-Draft 2-Sent");
	int name =scan.nextInt();
	switch (name)
	{
	case 1 : 
		obj1.setPath("C:\\System\\Draft");
		break;
	
	case 2:
		obj1.setPath("C:\\System\\Sent");
		break;
	}
	
	scan.close();
	
	//test.moveEmails(mails, obj1);
	
	
	
	
	/*     30 Days Deletion 
	File file = new File("C:\\System\\Trash\\testdelete.txt");
    long past =file.lastModified();
	System.out.println(past);
	long c = new Date().getTime();
	System.out.println(c);
	
	long diff = c - past;
System.out.println(diff);
	if (diff > ((long)30 * 24 * 60 * 60 * 1000)) {
	    System.out.print("yes");
	    file.delete();
	}
	else {
		  System.out.print("no");
	}*/