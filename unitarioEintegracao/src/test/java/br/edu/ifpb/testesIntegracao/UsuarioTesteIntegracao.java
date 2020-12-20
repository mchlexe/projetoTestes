package br.edu.ifpb.testesIntegracao;

import br.edu.ifpb.testesUnitarios.Usuario;
import br.edu.ifpb.testesUnitarios.UsuarioDAO;
import br.edu.ifpb.testesUnitarios.UsuarioDAOImpl;
import br.edu.ifpb.testesUnitarios.UsuarioException;
import org.dbunit.IDatabaseTester;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.testcontainers.containers.JdbcDatabaseContainer;
import org.testcontainers.containers.PostgreSQLContainer;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class UsuarioTesteIntegracao {

    private JdbcDatabaseContainer container;
    private IDatabaseTester databaseTester;
    private Connection conexao;
    private UsuarioDAO usuarioDAO;

    @Before
    public void setUp() throws Exception {
        if(container == null) {
            PostgreSQLContainer postgreSQLContainer;
            postgreSQLContainer = new PostgreSQLContainer().withUsername("postgresql").withPassword("123456").withDatabaseName("projeto-testes");
            container = postgreSQLContainer.withInitScript("postgres/create_schema.sql");
            container.start();
            conexao = DriverManager.getConnection(container.getJdbcUrl(), "postgresql", "123456");
        }
        configurarDBUnit();
        this.databaseTester.onSetup();
        usuarioDAO = new UsuarioDAOImpl(conexao);
    }

    private void configurarDBUnit() throws ClassNotFoundException, FileNotFoundException, DataSetException {
        IDataSet dataSet = new FlatXmlDataSetBuilder().build(new FileInputStream(("src/test/resources/postgres/usuarios_testset.xml")));
        this.databaseTester = new JdbcDatabaseTester("org.postgresql.Driver",
                container.getJdbcUrl(), "postgresql", "123456");
        this.databaseTester.setDataSet(dataSet);
        this.databaseTester.setSetUpOperation(DatabaseOperation.INSERT);
        this.databaseTester.setTearDownOperation(DatabaseOperation.DELETE);
    }

    @Test
    public void criarUsuarioDisponivel() throws SQLException {
        Usuario usuario = new Usuario("burie", "panic!now");
        usuarioDAO.criar(usuario);
    }

    @Test (expected = SQLException.class)
    public void criarUsuarioIndisponivel() throws SQLException {
        Usuario usuario = new Usuario("lynngunn", "giveMe60s");
        usuarioDAO.criar(usuario);
    }

    @Test
    public void removerUsuarioDisponivel() throws SQLException {
        Usuario usuario = new Usuario("lynngunn", "giveMe60s");
        usuarioDAO.remover(usuario);
    }

    @After
    public void finalizar() throws Exception {
        databaseTester.onTearDown();
        conexao.close();
    }
}
