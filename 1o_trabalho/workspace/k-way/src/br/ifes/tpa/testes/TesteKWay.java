package br.ifes.tpa.testes;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.ifes.tpa.gerador_de_registros.GeradorDeRegistros;
import br.ifes.tpa.merger.BufferIn;
import br.ifes.tpa.merger.BufferOut;
import br.ifes.tpa.merger.Cliente;
import br.ifes.tpa.merger.KWay;
import br.ifes.tpa.ordenador.Ordenador;

public class TesteKWay {
	
	private String PATH_ARQUIVO = "/home/isolino/Documentos/TPA/1o_trabalho/workspace/k-way/arquivos/";
	private String PATH_TEMP = "";
	private final int KB = 1000;
	private final int MB = 1000*1000;
	private final int GB = 1000*1000*1000;

	@Before
	public void setUp() throws Exception {
		
		new File(PATH_ARQUIVO + "temp/").mkdir();
		
		PATH_TEMP = PATH_ARQUIVO + "temp/" + "teste_ordenador.tpa";
		
		// TamArquivo(n) = 423*n + 266
		// TamArquivo(2364) = 1.021.515 bytes > 1MB
				
		GeradorDeRegistros.gerarRegistros(PATH_TEMP, 50);
				
		Ordenador.dividirArquivoPorMemoria(PATH_TEMP, 6 * KB);
		
	}

	@After
	public void tearDown() throws Exception {
	}


	
	@Test
	public void testComputar(){
		
		//instaciar KWay e setar os buffers
		
		KWay k_way = new KWay();
		
		k_way.addBuffersIn(new BufferIn(PATH_TEMP + "_part_0",10));
		k_way.addBuffersIn(new BufferIn(PATH_TEMP + "_part_1",10));
		k_way.addBuffersIn(new BufferIn(PATH_TEMP + "_part_2",10));
		k_way.addBuffersIn(new BufferIn(PATH_TEMP + "_part_3",10));
		
		k_way.setBufferOut(new BufferOut(PATH_TEMP + "_merge_0"));
		
		k_way.run();
		
		BufferIn bi = new BufferIn(PATH_TEMP + "_merge_0", 50);
		
		Cliente c = null;
		int cont =0;
		while((c = (Cliente)bi.getProximo()) != null){
			
			System.out.println(cont+++" - " + c);
			
		}
			
	}

}
