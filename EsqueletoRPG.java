import java.util.Random;
import java.util.Scanner;

/** ESQUELETO BASE PARA UM RPG QUIZ-STYLE NO TERMINAL
 * A ideia aqui é apenas ter a mecânica de como o jogo
 * se comportaria, sem preocupação com a história ou os
 * dados utilizados por enquanto
*/
public class EsqueletoRPG {

    /**
	 * Constante para guardar a quantidade de linhas para
	 * pular para fazer uma mudança de tela.
	 */
    public static final int ALTURA_TELA = 30;

    /**
	 * Constantes para mapeamento das telas do quiz.
	 */
    public static final int TELA_TITULO = 1;
    public static final int TELA_REGRAS = 2;
    public static final int TELA_INTRODUCAO = 3;
    public static final int TELA_DESAFIO = 4;

    public static final int TELA_SCORE = 5; // Nova tela adicionada para o Placar

    public static final int TELA_SAIR = 0;

    /**
	 * Variável que armazena o estado da tela atual do quiz.
	 */
    public static int telaAtual = TELA_TITULO; // Ajustado para iniciar na tela titulo logicamente

    public static int pontuacao;

    public static int numeroDesafio;
    public static String perguntaDesafio;
    public static String opcao1;
    public static String opcao2;
    public static String opcao3;
    public static String opcao4;
    public static int resposta;

    // --- NOVAS VARIÁVEIS PARA O SISTEMA DE SCORE (Pedidos 4 e 9) ---
    public static String[] nomesTop10 = new String[10];
    public static int[] pontosTop10 = new int[10];
    public static int totalScoresSalvos = 0; // Mostra quantos jogaram

    /**
	 * Estrutura de dados para armazenar as questões, alternativas e respostas
	 * dos desafios do quiz. Estão associados através da posição que ocupam no
	 * vetor.
	 */
    public static final String[] PERGUNTAS = {
            "Qual foi o primeiro computador eletromecânico construído pela IBM em 1944?",
            "Em Java, qual palavra-chave é usada para impedir que uma classe seja herdada?",
            "Na LIBRAS, qual configuração de mão é usada para o sinal de \"BRANCO\"?",
            "Qual cientista é considerada a primeira programadora da história?",
            "Qual é o retorno do método size() em uma lista vazia no Java Collections?",
            "Quem criou a Máquina Analítica (projeto que nunca foi concluído em vida)?",
            "Qual destes NÃO é um pilar da Orientação a Objetos?",
            "O sinal de \"HOJE\" em LIBRAS utiliza qual movimento?",
            "Em qual década o primeiro microprocessador (Intel 4004) foi lançado?",
            "Qual comando Git é usado para listar os commits realizados?",
            "Qual é o nome da interface pai de List, Set e Queue no Java?",
            "A tecnologia de válvulas foi a marca principal de qual geração de computadores?",
            "Em LIBRAS, o sinal de \"NOME\" é feito em qual parte do corpo?",
            "Qual é a função do arquivo .gitignore?",
            "Qual o nome da primeira máquina de calcular mecânica, criada por Pascal?",
            "O que acontece se você tentar acessar um índice inexistente em um Array Java?",
            "Em LIBRAS, a \"Expressão Facial\" é considerada um parâmetro?",
            "Qual é a principal diferença entre um ArrayList e um LinkedList em Java?",
            "Quem é conhecido como o \"Pai da Computação\" por decifrar a Enigma?",
            "Qual operador Java é usado para comparar a igualdade de dois valores primitivos?"
    };

