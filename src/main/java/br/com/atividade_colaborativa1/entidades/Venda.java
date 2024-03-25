package br.com.atividade_colaborativa1.entidades;

import java.util.List;

public class Venda {
	private long codVenda;
	private long id_Veiculo;
	private long codServico;
	private List<Peca> pecas;
	private float valorVenda;
	

    public long getCodVenda() {
        return codVenda;
    }

    public void setCodVenda(long codVenda) {
        this.codVenda = codVenda;
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


    public List<Peca> getPecas() {
        return pecas;
    }

    public void setPecas(List<Peca> pecas) {
        this.pecas = pecas;
    }

    public float getValorVenda() {
        return valorVenda;
    }

    public void setValorVenda(float valorVenda) {
        this.valorVenda = valorVenda;
    }

	@Override
	public String toString() {
		return "Venda [codVenda=" + codVenda + ", id_Veiculo=" + id_Veiculo + ", codServico=" + codServico + ", pecas="
				+ pecas + ", valorVenda=" + valorVenda + "]";
	}

    
}
