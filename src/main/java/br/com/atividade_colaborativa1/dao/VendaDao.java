package br.com.atividade_colaborativa1.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.atividade_colaborativa1.conexao.FabricaDeConexao;
import br.com.atividade_colaborativa1.entidades.Venda;

public class VendaDao {

    private Connection connection;

    public VendaDao() {
        try {
            this.connection = new FabricaDeConexao().getConnection();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao criar conexão com o banco de dados", e);
        }
    }

    public void criaTabelaVenda() {
        String sql = "CREATE TABLE IF NOT EXISTS venda (" +
                "id SERIAL PRIMARY KEY," +
                "id_veiculo BIGINT NOT NULL," +
                "cod_servico BIGINT NOT NULL," +
                "valor_venda FLOAT NOT NULL)";

        try {
            PreparedStatement stmt = this.connection.prepareStatement(sql);
            stmt.execute();
            stmt.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void insereVenda(Venda venda) {
        String sql = "INSERT INTO venda (id_veiculo, cod_servico, valor_venda) VALUES (?,?,?)";

        try {
            PreparedStatement stmt = this.connection.prepareStatement(sql);

            stmt.setLong(1, venda.getId_Veiculo());
            stmt.setLong(2, venda.getCodServico());
            stmt.setFloat(3, venda.getValorVenda());
            stmt.execute();
            stmt.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Venda byId(Long id) {
        String sql = "SELECT * FROM venda WHERE id=?";

        try {
            PreparedStatement stmt = this.connection.prepareStatement(sql);
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();

            Venda venda = new Venda();
            while (rs.next()) {
                venda.setId(id);
                venda.setId_Veiculo(rs.getLong("id_veiculo"));
                venda.setCodServico(rs.getLong("cod_servico"));
                venda.setValorVenda(rs.getFloat("valor_venda"));
            }
            return venda;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Venda> all() {
        try {
            List<Venda> vendas = new ArrayList<>();
            PreparedStatement stmt = this.connection.prepareStatement("SELECT * FROM venda");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Venda venda = new Venda();
                venda.setId(rs.getLong("id"));
                venda.setId_Veiculo(rs.getLong("id_veiculo"));
                venda.setCodServico(rs.getLong("cod_servico"));
                venda.setValorVenda(rs.getFloat("valor_venda"));

                vendas.add(venda);
            }
            rs.close();
            stmt.close();
            return vendas;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void atualizar(Venda venda, long id) {
        String sql = "UPDATE venda SET id_veiculo=?, cod_servico=?, valor_venda=? WHERE id=?";

        try {
            PreparedStatement stmt = this.connection.prepareStatement(sql);
            stmt.setLong(1, venda.getId_Veiculo());
            stmt.setLong(2, venda.getCodServico());
            stmt.setFloat(3, venda.getValorVenda());
            stmt.setLong(4, id);
            stmt.execute();
            stmt.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void excluir(long id) {
        try {
            PreparedStatement stmt = this.connection.prepareStatement("DELETE FROM venda WHERE id=?");
            stmt.setLong(1, id);
            stmt.execute();
            stmt.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
