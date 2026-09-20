package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Game {
    Scanner scanner = new Scanner(System.in);
    Random random = new Random();
    Player player;
    boolean modoFacil;

    Colosso colosso;

    public void start() {

        int progressoAtual = 1;
        
        System.out.println("\n========================================");
        System.out.println("       A LENDA DOS COLOSSOS");
        System.out.println("========================================");
        System.out.println("Seja bem-vindo ao jogo A Lenda dos Colossos! Você, nobre senhor, está à procura de salvar sua amada, que morreu de uma doença congênita. \nCabe a você matar vários gigantes para tentar salvá-la, mas isso não é garantia de sucesso. \nProcure sempre por itens e descanse o máximo que puder para chegar à cabeça dos gigantes e derrotá-los. \nTenha algo em mente: SUAS ESCOLHAS NÃO SÃO GARANTIA DE NADA. BOA SORTE!");
        modoFacil = escolherModo();
        System.out.print("Digite o nome do seu personagem: ");

        player = new Player(verificadorDeNome());
        while (progressoAtual <= 3) {
            fasesColossos(progressoAtual);
            
            System.out.println("\n----------------------------------------");
            System.out.println("Um " + colosso.getNome() + " surge a sua frente!");
            System.out.println("O ponto fraco fica na cabeça. Cuidado com a sua stamina.");
            
            
            List<BodyPart> partes = colosso.getPartesDoCorpo();
            
            for(int i = 0; i < partes.size() - 1; i++) {
            boolean conseguirSubir = subirColosso(partes.get(i));

            if (!conseguirSubir) {
                    gameOver();
                    return;
            }

        }

            if (!AtqFinal(partes.get(partes.size() - 1))) {
                gameOver();
                return;
            }
            progressoAtual++;
        }

        finalizarJogo();
    }

    private boolean escolherModo() {
        while (true) {
            System.out.println("\n----------------------------------------");
            System.out.println("           MODO DE JOGO");
            System.out.println("----------------------------------------");
            System.out.println("  1 - Fácil: você sempre vence os colossos");
            System.out.println("  2 - Normal: o resultado depende dos dados");
            System.out.print("\nEscolha uma opção: ");

            try {
                int opcao = Integer.parseInt(scanner.nextLine());

                if (opcao == 1) {
                    System.out.println("Modo fácil selecionado.");
                    return true;
                }

                if (opcao == 2) {
                    System.out.println("Modo normal selecionado.");
                    return false;
                }
            } catch (NumberFormatException e) {
                System.out.println("Apenas números!");
            }

            System.out.println("Opção inválida. Digite 1 ou 2.");
        }
    }

    private String verificadorDeNome() {
        while (true) {
            String nome = scanner.nextLine().trim();

            if (!nome.isEmpty()) {
                return nome;
            }

            System.out.println("O nome não pode ficar vazio.");
            System.out.print("Digite o nome do seu personagem: ");
        }
    }

    private void finalizarJogo() {
        int rolagemFinal = random.nextInt(100) + 1;

        System.out.println("\n========================================");
        System.out.println("       TODOS OS COLOSSOS DERROTADOS");
        System.out.println("========================================");
        System.out.println("Dado do destino: " + rolagemFinal);

        if (rolagemFinal <= 33) {
            System.out.println("\n*** FINAL 1: Você tenta salvar sua amada, porém não consegue ***");
            System.out.println("Sua amada estava morta e não conseguiu acordar após a derrota dos colossos.");
            System.out.println("Seu desejo de vê-la salva foi em vão, e você se torna um colosso.");
        } else if (rolagemFinal <= 66) {
            System.out.println("\n*** FINAL 2: Você consegue salvar sua amada, mas não sobrevive ***");
            System.out.println("Ao ver que sua amada não acordava, você sente um imenso frio no fundo da alma.");
            System.out.println("Quando sua vida se esvai, sua amada acorda e encontra você morto.");
        } else {
            System.out.println("\n*** FINAL 3: Você consegue salvar sua amada ***");
            System.out.println("Você vê sua amada acordar, e os dois saem das masmorras.");
            System.out.println("Juntos e felizes!");
            System.out.println("FIM");
        }
    }
    
        private void fasesColossos(int fase) {
        switch (fase) {
            case 1 -> {
            List<BodyPart> parte = new ArrayList<>();
            parte.add(new BodyPart("pé", "a base do colosso, coberta de musgo", 1, 10,
                new Item(1, "adaga enferrujada", 3)));
            parte.add(new BodyPart("perna", "pedra rachada, é possível se firmar nas fendas", 5, 20,
                new Item(2, "espada de pedra", 5)));
            parte.add(new BodyPart("torso", "o peito do colosso sobe e desce devagar", 10, 20,
                new Item(3, "elmo antigo", 4)));
            parte.add(new BodyPart("ombro", "vento forte aqui em cima", 15, 30,
                new Item(4, "lâmina sagrada", 10)));
            parte.add(new BodyPart("mão", "dedos de pedra se movem lentamente", 20, 25,
                new Item(2, "anel de escalada", 7)));
            parte.add(new BodyPart("cabeça", "o ponto fraco do colosso", 0, 0, null));
            colosso = new Colosso("Gargântua de Pedra", 25, 1, parte);
            }
            case 2 -> {
            List<BodyPart> parte = new ArrayList<>();
            parte.add(new BodyPart("pé", "escamas escorregadias, segure firme!", 10, 25,
                new Item(1, "faca de osso", 4)));
            parte.add(new BodyPart("corpo", "penas gigantes voam ao seu redor", 15, 30,
                new Item(2, "amuleto dos ventos", 6)));
            parte.add(new BodyPart("asa", "o movimento das asas do pássaro é forte", 20, 40,
                new Item(3, "lança da tempestade", 12)));
            parte.add(new BodyPart("cauda", "as penas da cauda cortam o ar", 20, 35,
                new Item(1, "penas cortantes", 8)));
            parte.add(new BodyPart("pescoço", "o ponto fraco do pássaro", 0, 0,
                new Item(4, "lâminas tempestuosas", 15)));
            colosso = new Colosso("Pássaro da Tempestade", 40, 2, parte);
            }
            case 3 -> {
            List<BodyPart> parte = new ArrayList<>();
            parte.add(new BodyPart("barbatana inferior", "é escorregadia e a correnteza puxa forte", 15, 30,
                new Item(1, "garras vorazes", 7)));
            parte.add(new BodyPart("carapaça dorsal", "cracas afiadas machucam suas mãos", 15, 30,
                new Item(2, "pérola do abismo", 13)));
            parte.add(new BodyPart("nuca", "o monstro se debate violentamente na água", 25, 50,
                new Item(3, "arpão ancestral", 20)));
            parte.add(new BodyPart("barbatana dorsal", "a água gira com força ao redor das escamas", 20, 40,
                new Item(4, "tridente abissal", 18)));
            parte.add(new BodyPart("cauda", "a cauda do leviatã golpeia a correnteza", 25, 45,
                new Item(2, "escama protetora", 11)));
            parte.add(new BodyPart("escama reversa", "o ponto fraco do leviatã", 0, 0, null));
            colosso = new Colosso("Boss secreto: Leviatã das Profundezas", 50, 3, parte);
            }
            default -> System.out.println("Fim de jogo");
        }
    }   
    
    private boolean subirColosso(BodyPart parte){
        boolean ColetarItem = false;
        boolean dormir = false;
        int tentativaDormir = 0;

        boolean itemDisponivel = parte.getItem() != null;

        System.out.println("\n----------------------------------------");
        System.out.println("          " + parte.getNome().toUpperCase());
        System.out.println("----------------------------------------");
        System.out.println(parte.getDescricao());

        while (true) { 
            System.out.println("Jogador: " + player.getNome());
            System.out.println("Stamina: " + player.getStamina() + "/" + player.getMaxStamina());
            System.out.println("\n  1 - Subir para a próxima parte"); 

            if(itemDisponivel && !ColetarItem){
                System.out.println("  2 - Procurar item");
            }

            if (!dormir && tentativaDormir <= 3
                    && player.getStamina() < player.getMaxStamina()) {
                System.out.println("  3 - Descansar");
            }

            int opcao = 0;

            while(true){
                System.out.print("\nEscolha uma opção: ");

                try{
                    opcao = Integer.parseInt(scanner.nextLine());

                    if(opcao >= 1 && opcao <= 3){
                        break;
                    } else {
                        System.out.println("Opção inválida. Digite 1, 2 ou 3."); 
                    }

                }catch(NumberFormatException e){
                    System.out.println("Entrada inválida. Digite apenas um número.");
                }
            }

            if(opcao == 1){
                if (modoFacil) {
                    System.out.println("Você sobe com segurança no modo fácil!");
                    return true;
                }

                if (player.getStamina() < parte.getCustoDeStamina()){
                    System.out.println("\nStamina insuficiente para subir. Você precisa de "
                            + parte.getCustoDeStamina() + " e possui " + player.getStamina() + ".");
                    System.out.println("Descanse antes de tentar subir novamente.");
                    continue;
                }

                player.usarStamina(parte.getCustoDeStamina());
                
                int rolarDado100 = random.nextInt(100) + 1;
                System.out.println("Você rola o dado de 100 lados e tira: " + rolarDado100);
                int chanceDeCair = parte.getChanceDeCair() / 3;
                if (rolarDado100 <= chanceDeCair) {
                    System.out.println("\nVocê perde o equilíbrio e cai...");
                    return false;
                }

                System.out.println("Você sobe com sucesso!");
                return true;
            } else if(opcao == 2 && itemDisponivel && !ColetarItem){
                int rolarDado10 = random.nextInt(10) + 1;

                System.out.println("Você rola o dado de 10 lados e tira: " + rolarDado10);
                if (rolarDado10 < 7) {
                    System.out.println("\nVocê não conseguiu encontrar o item.");
                    continue;
                }

                player.adicionarItem(parte.getItem());
                ColetarItem = true;
                System.out.println("\nVocê encontrou: " + parte.getItem().getNome()
                    + " (+" + parte.getItem().getAtqBonus() + " de ataque).");
            }else if (opcao == 3) {
                int rolarDado10 = random.nextInt(10) + 1;

                System.out.println("Você rola o dado de 10 lados e tira: " + rolarDado10);
                if (rolarDado10 == 1) {
                    System.out.println("\nVocê não conseguiu descansar.");
                    tentativaDormir = 4;
                    continue;
                }else if(rolarDado10 <= 5){
                    System.out.println("Você tentou descansar, mas se assustou. Recuperou 5 de stamina.");
                    player.restaurarStamina(5);
                    tentativaDormir++;
                    System.out.println("Você tem mais " + tentativaDormir + "/3 para conseguir dormir" );
                }else{
                    
                    if(player.getStamina() <= 20){
                        player.restaurarStamina(80);
                    }else{
                        player.restaurarStamina(50);
                    }
                    dormir = true;
                    System.out.println("\nVocê descansou e recuperou stamina.");
                    tentativaDormir = 0;
                }
            }else{
                System.out.println("\nOpção inválida para esta parte.");
            }

            if (player.getStamina() <= 0){
                System.out.println("\nSuas forças acabam e você despenca...");
                return false;
            }
        }
    }

    private boolean AtqFinal(BodyPart cabeca){

        System.out.println("\n----------------------------------------");
        System.out.println("          " + cabeca.getNome().toUpperCase());
        System.out.println("----------------------------------------");
        System.out.println(cabeca.getDescricao());
        System.out.println("\nVocê chegou ao ponto fraco.");
        System.out.println("Stamina atual: " + player.getStamina() + "/" + player.getMaxStamina());
        System.out.println("Seu ataque total: " + player.getAtaqueTotal());

        while (true) {
            System.out.println("\n  1 - Atacar");

            try {
                int opcao = Integer.parseInt(scanner.nextLine());
                if (opcao == 1) {
                    break;
                }
                System.out.println("Opção inválida. Digite 1 para atacar.");
            } catch (NumberFormatException e) {
                System.out.println("Opção inválida. Digite 1 para atacar.");
            }
        }

        int dano = player.getAtaqueTotal();

        if(modoFacil || dano >= colosso.getStamina()){
            System.out.println("\nO golpe atinge o ponto fraco. O colosso desaba!");
            System.out.println("\n*** VITÓRIA! ***");
            return true;
        }else {
                System.out.println("O golpe não foi forte o suficiente (era necessário " + colosso.getStamina()
                    + ", mas causou " + dano + ").");
                System.out.println("O colosso sacode, e você despenca.");
                System.out.println("\n*** DERROTA: procure armas mais fortes no corpo do colosso ***");
            return false;
        }

    }

    private void gameOver() {
        System.out.println("\nVocê cai de uma grande altura...");
        System.out.println("\n*** GAME OVER ***");

        while (true) {
            System.out.println("\n  1 - Jogar novamente");
            System.out.println("  2 - Sair do jogo");
            System.out.print("\nEscolha uma opção: ");

            try {
                int opcao = Integer.parseInt(scanner.nextLine());

                if (opcao == 1) {
                    start();
                    return;
                }

                if (opcao == 2) {
                    System.out.println("\nObrigado por jogar!");
                    return;
                }
            } catch (NumberFormatException e) {
                System.out.println("Apenas números!");

            }

            System.out.println("Opção inválida. Digite 1 ou 2.");
        }
    }
}