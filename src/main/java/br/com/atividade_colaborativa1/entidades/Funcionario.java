package br.com.atividade_colaborativa1.entidades;
public class Funcionario {
    private Long id;
	private String nomeCompleto;
	private String matricula;
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
    public String getMatricula() {
        return matricula;
    }
    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }
    @Override
    public String toString() {
        return "Funcionario [id=" + id + ", nomeCompleto=" + nomeCompleto + ", matricula=" + matricula + "]";
    }

    

}
