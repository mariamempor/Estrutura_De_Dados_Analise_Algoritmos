package model;

public class Usuario {
    private final int id;
    private final String nome;
    private final String setor;

    public Usuario(int id, String nome, String setor) {
        this.id = id;
        this.nome = nome;
        this.setor = setor;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getSetor() {
        return setor;
    }

    @Override
    public String toString() {
        return String.format("Usuario{id=%d, nome='%s', setor='%s'}", id, nome, setor);
    }
}
