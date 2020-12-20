package br.edu.ifpb.testesUnitarios;

import br.edu.ifpb.testesUnitarios.conexao.ConexaoException;
import br.edu.ifpb.testesUnitarios.conexao.ConexaoFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class UsuarioDAOImpl implements UsuarioDAO {

    private Connection conexao;

    public UsuarioDAOImpl() {
        try {
            this.conexao = ConexaoFactory.getConnection();
        } catch (ConexaoException e) {
            e.printStackTrace();
        }
    }

    public UsuarioDAOImpl(Connection conexao){
        this.conexao = conexao;
    }

    @Override
    public void criar(Usuario u) throws SQLException {
        String query = "INSERT INTO usuario(nome, senha) VALUES (?, ?)";
        PreparedStatement preparedStatement = this.conexao.prepareStatement(query);
        preparedStatement.setString(1, u.getNome());
        preparedStatement.setString(2, u.getSenha());
        preparedStatement.execute();
    }


    @Override
    public List<Usuario> listar() throws SQLException {
        String query = "SELECT * FROM usuario";
        List<Usuario> usuarios = new LinkedList<>();
        PreparedStatement preparedStatement = conexao.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();
        while(resultSet.next()) {
            Usuario usuario = new Usuario(
                    resultSet.getString(1),
                    resultSet.getString(2)
            );
            usuarios.add(usuario);
        }
        return usuarios;
    }

    @Override
    public void remover(Usuario u) throws SQLException {
        String query = "DELETE FROM usuario WHERE usuario.nome = ?";
        PreparedStatement preparedStatement = this.conexao.prepareStatement(query);
        preparedStatement.setString(1, u.getNome());
        preparedStatement.execute();
    }
}
