package GUI;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;



public class GmailLogin extends JPanel {
	
	private JTextField email;
	private JPasswordField password;
		
	public GmailLogin() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		JPanel panel1 = new JPanel();
		JPanel panel2 = new JPanel();
		JLabel label1 = new JLabel("Email: ");
		email = new JTextField();
		email.setColumns(20);
		JLabel label2 = new JLabel("Password");
		password = new JPasswordField();
		password.setColumns(20);
		panel1.add(label1);
		panel1.add(email);
		panel2.add(label2);
		panel2.add(password);
		add(panel1);
		add(panel2);
	}
	
	public String getEmail() {
		return email.getText();
	}
	
	public String getPassword() {
		return password.getText();
	}

}