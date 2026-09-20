package domain;

import java.util.List;

public class Colosso {
    
    private String nome;
    private int stamina;
    private List<BodyPart> partesDoCorpo;
    private int progresso;

    public Colosso(String nome, int stamina, int progresso, List<BodyPart> partesDoCorpo) {
        this.nome = nome;
        this.stamina = stamina;
        this.progresso = progresso;
        this.partesDoCorpo = partesDoCorpo;
    }

    public String getNome() {
        return nome;
    }

    public int getStamina() {
        return stamina;
    }

    public int getProgresso(){
        return progresso;
    }

    public List<BodyPart> getPartesDoCorpo() {
        return partesDoCorpo;
    }

}
