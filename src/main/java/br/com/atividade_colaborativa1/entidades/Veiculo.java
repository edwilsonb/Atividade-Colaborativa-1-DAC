package br.com.atividade_colaborativa1.entidades;

public class Veiculo implements Comparable<Veiculo> {
	private long id;
	private long id_Cliente;
	private String placa;
	private String modelo;
	private String marca;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getId_Cliente() {
		return id_Cliente;
	}
	public void setId_Cliente(long id_Cliente) {
		this.id_Cliente = id_Cliente;
	}
	public String getPlaca() {
		return placa;
	}
	public void setPlaca(String placa) {
		this.placa = placa;
	}
	public String getModelo() {
		return modelo;
	}
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	@Override
	public String toString() {
		return "Veiculo [id=" + id + ", id_Cliente=" + id_Cliente + ", placa=" + placa + ", modelo=" + modelo
				+ ", marca=" + marca + "]";
	}
	@Override
	public int compareTo(Veiculo outroVeiculo) {
		return Long.compare(this.id, outroVeiculo.id);
	}
	
}
