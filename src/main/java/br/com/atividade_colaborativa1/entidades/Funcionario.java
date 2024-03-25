package br.com.atividade_colaborativa1.entidades;
public class Funcionario {
    private Long id;
	private String nomeCompleto;
	private String endereco;
	private String cargo;
	
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getNomeCompleto() {
        return nomeCompleto;
    }
    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }
    
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public String getCargo() {
		return cargo;
	}
	public void setCargo(String cargo) {
		this.cargo = cargo;
	}
	@Override
	public String toString() {
		return "Funcionario [id=" + id + ", nomeCompleto=" + nomeCompleto + ", endereco=" + endereco + ", cargo="
				+ cargo + "]";
	}
	
    

}
