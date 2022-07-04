package br.com.pedro.calculadora;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class Calculadora extends JFrame {

	Calculadora(){

        organizarLayout();
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(232, 322);
        

    }

    private void organizarLayout() {
    	
    	setLayout(new BorderLayout());
       
    
        Display display = new Display();
        display.setPreferredSize(new Dimension(232,60));
        add(display,BorderLayout.NORTH);
        
        Teclado teclado = new Teclado();
        add(teclado,BorderLayout.CENTER);

    }

    public static void main(String[] args) {
        Calculadora c1 = new Calculadora();
    }

}
