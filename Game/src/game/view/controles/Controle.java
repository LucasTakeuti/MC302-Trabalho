package game.view.controles;

import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Controle extends JPanel {
	private JTextField powerField;
	private JTextField angleField;
	private JButton shootButton;
	private JButton jumpButton;
	private JTextArea lifes;
	private JTextArea currentTurn;

	public Controle() {
		setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
		
		powerField = new JTextField("22", 5);
		angleField = new JTextField("55", 5);
		
		shootButton = new JButton("Atirar");
		jumpButton = new JButton("Pular");
		lifes = new JTextArea();
		lifes.setEditable(false);
		currentTurn = new JTextArea();
		currentTurn.setEditable(false);
		
		add(powerField);
		add(angleField);
		add(shootButton);
		add(jumpButton);
		add(lifes);
		add(currentTurn);
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
	
	public void addShootListener(ActionListener actionListener) {
		shootButton.addActionListener(actionListener);
	}
	
	public void addJumpListener(ActionListener actionListener) {
		jumpButton.addActionListener(actionListener);
	}
	
	public void setLifes(String vidas) {
		lifes.setText(vidas);
	}
	
	public void setCurrentTurn(String turn) {
		currentTurn.setText(turn);
	}
}
