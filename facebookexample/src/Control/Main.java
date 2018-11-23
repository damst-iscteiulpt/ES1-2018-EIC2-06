package Control;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;

import GUI.Window;
import SocialNetworks.Gmail;

public class Main {

	public static void main(String[] args) {
		Window window = new Window();
		Gmail email = new Gmail("andre.andrade.iscte@gmail.com", "andreandrade123");
		try {
			email.connect();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			Message[] m = email.getEmails(Gmail.INBOX);
			for(Message f : m)
				System.out.println("Email subject: " + f.getSubject());
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

}
