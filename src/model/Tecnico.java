package model;

public class Tecnico {
    private final int id;
    private final String nome;
    private final String especialidade;

    public Tecnico(int id, String nome, String especialidade) {
        this.id = id;
        this.nome = nome;
        this.especialidade = especialidade;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    @Override
    public String toString() {
        return String.format("Tecnico{id=%d, nome='%s', especialidade='%s'}", id, nome, especialidade);
    }
}
