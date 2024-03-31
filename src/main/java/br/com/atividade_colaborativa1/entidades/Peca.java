package br.com.atividade_colaborativa1.entidades;

public class Peca implements Comparable<Peca>{
	private long id;
	private long codPeca;
	private float valor;
	private String nome;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	public long getCodPeca() {
		return codPeca;
	}
	public void setCodPeca(long codPeca) {
		this.codPeca = codPeca;
	}
	public float getValor() {
		return valor;
	}
	public void setValor(float valor) {
		this.valor = valor;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	@Override
	public String toString() {
		return "Peca [id=" + id +", codPeca=" + codPeca + ", valor=" + valor + ", nome=" + nome + "]";
	}
	@Override
	public int compareTo(Peca outraPeca) {
		return Long.compare(this.id,outraPeca.id);
	}
}
