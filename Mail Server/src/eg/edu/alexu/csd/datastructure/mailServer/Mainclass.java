package eg.edu.alexu.csd.datastructure.mailServer;

public class Mainclass {
public static void main (String [] args) {
	String email="Abood@mail.com,,,,,,,,";
	String data[]=email.split(",");
	
	System.out.println(data.length);
}
}
