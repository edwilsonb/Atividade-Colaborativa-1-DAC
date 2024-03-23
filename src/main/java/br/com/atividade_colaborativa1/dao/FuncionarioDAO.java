package br.com.atividade_colaborativa1.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
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

    public Funcionario byId(Long ind){ 

        return null;
    }
}
