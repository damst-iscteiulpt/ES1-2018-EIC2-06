package Main;

import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;

import SocialNetworks.Gmail;

public class Main {

	public static void main(String[] args) {
		Gmail email = null;
		try {
			email = new Gmail("andre.andrade.iscte@gmail.com", "andreandrade123");
		} catch (NoSuchProviderException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			email.connect();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
