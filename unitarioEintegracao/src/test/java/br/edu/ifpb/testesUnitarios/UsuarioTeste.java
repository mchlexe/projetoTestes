package br.edu.ifpb.testesUnitarios;

import org.junit.Assert;
import org.junit.Test;

public class UsuarioTeste {

    @Test
    public void criarUsuario() {
        Usuario usuario = new Usuario("dallasgreen", "little666");

        Assert.assertEquals("dallasgreen", usuario.getNome());
        Assert.assertEquals("little666", usuario.getSenha());
    }

    @Test
    public void fazerLogin() throws UsuarioException {
        Usuario usuario = new Usuario("dallasgreen", "little666");

        usuario.login(usuario.getSenha());

        Assert.assertEquals(true,usuario.getLogado());
        }
    }