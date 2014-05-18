package br.ifes.tpa.testes;

import org.junit.Assert;
import org.junit.Test;

import br.ifes.tpa.merger.BufferOut;
import br.ifes.tpa.merger.Cliente;

public class TesteBufferOut {

	private String PATH_ARQUIVO = "/home/isolino/Documentos/TPA/1o_trabalho/workspace/k-way/arquivos/isolino.tpa";
	
	@Test
	public void testBufferOut() {
		
		BufferOut buffer = new BufferOut(PATH_ARQUIVO);
		Assert.assertNotNull(buffer);
		Assert.assertTrue(buffer.getRegistros().length==100);
		//fail("Not yet implemented");
	}
	
	@Test
	public void testSetProximo(){
		
		BufferOut buffer = new BufferOut(1,PATH_ARQUIVO);
		Assert.assertTrue(buffer.getPonteiro()==0);
		
		Cliente c = new Cliente(123);
		
		buffer.setProximo(c);
		buffer.setProximo(c);
		buffer.setProximo(c);
		
		buffer.fecharBufferOut();
		
	}
	
	//(expected=IOException.class)
	public void testSetProximoComExcecao(){
		//como dentro do buffer existe um try e catch ,, creio que o JUnit não captura a exceção
		BufferOut buffer = new BufferOut(1,PATH_ARQUIVO);
		buffer.fecharBufferOut();
		buffer.setProximo(new String("enche buffer"));
		buffer.setProximo(new String("erro"));
	}

}
