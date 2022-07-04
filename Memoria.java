package br.com.pedro.calculadora.modelo;

import java.util.ArrayList;
import java.util.List;

import br.com.pedro.calculadora.Display;

public class Memoria {
	
	private enum TipoComando{
		NUMERO,ZERAR,MULTIPLICACAO,SOMA,DIMINUICAO,DIVICAO,VIRGULAR,IGUALDADE,TROCA_VALOR;
	}
	private static final Memoria instancia = new Memoria();
	private String textoAtual = "";
	private String textoBuffer = "";
	private boolean substituir = false;  
	private TipoComando ultimoComando = null;
	private String troca = "-";
	
	List<MemoriaObservador> listaObservadores = new ArrayList<>();
	

	private Memoria() {
		
	}

	public static Memoria getInstancia() {
		return instancia;
	}
	public void adicionarObservado(MemoriaObservador observador) {
		listaObservadores.add(observador);
		
	
	}
	public String getTextoAtual() {
		return textoAtual.isEmpty() ? "0" : textoAtual ;
	
	}
	public void processarComando(String texto) {
		
		TipoComando tipoComando = detectarTipoComando(texto);
		
		if(tipoComando == null) {
			return;
		} if(tipoComando == TipoComando.ZERAR) {
			textoAtual = "";
			textoBuffer = "";
			substituir = false;
			ultimoComando = null;
		} else if (tipoComando == TipoComando.NUMERO ||tipoComando == TipoComando.VIRGULAR) {
			textoAtual = substituir ? texto : textoAtual + texto;
			substituir = false;
		} else if (tipoComando == TipoComando.TROCA_VALOR && textoAtual.contains("-")) {
			
			textoAtual = textoAtual.substring(1);
		}
		else if(tipoComando == TipoComando.TROCA_VALOR) {
			if(!textoAtual.contains("-") && !textoAtual.isEmpty()) {
				textoAtual = troca + textoAtual;
			}
		
		} 
		else {
			substituir = true;
			textoAtual = obterResultadooperacao();
			textoBuffer = textoAtual;
			ultimoComando = tipoComando;
			}
		
		
		listaObservadores.forEach(o -> o.valorAlterador(getTextoAtual()));
		
	}
	
	private String obterResultadooperacao() {
		if(ultimoComando == null || ultimoComando == TipoComando.IGUALDADE) {
			return textoAtual;
		}
		
		double numeroBuffer = Double.parseDouble(textoBuffer.replace(",", "."));
		double numeroAtual = Double.parseDouble(textoAtual.replace(",", "."));
		
		double resultado = 0;
		
		
		if(ultimoComando == TipoComando.SOMA) {
		resultado = numeroBuffer + numeroAtual;
		} else if (ultimoComando == TipoComando.DIMINUICAO) {
			resultado = numeroBuffer - numeroAtual;
		} else if (ultimoComando == TipoComando.MULTIPLICACAO) {
			resultado = numeroBuffer * numeroAtual;
		} else if(ultimoComando == TipoComando.DIVICAO) {
			resultado = numeroBuffer / numeroAtual;
		} 
		
		String resultadoString = Double.toString(resultado).replace(".", ",");
		boolean inteiro = resultadoString.endsWith(",0");
		
	
		
		return inteiro ? resultadoString.replace(",0", " ") : resultadoString;
	}

	private TipoComando detectarTipoComando(String texto) {
		if(textoAtual.isEmpty() && texto == "0") {
			return null;
		}
		try {
		Integer.parseInt(texto);
		return TipoComando.NUMERO;
		}
		catch(NumberFormatException e){
			if("AC".equals(texto)) {
				return TipoComando.ZERAR;
			} else if("+".equals(texto)) {
				return TipoComando.SOMA;
			} else if("-".equals(texto)) {
				return TipoComando.DIMINUICAO;
			} else if("X".equals(texto)) {
				return TipoComando.MULTIPLICACAO;
			} else if("/".equals(texto)){
				return TipoComando.DIVICAO;
			} else if ("=".equals(texto)) {
				return TipoComando.IGUALDADE;
			} else if("±".equals(texto)) {
				return TipoComando.TROCA_VALOR;
			} 
			else if(",".equals(texto) && !textoAtual.contains(",")) {
				return TipoComando.VIRGULAR;
			}
			
		}
		return null;
	}
}
