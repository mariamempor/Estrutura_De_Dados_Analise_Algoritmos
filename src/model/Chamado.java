package model;

public class Chamado {
    private final int id;
    private final String descricao;
    private final Prioridade prioridade;
    private StatusChamado status;
    private final Usuario usuario;
    private Tecnico tecnico;
    private final int tempoEstimadoMinutos;

    public Chamado(int id, String descricao, Prioridade prioridade, Usuario usuario, int tempoEstimadoMinutos) {
        this.id = id;
        this.descricao = descricao;
        this.prioridade = prioridade;
        this.status = StatusChamado.ABERTO;
        this.usuario = usuario;
        this.tempoEstimadoMinutos = tempoEstimadoMinutos;
    }

    public int getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    public Prioridade getPrioridade() {
        return prioridade;
    }

    public StatusChamado getStatus() {
        return status;
    }

    public void setStatus(StatusChamado status) {
        this.status = status;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public Tecnico getTecnico() {
        return tecnico;
    }

    public void setTecnico(Tecnico tecnico) {
        this.tecnico = tecnico;
    }

    public int getTempoEstimadoMinutos() {
        return tempoEstimadoMinutos;
    }

    @Override
    public String toString() {
        String tecnicoNome = (tecnico == null) ? "Nao atribuido" : tecnico.getNome();
        return String.format(
                "Chamado{id=%d, prioridade=%s, status=%s, usuario=%s, tecnico=%s, tempo=%d min, descricao='%s'}",
                id, prioridade, status, usuario.getNome(), tecnicoNome, tempoEstimadoMinutos, descricao
        );
    }
}
