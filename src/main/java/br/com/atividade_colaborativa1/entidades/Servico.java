package br.com.atividade_colaborativa1.entidades;

public class Servico {
	private String tipo;
	private long codServico;
	private String data;
	private String descricao;
	private float valor;
	
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public long getCodServico() {
		return codServico;
	}
	public void setCodServico(long codServico) {
		this.codServico = codServico;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public float getValor() {
		return valor;
	}
	public void setValor(float valor) {
		this.valor = valor;
	}
	@Override
	public String toString() {
		return "Servico [tipo=" + tipo + ", codServico=" + codServico + ", data=" + data + ", descricao=" + descricao
				+ ", valor=" + valor + "]";
	}
	
}