    public static final String[] RESPOSTAS = {
            "1Harvard Mark I", "1final", "1Mão em B", "1Ada Lovelace", "01",
            "1Charles Babbage", "1Recursividade", "1Para baixo (duas vezes)", "11970", "1git log",
            "1Collection", "1ª Geração", "1Peito", "1Ignorar arquivos no commit", "1Pascalina",
            "1ArrayIndexOutOfBoundsException", "1Sim, obrigatório", "1A estrutura interna de dados", "1Alan Turing", "1=="
    };
    public static final String[][] ALTERNATIVAS = {
            {"ENIAC", "Z3", "UNIVAC"},
            {"static", "abstract", "private"},
            {"Mão em 5", "Mão em L", "Mão em S"},
            {"Grace Hopper", "Margaret Hamilton", "Joan Clarke"},
            {"-1", "null", "1"},
            {"Alan Turing", "Blaise Pascal", "John von Neumann"},
            {"Polimorfismo", "Herança", "Encapsulamento"},
            {"Para cima", "Circular", "Para os lados"},
            {"1960", "1980", "1950"},
            {"git commit", "git status", "git show"},
            {"Map", "Iterable", "ArrayList"},
            {"2ª Geração", "3ª Geração", "4ª Geração"},
            {"Cabeça", "Mão oposta", "Braço"},
            {"Excluir arquivos do PC", "Deletar branches", "Esconder senhas da IDE"},
            {"Ábaco", "Step Reckoner", "Diferencial"},
            {"Retorna 0", "Retorna null", "Erro de Compilação"},
            {"Não, é opcional", "Apenas em sinais de dor", "Só para verbos"},
            {"O ArrayList é mais lento", "O LinkedList não aceita Strings", "Não há diferença"},
            {"Steve Jobs", "Bill Gates", "Linus Torvalds"},
            {"=", "equals()", "==="}
    };

    public static void main(String[] args) {
        telaTitulo();
    }
    
	/**
	 * Configura variáveis e reseta valores necessários para jogo.
	 */
    public static void setup() {
        pontuacao = 0;
        embaralharQuestoes(PERGUNTAS, ALTERNATIVAS, RESPOSTAS);
    }

    /**
	 * Exibe a tela de título do jogo
	 * com o logo e o menu inicial.
	 */
    public static void telaTitulo() {
        while(true) {
            desenharTela(TELA_TITULO);

            int op = lerOpcoes("Escolha", new int[]{1, 2, 3, 4});

            switch (op) {
                case 1 -> iniciarJogo();
                case 2 -> telaRegras();
                case 3 -> telaScore();
                case 4 -> sair();
            }
        }
    }

    /**
     * Desenha uma tela específica do quiz, utilizando uma ou mais funções de desenhar.
     * @param codigoTela número que indica a tela a ser desenhada.
     */
    public static void desenharTela(int codigoTela) {
        telaAtual = codigoTela;

        limparTela();
        switch (codigoTela) {
            case TELA_DESAFIO -> desenharDesafio();
            case TELA_TITULO -> {
                desenharLogo();
                desenharMenuTitulo();
            }
            case TELA_REGRAS -> desenharRegras();
            case TELA_INTRODUCAO -> desenharIntroducao();
            case TELA_SCORE -> desenharScore();
            case TELA_SAIR -> desenharSair();
        }
    }

    /**
	 * Desenha na tela o título do jogo em ASCII Art
	 */
    public static void desenharLogo() {
        System.out.println("..######..##.....##.##.....##.########.########.....######..########.########..########..#######.");
        System.out.println(".##....##.##.....##.##.....##....##....##..........##....##.##.......##.....##....##....##.....##");
        System.out.println(".##.......##.....##.##.....##....##....##..........##.......##.......##.....##....##....##.....##");
        System.out.println(".##.......#########.##.....##....##....######......##.......######...########.....##....##.....##");
        System.out.println(".##.......##.....##.##.....##....##....##..........##.......##.......##...##......##....##.....##");
        System.out.println(".##....##.##.....##.##.....##....##....##..........##....##.##.......##....##.....##....##.....##");
        System.out.println("..######..##.....##..#######.....##....########.....######..########.##.....##....##.....#######.");
        System.out.println();
        System.out.println("-------------------------------------------------------------------------------------------------");
        System.out.println();
    }

