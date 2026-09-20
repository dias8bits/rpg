package domain;

import java.util.ArrayList;
import java.util.List;

public class Player {


    private String nome;
    private int stamina;
    private int maxStamina;
    private int atqBase;
    private List<Item> inventario;

    public Player(String nome) {
        this.nome = nome;
        this.maxStamina = 100;
        this.stamina = maxStamina;
        this.atqBase = 10;
        this.inventario = new ArrayList<>();
    }

    public void usarStamina(int quantidade) {
        stamina -= quantidade;
        if (stamina < 0) {
            stamina = 0;
        }
    }

    public void restaurarStamina(int quantidade) {
        stamina += quantidade;
        if (stamina > maxStamina) {
            stamina = maxStamina;
        }
    }
    
    public void adicionarItem(Item item) {
        inventario.add(item);
    }

    public int getAtaqueTotal() {
        int atqTotal = atqBase;
        for (Item item : inventario) {
            atqTotal += item.getAtqBonus();
        }
        return atqTotal;
    }

    public String getNome() {
        return nome;
    }

    public int getStamina() {
        return stamina;
    }

    public int getgetMaxStamina() {
        return maxStamina;
    }

    public List<Item> getInventario() {
        return inventario;
    }
}