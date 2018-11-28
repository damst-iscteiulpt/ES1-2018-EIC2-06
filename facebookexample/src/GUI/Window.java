package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.nio.channels.ShutdownChannelGroupException;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JSplitPane;
import javax.swing.KeyStroke;
import javax.swing.WindowConstants;

import SocialNetworks.FacebookMain;
import SocialNetworks.Gmail;
import SocialNetworks.Twitter;

public class Window extends JFrame {

	
	FacebookMain facebook;
	Gmail gmail;
	Twitter twitter;
	JSplitPane splitPane;
	JPanel mainPanel, buttonPanel, feedPanel, facebookPanel, twitterPanel, emailPanel;
	JButton feedButton, facebookButton, twitterButton, emailButton;

	public Window() {
		init();
	}

	public void init() {
		setName("ES1 Project");
		setTitle("ES1 Project");
		setSize(900, 500);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		initPanels();
		initButtons();
		initMenu();
		
		setContentPane(mainPanel);
		setVisible(true);
	}

	public void initPanels() {
		splitPane = new JSplitPane();
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
					g.drawImage(ImageIO.read(new File("./images/facebook.jpg")), 0, 0, getWidth(), getHeight(), null);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
		};
		
		twitterPanel = new JPanel() {
			
			@Override
			public void paintComponent(Graphics g) {
				try {
					g.drawImage(ImageIO.read(new File("./images/twitter.png")), 0, 0, getWidth(), getHeight(), null);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
		};
		emailPanel = new JPanel() {
			
			@Override
			public void paintComponent(Graphics g) {
				try {
					g.drawImage(ImageIO.read(new File("./images/gmail.jpg")), 0, 0, getWidth(), getHeight(), null);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
		};
		
		mainPanel.setLayout(new BorderLayout());
		buttonPanel.setLayout(new GridLayout(4, 1));
		
		splitPane.setLeftComponent(buttonPanel);
		splitPane.setRightComponent(feedPanel);
		
		mainPanel.add(splitPane, BorderLayout.CENTER);
	}

	public void initButtons() {
		feedButton = new JButton("Feed");
		feedButton.setBackground(Color.BLACK);
		feedButton.setForeground(Color.WHITE);
		feedButton.setFocusPainted(false);
		feedButton.setFont(new Font("Tahoma", Font.BOLD, 20));
		feedButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				splitPane.setRightComponent(feedPanel);
			}
		});
		facebookButton = new JButton("Facebook");
		facebookButton.setBackground(new Color(59, 89, 182));
		facebookButton.setForeground(Color.WHITE);
		facebookButton.setFocusPainted(false);
		facebookButton.setFont(new Font("Tahoma", Font.BOLD, 20));
		facebookButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				splitPane.setRightComponent(facebookPanel);
			}
		});
		twitterButton = new JButton("Twitter");
		twitterButton.setBackground(new Color(29, 202, 255));
		twitterButton.setForeground(Color.WHITE);
		twitterButton.setFocusPainted(false);
		twitterButton.setFont(new Font("Tahoma", Font.BOLD, 20));
		twitterButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				splitPane.setRightComponent(twitterPanel);
			}
		});
		emailButton = new JButton("E-mail");
		emailButton.setBackground(new Color(178, 49, 33));
		emailButton.setForeground(Color.WHITE);
		emailButton.setFocusPainted(false);
		emailButton.setFont(new Font("Tahoma", Font.BOLD, 20));
		emailButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				splitPane.setRightComponent(emailPanel);
			}
		});
		buttonPanel.add(feedButton);
		buttonPanel.add(facebookButton);
		buttonPanel.add(twitterButton);
		buttonPanel.add(emailButton);
	}
	
	public void initMenu() {
		JMenuBar menuBar = new JMenuBar();
		JMenu menu = new JMenu("Menu");
		menuBar.add(menu);
		
		JMenuItem filter = new JMenuItem("Filter");
		filter.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
            	JOptionPane.showMessageDialog(null, "FALTA FAZER O FILTRO", "NOT DONE 404 ", JOptionPane.ERROR_MESSAGE);
            }
        });
		menu.add(filter);
		
		JMenuItem exit = new JMenuItem("Exit");
		exit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
            	if (JOptionPane.showConfirmDialog(null, "Are you sure?, When exit we will disconnect", "Exit app",JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            	    System.exit(0);
            	}
            }
        });
		menu.add(exit);
		mainPanel.add(menuBar, BorderLayout.NORTH);
	}
	
	public void updateFacebook() {
		
	}
	
	public void updateTwitter() {
		
	}
	
	public void updateEmail() {
		
	}

}