    /**
	 * Desenha o MENU INICIAL com as opções para o usuário.
	 */
    public static void desenharMenuTitulo() {
        System.out.println("[1] -> Iniciar jogo");
        System.out.println("[2] -> Ler regras");
        System.out.println("[3] -> Ver Placar de Score");
        System.out.println("[4] -> Sair");
    }

    /**
	 * Desenha na tela o título do jogo em ASCII Art
	 */
    public static void desenharDesafio() {
        System.out.println("DESAFIO " + numeroDesafio + " de 20");
        System.out.println("Sua Pontuação: " + pontuacao);
        System.out.println("---------");
        System.out.println(perguntaDesafio);
        System.out.println("---------");
        System.out.println("1) " + opcao1);
        System.out.println("2) " + opcao2);
        System.out.println("3) " + opcao3);
        System.out.println("4) " + opcao4);
        System.out.println();
    }

    /**
	 * Exibe as regras do jogo para o usuário
	 */
    private static void desenharRegras() {
        //código provisório com msg e volta para o menu inicial
        System.out.println("\n\nREGRAS:\n\n");
        System.out.println("1. Responda as perguntas digitando o valor das opções '1', '2', '3' ou '4'.");
        System.out.println("2. Acertar de 1ª vale 10 pts, de 2ª vale 5 pts, de 3ª ou mais vale 2 pts.");
        System.out.println("3. Digite 0 para ver estas regras durante um desafio.");
        System.out.println("4. Ao final dos desafios digite seu nome para salvar na tabela de recordes.");
        System.out.println("5. Convide seus amigos para jogar na mesma máquina e veja quem é o melhor.");
        System.out.println("6. Não esqueça de se DIVERTIR!");
        System.out.println("\n\n");
    }
	/**
	 * Desenha a tela de introdução ao iniciar o jogo.
	 */
    public static void desenharIntroducao() {
        System.out.println("Tudo começa em 1930 quando os países encantados por um incrível esporte decidem criar um campeonato mundial...");
        System.out.println("E assim começa a Copa do Mundo!");
        System.out.println("Bem-vindo ao nosso Quiz! Aqui você pode provar que entende tudo do assunto!");
        System.out.println("\n\n");
    }
    
    // --- FUNÇÃO PARA DESENHAR O SCORE ---
    public static void desenharScore() {
        System.out.println("=================================");
        System.out.println("          TOP 10 SCORES          ");
        System.out.println("=================================");
        System.out.println("Jogadores que já participaram: " + totalScoresSalvos);
        System.out.println("---------------------------------");
        
        for (int i = 0; i < 10; i++) {
            if (nomesTop10[i] != null) {
                System.out.println((i + 1) + "º Lugar: " + nomesTop10[i] + " - " + pontosTop10[i] + " pts");
            } else {
                System.out.println((i + 1) + "º Lugar: ---");
            }
        }
        System.out.println("=================================\n");
    }
    /**
	 * Desenha a tela de saída do jogo.
	 */
    public static void desenharSair() {
        System.out.println("Obrigado por jogar!");
        System.out.println("\n\nAté mais!\n\n");
    }
    /**
	 * Limpa a tela do terminal.
	 * Limpa a tela imprimindo várias quebras de linhas vazias
	 * até encher a tela atual do usuário
	 */
    public static void limparTela() {
        for(int i = 0; i < ALTURA_TELA; i++) {
            System.out.println();
        }
    }

    public static void iniciarJogo() {
        setup();
        telaIntroducao();
        limparTela();
        
        // Laço que passa por todas as 20 questões aleatorizadas
        for(int i = 0; i < PERGUNTAS.length; i++) {
            jogarDesafio(i);
        }
        
        // Fim de Jogo (Pedido 8)
        limparTela();
        System.out.println("FIM DE JOGO! Você concluiu todas as missões.");
        System.out.println("Sua pontuação final foi: " + pontuacao + " pontos.\n");
        
        String nome = lerString("Digite seu nome para o Placar: ");
        salvarNoPlacar(nome, pontuacao);
        
        telaScore();
    }

