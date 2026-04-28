package estruturas;

import model.Chamado;
import model.Prioridade;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * FILA: organiza chamados aguardando atendimento.
 * Regras de prioridade: ALTA -> MEDIA -> BAIXA.
 */
public class FilaChamados {
    private final Queue<Chamado> filaAlta = new LinkedList<>();
    private final Queue<Chamado> filaMedia = new LinkedList<>();
    private final Queue<Chamado> filaBaixa = new LinkedList<>();

    public void enfileirar(Chamado chamado) {
        if (chamado.getPrioridade() == Prioridade.ALTA) {
            filaAlta.offer(chamado);
        } else if (chamado.getPrioridade() == Prioridade.MEDIA) {
            filaMedia.offer(chamado);
        } else {
            filaBaixa.offer(chamado);
        }
    }

    public Chamado desenfileirarProximo() {
        if (!filaAlta.isEmpty()) return filaAlta.poll();
        if (!filaMedia.isEmpty()) return filaMedia.poll();
        return filaBaixa.poll();
    }

    public boolean estaVazia() {
        return filaAlta.isEmpty() && filaMedia.isEmpty() && filaBaixa.isEmpty();
    }

    public int tamanhoTotal() {
        return filaAlta.size() + filaMedia.size() + filaBaixa.size();
    }

    public List<Chamado> listarEmOrdemAtendimento() {
        List<Chamado> todos = new ArrayList<>();
        todos.addAll(filaAlta);
        todos.addAll(filaMedia);
        todos.addAll(filaBaixa);
        return todos;
    }
}
