package GUI;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;



public class FacebookLogin extends JPanel {
	
	private JTextField acessToken;	
		
	public FacebookLogin() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		JPanel panel1 = new JPanel();
		JLabel label1 = new JLabel("AcessToken: ");
		acessToken = new JTextField();
		acessToken.setColumns(20);
		panel1.add(label1);
		panel1.add(acessToken);
		add(panel1);
	}
	
	public String getAcessToken() {
		return acessToken.getText();
	}

}