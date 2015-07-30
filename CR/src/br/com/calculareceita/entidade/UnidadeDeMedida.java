package br.com.calculareceita.entidade;

public enum UnidadeDeMedida {

	KG(1,"kg"),
	G(2,"g"),
	L(3,"l"),
	ML(4,"ml");
	public int valor;
	public String descricao;
	
	UnidadeDeMedida(int valor, String descricao) {
		this.valor = valor;
		this.descricao = descricao;
	}

	public int getValor() {
		return valor;
	}

	public void setValor(int valor) {
		this.valor = valor;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
	
