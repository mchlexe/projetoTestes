package br.edu.ifpb.testesUnitarios;

import java.sql.SQLException;
import java.util.List;

public interface UsuarioDAO {

    void criar(Usuario u) throws SQLException;

    List<Usuario> listar() throws SQLException;

    void remover(Usuario u) throws SQLException;
}
