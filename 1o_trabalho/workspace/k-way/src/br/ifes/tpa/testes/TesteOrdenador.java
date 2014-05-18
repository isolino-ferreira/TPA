package br.ifes.tpa.testes;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import br.ifes.tpa.gerador_de_registros.GeradorDeRegistros;
import br.ifes.tpa.merger.BufferIn;
import br.ifes.tpa.merger.Cliente;
import br.ifes.tpa.ordenador.Ordenador;

public class TesteOrdenador {
	
	private String PATH_ARQUIVO = "/home/isolino/Documentos/TPA/1o_trabalho/workspace/k-way/arquivos/";
	private String PATH_TEMP = "";
	private final int KB = 1000;
	private final int MB = 1000*1000;
	private final int GB = 1000*1000*1000;
	
	@Before
	public void pastaTemp(){
			
		new File(PATH_ARQUIVO + "temp/").mkdir();
		
		PATH_TEMP = PATH_ARQUIVO + "temp/" + "teste_ordenador.tpa";	
		
	}
	
	@Test
	public void testDividirArquivoMenorQueMemoria(){
		
		// TamArquivo(n) = 423*n + 266
		// TamArquivo(2364) = 1.021.515 bytes > 1MB
		
		GeradorDeRegistros.gerarRegistros(PATH_TEMP, 50);
		
		Ordenador.dividirArquivoPorMemoria(PATH_TEMP, 500 * KB);
		
		BufferIn bi = new BufferIn(PATH_TEMP + "_part_0",10);
		
		//Lendo arquivo ordenado
		
		List<Cliente> lc = new ArrayList<>();
		
		Cliente cl;
		
		while((cl =(Cliente) bi.getProximo()) != null)
			lc.add(cl);
		
		System.out.println(" >> "+lc.size());
		
		Collections.sort(lc);
		
		//for(Cliente c: lc)
		//	System.out.println(c);
		
		
		
	}
	
	//@Test
	public void naoEhPossivel(){
		
		List<Cliente> lc = new ArrayList<>();
		
		GeradorDeRegistros.gerarRegistros(PATH_TEMP, 10);
		
		BufferIn bi = new BufferIn(PATH_TEMP,10);
		
		Cliente cl;
		
		while((cl =(Cliente) bi.getProximo()) != null)
			lc.add(cl);
		
		System.out.println(" >> "+lc.size());
		
		Collections.sort(lc);
		
		for(Cliente c: lc)
			System.out.println(c);
	}
}
