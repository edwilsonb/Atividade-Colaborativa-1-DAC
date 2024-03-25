package br.com.atividade_colaborativa1.conexao;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * FabricaDeConexao
 */
public class FabricaDeConexao {

    private final String URL = "jdbc:postgresql://localhost:5432/Caminho do seu Coração";
	private final String USER = "nomeDoUser";
	private final String PASSWORD = "senha";
	private final String DRIVER_CLASS = "org.postgresql.Driver";
	
	private Connection createConnection()  throws ClassNotFoundException{
		
		Connection connection = null;
		
		try {
			Class.forName(DRIVER_CLASS);
			connection = DriverManager.getConnection(URL, USER, PASSWORD);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return connection;
		
	}
	
	public Connection getConnection() {
		
		try {
			return createConnection();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}