package domain;

public class Item {

    private int raridade;
    private String nome;
    private int atqBonus;

    public Item(int raridade, String nome, int atqBonus) {
        this.raridade = raridade;
        this.nome = nome;
        this.atqBonus = atqBonus;
    }

    public String getNome() {
        return nome;
    }

    public int getRaridade() {
        return raridade;
    }

    public int getAtqBonus() {
        return atqBonus;
    }
}