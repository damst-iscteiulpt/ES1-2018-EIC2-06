package SocialNetworks;

import java.io.*;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.sun.mail.smtp.SMTPTransport;

public class Gmail {

	public Gmail() {
		
	}

	public static void doit() throws MessagingException, IOException {
	    Folder folder = null;
	    Store store = null ;
	    try {
	      Properties props = System.getProperties();
	      props.setProperty("mail.store.protocol", "imaps");
	      props.setProperty("mail.store.port", "993");
	      Session session = Session.getDefaultInstance(props, null);
//	       session.setDebug(true);
	      store = session.getStore("imaps");
	      store.connect("imap.gmail.com",email, password*);
	      
	      folder = store.getFolder("Inbox");
	      /* Others GMail folders :
	       * [Gmail]/All Mail   This folder contains all of your Gmail messages.
	       * [Gmail]/Drafts     Your drafts.
	       * [Gmail]/Sent Mail  Messages you sent to other people.
	       * [Gmail]/Spam       Messages marked as spam.
	       * [Gmail]/Starred    Starred messages.
	       * [Gmail]/Trash      Messages deleted from Gmail.
	       */
	      folder.open(Folder.READ_WRITE);
	      Message messages[] = folder.getMessages();
	      System.out.println("No of Messages : " + folder.getMessageCount());
	      System.out.println("No of Unread Messages : " + folder.getUnreadMessageCount());
	      for (int i=0; i < messages.length; ++i) {
	        System.out.println("MESSAGE #" + (i + 1) + ":");
	        Message msg = messages[i];
	        /*
	          if we don''t want to fetch messages already processed
	          if (!msg.isSet(Flags.Flag.SEEN)) {
	             String from = "unknown";
	             ...
	          }
	        */
	        String from = "unknown";
	        if (msg.getReplyTo().length >= 1) {
	          from = msg.getReplyTo()[0].toString();
	        }
	        else if (msg.getFrom().length >= 1) {
	          from = msg.getFrom()[0].toString();
	        }
	        String subject = msg.getSubject();
	        System.out.println("Saving ... " + subject +" " + from);
	        // you may want to replace the spaces with "_"
	        // the TEMP directory is used to store the files
	        String filename = "c:/temp/" +  subject;
	        msg.setFlag(Flags.Flag.SEEN,true);
	        // to delete the message
	        // msg.setFlag(Flags.Flag.DELETED, true);
	      }
	    }
	    finally {
	      if (folder != null) { folder.close(true); }
	      if (store != null) { store.close(); }
	    }
	  }

	public void send_message(String conteudo, String sender_email, String header, String subject)
			throws MessagingException {
		Properties props = System.getProperties();
		props.put("mail.smtps.host", "smtp.gmail.com");
		props.put("mail.smtps.auth", "true");
		Session session = Session.getInstance(props, null);
		Message msg = new MimeMessage(session);
		msg.setFrom(new InternetAddress(email));
		msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(sender_email, false));
		msg.setSubject("Heisann " + System.currentTimeMillis());
		msg.setText(conteudo);
		msg.setHeader("X-Mailer", subject);
		msg.setSentDate(new Date());
		SMTPTransport t = (SMTPTransport) session.getTransport("smtps");
		t.connect("smtp.gmail.com", email_sender, "262577136d");
		t.sendMessage(msg, msg.getAllRecipients());
		System.out.println("Response: " + t.getLastServerResponse());
		t.close();
	}

	public static void Testemail() throws Exception {
		Gmail.doit();
	}
	
}
