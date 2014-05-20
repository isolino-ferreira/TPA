package ifes.tpa.mergeexterno;

import java.util.ArrayList;
import br.ifes.tpa.gerador_de_registros.GeradorDeRegistros;

public class Facade {
	
	private static String path = "/home/isolino/Documentos/TPA/1o_trabalho/workspace/merge-externo/arquivos/";
	
	private static int numRegistros = 23;
	
	public static void run(){
		
		//GeradorDeRegistros.gerarRegistros(path + "isolino.tpa", numRegistros);
		
		GeradorDeRegistros.gerarRegistrosViciados(path + "isolino.tpa", numRegistros);
		
		ArrayList<String> arquivos = MergeExterno.dividirArquivoEm2(path + "isolino.tpa", numRegistros);
		
		String saida = MergeExterno.mergeExterno(path + arquivos.get(0), path + arquivos.get(1));
		
		System.out.println( path + "\n" + saida);
		
	}

	

}
