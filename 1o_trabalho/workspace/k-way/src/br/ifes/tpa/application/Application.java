package br.ifes.tpa.application;

import java.util.ArrayList;
import java.util.Iterator;

import br.ifes.tpa.gerador_de_registros.GeradorDeRegistros;
import br.ifes.tpa.merger.BufferIn;
import br.ifes.tpa.merger.BufferOut;
import br.ifes.tpa.merger.KWay;
import br.ifes.tpa.ordenador.Ordenador;

public class Application {

	public static void main(String[] args) {
		
		System.out.println(run());

	}
	
	public static String run(){
		
		Gerenciador ger = new Gerenciador();
		
		//Gerar Registros
		
		GeradorDeRegistros.gerarRegistros(ger.getPATH_ARQUIVOS() + "isolino.tpa", ger.getNUM_REGISTROS());
		
		ArrayList<String> partes = Ordenador.dividirArquivoPorMemoria(ger.getPATH_ARQUIVOS() + "isolino.tpa", ger.getMEMORIA());
		
		Iterator<Integer> ii = ger.getNiveisIterator();
		
		//System.out.println("POP"+partes);
		
		int nivel = 0;
		
		while(ii.hasNext()){
			
			int qntPartes = ii.next().intValue();
			
			if (qntPartes==0)
				qntPartes = partes.size();
			
			ArrayList<String> novasPartes = new ArrayList<>();
			
			for(int i = 0; i < partes.size();i = i+qntPartes){
				
				KWay kway = new KWay();
				
				String arquivoSaida = nivel + "" + i;
				
				for(int j = i; j < i+qntPartes; j++){
					
					kway.addBuffersIn(new BufferIn(ger.getPATH_ARQUIVOS() + partes.get(j), 10));
					
					partes.remove(j);
					
					partes.add(j, null);
					
					arquivoSaida += j;
					
				}
				
				novasPartes.add(arquivoSaida);
				
				kway.setBufferOut(new BufferOut(ger.getPATH_ARQUIVOS() + arquivoSaida));
				
				kway.run();
				
			}
			
			int loop = partes.size();
			
			for(int i = 0; i < loop; i++){
				
				if(partes.get(i) != null)
					
					novasPartes.add(partes.get(i));		
				
			} 
			
			partes = novasPartes;
			
			nivel++;
			
		}
		
		String arquivoFinal = null;
			
		if(partes.size()==1)
			
			arquivoFinal = ger.getPATH_ARQUIVOS() + partes.get(0);
		
		return arquivoFinal;
		
	}

}
