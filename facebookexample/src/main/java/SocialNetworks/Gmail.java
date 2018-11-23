package SocialNetworks;

import java.io.*;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.sun.mail.smtp.SMTPTransport;

public class Gmail {
	
	private static String HOST = "gmail.com";
	private static String TRANSPORT_PROTOCOL = "smtp";
	private static String STORE_PROTOCOL = "imap";
	private static String SENDING_HOST = TRANSPORT_PROTOCOL + "." + HOST;
	private static String RECEIVING_HOST = STORE_PROTOCOL + "." + HOST;
	
	public static String INBOX  = "Inbox";
	public static String ALL_MAIL = "All Mail";
	public static String DRAFTS = "Drafts";
	public static String SENT_MAIL = "Sent Mail";
	public static String SPAM = "Spam";
	public static String STARRED = "Starred";
	public static String TRASH = "Trash";
	
	private final String email;
	private final String password;
	private Properties properties;
	private Session session;
	private Store store;
	private SMTPTransport smtpt;

	public Gmail(String email, String password) {
		this.email = email;
		this.password = password;
		properties = new Properties();
		properties.setProperty("mail.debug", "true");
		properties.setProperty("mail.host", HOST);
		properties.setProperty("mail.store.protocol", STORE_PROTOCOL);
		properties.setProperty("mail.store.port", "993");
		properties.setProperty("mail.imap.ssl.enable", "true");
		properties.setProperty("mail.smtp.ssl.enable", "true");
		properties.setProperty("mail.transport.protocol", TRANSPORT_PROTOCOL);
//		properties.setProperty("mail.transport.port", "993");
	}
	
	public void connect() throws MessagingException {
		session = Session.getDefaultInstance(properties, null);
		store = session.getStore(STORE_PROTOCOL);
		store.connect(RECEIVING_HOST, email, password);
		smtpt = (SMTPTransport) session.getTransport(TRANSPORT_PROTOCOL);
		smtpt.connect(SENDING_HOST, email, password);
	}
	
	public Message[] getEmails(String folder) throws MessagingException {
		 Folder folderObj = store.getFolder(folder);
		 folderObj.open(Folder.READ_WRITE);
		 return folderObj.getMessages();
	}
	
	public void sendEmail(String receiversEmail, String subject, String content) throws MessagingException {
		Message msg = new MimeMessage(session);
		msg.setFrom(new InternetAddress(email));
		msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(receiversEmail, false));
		msg.setSubject("Heisann " + System.currentTimeMillis());
		msg.setText(content);
		msg.setHeader("X-Mailer", subject);
		msg.setSentDate(new Date());
		smtpt.sendMessage(msg, msg.getAllRecipients());
	}
	
	public void close() throws MessagingException {
		store.close();
		smtpt.close();
	}

//	public void doit() throws MessagingException, IOException {
//	    Folder folder = null;
//	    Store store = null ;
//	    try {
//	      Properties props = System.getProperties();
//	      props.setProperty("mail.store.protocol", "imaps");
//	      props.setProperty("mail.store.port", "993");
//	      Session session = Session.getDefaultInstance(props, null);
////	       session.setDebug(true);
//	      store = session.getStore("imaps");
//	      store.connect("imap.gmail.com",email, password);
//	      
//	      folder = store.getFolder(INBOX);
//	      
//	      folder.open(Folder.READ_WRITE);
//	      Message messages[] = folder.getMessages();
//	      System.out.println("No of Messages : " + folder.getMessageCount());
//	      System.out.println("No of Unread Messages : " + folder.getUnreadMessageCount());
//	      for (int i=0; i < messages.length; ++i) {
//	        System.out.println("MESSAGE #" + (i + 1) + ":");
//	        Message msg = messages[i];
//	        /*
//	          if we don''t want to fetch messages already processed
//	          if (!msg.isSet(Flags.Flag.SEEN)) {
//	             String from = "unknown";
//	             ...
//	          }
//	        */
//	        String from = "unknown";
//	        if (msg.getReplyTo().length >= 1) {
//	          from = msg.getReplyTo()[0].toString();
//	        }
//	        else if (msg.getFrom().length >= 1) {
//	          from = msg.getFrom()[0].toString();
//	        }
//	        String subject = msg.getSubject();
//	        System.out.println("Saving ... " + subject +" " + from);
//	        // you may want to replace the spaces with "_"
//	        // the TEMP directory is used to store the files
//	        String filename = "c:/temp/" +  subject;
//	        msg.setFlag(Flags.Flag.SEEN,true);
//	        // to delete the message
//	        // msg.setFlag(Flags.Flag.DELETED, true);
//	      }
//	    }
//	    finally {
//	      if (folder != null) { folder.close(true); }
//	      if (store != null) { store.close(); }
//	    }
//	  }
//
//	public void send_message(String receiversEmail, String content, String header, String subject)
//			throws MessagingException {
//		Properties props = System.getProperties();
//		props.put("mail.smtps.host", "smtp.gmail.com");
//		props.put("mail.smtps.auth", "true");
//		Session session = Session.getInstance(props, null);
//		Message msg = new MimeMessage(session);
//		msg.setFrom(new InternetAddress(email));
//		msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(receiversEmail, false));
//		msg.setSubject("Heisann " + System.currentTimeMillis());
//		msg.setText(content);
//		msg.setHeader("X-Mailer", subject);
//		msg.setSentDate(new Date());
//		SMTPTransport t = (SMTPTransport) session.getTransport("smtps");
//		t.connect("smtp.gmail.com", receiversEmail, "262577136d");
//		t.sendMessage(msg, msg.getAllRecipients());
//		System.out.println("Response: " + t.getLastServerResponse());
//		t.close();
//	}
	
}
