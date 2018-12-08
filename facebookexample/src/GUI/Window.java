package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.WindowConstants;

import Control.Control;
import Control.Notification;

public class Window extends JFrame {

	private Control control;
	private JSplitPane splitPane;
	private JScrollPane scrollPane;
	private JPanel mainPanel, buttonPanel, feedPanel, facebookPanel, twitterPanel, emailPanel;
	private JButton feedButton, facebookButton, twitterButton, emailButton;

	public Window(Control control) {
		this.control = control;
		init();
	}

	private void init() {
		setName("ES1 Project");
		setTitle("ES1 Project");
		Dimension d = new Dimension(900, 500);
		setSize(d);
		setMinimumSize(d);
		setPreferredSize(d);

		setLocationRelativeTo(null);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		initPanels();
		initButtons();
		initMenu();
//		testBubbles();

		setContentPane(mainPanel);
	}

	private void initPanels() {
		splitPane = new JSplitPane();
		scrollPane = new JScrollPane();
		mainPanel = new JPanel();
		buttonPanel = new JPanel();

		feedPanel = new JPanel() {

			@Override
			public void paintComponent(Graphics g) {
				try {
					g.drawImage(ImageIO.read(new File("./images/iscte.jpg")), 0, 0, getWidth(), getHeight(), null);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		};

		facebookPanel = new JPanel() {

			@Override
			public void paintComponent(Graphics g) {
				try {
					g.drawImage(ImageIO.read(new File(Platform.FACEBOOK.getBackgroundImage())), 0, 0, getWidth(),
							getHeight(), null);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		};

		twitterPanel = new JPanel() {

			@Override
			public void paintComponent(Graphics g) {
				try {
					g.drawImage(ImageIO.read(new File(Platform.TWITTER.getBackgroundImage())), 0, 0, getWidth(),
							getHeight(), null);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		};
		emailPanel = new JPanel() {

			@Override
			public void paintComponent(Graphics g) {
				try {
					g.drawImage(ImageIO.read(new File(Platform.EMAIL.getBackgroundImage())), 0, 0, getWidth(),
							getHeight(), null);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		};

		mainPanel.setLayout(new BorderLayout());
		buttonPanel.setLayout(new GridLayout(4, 1));
		feedPanel.setLayout(new BoxLayout(feedPanel, BoxLayout.Y_AXIS));
		
		scrollPane.setViewportView(feedPanel);

		splitPane.setLeftComponent(buttonPanel);
		splitPane.setRightComponent(scrollPane);

		mainPanel.add(splitPane, BorderLayout.CENTER);
	}

	private void initButtons() {
		feedButton = new JButton("Feed");
		feedButton.setBackground(Color.BLACK);
		feedButton.setForeground(Color.WHITE);
		feedButton.setFocusPainted(false);
		feedButton.setFont(new Font("Tahoma", Font.BOLD, 20));
		feedButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				scrollPane.setViewportView(feedPanel);
			}
		});
		facebookButton = new JButton("Facebook");
		facebookButton.setBackground(new Color(59, 89, 182));
		facebookButton.setForeground(Color.WHITE);
		facebookButton.setFocusPainted(false);
		facebookButton.setFont(new Font("Tahoma", Font.BOLD, 20));
		facebookButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				scrollPane.setViewportView(facebookPanel);
			}
		});
		twitterButton = new JButton("Twitter");
		twitterButton.setBackground(new Color(29, 202, 255));
		twitterButton.setForeground(Color.WHITE);
		twitterButton.setFocusPainted(false);
		twitterButton.setFont(new Font("Tahoma", Font.BOLD, 20));
		twitterButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				scrollPane.setViewportView(twitterPanel);
			}
		});
		emailButton = new JButton("E-mail");
		emailButton.setBackground(new Color(178, 49, 33));
		emailButton.setForeground(Color.WHITE);
		emailButton.setFocusPainted(false);
		emailButton.setFont(new Font("Tahoma", Font.BOLD, 20));
		emailButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				scrollPane.setViewportView(emailPanel);
			}
		});
		buttonPanel.add(feedButton);
		buttonPanel.add(facebookButton);
		buttonPanel.add(twitterButton);
		buttonPanel.add(emailButton);
	}

	private void initMenu() {
		Window window = this;
		JMenuBar menuBar = new JMenuBar();
		JMenu menu = new JMenu("Menu");
		menuBar.add(menu);

		JMenuItem cred = new JMenuItem("Add Credentials");
		cred.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int option = JOptionPane.showOptionDialog(window, "Choose platform:", "Add credentials",
						JOptionPane.OK_OPTION, JOptionPane.PLAIN_MESSAGE, null,
						new String[] { "Facebook", "Twitter", "Email" }, "Facebook");
				switch (option) {
				case 0:
					FacebookLogin facebookLogin = new FacebookLogin();
					JOptionPane.showConfirmDialog(null, facebookLogin, "Facebook Credentials",
							JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
					control.writeFacebookCredentials(facebookLogin.getAcessToken());
					break;
				case 1:
					TwitterLogin twitterLogin = new TwitterLogin();
					JOptionPane.showConfirmDialog(null, twitterLogin, "Twitter Credentials",
							JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
					control.writeTwitterCredentials(twitterLogin.getKey1(), twitterLogin.getKey2(), twitterLogin.getKey3(), twitterLogin.getKey4());
					break;
				case 2:
					GmailLogin gmailLogin = new GmailLogin();
					JOptionPane.showConfirmDialog(null, gmailLogin, "Gmail Credentials",
							JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
					control.writeEmailCredentials(gmailLogin.getEmail(), gmailLogin.getPassword());
					break;
				}
			}
		});
		menu.add(cred);

		JMenuItem activate = new JMenuItem("Activate Platform");
		activate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int option = JOptionPane.showOptionDialog(window, "Choose platform:", "Activate Platform",
						JOptionPane.OK_OPTION, JOptionPane.PLAIN_MESSAGE, null,
						new String[] { "Facebook", "Twitter", "Email" }, "Facebook");
				switch (option) {
				case 0:
					control.activateFacebook();
					buttonPanel.add(facebookButton);
					buttonPanel.revalidate();
					buttonPanel.repaint();
					break;
				case 1:
					control.activateTwitter();
					buttonPanel.add(twitterButton);
					buttonPanel.revalidate();
					buttonPanel.repaint();
					break;
				case 2:
					control.activateEmail();
					buttonPanel.add(emailButton);
					buttonPanel.revalidate();
					buttonPanel.repaint();
					break;
				}
			}
		});
		menu.add(activate);

		JMenuItem desactivate = new JMenuItem("Deactivate Platform");
		desactivate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int option = JOptionPane.showOptionDialog(window, "Choose platform:", "Activate Platform",
						JOptionPane.OK_OPTION, JOptionPane.PLAIN_MESSAGE, null,
						new String[] { "Facebook", "Twitter", "Email" }, "Facebook");
				switch (option) {
				case 0:
					control.deactivateFacebook();
					buttonPanel.remove(facebookButton);
					buttonPanel.revalidate();
					buttonPanel.repaint();
					break;
				case 1:
					control.deactivateTwitter();
					buttonPanel.remove(twitterButton);
					buttonPanel.revalidate();
					buttonPanel.repaint();
					break;
				case 2:
					control.deactivateEmail();
					buttonPanel.remove(emailButton);
					buttonPanel.revalidate();
					buttonPanel.repaint();
					break;
				}
			}
		});
		menu.add(desactivate);

		JMenuItem exit = new JMenuItem("Exit");
		exit.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				if (JOptionPane.showConfirmDialog(null, "Are you sure?, When exit we will disconnect", "Exit app",
						JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
					System.exit(0);
				}
			}

		});
		menu.add(exit);
		mainPanel.add(menuBar, BorderLayout.NORTH);
	}

	public void testBubbles() {
		Bubble b1 = new Bubble(600, 100, Platform.FACEBOOK, "User", "Subject", "Isto é fodido!");
		Bubble b2 = new Bubble(600, 100, Platform.EMAIL, "User", "Subject", "Isto é fodido!");
//		JPanel p1 = new JPanel();
//		JPanel p2 = new JPanel();
//		p1.add(b1);
//		p2.add(b2);
		feedPanel.add(b1);
		feedPanel.add(b2);
	}
	
	public void credentialsError(Platform platform) {
		JOptionPane.showMessageDialog(this,
				"Error finding " + platform.prettyName() + " credentials. Try adding credentials again before trying again.",
				"Error 404!!!!!!!!!", JOptionPane.ERROR_MESSAGE);
	}

	public void connectingError(Platform platform) {
		JOptionPane.showMessageDialog(this,
				"Error login in to " + platform.prettyName() + ". Check your credentials and internet connection.",
				"Error 404!!!!!!!!!", JOptionPane.ERROR_MESSAGE);
	}
	
	public void updateFeed(List<Notification> notifications) {
		feedPanel.removeAll();
		for(Notification n : notifications)
			feedPanel.add(new Bubble(n));
	}
	
	public void updateFacebook() {

	}

	public void updateTwitter() {

	}

	public void updateEmail() {

	}

}
