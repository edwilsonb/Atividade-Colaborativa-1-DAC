package br.com.atividade_colaborativa1.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.atividade_colaborativa1.conexao.FabricaDeConexao;
import br.com.atividade_colaborativa1.entidades.Cliente;

public class ClienteDao {

	private Connection connection;
	
	public ClienteDao() {
	    try {
            this.connection = new FabricaDeConexao().getConnection();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao criar conexão com o banco de dados", e);
        }
    }
	
	public void criaTabelaCliente() {
		
		String sql = "CREATE TABLE IF NOT EXISTS cliente (" +
	             "id SERIAL PRIMARY KEY," +
	             "nome VARCHAR(100) NOT NULL," +
	             "endereco VARCHAR(100) NOT NULL," +
	             "data_cadastro VARCHAR(10) NOT NULL)";

		
		try {
			PreparedStatement stmt = this.connection.prepareStatement(sql);
			stmt.execute();
			stmt.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void insereCliente(Cliente cliente) {
		
		String sql = "insert into cliente (nome, endereco, data_cadastro) values (?,?,?)";
		
		try {
			PreparedStatement stmt = this.connection.prepareStatement(sql);
			
			stmt.setString(1, cliente.getNome());
			stmt.setString(2, cliente.getEndereco());
			stmt.setString(3, cliente.getDataCadastro());
			stmt.execute();
			stmt.close();
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public Cliente byId(Long id) {
		
		String sql = "select * from cliente where id=?";
		
		try {
			PreparedStatement stmt = this.connection.prepareStatement(sql);
			stmt.setLong(1, id);
			ResultSet rs = stmt.executeQuery();
			
			Cliente cliente = new Cliente();
			while (rs.next()) {
				cliente.setId(id);
				cliente.setNome(rs.getString("nome"));
				cliente.setEndereco(rs.getString("endereco"));
				cliente.setDataCadastro(rs.getString("data_cadastro"));
			}
			return cliente;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public List<Cliente> all() {
		
		try {
			List<Cliente> clientes = new ArrayList<Cliente>();
			PreparedStatement stmt = this.connection.prepareStatement("select * from cliente");
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				Cliente cliente = new Cliente();
				cliente.setId(rs.getLong("id"));
				cliente.setNome(rs.getString("nome"));
				cliente.setEndereco(rs.getString("endereco"));
				cliente.setDataCadastro(rs.getString("data_cadastro"));
				
				clientes.add(cliente);
			}
			rs.close();
			stmt.close();
			return clientes;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}	
	}
	
	public void atualizar(Cliente cliente, long id) {
		
		String sql = "update cliente set nome=?, endereco=?, data_cadastro=? where id=?";
		
		try {
				PreparedStatement stmt = this.connection.prepareStatement(sql);
				stmt.setString(1, cliente.getNome());
				stmt.setString(2, cliente.getEndereco());
				stmt.setString(3, cliente.getDataCadastro());
				stmt.setLong(4, id);
				stmt.execute();
				stmt.close();
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void excluir(long id) {
		
		try {			
				PreparedStatement stmt = this.connection.prepareStatement("delete from cliente where id=?");
				stmt.setLong(1, id);
				stmt.execute();
				stmt.close();
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
