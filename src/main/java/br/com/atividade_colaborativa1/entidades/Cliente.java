package br.com.atividade_colaborativa1.entidades;

public class Cliente implements Comparable<Cliente> {
	
	private Long id;
	private String nome;
	private String endereco;
	private String dataCadastro;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public String getDataCadastro() {
		return dataCadastro;
	}
	public void setDataCadastro(String dataCadastro) {
		this.dataCadastro = dataCadastro;
	}
	@Override
	public String toString() {
		return "Cliente [id=" + id + ", nome=" + nome + ", endereco=" + endereco + ", dataCadastro=" + dataCadastro
				+ "]";
	}
	@Override
	public int compareTo(Cliente outroId) {
		return Long.compare(this.id, outroId.id);
	}
}
