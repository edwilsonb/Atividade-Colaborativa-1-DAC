package br.com.atividade_colaborativa1.entidades;

import java.util.List;

public class Venda implements Comparable<Venda>{
	private long id;
	private long id_Veiculo;
	private long codServico;
	private float valorVenda;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

    public long getId_Veiculo() {
		return id_Veiculo;
	}

	public void setId_Veiculo(long id_Veiculo) {
		this.id_Veiculo = id_Veiculo;
	}

	public long getCodServico() {
        return codServico;
    }

 
    public void setCodServico(long codServico) {
        this.codServico = codServico;
    }

    public float getValorVenda() {
        return valorVenda;
    }

    public void setValorVenda(float valorVenda) {
        this.valorVenda = valorVenda;
    }

	@Override
	public String toString() {
		return "Venda [id=" + id + ", id_Veiculo=" + id_Veiculo + ", codServico=" + codServico + ", valorVenda=" + valorVenda + "]";
	}

	@Override
	public int compareTo(Venda outraVenda) {
		
		return Long.compare(this.id, outraVenda.id) ;
	}
    
}
