package br.com.atividade_colaborativa1.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.atividade_colaborativa1.conexao.FabricaDeConexao;
import br.com.atividade_colaborativa1.entidades.Peca;

public class PecaDao {

    private Connection connection;
    
    public PecaDao() {
        try {
            this.connection = new FabricaDeConexao().getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void getErrorMessage(String method, String err) {
        System.out.println("Erro ao " + method + " a Peça: " + err);
    }
    
    public void criaTabelaPeca() {
        String sql = "CREATE TABLE IF NOT EXISTS peca ("
                + "id SERIAL PRIMARY KEY,"
                + "cod_peca BIGINT UNIQUE,"
                + "valor FLOAT,"
                + "nome VARCHAR(100)"
                + ")";
        
        try {
            PreparedStatement stmt = this.connection.prepareStatement(sql);
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    public void inserirPeca(Peca peca) {
        String sql = "INSERT INTO peca (cod_peca, valor, nome) VALUES (?, ?, ?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setLong(1, peca.getCodPeca());
            stmt.setFloat(2, peca.getValor());
            stmt.setString(3, peca.getNome());
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    public List<Peca> all() { 
		
		try {
			List<Peca> pecas = new ArrayList<Peca>(); 
			PreparedStatement stmt = this.connection.prepareStatement("select * from peca");
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				Peca peca = new Peca();
				peca.setId(rs.getLong("id"));
				peca.setCodPeca(rs.getLong("cod_peca"));
				peca.setValor(rs.getFloat("valor"));
				peca.setNome(rs.getString("nome"));				
				pecas.add(peca); 
			}
			rs.close();
			stmt.close();
			return pecas;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}	
	}
    
    public Peca byId(long id) {
        try {
            PreparedStatement stmt = this.connection.prepareStatement("SELECT * FROM peca WHERE id=?");
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            Peca peca = new Peca();
            while (rs.next()) {
            		peca.setId(rs.getLong("id"));
                peca.setCodPeca(rs.getLong("cod_peca"));
                peca.setValor(rs.getFloat("valor"));
                peca.setNome(rs.getString("nome"));
            }
            return peca;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    
    public Peca byCodPeca(long codPeca) {
        try {
            PreparedStatement stmt = this.connection.prepareStatement("SELECT * FROM peca WHERE cod_peca=?");
            stmt.setLong(1, codPeca);
            ResultSet rs = stmt.executeQuery();
            Peca peca = new Peca();
            while (rs.next()) {
            		peca.setId(rs.getLong("id"));
                peca.setCodPeca(rs.getLong("cod_peca"));
                peca.setValor(rs.getFloat("valor"));
                peca.setNome(rs.getString("nome"));
            }
            return peca;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    
    public Peca atualizarPeca(Peca peca, long id) {
        try {
            PreparedStatement stmt = this.connection.prepareStatement("UPDATE peca SET valor = ?, nome = ? WHERE id = ?");
            stmt.setFloat(1, peca.getValor());
            stmt.setString(2, peca.getNome());
            stmt.setLong(3, id);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected == 0) {
                throw new RuntimeException("Nenhuma peça foi atualizada. Verifique o código da peça fornecido.");
            }
            return peca;
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }
    
    public void removerPeca(long id) {
        try {
            PreparedStatement stmt = this.connection.prepareStatement("DELETE FROM peca WHERE id=?");
            stmt.setLong(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }
}
