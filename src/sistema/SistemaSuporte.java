package sistema;

import estruturas.ArvoreChamados;
import estruturas.FilaChamados;
import estruturas.ListaUsuarios;
import estruturas.PilhaHistorico;
import model.Chamado;
import model.Prioridade;
import model.StatusChamado;
import model.Tecnico;
import model.Usuario;

import java.util.ArrayList;
import java.util.List;

public class SistemaSuporte {
    // Estruturas obrigatórias
    private final FilaChamados filaChamados = new FilaChamados();        // FILA
    private final PilhaHistorico pilhaHistorico = new PilhaHistorico();   // PILHA
    private final ListaUsuarios listaUsuarios = new ListaUsuarios();      // LISTA LIGADA
    private final ArvoreChamados arvoreChamados = new ArvoreChamados();   // ÁRVORE BINÁRIA

    private final List<Tecnico> tecnicos = new ArrayList<>();

    private int sequenciaUsuario = 1;
    private int sequenciaChamado = 1000;

    private int totalAtendidos = 0;
    private int somaTempoAtendimento = 0;

    public SistemaSuporte() {
        carregarDadosIniciais();
    }

    private void carregarDadosIniciais() {
        cadastrarTecnico("Marina", "Redes");
        cadastrarTecnico("Carlos", "Hardware");
        cadastrarTecnico("Ana", "Sistemas Operacionais");

        Usuario u1 = cadastrarUsuario("João Silva", "Financeiro");
        Usuario u2 = cadastrarUsuario("Fernanda Lima", "RH");
        Usuario u3 = cadastrarUsuario("Pedro Souza", "Comercial");

        abrirChamado("Sem acesso à internet no setor", Prioridade.ALTA, u1, 25);
        abrirChamado("Impressora não responde", Prioridade.MEDIA, u2, 20);
        abrirChamado("Atualização de antivírus", Prioridade.BAIXA, u3, 15);
    }

    public Usuario cadastrarUsuario(String nome, String setor) {
        Usuario usuario = new Usuario(sequenciaUsuario++, nome, setor);
        listaUsuarios.adicionar(usuario);
        return usuario;
    }

    public Tecnico cadastrarTecnico(String nome, String especialidade) {
        Tecnico tecnico = new Tecnico(tecnicos.size() + 1, nome, especialidade);
        tecnicos.add(tecnico);
        return tecnico;
    }

    public Chamado abrirChamado(String descricao, Prioridade prioridade, Usuario usuario, int tempoEstimadoMinutos) {
        Chamado chamado = new Chamado(sequenciaChamado++, descricao, prioridade, usuario, tempoEstimadoMinutos);
        filaChamados.enfileirar(chamado);
        arvoreChamados.inserir(chamado);
        return chamado;
    }

    public Chamado atenderProximoChamado(int idTecnico) {
        Chamado proximo = filaChamados.desenfileirarProximo();
        if (proximo == null) {
            return null;
        }

        Tecnico tecnico = buscarTecnicoPorId(idTecnico);
        if (tecnico == null && !tecnicos.isEmpty()) {
            tecnico = tecnicos.get(0);
        }

        proximo.setStatus(StatusChamado.EM_ATENDIMENTO);
        proximo.setTecnico(tecnico);

        proximo.setStatus(StatusChamado.FINALIZADO);
        pilhaHistorico.registrar(proximo);

        totalAtendidos++;
        somaTempoAtendimento += proximo.getTempoEstimadoMinutos();

        return proximo;
    }

    public Chamado buscarChamadoPorId(int id) {
        return arvoreChamados.buscarPorId(id);
    }

    public List<Chamado> listarChamadosEmEspera() {
        return filaChamados.listarEmOrdemAtendimento();
    }

    public List<Chamado> listarHistorico() {
        return pilhaHistorico.listarDoTopo();
    }

    public List<Usuario> listarUsuarios() {
        return listaUsuarios.listarTodos();
    }

    public Usuario buscarUsuarioPorId(int id) {
        return listaUsuarios.buscarPorId(id);
    }

    public List<Tecnico> listarTecnicos() {
        return tecnicos;
    }

    public Tecnico buscarTecnicoPorId(int id) {
        for (Tecnico tecnico : tecnicos) {
            if (tecnico.getId() == id) {
                return tecnico;
            }
        }
        return null;
    }

    public int getTotalEmEspera() {
        return filaChamados.tamanhoTotal();
    }

    public int getTotalAtendidos() {
        return totalAtendidos;
    }

    public double getTempoMedioAtendimento() {
        if (totalAtendidos == 0) {
            return 0;
        }
        return (double) somaTempoAtendimento / totalAtendidos;
    }

    public List<Chamado> listarChamadosOrdenadosPorId() {
        return arvoreChamados.listarEmOrdem();
    }
}
