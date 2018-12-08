package Control;

import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;

import GUI.Window;
import SocialNetworks.Gmail;

public class Main {

	public static void main(String[] args) {
		Control control = Control.getInstance();
		control.start();
	}

}
