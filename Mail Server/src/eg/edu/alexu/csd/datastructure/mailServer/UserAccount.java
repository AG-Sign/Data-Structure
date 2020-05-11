package eg.edu.alexu.csd.datastructure.mailServer;

public class UserAccount implements IContact{
	private String email;//The email-address of the user
	private String name;//The Nickname of the user
	private String password;//The password of the account of the user
	public UserAccount() {
	}
	public UserAccount(String email,String password,String name) {
		this.email=email;
		this.password=password;
		this.name=name;
	}
	public void setEmail(String email) {
		this.email=email;
	}
	public void setName(String name) {
		this.name=name;
	}
	public void setPassword(String password) {
		this.password=password;
	}
	public String getEmail() {
		return this.email;
	}
	public String getName() {
		return this.name;
	}
	public String getPassword() {
		return this.password;
	}

}
