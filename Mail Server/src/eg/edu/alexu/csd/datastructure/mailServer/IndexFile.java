package eg.edu.alexu.csd.datastructure.mailServer;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public class IndexFile implements IIndex{
	/**
	 * Sets the info of the email to the index object
	 * @param newEmail
	 * The email whose info will be stored in the index file 
	 */
	private IndexObject setIndexFile(Mail newEmail) {
		IndexObject newIndexFile=new IndexObject();
		newIndexFile.setDate(newEmail.getDate());//TODO date won't be empty even if it is a draft
		newIndexFile.setFrom(newEmail.getFrom());//TODO From cannot be empty
		if((newEmail.getTo())!=null) {
		newIndexFile.setTo(newEmail.getTo());
		}
		if((newEmail.getSubject())!=null)
		newIndexFile.setSubject(newEmail.getSubject());
		newIndexFile.setPriority(newEmail.getPriority());//TODO if priority not set put it as 4 
		return newIndexFile;
	}
	/**
	 * 
	 * @param indexLine
	 * An index object that contains the info of one line of the index file 
	 * @return
	 * An Email object that contains the info of the email with the body and attachments
	 */
	private Mail setEmail(IndexObject indexLine) {
		Mail newEmail=new Mail();
		newEmail.setDate(indexLine.getDate());
		newEmail.setFrom(indexLine.getFrom());
		newEmail.setPriority(indexLine.getPriority());
		newEmail.setSubject(indexLine.getSubject());
		newEmail.setTo(indexLine.getTo());
		newEmail.setPath(indexLine.getPath());
		return newEmail;
	}
	@Override
	public void write(IMail email,IFolder des) {//TODO I assume that the path in the folder is the path of the index file is that right ?
		IndexObject newIndexFile=new IndexObject();
		Mail newEmail=(Mail)email;
		newIndexFile=setIndexFile(newEmail);
		newIndexFile.setPath(newEmail.getPath());
		File indexFile=new File(((Folder)des).getPath());//Get the path of the index file 
		try {
		FileWriter fr=new FileWriter(indexFile,true);
		BufferedWriter writer=new BufferedWriter(fr);
		writer.append(newIndexFile.getPath());
		writer.append("===");
		writer.append(newIndexFile.getDate().replace("-", "/").replace(",", ":"));
		writer.append("===");
		writer.append(newIndexFile.getFrom());
		writer.append("===");
		if((newEmail.getTo())==null) {//In drafts,the receivers may not be specified
			writer.append("NORECEIVERS");
		}
		else {
			for(int i=0;i<newIndexFile.getTo().size();i++) {
				writer.append((String)newIndexFile.getTo().get(i));
				if(i!=(newIndexFile.getTo().size()-1))
				writer.append("˜");
			}
		}
		writer.append("===");
		if((newEmail.getSubject())==null||newEmail.getSubject().length()==0) {
			writer.append("NOSUBJECT");
		}
		else {
			writer.append(newIndexFile.getSubject());
		}
		writer.append("===");
		writer.append(Integer.toString((newIndexFile.getPriority())));	
		writer.append("\n");
		writer.close();
		}
		catch (IOException e) {
			e.printStackTrace();
		}		
	}

	@Override
	public void writeAll(SLL newEmails,IFolder des) {
			for(int i=0;i<newEmails.size();i++) {
				write((Mail)newEmails.get(i),des);
			}
	}
	
	@Override
	public IndexObject readLine(IFolder des,int lineNum) {//TODO EMPTY INDEX FILE AND EMPTY BODY TEXT it should be handled in the GUI using the count lines method
		IndexObject indexFile =new IndexObject();
		try {
			FileReader indexFilePath=new FileReader(((Folder)des).getPath());
			BufferedReader reader=new BufferedReader(indexFilePath);
			for(int i=1;i<lineNum;i++) {//We start from 1 because lineNum start from 1 also
				reader.readLine();//Skip that line
			}
			String line=reader.readLine();//A string which contains the contents of a line of the index file
			String [] Fields=line.split("===");//An array contains the fields of the index file 
			indexFile.setPath(Fields[0]);
			indexFile.setDate(Fields[1]);
			indexFile.setFrom(Fields[2]);
			String [] receiversArr=Fields[3].split("˜");//separate the receivers of the email
			//Make a SLL of the receivers
			SLL receiversSLL=new SLL();
			for(int i=0;i<receiversArr.length;i++) {
				receiversSLL.add(receiversArr[i]);
			}
			indexFile.setTo(receiversSLL);
			indexFile.setSubject(Fields[4]);//TODO if the receivers are NORECEIVERS or if the subject is NOSUBJECT we need to handle those cases
			//when converting the draft into an email and a solution is to display the contents of the draft onto the composing GUI to continue
			//composing  and you shall choose whether to overwrite the old draft or make a new one with a new Data and user can choose from all 
			//the given dates of the drafts 
			reader.close();
			indexFile.setPriority(Integer.parseInt(Fields[5]));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		return indexFile;
	}

	@Override
	public SLL read10(IFolder des) {//TODO In GUI special case if the index file doesn't contain 10 lines,you should check first using count lines
		SLL indices=new SLL();//A single linked list which contains 10 index objects 
		for(int i=0;i<10;i++) {
			IndexObject indexLine=readLine(des,i+1);//i+1 because we send the right number of the line such as 1 for the first line and 2 for the secomd 
			indices.add(indexLine);
		}
		
		return indices;
	}
	/**
	 *
	 * @param des
	 * The index file which this function will count the number of lines in 
	 * @return
	 * The number of lines in the text index file 
	 */
	int countLines(IFolder des) {
		int lines=0;
		try {
			FileReader fr=new FileReader(((Folder)des).getPath());
			BufferedReader reader=new BufferedReader(fr);
			while((reader.readLine())!=null) {
				lines++;
			}
			reader.close();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		return lines;
	}
	@Override
	public SLL readAll(IFolder des) {
		SLL indices=new SLL();//A single linked list which contains index objects  that hold all the information in the text index file
		try {
			FileReader fr=new FileReader(((Folder)des).getPath());
			BufferedReader reader=new BufferedReader(fr);
			int linesNum=countLines(des);
			
			for(int i=0;i<linesNum;i++) {
				IndexObject indexLine=readLine(des,i+1);
				indices.add(indexLine);
			}
			reader.close();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		return indices;
	}

	@Override
	public SLL getEmails(SLL Indexes) {
		SLL Emails=new SLL();
		for(int i=0;i<Indexes.size();i++) {
			Mail newEmail=new Mail();
			newEmail=setEmail((IndexObject)Indexes.get(i));
			//System.out.println(newEmail.getPath());
			//reading the body of the email and putting it into the email object
			try {
				File f=new File(newEmail.getPath()+"\\Body.txt");
				FileReader fr=new FileReader(f);
				BufferedReader reader=new BufferedReader(fr);
				String line;
				String Body="";
				Folder des =new Folder();
				des.setPath(f.toString());
				if((countLines(des)==0)){
					Body="NOTEXT";//TODO In GUI SEE how are you going to display NOTEXT on the screen to the User 
				}
				else {
				while((line=reader.readLine())!=null){
				Body+=line;
				Body+="\n";
				}
				}
				newEmail.setBody(Body);
				reader.close();
				fr.close();
			}
			
			catch(IOException e) {
				e.printStackTrace();
			}
			//reading the paths of the attachments and storing them into the email object
			MyFileVisitor visitor=new MyFileVisitor();
			try {
				Files.walkFileTree(Paths.get(newEmail.getPath()),visitor);
				newEmail.setAttachments(visitor.getAttachments());//TODO in GUI attachments SLL is equal to null if there are no attachments in the email folder 
			} catch (IOException e) {
				e.printStackTrace();
				e.printStackTrace();
			}
			Emails.add(newEmail);
		}
		//System.out.println(Emails.size());
		return Emails;
	}
	@Override
	public void copyAttachments(SLL attachments,IFolder To) {
		for(int i=0;i<attachments.size();i++) {
			Path attach=Paths.get((String)attachments.get(i));
			try {
				Files.copy(attach, Paths.get(((Folder)To).getPath()+"\\"+attach.getFileName().toString()));
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		
	}
	@Override
	public void deleteAttachment(String path) {
		File attachment=new File(path);
		attachment.delete();
	}
	@Override
	public void deleteAttachments(SLL paths) {
		for(int i=0;i<paths.size();i++) {
			deleteAttachment((String) paths.get(i));
		}
	}
	@Override
	public void deleteLine(IFolder des,IFolder trash,String path ) {//TODO would be used in removing contacts 
		try {
			File oldFile= new File(((Folder)des).getPath());
			FileReader fr=new FileReader(oldFile);
			BufferedReader reader=new BufferedReader(fr);
			File trashIndex=new File(((Folder)trash).getPath());
			String trashLine="";
			File temp=new File("temp.txt");
			FileWriter fw=new FileWriter(temp,true);
			BufferedWriter bw=new BufferedWriter(fw);
			PrintWriter writer=new PrintWriter(bw);
			String currentLine;
			while((currentLine=reader.readLine())!=null) {
				String []data =currentLine.split("===");
				if(!(data[0].equals(path))) {
				writer.println(currentLine);
				
				}
				else {
				
					trashLine=currentLine;
					String [] data2=trashLine.split("===");
					String[] last=data[0].split("\\\\");
					last[2]="Trash";
					String trashLine2="";
					for(int i=0;i<last.length;i++) {
						trashLine2+=last[i];
						trashLine2+="\\\\";
					}
					data2[0]=trashLine2.substring(0,trashLine2.lastIndexOf("\\\\"));
					trashLine="";
					for(int i=0;i<data2.length;i++) {
						trashLine+=data2[i];
						if(i!=data2.length-1)
						trashLine+="===";
					}
					FileWriter fw2=new FileWriter(trashIndex,true);
					BufferedWriter writer2=new BufferedWriter(fw2);
					PrintWriter pr=new PrintWriter(writer2);
					pr.println(trashLine);
					pr.close();
					writer2.close();
					fw2.close();
				}
			}
			writer.close();
			bw.close();
			fw.close();
			reader.close();
			fr.close();
			oldFile.delete();
			File rename=new File(((Folder)des).getPath());
			
			if(!temp.renameTo(rename)) {
			}
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@Override
	public void deleteLine2(IFolder des,IFolder trash,int line ) {//TODO would be used in removing contacts 
		File test =new File(((Folder)trash).getPath());
		if(test.exists())
		{
		try {
			File oldFile= new File(((Folder)des).getPath());
			FileReader fr=new FileReader(oldFile);
			BufferedReader reader=new BufferedReader(fr);
			File trashIndex=new File(((Folder)trash).getPath()+"\\Index.txt");
			String trashLine="";
			File temp=new File("temp.txt");
			FileWriter fw=new FileWriter(temp,true);
			BufferedWriter bw=new BufferedWriter(fw);
			PrintWriter writer=new PrintWriter(bw);
			String currentLine;
			int currentLineNum=0;
			while((currentLine=reader.readLine())!=null) {
				if(currentLineNum!=line) {
				writer.println(currentLine);
				}
				else {
					trashLine=currentLine;
					FileWriter fw2=new FileWriter(trashIndex,true);
					BufferedWriter writer2=new BufferedWriter(fw2);
					PrintWriter pr=new PrintWriter(writer2);
					pr.println(trashLine);
					pr.close();
					writer2.close();
					fw2.close();
				}
				currentLineNum++;
			}
			writer.close();
			bw.close();
			fw.close();
			reader.close();
			fr.close();
			oldFile.delete();
			File rename=new File(((Folder)des).getPath());
			temp.renameTo(rename);
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		}
		else {
			
		}
	}
	@Override
	public void setLine(IFolder des,IMail email,String path ) {
		try {
			File oldFile= new File(((Folder)des).getPath());
			FileReader fr=new FileReader(oldFile);
			BufferedReader reader=new BufferedReader(fr);
			File temp=new File("temp.txt");
			FileWriter fw=new FileWriter(temp,true);
			BufferedWriter bw=new BufferedWriter(fw);
			PrintWriter writer=new PrintWriter(bw);
			String currentLine;
			int currentLineNum=0;
			while((currentLine=reader.readLine())!=null) {
				String [] data=currentLine.split("===");
				if(!data[0].equals(path)) {
				writer.println(currentLine);
				}
				else {
					String [] fields=currentLine.split("===");
					fields[1]=((Mail)email).getDate();
					String to="";
					for(int i=0;i<((Mail)email).getTo().size();i++) {
						to+=(String)(((Mail)email).getTo().get(i));
						if(i!=((Mail)email).getTo().size()-1)
						to+="˜";
					}
					fields[3]=to;
					fields[4]=((Mail)email).getSubject();
					fields[5]=Integer.toString(((Mail)email).getPriority());
					String newLine="";
					for(int i=0;i<fields.length;i++) {
						newLine=newLine.concat(fields[i]);
						if(i!=fields.length-1)
						newLine=newLine.concat("===");
					}
					writer.println(newLine);
					
				}
				currentLineNum++;
				
			}
			writer.close();
			bw.close();
			fw.close();
			reader.close();
			fr.close();
			oldFile.delete();
			File rename=new File(((Folder)des).getPath());//Copy the new Index file to the its directory
		
			//Write the new body on the index file
			temp.renameTo(rename);
			File body=new File(((Mail)email).getPath());
			FileWriter fw2=new FileWriter(body);
			BufferedWriter bw2=new BufferedWriter(fw2);
			bw2.write(((Mail)email).getBody());
			bw2.close();
			fw2.close();
		}catch(IOException e1) {
			e1.printStackTrace();
		}
			//Delete the old attachment and copy the new ones 
			try {
				Files.walkFileTree(Paths.get(((Mail)email).getPath()), new DeleteFileVisitor());
			} catch (IOException e) {
				e.printStackTrace();
			}
			if(((Mail)email).getAttachments()!=null) {
				Folder sender=new Folder();
				sender.setPath(((Mail)email).getPath());
				IndexFile i=new IndexFile();
				i.copyAttachments(((Mail)email).getAttachments(), sender);//Copy to the sender
			}
		}
		

	@Override
	public void viewAttachment(String path) {
		File attachment=new File(path);
		try {
			Desktop.getDesktop().open(attachment);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public SLL  getAttachments(String path) {
		SLL attachments=null;
		try {
			MyFileVisitor newFV=new MyFileVisitor();
			Files.walkFileTree(Paths.get(path), newFV);
			attachments=newFV.getAttachments();
		}catch(IOException e) {
			e.printStackTrace();
		}
		return attachments;
	}
	
}