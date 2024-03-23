package br.com.atividade_colaborativa1.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.atividade_colaborativa1.conexao.FabricaDeConexao;
import br.com.atividade_colaborativa1.entidades.Funcionario;

public class FuncionarioDAO {

    private Connection connection;

    
    public FuncionarioDAO ()throws ClassNotFoundException{
        this.connection = new FabricaDeConexao().getConnection();
    }

    public void insereFuncionario (Funcionario funcionario){

        String query  = "INSERT INTO funcionario ( nome_completo, matricula ) values (?, ?)";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, funcionario.getNomeCompleto());
            statement.setString(2, funcionario.getMatricula());

            statement.execute();

            statement.close();
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public Funcionario byId(Long id){

        String query = "SELECT * FROM funcionario WHERE id=?";
        try {
            PreparedStatement statement = this.connection.prepareStatement(query);
            statement.setLong(1, id);
            ResultSet resultado = statement.executeQuery();

            Funcionario funcionario = new Funcionario();

            while (resultado.next()) {
                funcionario.setId(resultado.getLong("id"));
                funcionario.setNomeCompleto(resultado.getString("nome_completo"));
                funcionario.setMatricula(resultado.getString("Matricula")); 
            }
            return funcionario;


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
