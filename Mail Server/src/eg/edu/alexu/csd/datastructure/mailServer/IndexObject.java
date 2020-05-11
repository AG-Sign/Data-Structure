package eg.edu.alexu.csd.datastructure.mailServer;

public class IndexObject {
	private String path;//Path of the email
	private String date;//The date on which the email was composed
	private String from;//The sender of the email
	private SLL to;//The receivers of the email
	private String subject;//The subject of the email
	private int priority;//The priority of the email
	
	
	
	/*
	 * Setters And Getters
	 */
	public void setPath(String path) {
		this.path=path;
	}
	
	public String getPath() {
		return this.path;
	}
	
	public void setDate(String date) {
		this.date=date;
	}
	
	public String getDate() {
		return this.date;
	}
	
	public void setTo(SLL to) {
		this.to=to;
	}
	
	public SLL getTo() {
		return this.to;
	}
	public void setFrom(String from) {
		this.from=from;
	}
	
	public String getFrom() {
		return this.from;
	}
	public void setSubject(String subject) {
		this.subject=subject;
	}
	
	public String getSubject() {
		return this.subject;
	}
	
	public void setPriority(int priority) {
		this.priority=priority;
	}
	public int getPriority() {
		return this.priority;
	}

}