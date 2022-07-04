package br.com.pedro.calculadora;
import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;

@SuppressWarnings("serial")
public class Botao extends JButton{
	
	Botao(String valor, Color cor){
		setText(valor);
		setOpaque(true);
		setBackground(cor);
		setFont(new Font("SansSerif",Font.BOLD,23));
		setBorder(BorderFactory.createLineBorder(Color.WHITE));
		setForeground(Color.WHITE);
	}

}
