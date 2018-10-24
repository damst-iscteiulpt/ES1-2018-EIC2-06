package GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class Window {
	
	private JFrame frame;
	
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
		//Menu
		JMenuBar menuBar = new JMenuBar();
		frame.add(menuBar, BorderLayout.NORTH);
		JMenu menu = new JMenu("Menu");
		menuBar.add(menu);
		
		//Painel Redes Sociais, etc..
		JPanel p = new JPanel();
		frame.add(p, BorderLayout.WEST);
		p.setLayout(new GridLayout(3,1));
		
		JButton Facebook = new JButton("Facebook");
		p.add(Facebook);
		
		JButton Twitter = new JButton("Twitter");
		p.add(Twitter);
		
		JButton Email = new JButton ("E-mail");
		p.add(Email);
		
		//Painel Login, Sign-up;
		//Login: e-mail, password;
		//Sign-up:e-mail, password, confirm password; 
		JPanel p1 = new JPanel();
		frame.add(p1, BorderLayout.CENTER);
		
		JButton Login = new JButton("Login");
		Login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		p1.add(Login, BorderLayout.CENTER);
		
		JButton Sign = new JButton("Sign-Up");
		Sign.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		p1.add(Sign, BorderLayout.CENTER);
		
	}
	
	public static void main(String[] args) {
		Window w = new Window();
		w.open();
	}
}
