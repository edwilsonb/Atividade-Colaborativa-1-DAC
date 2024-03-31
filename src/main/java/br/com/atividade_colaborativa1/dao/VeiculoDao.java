package br.com.atividade_colaborativa1.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.atividade_colaborativa1.conexao.FabricaDeConexao;
import br.com.atividade_colaborativa1.entidades.Veiculo;

public class VeiculoDao {

    private Connection connection;
    
    public VeiculoDao() {
        try {
            this.connection = new FabricaDeConexao().getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void getErrorMessage(String method, String err) {
        System.out.println("Erro ao " + method + " o Veículo: " + err);
    }
    
    
    public void criaTabelaVeiculo() {
        String sql = "CREATE TABLE IF NOT EXISTS veiculo ("
                + "id SERIAL PRIMARY KEY,"
                + "id_cliente BIGINT,"
                + "placa VARCHAR(20) NOT NULL,"
                + "modelo VARCHAR(50),"
                + "marca VARCHAR(50)"
                + ")";
        
        try {
            PreparedStatement stmt = this.connection.prepareStatement(sql);
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    public void inserirVeiculo(Veiculo veiculo) {
        String sql = "INSERT INTO veiculo (id_cliente, placa, modelo, marca) VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setLong(1, veiculo.getId_Cliente());
            stmt.setString(2, veiculo.getPlaca());
            stmt.setString(3, veiculo.getModelo());
            stmt.setString(4, veiculo.getMarca());
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    public List<Veiculo> all() {
		
		try {
			List<Veiculo> veiculos = new ArrayList<Veiculo>();
			PreparedStatement stmt = this.connection.prepareStatement("select * from veiculo");
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				Veiculo veiculo = new Veiculo();
				veiculo.setId(rs.getLong("id"));
				veiculo.setId_Cliente(rs.getLong("id_cliente"));
				veiculo.setPlaca(rs.getString("placa"));
				veiculo.setModelo(rs.getString("modelo"));
				veiculo.setMarca(rs.getString("marca")); 
				
				veiculos.add(veiculo);
			}
			rs.close();
			stmt.close();
			return veiculos;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}	
	} 
    
    public Veiculo buscarPorId(long id) {
        try {
            PreparedStatement stmt = this.connection.prepareStatement("SELECT * FROM veiculo WHERE id=?");
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            Veiculo veiculo = new Veiculo();
            while (rs.next()) {
                veiculo.setId(rs.getLong("id"));
                veiculo.setId_Cliente(rs.getLong("id_cliente"));
                veiculo.setPlaca(rs.getString("placa"));
                veiculo.setModelo(rs.getString("modelo"));
                veiculo.setMarca(rs.getString("marca"));
            }
            return veiculo;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    public Veiculo atualizarVeiculo(Veiculo veiculo, long id) {
        try {
            PreparedStatement stmt = this.connection.prepareStatement("UPDATE veiculo SET id_cliente = ?, placa = ?, modelo = ?, marca = ? WHERE id = ?");
            stmt.setLong(1, veiculo.getId_Cliente());
            stmt.setString(2, veiculo.getPlaca());
            stmt.setString(3, veiculo.getModelo());
            stmt.setString(4, veiculo.getMarca());
            stmt.setLong(5, id);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected == 0) {
                throw new RuntimeException("Nenhum veículo foi atualizado. Verifique o ID fornecido.");
            }
            return veiculo;
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }
    
    public void removerVeiculo(long id) {
        try {
            PreparedStatement stmt = this.connection.prepareStatement("DELETE FROM veiculo WHERE id=?");
            stmt.setLong(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }
    
}
