package Control;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

import javax.mail.Message;
import javax.mail.MessagingException;

import com.restfb.Connection;
import com.restfb.types.Post;

import GUI.Platform;
import GUI.Window;
import SocialNetworks.*;
import twitter4j.Status;

public class Control {

	private static Control instance;

	public static Control getInstance() {
		if (instance == null)
			instance = new Control();
		return instance;
	}

	private static String USER_PATH = "User/";

	private Window window;

	private boolean connected = false;

	private String facebookAcessToken, twitterAPIKey, twitterSecretAPIKey, twitterAcessToken, twitterSecretAcessToken,
			emailString, emailPassword;

	private Gmail email;
	private Twitter twitter;
	private Facebook facebook;

	private ArrayList<Message> emails;
	private Connection<Post> posts;
	private ArrayList<Status> twits;

	private Control() {
		window = new Window(this);
		window.pack();
		window.setVisible(true);
	}

	public void activateFacebook() {
		if(!hasFacebookCredentials())
			readFacebookCredentials();
		connectFacebook();
	}

	public void activateTwitter() {
		if(!hasTwitterCredentials())
			readTwitterCredentials();
		connectTwitter();
	}

	public void activateEmail() {
		if(hasEmailCredentials())
			readEmailCredentials();
		connectEmail();
		try {
			window.updateFeed(email.getNotifications());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void deactivateFacebook() {
		disconnectFacebook();
		facebook = null;
	}

	public void deactivateTwitter() {
		disconnectTwitter();
		twitter = null;
	}

	public void deactivateEmail() {
		disconnectEmail();
		email = null;
	}

	public boolean hasFacebookCredentials() {
		return facebookAcessToken != null && !facebookAcessToken.isEmpty();
	}

	public boolean hasTwitterCredentials() {
		return twitterAPIKey != null && !twitterAPIKey.isEmpty() && twitterSecretAPIKey != null
				&& !twitterSecretAPIKey.isEmpty() && twitterAcessToken != null && !twitterAcessToken.isEmpty()
				&& twitterSecretAcessToken != null && !twitterSecretAcessToken.isEmpty();
	}

	public boolean hasEmailCredentials() {
		return email != null && !emailString.isEmpty() && emailPassword != null && !emailPassword.isEmpty();
	}

	public boolean isFacebookActive() {
		return facebook != null;
	}

	public boolean isTwitterActive() {
		return twitter != null;
	}

	public boolean isEmailActive() {
		return email != null;
	}

	public void start() {
		readEmailCredentials();
		readFacebookCredentials();
		readTwitterCredentials();
		if(hasTwitterCredentials())
			connectTwitter();
		if(hasFacebookCredentials())
			connectFacebook();
		if(hasEmailCredentials())
			connectEmail();
		if(isEmailActive()) {
			System.out.println("ola");
			try {
				window.updateFeed(email.getNotifications());
			} catch (IOException e) {
				e.printStackTrace();
			} catch (MessagingException e) {
				e.printStackTrace();
			}
		}
	}

	private void readFacebookCredentials() {
		File file = new File(USER_PATH + "facebookCredentials.txt");
		Scanner s = null;
		try {
			s = new Scanner(file);
			facebookAcessToken = s.nextLine();
		} catch (FileNotFoundException | NoSuchElementException e) {
			window.credentialsError(Platform.FACEBOOK);
			e.printStackTrace();
		} finally {
			s.close();
		}
	}

	private void readTwitterCredentials() {
		File file = new File(USER_PATH + "twitterCredentials.txt");
		Scanner s = null;
		try {
			s = new Scanner(file);
			twitterAPIKey = s.nextLine();
			twitterSecretAPIKey = s.nextLine();
			twitterAcessToken = s.nextLine();
			twitterSecretAcessToken = s.nextLine();
		} catch (FileNotFoundException | NoSuchElementException e) {
			window.credentialsError(Platform.TWITTER);
			e.printStackTrace();
		} finally {
			s.close();
		}
	}

	private void readEmailCredentials() {
		File file = new File(USER_PATH + "emailCredentials.txt");
		Scanner s = null;
		try {
			s = new Scanner(file);
			emailString = s.nextLine();
			emailPassword = s.nextLine();
		} catch (FileNotFoundException | NoSuchElementException e) {
			window.credentialsError(Platform.EMAIL);
			e.printStackTrace();
		} finally {
			s.close();
		}
	}

	public void writeTwitterCredentials(String twitterAPIKey, String twitterSecretAPIKey, String twitterAcessToken,
			String twitterSecretAcessToken) {
		File file = new File(USER_PATH + "twitterCredentials.txt");
		try {
			FileWriter fw = new FileWriter(file);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(twitterAPIKey);
			bw.newLine();
			bw.write(twitterSecretAPIKey);
			bw.newLine();
			bw.write(twitterAcessToken);
			bw.newLine();
			bw.write(twitterSecretAcessToken);
			bw.close();
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void writeEmailCredentials(String email, String password) {
		File file = new File(USER_PATH + "emailCredentials.txt");
		try {
			FileWriter fw = new FileWriter(file);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(email);
			bw.newLine();
			bw.write(password);
			bw.close();
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void writeFacebookCredentials(String facebookAcessToken) {
		File file = new File(USER_PATH + "facebookCredentials.txt");
		try {
			FileWriter fw = new FileWriter(file);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(facebookAcessToken);
			bw.close();
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// por acabar
	public void readLogs() {
		File credentials = new File(USER_PATH);
		for (File file : credentials.listFiles()) {
			FileInputStream fileIn = null;
			ObjectInputStream in;
			try {
				fileIn = new FileInputStream(file);
				in = new ObjectInputStream(fileIn);
				switch (file.getName()) {
				case "facebookLogs.txt":

					break;
				case "twitterLogs.txt":

					break;
				case "emailLogs.txt":
					emails = (ArrayList<Message>) in.readObject();
					break;
				}
				in.close();
				fileIn.close();
			} catch (ClassNotFoundException | IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void readFacebookLog() {

	}

	public void readEmailLog() {

	}

	public void readTwitterLog() {

	}

	private void connectEmail() {
		System.out.println(emailString);
		System.out.println(emailPassword);
		email = new Gmail(emailString, emailPassword);
		try {
			email.connect();
		} catch (MessagingException e) {
			window.connectingError(Platform.EMAIL);
			e.printStackTrace();
		}

	}

	private void connectFacebook() {
		facebook = new Facebook(facebookAcessToken);
		if (!facebook.isConnected())
			window.connectingError(Platform.FACEBOOK);
	}

	private void connectTwitter() {

	}

	public void disconnectEmail() {
		try {
			email.close();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

	public void disconnectFacebook() {

	}

	public void disconnectTwitter() {

	}

}
