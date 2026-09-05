package domain;

public class BodyPart {

    private String name;
    private String description;
    private int fallChance;
    private int staminaCost;
    private Item item;

    public BodyPart(String name, String description, int fallChance, int staminaCost, Item item) {
        this.name = name;
        this.description = description;
        this.fallChance = fallChance;
        this.staminaCost = staminaCost;
        this.item = item;
    }

    public String getName() { return name; }
    public String getDescription() { return description; }
    public int getFallChance() { return fallChance; }
    public int getStaminaCost() { return staminaCost; }
    public Item getItem() { return item; }
}