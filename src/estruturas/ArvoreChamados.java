package estruturas;

import model.Chamado;

import java.util.ArrayList;
import java.util.List;

/**
 * ÁRVORE BINÁRIA DE BUSCA (BST) manual por ID do chamado.
 */
public class ArvoreChamados {
    private static class No {
        Chamado chamado;
        No esquerdo;
        No direito;

        No(Chamado chamado) {
            this.chamado = chamado;
        }
    }

    private No raiz;

    public void inserir(Chamado chamado) {
        raiz = inserirRec(raiz, chamado);
    }

    private No inserirRec(No atual, Chamado chamado) {
        if (atual == null) {
            return new No(chamado);
        }

        if (chamado.getId() < atual.chamado.getId()) {
            atual.esquerdo = inserirRec(atual.esquerdo, chamado);
        } else if (chamado.getId() > atual.chamado.getId()) {
            atual.direito = inserirRec(atual.direito, chamado);
        } else {
            atual.chamado = chamado;
        }
        return atual;
    }

    public Chamado buscarPorId(int id) {
        No no = buscarRec(raiz, id);
        return no == null ? null : no.chamado;
    }

    private No buscarRec(No atual, int id) {
        if (atual == null || atual.chamado.getId() == id) {
            return atual;
        }
        if (id < atual.chamado.getId()) {
            return buscarRec(atual.esquerdo, id);
        }
        return buscarRec(atual.direito, id);
    }

    public List<Chamado> listarEmOrdem() {
        List<Chamado> chamados = new ArrayList<>();
        emOrdemRec(raiz, chamados);
        return chamados;
    }

    private void emOrdemRec(No no, List<Chamado> chamados) {
        if (no != null) {
            emOrdemRec(no.esquerdo, chamados);
            chamados.add(no.chamado);
            emOrdemRec(no.direito, chamados);
        }
    }
}
