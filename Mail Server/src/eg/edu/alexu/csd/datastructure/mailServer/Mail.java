package eg.edu.alexu.csd.datastructure.mailServer;

public class Mail implements IMail{
	private String path;
	private String from;//Sender of the email
	private SLL to ;//Receiver of the email
	private String subject;//The subject of the email
	private String date;//The date on which the email was sent
	private String body;//The body of the email
	private int priority;//The priority of the email
	private String emailFolder;
	private String folder;
	private int index;
	private SLL attachments=new SLL();
	public void setIndex(int index)
	{
		this.index=index;
	}
	public int getIndex()
	{
		return this.index;
	}
	public void setEmailFolder(String emailFolder) {
		this.emailFolder=emailFolder;
	}
	public String getEmailFolder() {
		return this.emailFolder;
	}
	public void setFrom(String from) {
		this.from=from;
	}
	public void setSubject(String subject) {
		this.subject=subject;
	}
	public void setDate(String date) {
		this.date=date;
	}
	public void setBody(String body) {
		this.body=body;
	}
	public void setPriority(int priority) {
		this.priority=priority;
	}
	public void setTo(SLL to) {
		this.to=to;
	}
	public void setAttachments(SLL attachments) {
		this.attachments=attachments;
	}
	public void setFolder(String Folder) {
		this.folder=Folder;
	}
	public void setPath() {//Used in the GUI
		this.path="Accounts\\"+this.from+"\\"+this.emailFolder+"\\"+this.from+this.date;
	}
	public void setPath(String path) {//Used when reading from the index file
		this.path=path;
	}
	public String getPath() {
		return this.path;
	}
	/**
	 * 
	 * @param path
	 * Path of the attachment to be sent
	 */
	public String getFrom() {
		return this.from;
	}

	public String getSubject() {
		return this.subject;
	}
	public String getDate() {
		return this.date;
	}
	public String getBody() {
		return this.body;
	}
	public int getPriority() {
		return this.priority;
	}
	public SLL getTo() {
		return this.to;
	}
	public SLL getAttachments() {
		return this.attachments;
	}
	public String getFolder() {
		return this.folder;
	}
}