    private static void telaRegras() {
        desenharTela(TELA_REGRAS);
        lerString("Tecle ENTER para continuar...");
    }

    public static void telaIntroducao() {
        desenharTela(TELA_INTRODUCAO);
        lerString("Tecle ENTER para continuar...");
    }
    
    // --- TELA DE SCORE (Obrigatório digitar 0 para voltar - Pedido 8) ---
    public static void telaScore() {
        desenharTela(TELA_SCORE);
        lerOpcoes("Digite 0 para voltar ao menu principal", new int[]{0});
    }

    public static void sair() {
        desenharTela(TELA_SAIR);
        System.exit(0);
    }

    // --- NOVA FUNÇÃO UNIFICADA QUE SUBSTITUI DESAFIO1, DESAFIO2... ---
    public static void jogarDesafio(int indiceAtual) {
        numeroDesafio = indiceAtual + 1;
        perguntaDesafio = PERGUNTAS[indiceAtual];

        // 1. Cria um vetor temporário para juntar a resposta certa e as 3 erradas
        String[] opcoes = new String[4];
        opcoes[0] = RESPOSTAS[indiceAtual];
        opcoes[1] = ALTERNATIVAS[indiceAtual][0];
        opcoes[2] = ALTERNATIVAS[indiceAtual][1];
        opcoes[3] = ALTERNATIVAS[indiceAtual][2];

        // 2. Randomiza a ordem das alternativas
        Random random = new Random();
        for (int i = 0; i < opcoes.length; i++) {
            int pos = random.nextInt(opcoes.length-i) + i;
            String temp = opcoes[i];
            opcoes[i] = opcoes[pos];
            opcoes[pos] = temp;
        }

        opcao1 = opcoes[0];
        opcao2 = opcoes[1];
        opcao3 = opcoes[2];
        opcao4 = opcoes[3];

        // 3. Descobre em qual numero a resposta certa caiu
        for (int i = 0; i < 4; i++) {
            if (opcoes[i].equals(RESPOSTAS[indiceAtual])) {
                resposta = i + 1; 
            }
        }

        boolean acertou = false;
        int tentativas = 1;

        while (!acertou) {
            desenharTela(TELA_DESAFIO);

            // Usando lerOpcoes ao invés de Scanner.nextInt resolve o erro das letras 
            int escolha = lerOpcoes("Diga sua resposta (ou 0 para regras)", new int[]{0, 1, 2, 3, 4});

            if(escolha == 0) {
                telaRegras();
            } else if(escolha == resposta) {
                // Acertou
                acertou = true;
                int pontosGanhos;
                
                // Distribui os pontos baseado na tentativa (Pedido 5)
                if (tentativas == 1) {
                    pontosGanhos = 10;
                } else if (tentativas == 2) {
                    pontosGanhos = 5;
                } else {
                    pontosGanhos = 2;
                }
                pontuacao += pontosGanhos;

                System.out.println("\nResposta Certa! Você ganhou " + pontosGanhos + " pontos.");
                
                // Mensagem obrigatória não pulada pelo random (Pedido 6)
                System.out.println("Você completou a missão atual com sucesso!");
                lerString("Aperte ENTER para continuar para a próxima...");
                limparTela();
            } else {
                // Errou
                tentativas++;
                System.out.println("\nResposta Incorreta! Você sofreu um ataque, mas pode tentar de novo.");
                lerString("Aperte ENTER para tentar novamente na mesma questão...");
            }
        }
    }

