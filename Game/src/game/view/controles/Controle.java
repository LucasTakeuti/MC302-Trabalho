package game.view.controles;

import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Controle extends JPanel {
	private JTextField powerField;
	private JTextField angleField;
	private JButton shootButton;
	
	public Controle() {
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		
		powerField = new JTextField("22", 5);
		angleField = new JTextField("55", 5);
		
		shootButton = new JButton("Atirar");
		
		add(powerField);
		add(angleField);
		add(shootButton);
	}
	
	public double getPower() {
		return Double.parseDouble(this.powerField.getText());
	}
	
	public double getAngle() {
		return Double.parseDouble(this.angleField.getText());
	}

	public void bttnFocus() {
		shootButton.requestFocus();
	}
	
	public void addListener(ActionListener actionListener) {
		shootButton.addActionListener(actionListener);
	}
}
