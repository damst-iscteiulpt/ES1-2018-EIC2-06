package GUI;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;



public class TwitterLogin extends JPanel {
	
	private JTextField key1, key2, key3, key4;
		
	public TwitterLogin() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		JPanel panel1 = new JPanel();
		JPanel panel2 = new JPanel();
		JPanel panel3 = new JPanel();
		JPanel panel4 = new JPanel();
		JLabel label1 = new JLabel("AuthConsumerKey");
		key1 = new JTextField();
		key1.setColumns(20);
		JLabel label2 = new JLabel("AuthConsumerSecret");
		key2 = new JTextField();
		key2.setColumns(20);
		JLabel label3 = new JLabel("AcessToken");
		key3 = new JTextField();
		key3.setColumns(20);
		JLabel label4 = new JLabel("AcessTokenSecret");
		key4 = new JTextField();
		key4.setColumns(20);
		panel1.add(label1);
		panel1.add(key1);
		panel2.add(label2);
		panel2.add(key2);
		panel3.add(label3);
		panel3.add(key3);
		panel4.add(label4);
		panel4.add(key4);
		add(panel1);
		add(panel2);
		add(panel3);
		add(panel4);
	}
	
	public String getKey1() {
		return key1.getText();
	}

	public String getKey2() {
		return key2.getText();
	}

	public String getKey3() {
		return key3.getText();
	}

	public String getKey4() {
		return key4.getText();
	}

}