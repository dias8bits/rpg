package domain;

import java.util.ArrayList;
import java.util.List;

public class Player {

    private String name;
    private int stamina;
    private int maxStamina;
    private int baseAttack;
    private List<Item> items;

    public Player(String name) {
        this.name = name;
        this.maxStamina = 100;
        this.stamina = maxStamina;
        this.baseAttack = 10;
        this.items = new ArrayList<>();
    }

    public void useStamina(int amount) {
        stamina -= amount;
        if (stamina < 0) stamina = 0;
    }

    public void rest(int amount) {
        stamina += amount;
        if (stamina > maxStamina) stamina = maxStamina;
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public int getTotalAttack() {
        int total = baseAttack;
        for (Item item : items) {
            total += item.getAttackBonus();
        }
        return total;
    }

    public String getName() { return name; }
    public int getStamina() { return stamina; }
    public int getMaxStamina() { return maxStamina; }
    public List<Item> getItems() { return items; }
}