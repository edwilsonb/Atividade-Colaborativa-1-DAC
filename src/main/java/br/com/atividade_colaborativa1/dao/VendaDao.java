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
	        }
	    }
	    
	    public void getErrorMessage(String method, String err) {
	        System.out.println("Erro ao " + method + " a Venda: " + err);
	    }
	    
	    public void criaTabelaVenda() {
	        String sql = "CREATE TABLE IF NOT EXISTS venda ("
	                + "id SERIAL PRIMARY KEY,"
	                + "cod_venda BIGINT UNIQUE,"
	                + "id_veiculo BIGINT UNIQUE,"
	                + "cod_servico BIGINT,"
	                + "valor_venda FLOAT"
	                + ")";
	        
	        try {
	            PreparedStatement stmt = this.connection.prepareStatement(sql);
	            stmt.execute();
	            stmt.close();
	        } catch (SQLException e) {
	            throw new RuntimeException(e);
	        }
	    }
	    
	    public void inserirVenda(Venda venda) {
	        String sql = "INSERT INTO venda (cod_venda, id_veiculo, cod_servico, valor_venda) VALUES (?, ?, ?, ?)";
	        try {
	            PreparedStatement stmt = connection.prepareStatement(sql);
	            stmt.setLong(1, venda.getCodVenda());
	            stmt.setLong(2, venda.getId_Veiculo());
	            stmt.setLong(3, venda.getCodServico());
	            stmt.setFloat(4, venda.getValorVenda());
	            stmt.execute();
	            stmt.close();
	        } catch (SQLException e) {
	            throw new RuntimeException(e);
	        }
	    }
	    
	    public List<Venda> all() {       
			
			try {
				List<Venda> vendas = new ArrayList<Venda>();    
				PreparedStatement stmt = this.connection.prepareStatement("select * from venda");
				ResultSet rs = stmt.executeQuery(); 
				
				while (rs.next()) {
					Venda venda = new Venda();
					venda.setId(rs.getLong("id"));
					venda.setId_Veiculo(rs.getLong("id_veiculo"));
					venda.setCodVenda(rs.getLong("cod_venda"));
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
	    
	    public Venda buscarPorCodVenda(long codVenda) {
	        try {
	            PreparedStatement stmt = this.connection.prepareStatement("SELECT * FROM venda WHERE cod_venda=?");
	            stmt.setLong(1, codVenda);
	            ResultSet rs = stmt.executeQuery();
	            Venda venda = new Venda(); 
	            while (rs.next()) {
	            	    venda.setId(rs.getLong("id")); 
	                venda.setCodVenda(rs.getLong("cod_venda"));
	                venda.setId_Veiculo(rs.getLong("id_veiculo"));
	                venda.setCodServico(rs.getLong("cod_servico"));
	                venda.setValorVenda(rs.getFloat("valor_venda"));
	            }
	            return venda;
	        } catch (SQLException e) {
	            throw new RuntimeException(e);
	        }
	    }
	    
	    public Venda atualizarVenda(Venda venda) {
	        try {
	            PreparedStatement stmt = this.connection.prepareStatement("UPDATE venda SET id_veiculo = ?, cod_servico = ?, valor_venda = ? WHERE cod_venda = ?");
	            stmt.setLong(1, venda.getId_Veiculo());
	            stmt.setLong(2, venda.getCodServico());
	            stmt.setFloat(3, venda.getValorVenda());
	            stmt.setLong(4, venda.getCodVenda());
	            int rowsAffected = stmt.executeUpdate();
	            if (rowsAffected == 0) {
	                throw new RuntimeException("Nenhuma venda foi atualizada. Verifique o código de venda fornecido.");
	            }
	            return venda;
	        } catch (SQLException e) {
	            throw new RuntimeException();
	        }
	    }
	    
	    public void removerVenda(long codVenda) {
	        try {
	            PreparedStatement stmt = this.connection.prepareStatement("DELETE FROM venda WHERE cod_venda=?");
	            stmt.setLong(1, codVenda);
	            stmt.executeUpdate();
	        } catch (SQLException e) {
	            throw new RuntimeException();
	        }
	    }
	    
	}