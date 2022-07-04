package br.com.pedro.calculadora;


import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

import br.com.pedro.calculadora.modelo.Memoria;
import br.com.pedro.calculadora.modelo.MemoriaObservador;

public class Display extends JPanel implements MemoriaObservador{
	
	
	private final JLabel label;
	 Display(){
		 Memoria.getInstancia().adicionarObservado(this);
		 setBackground(new Color(25, 25, 25));
	       label = new JLabel(Memoria.getInstancia().getTextoAtual());
	       label.setForeground(Color.WHITE);
	       label.setFont(new Font("SansSerif",Font.BOLD,28));
	       setLayout(new FlowLayout(FlowLayout.RIGHT,9,15));
	       add(label);
	    }
	@Override
	public void valorAlterador(String novoValor) {
		label.setText(novoValor);
		
	}
	    
}
