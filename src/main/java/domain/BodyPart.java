package domain;

public class BodyPart {

    private String nome;
    private String descricao;
    private int chanceDeCair;
    private int custoDeStamina;
    private Item item;

    public BodyPart(String nome, String descricao, int chanceDeCair, int custoDeStamina, Item item) {
        this.nome = nome;
        this.descricao = descricao;
        this.chanceDeCair = chanceDeCair;
        this.custoDeStamina = custoDeStamina;
        this.item = item;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public int getChanceDeCair() {
        return chanceDeCair;
    }

    public int getCustoDeStamina() {
        return custoDeStamina;
    }

    public Item getItem(){
        return item;
    }
}