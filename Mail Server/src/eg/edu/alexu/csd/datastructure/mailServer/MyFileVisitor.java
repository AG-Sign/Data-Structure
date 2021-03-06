package eg.edu.alexu.csd.datastructure.mailServer;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

public class MyFileVisitor extends SimpleFileVisitor<Path>{
	private SLL attachments=new SLL();

	@Override
	public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
		return FileVisitResult.CONTINUE;
	}

	@Override
	public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
		if(file.getFileName().toString().equals("Body.txt")) {
			return FileVisitResult.CONTINUE;
		}
		else {
			attachments.add(file.toString().replace("\\", "\\\\"));
		}
		return FileVisitResult.CONTINUE;
	}

	@Override
	public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
		System.err.println(exc.getMessage());
		return FileVisitResult.CONTINUE;
	}
	@Override
	public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
		return FileVisitResult.CONTINUE;
	}
	/**
	 * 
	 * @return
	 * A single linked list containing the attachments in the email folder
	 */
	public SLL getAttachments() {
		return this.attachments;
	}

}