    // --- FUNÇÃO PARA ORDENAR E SALVAR NO PLACAR ---
    public static void salvarNoPlacar(String nome, int pontos) {
        totalScoresSalvos++; 
        
        int posicaoParaEntrar = -1;
        
        // Verifica se a pontuação bate os 10 melhores
        for (int i = 0; i < 10; i++) {
            if (nomesTop10[i] == null || pontos > pontosTop10[i]) {
                posicaoParaEntrar = i;
                break;
            }
        }
        
        // Empurra os de baixo e insere o novo campeão
        if (posicaoParaEntrar != -1) {
            for (int i = 9; i > posicaoParaEntrar; i--) {
                nomesTop10[i] = nomesTop10[i - 1];
                pontosTop10[i] = pontosTop10[i - 1];
            }
            nomesTop10[posicaoParaEntrar] = nome;
            pontosTop10[posicaoParaEntrar] = pontos;
        }
    }

    public static void embaralharQuestoes(String[] questoes, String[][] alternativas, String[] respostas) {
        Random random = new Random();

        for(int i = 0; i < questoes.length - 1; i++) {
            int j = random.nextInt(questoes.length-i) + i;

            String tempQuestoes = questoes[i];
            questoes[i] = questoes[j];
            questoes[j] = tempQuestoes;

            String tempRespostas = respostas[i];
            respostas[i] = respostas[j];
            respostas[j] = tempRespostas;

            String[] tempAlternativa = alternativas[i];
            alternativas[i] = alternativas[j];
            alternativas[j] = tempAlternativa;
        }
    }

    public static boolean validarResposta(int resposta) {
        return resposta >= 0 && resposta <= 4;
    }

    public static int lerOpcoes(String textoParaEntrada, int[] valoresValidos) {
        textoParaEntrada = adicionarDoisPontos(textoParaEntrada);

        while(true) {
            boolean erroFormato = false;
            String entradaUsuario = lerString(textoParaEntrada);

            try {
                int num = Integer.parseInt(entradaUsuario.trim());
                if(contemValor(num, valoresValidos)) {
                    return num;
                }
            }catch (NumberFormatException e) {
                erroFormato = true;
            }
            handleErro(erroFormato ? "Erro: digite um número!" : "Opção inválida!");
        }
    }

    public static void handleErro(String msg) {
        System.out.println(msg);
        desenharTela(telaAtual);
    }

    public static String lerString(String textoParaEntrada) {
        Scanner input = new Scanner(System.in);
        System.out.print(textoParaEntrada);
        return input.nextLine();
    }

    public static String lerOpcoes(String textoParaEntrada, String[] valoresValidos, boolean ignorarCaixa) {
        textoParaEntrada = adicionarDoisPontos(textoParaEntrada);

        while(true) {
            String entradaUsuario = lerString(textoParaEntrada);
            if (contemValor(entradaUsuario, valoresValidos, ignorarCaixa)) { 
                return entradaUsuario;
            }
            handleErro("Opção inválida!");
        }
    }

    public static String lerComPadrao(String textoParaEntrada, String pattern) {
        textoParaEntrada = adicionarDoisPontos(textoParaEntrada);

        while(true) {
            String entradaUsuario = lerString(textoParaEntrada);
            if(entradaUsuario.matches(pattern)) {
                return entradaUsuario;
            }
            handleErro("Opção inválida!");
        }
    }

    public static String adicionarDoisPontos(String texto) {
        texto = texto.trim();
        if(!texto.matches("^.*:$")){
            texto += ": ";
        }else {
            texto += " ";
        }
        return texto;
    }

    public static boolean contemValor(int busca, int[] fonte) {
        for(int valor : fonte) {
            if(valor == busca)
                return true;
        }
        return false;
    }

    public static boolean contemValor(String busca, String[] fonte, boolean ignorarCaixa) {
        for(String valor : fonte) {
            if(ignorarCaixa ? valor.equalsIgnoreCase(busca) : valor.equals(busca)) {
                return true;
            }
        }
        return false;
    }
}