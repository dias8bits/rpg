package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Game {

    Scanner scanner = new Scanner(System.in);
    Random random = new Random();
    Player player;

    List<BodyPart> bodyParts;
    int colossusHp;

    public void start() {

        System.out.println("-- COLOSSO --");
        System.out.print("Nome: ");

        player = new Player(scanner.nextLine());

        setupColossus();

        System.out.println("\num colosso de pedra se ergue à sua frente");
        System.out.println("o ponto fraco fica na cabeça. Cuidado com a stamina");

        for (int i = 0; i < bodyParts.size() - 1; i++) {

            boolean survived = part(bodyParts.get(i));

            if (!survived) {
                gameOver();
                return;
            }
        }

        finalAttack(bodyParts.get(bodyParts.size() - 1));
    }

    private void setupColossus() {

        bodyParts = new ArrayList<>();

        bodyParts.add(new BodyPart("pé", "a base do colosso, coberta de musgo", 5, 15,
                new Item("adaga enferrujada", 3)));

        bodyParts.add(new BodyPart("perna", "pedra rachada, dá pra se firmar nas fendas", 10, 20,
                new Item("espada de pedra", 5)));

        bodyParts.add(new BodyPart("torso", "o peito do colosso sobe e desce devagar", 15, 20,
                new Item("elmo antigo", 4)));

        bodyParts.add(new BodyPart("ombro", "vento forte aqui em cima", 20, 20,
                new Item("lâmina sagrada", 10)));

        bodyParts.add(new BodyPart("Cabeça", "o ponto fraco do colosso", 0, 0, null));

        colossusHp = 25;
    }

    private boolean part(BodyPart part) {

        boolean collected = false;
        boolean rested = false;

        System.out.println("\n-- " + part.getName() + " --");
        System.out.println(part.getDescription());

        while (true) {

            System.out.println("\nstamina: " + player.getStamina() + "/" + player.getMaxStamina());
            System.out.println("\n1 - subir para a proxima parte");

            if (part.getItem() != null && !collected) {
                System.out.println("2 - procurar item");
            }

            if (!rested) {
                System.out.println("3 - Descansar (+20 stamina)");
            }

            System.out.print("\nescolha: ");

            int option = scanner.nextInt();

            if (option == 1) {

                if (player.getStamina() < part.getStaminaCost()) {
                    System.out.println("\nstamina insuficiente pra subir. descanse primeiro.");
                    continue;
                }

                player.useStamina(part.getStaminaCost());

                int roll = random.nextInt(100);

                if (roll < part.getFallChance()) {
                    System.out.println("\nvoce perde o equilíbrio e cai...");
                    return false;
                }

                System.out.println("\nvoce sobe com sucesso!");
                return true;

            } else if (option == 2 && part.getItem() != null && !collected) {

                player.addItem(part.getItem());
                collected = true;

                System.out.println("\nvoce encontrou: " + part.getItem().getName()
                        + " ( +" + part.getItem().getAttackBonus() + " de ataque)");

            } else if (option == 3) {

                player.rest(20);
                rested = true;
                System.out.println("\nvoce descansa e recupera stamina");

            } else {
                System.out.println("\nopcao invalida");
            }

            if (player.getStamina() <= 0) {
                System.out.println("\nsuas forcas acabam e você despenca...");
                return false;
            }
        }
    }

    private void finalAttack(BodyPart head) {

        System.out.println("\n-- " + head.getName() + " --");
        System.out.println(head.getDescription());
        System.out.println("\nvoce chegou ao ponto fraco");
        System.out.println("eeu ataque total: " + player.getTotalAttack());

        System.out.println("\n1 - atacar");
        System.out.print("\nescolha: ");

        scanner.nextInt();

        int damage = player.getTotalAttack();

        if (damage >= colossusHp) {
            System.out.println("\no golpe atinge o ponto fraco. O colosso desaba");
            System.out.println("\n*** VITORIA! ***");
        } else {
            System.out.println("\no golpe não foi forte o suficiente (precisava de " + colossusHp
                    + ", causou " + damage + ")");
            System.out.println("o colosso sacode e voce despenca");
            System.out.println("\n*** DERROTA — procure armas mais fortes no corpo do colosso ***");
        }
    }

    private void gameOver() {
        System.out.println("\nvoce cai de uma grande altura...");
        System.out.println("\n*** GAME OVER ***");
    }
}