package eg.edu.alexu.csd.datastructure.mailServer;


import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.swing.JOptionPane;

public class Friends implements IContact{
private String name;
private String [] emails;
public void setEmails(String [] emails) {
	this.emails=emails;
}
public String []getEmails() {
	return this.emails;
}
public void setName(String name) {
	this.name=name;
}
public String getName() {
	return this.name;
}
public boolean addContact (IContact newContact,App ap) {
	File path=new File("Accounts\\"+ap.currentUser.getEmail()+"\\Contacts.txt");
	FileWriter fw;
	boolean success=checkExistList(((Friends)newContact).getEmails());
	if(!success) {
		return success;
	}
	try {
		fw = new FileWriter(path,true);
		BufferedWriter writer=new BufferedWriter(fw);
		writer.write(((Friends)newContact).getName());
		writer.write("===");
		for(int i=0;i<((Friends)newContact).getEmails().length;i++) {
			writer.write((String)(((Friends)newContact).getEmails()[i]));
			if(i!=((Friends)newContact).getEmails().length-1) {
				writer.write("===");
			}
		}
		writer.newLine();
		writer.close();
		fw.close();
	}catch (IOException e) {
		e.printStackTrace();
	}
	return true;
}
public void rename(String newName,int oldLine,App ap) {
try {
File path=new File("Accounts\\"+ap.currentUser.getEmail()+"\\Contacts.txt");
FileReader fr=new FileReader(path);
BufferedReader reader=new BufferedReader(fr);
File temp=new File("temp.txt");
FileWriter fw=new FileWriter(temp,true);
BufferedWriter bw=new BufferedWriter(fw);
PrintWriter writer=new PrintWriter(bw);
String currentLine;
int currentLineNum=0;
while((currentLine=reader.readLine())!=null) {
	if(currentLineNum!=oldLine) {
		writer.println(currentLine);
	}
	else {
		String []data=currentLine.split("===");
		data[0]=newName;
		for(int i=0;i<data.length;i++) {
			writer.print(data[i]);
			if(i!=data.length-1) {
				writer.print("===");
			}
			else {
				writer.println();
			}
		}
	}
	currentLineNum++;
}
writer.flush();
writer.close();
bw.close();
fw.close();
reader.close();
fr.close();
path.delete();
File dump=new File("Accounts\\"+ap.currentUser.getEmail()+"\\Contacts.txt");
temp.renameTo(dump);

}catch(IOException e) {
	e.printStackTrace();
}
}
public boolean addNewEmail(String newEmail, int FriendNameLine,App ap) {//Get the line from the choosen friend Name
	if(!checkExist(newEmail)) {
		return false;
	}
	try {
	File path=new File("Accounts\\"+ap.currentUser.getEmail()+"\\Contacts.txt");
	FileReader fr=new FileReader(path);
	BufferedReader reader=new BufferedReader(fr);
	File temp=new File("temp.txt");
	FileWriter fw=new FileWriter(temp,true);
	BufferedWriter bw=new BufferedWriter(fw);
	PrintWriter writer=new PrintWriter(bw);
	int currentLineNum=0;
	String currentLine;
	while((currentLine=reader.readLine())!=null) {
		if(currentLineNum!=FriendNameLine) {
			writer.println(currentLine);
		}
		else {
			currentLine+="==="+newEmail;
			writer.println(currentLine);
		}
		currentLineNum++;
	}
	writer.close();
	bw.close();
	fw.close();
	reader.close();
	fr.close();
	Files.deleteIfExists(Paths.get(path.getAbsolutePath()));
	Path dump=Paths.get("Accounts\\"+ap.currentUser.getEmail()+"\\Contacts.txt");
	Files.move(Paths.get(temp.getAbsolutePath()), dump);
	}catch(IOException e) {
		e.printStackTrace();
	}
	return true;
}
public void deleteOldEmail(App ap,int FriendNameLine,int deletedEmailLine) {
	try {
		File path=new File("Accounts\\"+ap.currentUser.getEmail()+"\\Contacts.txt");
		FileReader fr=new FileReader(path);
		BufferedReader reader=new BufferedReader(fr);
		File temp=new File("temp.txt");
		FileWriter fw=new FileWriter(temp,true);
		BufferedWriter bw=new BufferedWriter(fw);
		PrintWriter writer=new PrintWriter(bw);
		int currentLineNum=0;
		String currentLine;
		while((currentLine=reader.readLine())!=null) {
			if(currentLineNum!=FriendNameLine) {
				writer.println(currentLine);
			}
			else {
				String []data =currentLine.split("===");
				if(data.length==2) {
					JOptionPane.showMessageDialog(null, "The contact must have at least one email");
					reader.close();
					fr.close();
					writer.close();
					bw.close();
					fw.close();
					return;
				}
				currentLine=data[0];
				currentLine+="===";
				for(int i=1;i<data.length;i++) {
					if(i!=deletedEmailLine+1) {
						currentLine+=data[i];
						if(i!=data.length-1) {
							currentLine+="===";
						}
					}
					
				}
				if(currentLine.endsWith("===")) {
					currentLine=(String) currentLine.subSequence(0, currentLine.lastIndexOf("==="));
				}
				writer.println(currentLine);
			}
			currentLineNum++;
		}
		writer.close();
		bw.close();
		fw.close();
		reader.close();
		fr.close();
		path.delete();
		File dump=new File("Accounts\\"+ap.currentUser.getEmail()+"\\Contacts.txt");
		temp.renameTo(dump);
	}catch(IOException e) {
		e.printStackTrace();
	}
}
public boolean checkExist(String email) {
	File path= new File("Accounts\\"+email);
	if(!path.exists()) {
		return false;
	}
	else {
		return true;
	}
}
public boolean checkExistList(String [] emails) {
	for(int i=0;i<emails.length;i++) {
		if(!checkExist((String)emails[i])) {
			return false;
		}
	}
	return true;
}
public String [] getFriends(App ap) {
	IndexFile i=new IndexFile();
	Folder des =new Folder();
	des.setPath("Accounts\\"+ap.currentUser.getEmail()+"\\Contacts.txt");
	int linesNum=i.countLines(des);
	if(linesNum==0) {
		return null;
	}
	 String []names=new String[linesNum];
	try {
	File path=new File("Accounts\\"+ap.currentUser.getEmail()+"\\Contacts.txt");
	FileReader fr=new FileReader(path);
	BufferedReader reader=new BufferedReader(fr);
	String currentLine;
	int j=0;
	while((currentLine=reader.readLine())!=null) {
		String [] data=currentLine.split("===");
		names[j++]=data[0];
	}
	reader.close();
	fr.close();
	}catch(IOException e) {
		e.printStackTrace();
	}
	return names;
}
public void deleteContact(int deletedContactLine,App ap) {
	try {
	File path=new File("Accounts\\"+ap.currentUser.getEmail()+"\\Contacts.txt");
	FileReader fr=new FileReader(path);
	BufferedReader reader =new BufferedReader(fr);
	File temp=new File("temp.txt");
	FileWriter fw=new FileWriter(temp,true);
	BufferedWriter bw =new BufferedWriter(fw);
	PrintWriter writer= new PrintWriter(bw);
	String currentLine;
	int currentLineNum=0;
	while((currentLine=reader.readLine())!=null) {
		if(currentLineNum!=deletedContactLine) {
			writer.println(currentLine);
		}
		currentLineNum++;
	}
	writer.close();
	bw.close();
	fw.close();
	reader.close();
	fr.close();
	path.delete();
	File dump=new File("Accounts\\"+ap.currentUser.getEmail()+"\\Contacts.txt");
	temp.renameTo(dump);
	}catch(IOException e) {
		e.printStackTrace();
	}
}
public String [] getFriendEmails(int friendLine,App ap) {
	String [] out=null;
	try {
	File path= new File("Accounts\\"+ap.currentUser.getEmail()+"\\Contacts.txt");
	FileReader fr=new FileReader(path);
	BufferedReader reader =new BufferedReader(fr);
	String currentLine;
	for(int i=0;i<friendLine;i++) {
		reader.readLine();
	}
	currentLine=reader.readLine();
	String []data =currentLine.split("===");
	 out=new String[data.length-1];
	for(int i=1;i<data.length;i++) {
		out[i-1]=data[i];
	}
	reader.close();
	fr.close();
	}catch(IOException e) {
		e.printStackTrace();
	}
	return out;
}
}