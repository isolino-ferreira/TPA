package ifes.tpa.testes;

import ifes.tpa.mergeexterno.Facade;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.ifes.tpa.merger.BufferIn;
import br.ifes.tpa.merger.Cliente;

public class TesteFacade {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	//@Test
	public void test() {
		
		Facade.run();
		
	}
	
	@Test
	public void lerSaida(){
		String path = "/home/isolino/Documentos/TPA/1o_trabalho/workspace/merge-externo/arquivos/";
		BufferIn b = new BufferIn(path + "1round_4", 100);
		
		Cliente c;
		while((c = (Cliente) b.getProximo())!=null)
			System.out.println(c);
		
		
	}

}
