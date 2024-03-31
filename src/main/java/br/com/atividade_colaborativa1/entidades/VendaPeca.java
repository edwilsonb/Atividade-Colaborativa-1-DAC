package br.com.atividade_colaborativa1.entidades;


public class VendaPeca {
    private long id;
    private long idVenda;
    private long idPeca;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getIdVenda() {
        return idVenda;
    }

    public void setIdVenda(long idVenda) {
        this.idVenda = idVenda;
    }

    public long getIdPeca() {
        return idPeca;
    }

    public void setIdPeca(long idPeca) {
        this.idPeca = idPeca;
    }

    @Override
    public String toString() {
        return "VendaPeca [id=" + id + ", idVenda=" + idVenda + ", idPeca=" + idPeca + "]";
    }
}

