package br.ifes.tpa.merger;

public abstract class Buffer {
	
	private int tam;
	private int ponteiro;
	private Object[] registros;
	
	public void setTam(int tam){
		this.tam = tam;
	}

	public int getPonteiro() {
		return ponteiro;
	}

	public void setPonteiro(int ponteiro) {
		this.ponteiro = ponteiro;
	}

	public Object[] getRegistros() {
		return registros;
	}

	public void setRegistros(Object[] registros) {
		this.registros = registros;
	}

	public int getTam() {
		return tam;
	}
	
	

}
