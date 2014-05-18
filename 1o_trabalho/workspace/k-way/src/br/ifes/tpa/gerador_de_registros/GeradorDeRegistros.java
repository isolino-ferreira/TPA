package br.ifes.tpa.gerador_de_registros;

import br.ifes.tpa.merger.BufferOut;
import br.ifes.tpa.merger.Cliente;

public class GeradorDeRegistros {
	
	public static void gerarRegistros(int tam){
		
		gerarRegistros("/home/isolino/Documentos/TPA/1o_trabalho/workspace/k-way/arquivos/isolino.tpa",	tam	);
	}
	
	public static void gerarRegistros(String path, int tam){
		
		BufferOut bufferOut = new BufferOut(path);
		
		float saldo;
		
		for(int i = 0; i < tam; i++){
			
			saldo = (float) ((Math.random()*1000) - (Math.random()*500));
			
			bufferOut.setProximo(new Cliente(saldo));
			
		}
			
		bufferOut.fecharBufferOut();
	}	
	

}
