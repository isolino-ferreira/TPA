package ifes.tpa.mergeexterno;

import java.util.ArrayList;

import br.ifes.tpa.merger.BufferIn;
import br.ifes.tpa.merger.BufferOut;
import br.ifes.tpa.merger.Cliente;

public class MergeExterno {
	
	public static ArrayList<String> dividirArquivoEm2(String path, int numRegistros) {
			
			int tam = numRegistros / 2;
			int resto = numRegistros % 2;
			
			String pasta = path.substring(0, path.lastIndexOf("/")+1);
			
			BufferIn bi = new BufferIn(path , 100);
			BufferOut bo = new BufferOut(pasta + "m.ext.1");
			
			for(int i=0; i < tam + resto; i++){
				
				Object o = bi.getProximo();
				
				if(o!=null)
					bo.setProximo(o);
				
			}
			
			bo.fecharBufferOut();
			
			bo = new BufferOut(pasta + "m.ext.2");
			
			for(int i=0; i < tam; i++){
				
				Object o = bi.getProximo();
				
				if(o!=null)
					bo.setProximo(o);
				
			}
			
			bo.fecharBufferOut();
			
			ArrayList<String> l = new ArrayList<String>();
			l.add("m.ext.1");
			l.add("m.ext.2");
			
			return l;
			
		}

	public static String mergeExterno(String path1, String path2) {
		
		String pasta = path1.substring(0, path1.lastIndexOf("/")+1);
		String entrada1 = path1.substring( path1.lastIndexOf("/")+1);
		String entrada2 = path2.substring( path2.lastIndexOf("/")+1);
		
		String saida1 = null;
		String saida2 = null;
		
		BufferIn bi1;
		BufferIn bi2;
		BufferOut bo1;
		BufferOut bo2;
		
		int contExterno = 0;
		int contInterno = 0;
		
		do{
			
			bi1 = new BufferIn( pasta + entrada1, 100);
			bi2 = new BufferIn( pasta + entrada2, 100);
			
			saida1 = "1round_" + contExterno;
			bo1 = new BufferOut ( pasta + saida1 );
			
			saida2 = "2round_" + contExterno;
			bo2 = new BufferOut ( pasta + saida2 );
			
			contInterno = 0;
			
			boolean temRegistrosNosBuffersIn = true;
			
			int numRegistrosComparados = (int) Math.pow(2, contExterno);
			
			BufferOut bufferOutAtivo = null;
			
			while( temRegistrosNosBuffersIn ){
				
				if ( contInterno % 2 == 0)
					bufferOutAtivo = bo1;
				
				else
					bufferOutAtivo = bo2;
				
				Cliente c1 = null, c2 = null;
				
				int numLeiturasBufferIn1 = 0;
				int numLeiturasBufferIn2 = 0;
				
				for ( int i = 0; i < numRegistrosComparados * 2; i++){
					
					if (( c1 == null) && (numLeiturasBufferIn1 < numRegistrosComparados)){
						
						c1 = (Cliente)bi1.getProximo();
						numLeiturasBufferIn1++;
						
						if(c1 == null)
							temRegistrosNosBuffersIn = false;
						
					}
					
					if (( c2 == null) && (numLeiturasBufferIn2 < numRegistrosComparados)){
						
						c2 = (Cliente)bi2.getProximo();
						numLeiturasBufferIn2++;
						
						if(c2 == null)
							temRegistrosNosBuffersIn = false;
						
					}
					
					if ( c1 != null ) {
						
						if (c2 != null) {
							
							System.out.println("c1: " + c1.getSaldo() + "\nc2: " + c2.getSaldo());
							
							if(c1.compareTo(c2) < 0){
								
								bufferOutAtivo.setProximo(c1);
								c1 = null;
								
							}else{
								
								bufferOutAtivo.setProximo(c2);
								c2 = null;
								
							}
							
						}else{
							
							bufferOutAtivo.setProximo(c1);
							c1 = null;
							
						}
						
					}else if (c2 != null){
						
						bufferOutAtivo.setProximo(c2);
						c2 = null;
						
					}else{
						
						i = numRegistrosComparados * 2;
						
					}
					
					
					
				}
				
				contInterno++;
				
			}//while
			
			bi1.fecharBUfferIn();
			bi2.fecharBUfferIn();
			bo1.fecharBufferOut();
			bo2.fecharBufferOut();
			
			//apagarAquivo( pasta + entrada1);
			//apagarAquivo( pasta + entrada2);
			
			entrada1 = saida1;
			entrada2 = saida2;
			
			contExterno++;
			
		}while(contInterno!=1);
	
		return entrada1;
		
	}

}
