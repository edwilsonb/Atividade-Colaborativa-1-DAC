package br.com.atividade_colaborativa1.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.atividade_colaborativa1.conexao.FabricaDeConexao;
import br.com.atividade_colaborativa1.entidades.Servico;

public class ServicoDao {

    private Connection connection;
    
    public ServicoDao() {
        try {
            this.connection = new FabricaDeConexao().getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void getErrorMessage(String method, String err) {
        System.out.println("Erro ao " + method + " o Serviço: " + err);
    }
    
    public void criaTabelaServico() {
        String sql = "CREATE TABLE IF NOT EXISTS servico ("
                + "id SERIAL PRIMARY KEY,"
                + "tipo VARCHAR(100),"
                + "cod_servico BIGINT,"
                + "data VARCHAR(10),"
                + "descricao TEXT,"
                + "valor FLOAT"
                + ")";
        
        try {
            PreparedStatement stmt = this.connection.prepareStatement(sql);
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    public void inserirServico(Servico servico) {
        String sql = "INSERT INTO servico (tipo, cod_servico, data, descricao, valor) VALUES (?, ?, ?, ?, ?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, servico.getTipo());
            stmt.setLong(2, servico.getCodServico());
            stmt.setString(3, servico.getData());
            stmt.setString(4, servico.getDescricao());
            stmt.setFloat(5, servico.getValor());
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    public Servico buscarPorCodServico(long codServico) {
        try {
            PreparedStatement stmt = this.connection.prepareStatement("SELECT * FROM servico WHERE cod_servico=?");
            stmt.setLong(1, codServico);
            ResultSet rs = stmt.executeQuery();
            Servico servico = new Servico();
            while (rs.next()) {
                servico.setTipo(rs.getString("tipo"));
                servico.setCodServico(rs.getLong("cod_servico"));
                servico.setData(rs.getString("data"));
                servico.setDescricao(rs.getString("descricao"));
                servico.setValor(rs.getFloat("valor"));
            }
            return servico;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    public Servico atualizarServico(Servico servico) {
        try {
            PreparedStatement stmt = this.connection.prepareStatement("UPDATE servico SET tipo = ?, data = ?, descricao = ?, valor = ? WHERE cod_servico = ?");
            stmt.setString(1, servico.getTipo());
            stmt.setString(2, servico.getData());
            stmt.setString(3, servico.getDescricao());
            stmt.setFloat(4, servico.getValor());
            stmt.setLong(5, servico.getCodServico());
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected == 0) {
                throw new RuntimeException("Nenhum serviço foi atualizado. Verifique o código de serviço fornecido.");
            }
            return servico;
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }
    
    public void removerServico(long codServico) {
        try {
            PreparedStatement stmt = this.connection.prepareStatement("DELETE FROM servico WHERE cod_servico=?");
            stmt.setLong(1, codServico);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }
}
