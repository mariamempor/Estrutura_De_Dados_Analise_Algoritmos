package estruturas;

import model.Chamado;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * PILHA: histórico de chamados já atendidos.
 * O último chamado atendido é o primeiro a ser exibido.
 */
public class PilhaHistorico {
    private final Stack<Chamado> historico = new Stack<>();

    public void registrar(Chamado chamadoFinalizado) {
        historico.push(chamadoFinalizado);
    }

    public boolean estaVazia() {
        return historico.isEmpty();
    }

    public int tamanho() {
        return historico.size();
    }

    public List<Chamado> listarDoTopo() {
        List<Chamado> copia = new ArrayList<>(historico);
        List<Chamado> resultado = new ArrayList<>();
        for (int i = copia.size() - 1; i >= 0; i--) {
            resultado.add(copia.get(i));
        }
        return resultado;
    }
}
