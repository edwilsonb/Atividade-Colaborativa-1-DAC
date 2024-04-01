package br.com.atividade_colaborativa1.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
                + "cod_servico BIGINT UNIQUE,"
                + "valor FLOAT,"
                + "tipo VARCHAR(100),"
                + "data VARCHAR(10) NOT NULL,"
                + "descricao TEXT"
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
        String sql = "INSERT INTO servico (cod_servico, valor, tipo, data, descricao) VALUES (?, ?, ?, ?, ?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setLong(1, servico.getCodServico());
            stmt.setFloat(2, servico.getValor());
            stmt.setString(3, servico.getTipo());
            stmt.setString(4, servico.getData());
            stmt.setString(5, servico.getDescricao());
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    public List<Servico> all() { 
		
		try {
			List<Servico> servicos = new ArrayList<Servico>(); 
			PreparedStatement stmt = this.connection.prepareStatement("select * from servico");
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				Servico servico = new Servico();
				servico.setId(rs.getLong("id"));
				servico.setCodServico(rs.getLong("cod_servico"));
				servico.setValor(rs.getFloat("valor"));
				servico.setTipo(rs.getString("tipo"));
				servico.setData(rs.getString("data"));
				servico.setDescricao(rs.getString("descricao"));
				servicos.add(servico); 
			}
			rs.close();
			stmt.close();
			return servicos;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}	
	}
    
    public Servico byId(long id) {
        try {
            PreparedStatement stmt = this.connection.prepareStatement("SELECT * FROM servico WHERE id=?");
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            Servico servico = new Servico();
            while (rs.next()) {
            		servico.setId(rs.getLong("id"));
                servico.setCodServico(rs.getLong("cod_servico"));
                servico.setValor(rs.getFloat("valor"));
                servico.setTipo(rs.getString("tipo"));
                servico.setData(rs.getString("data"));
                servico.setDescricao(rs.getString("descricao"));
            }
            return servico;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    
    public Servico atualizarServico(Servico servico, long id) {
        try {
            PreparedStatement stmt = this.connection.prepareStatement("UPDATE servico SET valor = ?, tipo = ?, data = ?, descricao = ?, cod_servico = ?  WHERE id = ?");
            stmt.setFloat(1, servico.getValor());
            stmt.setString(2, servico.getTipo());
            stmt.setString(3, servico.getData());
            stmt.setString(4, servico.getDescricao());
            stmt.setLong(5, servico.getCodServico());
            stmt.setLong(6, id);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected == 0) {
                throw new RuntimeException("Nenhum serviço foi atualizado. Verifique o código do serviço fornecido.");
            }
            return servico;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    public void removerServico(long id) {
        try {
            PreparedStatement stmt = this.connection.prepareStatement("DELETE FROM servico WHERE id=?");
            stmt.setLong(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

	public Servico byCodServico(long codServico) {
		try {
            PreparedStatement stmt = this.connection.prepareStatement("SELECT * FROM servico WHERE cod_servico=?");
            stmt.setLong(1, codServico);
            ResultSet rs = stmt.executeQuery();
            Servico servico = new Servico();
            while (rs.next()) {
            		servico.setId(rs.getLong("id"));
                servico.setCodServico(rs.getLong("cod_servico"));
                servico.setValor(rs.getFloat("valor"));
                servico.setTipo(rs.getString("tipo"));
                servico.setData(rs.getString("data"));
                servico.setDescricao(rs.getString("descricao"));
            }
            return servico;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
