package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import Control.Notification;

public class Bubble extends JPanel {
	
	private static int BORDER = 10;
	private static int ICON_SIZE = 30;
	private static int ICON_BORDER = 10;
	private static int ICON_TOTAL_SIZE = ICON_SIZE + (2 * ICON_BORDER);
	private static int TEXT_BOX_DISTANCE_FROM_ICON = 20;
	private static int TEXT_MAXIMUM_LINES = 2;
	private static int TITLE_SPACE = 10;
	
	private static int TITLE_TEXT_SIZE = 16;
	private static int TEXT_SIZE = 14;
	private static int TEXT_PADING = 10;
	
	private Notification notification;
	
	public Bubble(Notification notification) {
		this.notification = notification;
		Dimension d = new Dimension(600, 100);
		setSize(d);
		setMinimumSize(d);
		setPreferredSize(d);
		setMaximumSize(d);
	}

	public Bubble(int width, int height, Notification notification) {
		this.notification = notification;
		Dimension d = new Dimension(width, height);
		setSize(d);
		setMinimumSize(d);
		setPreferredSize(d);
		setMaximumSize(d);
	}
	
	public int getTextMaximumWidth() {
		return getWidth() - ICON_TOTAL_SIZE - (2 * TEXT_PADING) - (2 * BORDER);
	}
	
	public int getTextMaximumHeight(FontMetrics fm, ArrayList<String> lines) {
		return (fm.getHeight() * (lines.size() + 2)) + TITLE_SPACE + (3 * TITLE_SPACE);
	}
	
	public String getUerSubjectLine(FontMetrics fm) {
		String line = "" + notification.getUser();
		if(fm.stringWidth(line + notification.getSubject()) < getTextMaximumWidth()) {
			while(fm.stringWidth(line + notification.getSubject()) < getTextMaximumWidth())
				line += " ";
			line += notification.getSubject();
		} else {
			String subjectCopy = new String(notification.getSubject());
			while(fm.stringWidth(line + " " + subjectCopy) > getTextMaximumWidth())
				subjectCopy = subjectCopy.substring(0, subjectCopy.length() - 1);
			line += subjectCopy;
		}
		return line;
	}
	
	public String getReducedLine(String line, FontMetrics fm) {
		return line;
	}
	
	private ArrayList<String> getLines(FontMetrics fm) {
		LinkedList<String> words = new LinkedList<String>(Arrays.asList(notification.getText().split(" ")));
		ArrayList<String> lines = new ArrayList<String>(TEXT_MAXIMUM_LINES);
		for(int i = 0; !words.isEmpty() && i < TEXT_MAXIMUM_LINES; i++) {
			String line = words.getFirst();
			words.removeFirst();
			while (!words.isEmpty() && fm.stringWidth(line + words.getFirst()) < getTextMaximumWidth()) {
				line += " " + words.getFirst();
				words.removeFirst();
			}
			lines.add(line);
		}
		if(!words.isEmpty()) {
			String line = lines.get(lines.size());
			lines.remove(line);
			line = getReducedLine(line, fm);
			lines.add(line);
		}
		return lines;
	}
		
	@Override
	public void paintComponent(Graphics g) {
		
		Graphics2D g2 = (Graphics2D) g;
		g2.setFont(new Font("default", Font.BOLD, TITLE_TEXT_SIZE));
		FontMetrics fm = g2.getFontMetrics();
		
		g2.fillRoundRect(BORDER, BORDER,  (2 * ICON_BORDER) + ICON_SIZE, (2 * ICON_BORDER) + ICON_SIZE, ICON_BORDER, ICON_BORDER);
		try {
			g.drawImage(ImageIO.read(new File(notification.getPlatform().getIconPath())), BORDER + ICON_BORDER, BORDER + ICON_BORDER, ICON_SIZE, ICON_SIZE, null, null);
		} catch (IOException e) {
			e.printStackTrace();
		}
		ArrayList<String> lines = getLines(fm);
//		Dimension d = new Dimension(getParent().getWidth() - (2 * BORDER), getTextMaximumHeight(fm, lines));
//		setSize(d);
//		setMinimumSize(d);
//		setPreferredSize(d);
//		setMaximumSize(d);
		g2.fill3DRect(TEXT_BOX_DISTANCE_FROM_ICON + ICON_TOTAL_SIZE + BORDER, BORDER, getTextMaximumWidth() + (2 * TEXT_PADING), getTextMaximumHeight(fm, lines), true);
		g2.setColor(Color.WHITE);
		g2.setFont(new Font("default", Font.BOLD, TITLE_TEXT_SIZE));
		g2.drawString(notification.getPlatform().prettyName() + " Notification", TEXT_BOX_DISTANCE_FROM_ICON + ICON_TOTAL_SIZE + BORDER + TEXT_PADING, fm.getAscent() + TEXT_PADING + BORDER);
		g2.setFont(new Font("default", Font.PLAIN, TITLE_TEXT_SIZE));
		g.drawString(getUerSubjectLine(fm), TEXT_BOX_DISTANCE_FROM_ICON + ICON_TOTAL_SIZE + BORDER + TEXT_PADING, (2 * fm.getHeight()) + TITLE_SPACE + BORDER);
		g2.setFont(new Font("default", Font.PLAIN, TEXT_SIZE));
		for(int i = 0; i < lines.size(); i++)
			g.drawString(lines.get(i), TEXT_BOX_DISTANCE_FROM_ICON + ICON_TOTAL_SIZE + BORDER + TEXT_PADING, ((3 + i) * fm.getHeight()) + TITLE_SPACE + BORDER);
	}
	
	
}
