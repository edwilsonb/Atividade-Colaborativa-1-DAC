package br.com.atividade_colaborativa1.conexao;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * FabricaDeConexao
 */
public class FabricaDeConexao {

    public Connection getConnection() throws ClassNotFoundException{
        
        try {
            Class.forName("Caminho do sei coração");
            return DriverManager.getConnection(null, null, null);
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
    
}