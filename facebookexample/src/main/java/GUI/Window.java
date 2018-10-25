package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.channels.ShutdownChannelGroupException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class Window {
	
	private JFrame frame;
	private boolean facebook = false;
	private boolean twitter = false;
	private boolean email = false;
	private JButton Login;
	
	public Window() {
		frame = new JFrame("Test");
		
		// para que o botao de fechar a janela termine a aplicacao
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		// Iniciação do BorderLayout
		frame.setLayout(new BorderLayout());
		
		//Localização onde começa a aplicação
		frame.setLocation(0, 0);
		
		//Conteúdo da frame
		addFrameContent();
		
		// para que a janela se redimensione de forma a ter todo o seu conteudo visivel
		frame.pack();
	}
	
	public void open() {
		// para abrir a janela (torna-la visivel)
		frame.setVisible(true);
		
		//Onde se define o tamanho da frame
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setSize(1900,1000);
	}
	
	private void addFrameContent() {
		//Painel Login, Sign-up;
		JPanel p1 = new JPanel();
		frame.add(p1, BorderLayout.CENTER);
		
		//Login: e-mail, password;
		Login = new JButton("Login");
		Login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!facebook && !twitter && !email) {
					JOptionPane.showMessageDialog(frame, "Select one of the 3 options on the left side of the screen", "ERROR(No option found) ", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		p1.add(Login, BorderLayout.CENTER);
		//Sign-up:e-mail, password, confirm password;
		/*
		JButton Sign = new JButton("Sign-Up");
		Sign.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		p1.add(Sign, BorderLayout.CENTER);
		*/ 
		
		//Menu
		JMenuBar menuBar = new JMenuBar();
		frame.add(menuBar, BorderLayout.NORTH);
		JMenu menu = new JMenu("Menu");
		menuBar.add(menu);
		
		//Painel das Redes Sociais
		JPanel p = new JPanel();
		frame.add(p, BorderLayout.WEST);
		p.setLayout(new GridLayout(3,1));
		
		JButton Facebook = new JButton("Facebook");
		Facebook.setBackground(new Color(59, 89, 182));
	    Facebook.setForeground(Color.WHITE);
	    Facebook.setFocusPainted(false);
	    Facebook.setFont(new Font("Tahoma", Font.BOLD, 20));
	    Facebook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(facebook == false) {
					Login.setText("Login in Facebook");
					Login.setBackground(new Color(59, 89, 182));
					Login.setForeground(Color.WHITE);
					facebook = true;
					twitter = false;
					email = false;
				}
				
			}
		});
		p.add(Facebook);
		
		JButton Twitter = new JButton("Twitter");
		Twitter.setBackground(new Color(29, 202, 255));
	    Twitter.setForeground(Color.WHITE);
	    Twitter.setFocusPainted(false);
	    Twitter.setFont(new Font("Tahoma", Font.BOLD, 20));
	    Twitter.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		if(twitter == false) {
	    			Login.setText("Login in Twitter");
	    			Login.setBackground(new Color(29, 202, 255));
	    		    Login.setForeground(Color.WHITE);
	    			facebook = false;
	    			twitter = true;
	    			email = false;
	    		}
			
	    	}
	    });
		p.add(Twitter);
		
		JButton Email = new JButton ("E-mail");
		Email.setBackground(new Color(178, 49, 33));
	    Email.setForeground(Color.WHITE);
	    Email.setFocusPainted(false);
	    Email.setFont(new Font("Tahoma", Font.BOLD, 20));
	    Email.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		if(email == false) {
	    			Login.setText("Login in Email");
	    			Login.setBackground(new Color(178, 49, 33));
	    		    Login.setForeground(Color.WHITE);
	    			facebook = false;
	    			twitter = false;
	    			email = true;
	    		}
			
	    	}
	    });
		p.add(Email);	
	}
/*
	public void facebookActivate() {
		if(facebook == false) {
			//Painel Login, Sign-up;
			JPanel p1 = new JPanel();
			
			//Login: e-mail, password;
			JButton Login = new JButton("Login in Facebook");
			Login.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
				}
			});
			p1.add(Login);
			frame.add(p1, BorderLayout.CENTER);
			
			facebook = true;
			twitter = false;
			email = false;
		}
		frameRef();
	}
	
	public void TwitterActivate() {
		if(twitter == false) {
			//Painel Login, Sign-up;
			JPanel p2 = new JPanel();
			
			//Login: e-mail, password;
			JButton Loginv2 = new JButton("Login in Twitter");
			Loginv2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
				}
			});
			p2.add(Loginv2);
			frame.add(p2, BorderLayout.CENTER);
			
			facebook = false;
			twitter = true;
			email = false;
		}
		frameRef();
	}
*/
	public void frameRef() {
		frame.invalidate();
		frame.validate();
		frame.repaint();
	}
	
	public static void main(String[] args) {
		Window w = new Window();
		w.open();
	}
}
