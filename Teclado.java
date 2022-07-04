package br.com.pedro.calculadora;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import br.com.pedro.calculadora.modelo.Memoria;

public class Teclado extends JPanel implements ActionListener{
	
	private final Color COR_CINZA_CLARO = new Color(87, 89, 88);
	private final Color COR_BRANCO = new Color(233, 233, 240);
	private final Color COR_PRETO = new Color(5,5,5);
	
	Teclado(){
		 GridBagLayout layout = new GridBagLayout(); // Criação de Layout para o JPanel
		GridBagConstraints c = new GridBagConstraints();
		c.fill = (GridBagConstraints.BOTH); // Preenche os espaços em branco do button
        setLayout(layout);
        c.weightx = 1; //Expandi para completar a janela
        c.weighty = 1;
        
        //Linha 1
        c.gridwidth = 2;
        adiconarBotao("AC", COR_CINZA_CLARO, c, 0, 0);
        c.gridwidth = 1;
        adiconarBotao("±", COR_CINZA_CLARO, c, 2, 0);
        adiconarBotao("/", COR_PRETO, c, 3, 0);
        //Linha 2
        adiconarBotao("7", COR_CINZA_CLARO, c, 0, 1);
        adiconarBotao("8", COR_CINZA_CLARO, c, 1, 1);
        adiconarBotao("9", COR_CINZA_CLARO, c, 2, 1);
        adiconarBotao("X", COR_PRETO, c, 3, 1);
        // Linha 2
        
        adiconarBotao("4", COR_CINZA_CLARO, c, 0, 2);
        adiconarBotao("5", COR_CINZA_CLARO, c, 1, 2);
        adiconarBotao("6", COR_CINZA_CLARO, c, 2, 2);
        adiconarBotao("-", COR_PRETO, c, 3, 2);
        //Linha 3
        adiconarBotao("3", COR_CINZA_CLARO, c, 0, 3);
        adiconarBotao("2", COR_CINZA_CLARO, c, 1, 3);
        adiconarBotao("1", COR_CINZA_CLARO, c, 2, 3);
        adiconarBotao("+", COR_PRETO, c, 3, 3);
        //Linha 4
        
        c.gridwidth = 2;
        adiconarBotao("0", COR_CINZA_CLARO, c, 0, 4);
        c.gridwidth = 1;
        adiconarBotao(",", COR_CINZA_CLARO, c, 2, 4);
        adiconarBotao("=", COR_PRETO, c, 3, 4);
        
        
        
     
        
        
    }
	public void adiconarBotao(String nome,Color cor, GridBagConstraints c, int x, int y) {
		c.gridx = x;
		c.gridy = y;
		Botao botao = new Botao(nome,cor);
		botao.addActionListener(this);
		add(botao, c);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton botao = (JButton) e.getSource();
		
		Memoria.getInstancia().processarComando(botao.getText());
		
		
		
		
	}

}
