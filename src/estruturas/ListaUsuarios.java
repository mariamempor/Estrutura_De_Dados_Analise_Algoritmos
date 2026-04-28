package estruturas;

import model.Usuario;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * LISTA LIGADA: armazenamento de usuários cadastrados no sistema.
 */
public class ListaUsuarios {
    private final LinkedList<Usuario> usuarios = new LinkedList<>();

    public void adicionar(Usuario usuario) {
        usuarios.add(usuario);
    }

    public Usuario buscarPorId(int id) {
        for (Usuario usuario : usuarios) {
            if (usuario.getId() == id) {
                return usuario;
            }
        }
        return null;
    }

    public List<Usuario> listarTodos() {
        return Collections.unmodifiableList(usuarios);
    }

    public boolean estaVazia() {
        return usuarios.isEmpty();
    }
}
