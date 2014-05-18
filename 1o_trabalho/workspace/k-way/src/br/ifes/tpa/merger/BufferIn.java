package br.ifes.tpa.merger;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class BufferIn extends Buffer {
	
	private ObjectInput arquivo;
	
	public BufferIn(String arquivo, int tam){
		
		setTam(tam);
		
		try {
			
			if(new File(arquivo).exists())
				this.arquivo = new ObjectInputStream(new FileInputStream(arquivo));
			
		} catch (FileNotFoundException e) {	e.printStackTrace();
		
		} catch (IOException e) {e.printStackTrace();}
	}
	
	public Object[] lerDoDisco(){
		
		//http://www.portugal-a-programar.pt/topic/50044-erro-ao-ler-ficheiro-arraylist-serialize/
		ArrayList<Object> listaRegistros = new ArrayList<>();
		
		try {
			
			for( int i=0; i<getTam(); i++){
				
				Object o = arquivo.readObject();
				
				listaRegistros.add(o);
				
			}
			
		} catch (EOFException e) { 
		
		} catch (IOException e) {	e.printStackTrace();
		
		} catch (ClassNotFoundException e) {	e.printStackTrace();
		
		} finally {
			
			setPonteiro(listaRegistros.size());
			
		}
		
		return listaRegistros.toArray();
		
	}
	
	public Object getProximo(){
		
		if(getPonteiro()==0){
			
			setRegistros(lerDoDisco());
			
			if(getRegistros().length==0){
				return null;
			}
		}
		
		int index = getRegistros().length - getPonteiro();
		
		setPonteiro(getPonteiro()-1);
		
		return getRegistros()[index];
	}
	
	public void fecharBUfferIn(){
		try {
			arquivo.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
