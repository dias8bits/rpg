package domain;

public class Item {

    private String name;
    private int attackBonus;

    public Item(String name, int attackBonus) {
        this.name = name;
        this.attackBonus = attackBonus;
    }

    public String getName() { return name; }
    public int getAttackBonus() { return attackBonus; }
}