package br.ifes.tpa.testes;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.ifes.tpa.merger.BufferIn;

public class TesteBufferIn {

	private String PATH_ARQUIVO = "/home/isolino/Documentos/TPA/1o_trabalho/workspace/k-way/arquivos/isolino.tpa";
	
	@Before
	public void setUp() throws Exception {
	}

	//@Test
	public void testBufferIn() {
		BufferIn buffer = new BufferIn(PATH_ARQUIVO, 1);
		Assert.assertNotNull(buffer);
		//Assert.assertTrue(buffer.getRegistros().length==1);
	}
	
	//@Test
	public void testLerDoDisco(){
		BufferIn buffer = new BufferIn(PATH_ARQUIVO, 1);
		Assert.assertNotNull(buffer);
		
		Pessoa p1 = (Pessoa)buffer.getProximo();
		Assert.assertNotNull(p1);
		Assert.assertEquals(10,p1.getIdade());
		
		Assert.assertTrue(buffer.getRegistros().length==1);
		Assert.assertTrue(buffer.getPonteiro()==0);
		
		p1 = (Pessoa)buffer.getProximo();
		Assert.assertNotNull(p1);
		Assert.assertEquals(20,p1.getIdade());
		
		String str = (String)buffer.getProximo();
		Assert.assertNotNull(str);
		System.out.println(str);
		Assert.assertEquals("isolino3",str);
		
		buffer.fecharBUfferIn();
		
	}
	
	@Test
	public void testLerDoDiscoFinalDeArquivo(){
		
		//Criar arquivo com 3 objetos(registros)
		
		BufferIn buffer = new BufferIn(PATH_ARQUIVO, 4);
		
		for(int i=0;i<=4;i++){
			
			Object o = buffer.getProximo();
			
			if(i>=4){
				Assert.assertNull(o);
			}
		}

	}

}
