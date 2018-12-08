package Control;

import java.util.Date;

import GUI.Platform;

public class Notification implements Comparable<Notification> {

	private Platform platform;
	private Date date;
	private String user, subject, text;

	public Notification(Platform platform, Date date, String user, String subject, String text) {
		this.platform = platform;
		this.date = date;
		this.user = user;
		this.subject = subject;
		this.text = text;
	}
	
	public Platform getPlatform() {
		return platform;
	}

	public Date getDate() {
		return date;
	}

	public String getUser() {
		return user;
	}

	public String getSubject() {
		return subject;
	}

	public String getText() {
		return text;
	}

	@Override
	public int compareTo(Notification o) {
		return date.compareTo(o.date);
	}

}
