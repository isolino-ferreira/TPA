package br.ifes.tpa.testes;

import static org.junit.Assert.*;


import org.junit.Test;

import br.ifes.tpa.gerador_de_registros.GeradorDeRegistros;
import br.ifes.tpa.merger.BufferIn;

public class TesteGeradoraDeRegistros {

	private String PATH_ARQUIVO = "/home/isolino/Documentos/TPA/1o_trabalho/workspace/k-way/arquivos/isolino.tpa";
	
	
	@Test
	public void testGerarRegistros(){
		
		GeradorDeRegistros.gerarRegistros(50);
		
		BufferIn buffer = new BufferIn(PATH_ARQUIVO,100);
		
		Object obj;
		
		int cont = 0;
		
		while((obj = buffer.getProximo()) != null){
			
			cont++;
			
		}
		
		assertNotEquals(1704, cont);
		
	}


}
