package src.br.com.senac.begods.chutecerto;

import java.util.Random;
import java.util.Scanner;

/** ESQUELETO BASE PARA UM RPG QUIZ-STYLE NO TERMINAL
 * A ideia aqui é apenas ter a mecânica de como o jogo
 * se comportaria, sem preocupação com a história ou os
 * dados utilizados por enquanto
*/
public class Main {

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
    public static final int TELA_SCORE = 5;
    public static final int TELA_FINALIZAR = 6;
    public static final int TELA_SAIR = 0;

    /**
	 * Variável que armazena o estado da tela atual do quiz.
	 */
    public static int telaAtual = TELA_TITULO; // Ajustado para iniciar na tela titulo logicamente


    public static int numeroDesafio;
    public static String perguntaDesafio;
    public static String opcao1;
    public static String opcao2;
    public static String opcao3;
    public static String opcao4;
    public static int resposta;

    public static boolean fimDaPartida = false;

    // --- NOVAS VARIÁVEIS PARA O SISTEMA DE SCORE (Pedidos 4 e 9) ---
    public static String[] nomesTop10 = new String[10];
    public static int[] pontosTop10 = new int[10];
    public static int pontuacao;



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
			"Harvard Mark I",
			"final",
			"Mão em B",
			"Ada Lovelace",
			"0",
			"Charles Babbage",
			"Recursividade",
			"Para baixo (duas vezes)",
			"1970",
			"git log",
			"Collection",
			"1ª Geração",
			"Peito",
			"Ignorar arquivos no commit",
			"Pascalina",
			"ArrayIndexOutOfBoundsException",
			"Sim, obrigatório",
			"A estrutura interna de dados",
			"Alan Turing",
			"=="
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
     * Espera o usuário digitar "Enter" para continuar o jogo.
     */
    public static void pausar() {
        lerString("Tecle ENTER para continuar...");
    }

	/**
	 * Configura variáveis e reseta valores necessários para jogo.
	 */
    public static void setup() {
        pontuacao = 0;
        embaralharQuestoes(PERGUNTAS, ALTERNATIVAS, RESPOSTAS);
    }

    public static void iniciarJogo() {
        setup();
        telaIntroducao();
        limparTela();

        // Laço que passa por todas as 20 questões aleatorizadas
        for(int i = 0; i < PERGUNTAS.length; i++) {
            telaDesafio(i);
        }

        telaFinalizar();
    }

    /**
	 * Exibe a tela de título do jogo
	 * com o logo e o menu inicial.
	 */
    public static void telaTitulo() {
        //Não limpa a tela na primeira renderização
        boolean limparTela = false;

        while(true) {
            desenharTela(TELA_TITULO, limparTela);

            //Limpar a tela ao voltar no menu após a primeira renderização
            limparTela = true;

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
     * Exibe a tela de regras do jogo.
     */
    public static void telaRegras() {
        desenharTela(TELA_REGRAS);
        pausar();
    }

    /**
     * Exibe a tela de introdução do quiz.
     */
    public static void telaIntroducao() {
        desenharTela(TELA_INTRODUCAO);
        pausar();
    }

    /**
     * Exibe a tela de final do quiz, pega o nome do usuário, salva e mostra o placar
     */
    public static void telaFinalizar() {
        //controle de exibição da pontuação na tela de score no fim da partida
        fimDaPartida = true;

        desenharTela(TELA_FINALIZAR);

        String nome = lerComPadrao("Digite seu nome para o Placar: ", "^(?=.*[A-Za-z])[A-Za-z ]+$", "Por favor, utilize somente letras!").trim();

        //pega apenas as 3 primeiras letras
        nome = nome.length() > 3 ? nome.substring(0, 3) : nome;

        //salva a pontuação nos recordes se estiver no top 10
        boolean scoreSalvo = salvarNoPlacar(nome, pontuacao);

        desenharTela(TELA_SCORE);
        desenharResultado(scoreSalvo);

        fimDaPartida = false;
    }

    /**
     * Exibe a tela de recordes do jogo.
     */
    public static void telaScore() {
        desenharTela(TELA_SCORE);
        pausar();
    }

    /**
     * Exibe a tela de saída e encerra o jogo.
     */
    public static void sair() {
        desenharTela(TELA_SAIR);
        System.exit(0);
    }

    /**
     * Exibe a tela de desafio do quiz e realiza a lógica de pontuação.
     * @param indiceAtual indice de qual desafio deve ser exibido.
     */
    public static void telaDesafio(int indiceAtual) {
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

        desenharTela(TELA_DESAFIO);

        //recebe a entrada como letra
        String escolhaLetra = lerOpcoes("Diga sua resposta", new String[]{"A", "B", "C", "D"}, true, "\nDigite 'A', 'B', 'C' ou 'D' para escolher uma alternativa.").toUpperCase();

        //mapeia a letra para o valor numérico correspondente
        int escolhaNum = switch (escolhaLetra) {
            case "A" -> 1;
            case "B" -> 2;
            case "C" -> 3;
            default -> 4;
        };

        if(escolhaNum == resposta) {
            pontuacao += 10;

            System.out.println("\nGOOOOOOOOOOL!!!");
            System.out.println("\nVocê chutou a bola e acertou no gol.\nVocê ganhou " + 10 + " pontos!");
            System.out.println("\nContinue assim, artilheiro!\n\n");
        } else {
            System.out.println("\nErrou o chute!!!");
            System.out.println("\nTudo bem, bola fora acontece. Não desista e continue chutando!!!\n");
        }

        pausar();
    }

    /**
     * Desenha uma tela específica do quiz, limpando a tela antes por padrão.
     * @param codigoTela número que indica a tela a ser desenhada.
     */
    public static void desenharTela(int codigoTela) {
        desenharTela(codigoTela, true);
    }

    /**
     * Desenha uma tela específica do quiz, utilizando uma ou mais funções de desenhar.
     * @param codigoTela número que indica a tela a ser desenhada.
     */
    public static void desenharTela(int codigoTela, boolean limparTela) {
        telaAtual = codigoTela;

        if(limparTela){
            limparTela();
        }

        switch (codigoTela) {
            case TELA_TITULO -> {
                desenharLogo();
                desenharMenuTitulo();
            }
            case TELA_DESAFIO -> desenharDesafio();
            case TELA_REGRAS -> desenharRegras();
            case TELA_SCORE -> desenharScore();
            case TELA_INTRODUCAO -> desenharIntroducao();
            case TELA_FINALIZAR -> desenharFinalizar();
            case TELA_SAIR -> desenharSair();
        }
    }

    /**
     * Desenha na tela o título do jogo em ASCII Art
     */
    public static void desenharLogo() {
        System.out.println();
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
        System.out.println("[1] -> Iniciar Partida");
        System.out.println("[2] -> Ler Regras");
        System.out.println("[3] -> Ver Placar de Líderes");
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
        System.out.println("A) " + opcao1);
        System.out.println("B) " + opcao2);
        System.out.println("C) " + opcao3);
        System.out.println("D) " + opcao4);
        System.out.println();
    }

	/**
	 * Exibe as regras do jogo para o usuário
	 */
	private static void desenharRegras() {
		//código provisório com msg e volta para o menu inicial
		System.out.println("\n\nREGRAS:\n\n");
		System.out.println("1. Responda as perguntas digitando o valor das opções 'A', 'B', 'C' ou 'D'.");
		System.out.println("2. Cada resposta correta somará 10 pontos na pontuação.");
		System.out.println("3. Ao final dos desafios digite seu nome para salvar na tabela de recordes.");
		System.out.println("4. Convide seus amigos para jogar na mesma máquina e veja quem é o melhor.");
		System.out.println("5. Não esqueça de se DIVERTIR!");
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

    /**
     * Desenha a tela de finalização do jogo.
     */
    public static void desenharFinalizar() {
        System.out.println("------------------ FIM DA PARTIDA! -----------------");
        System.out.println();
        System.out.println("Parabéns, você chegou ao final da copa de perguntas.");
        System.out.println("Sua pontuação foi de " + pontuacao + " pontos!\n");
    }

    /**
     * Desenha a mensagem de sucesso ou falha ao tentar entrar no Placar de Líderes
     * @param entrouNoPlacar informa se o jogador conseguiu ser salvo no placar.
     */
    public static void desenharResultado(boolean entrouNoPlacar) {
        if(entrouNoPlacar) {
            System.out.println("\nIncrível! Você garantiu o seu lugar no Top 10!\n\n");
        } else{
            System.out.println("Você não conseguiu se classificar no placar de líderes dessa vez...");
            System.out.println("Não desista! Estude mais e tente novamente!\n\n");
        }
        pausar();
    }

    /**
     * Desenha a tela de score do jogo.
     */
    public static void desenharScore() {
        System.out.println("=================================");
        System.out.println("          TOP 10 SCORES          ");
        System.out.println("=================================");

        if(fimDaPartida) {
            System.out.println("Sua pontuação: " + pontuacao);
            System.out.println("---------------------------------");
        }

        for (int i = 0; i < 10; i++) {
            if (nomesTop10[i] != null) {
                System.out.printf("%2dº Lugar: %-3s - %3d pts\n", (i + 1), nomesTop10[i].toUpperCase(), pontosTop10[i]);
            } else {
                System.out.printf("%2dº Lugar: ---\n", (i + 1));
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

    /**
     * Salva o nome do jogador e sua pontuação no placar de recordes
     * se a pontuação estiver entre as 10 melhores.
     * @param nome nome que será salvo nos recordes.
     * @param pontos pontuação do jogador que será salva.
     */
    public static boolean salvarNoPlacar(String nome, int pontos) {
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
            return true; //salvo com sucesso
        }

        //pontuação não salva
        return false;
    }

    /**
     * Altera a ordem dos elementos nos vetores passados como parâmetros, servindo para embaralhar as questões com suas
     * respectivas respostas e alternativas, mantendo-as sempre nos índices correspondentes.
     * @param questoes vetor de String contendo as questões do quiz.
     * @param alternativas matriz de String, contendo um vetor em cada posição com as alternativas incorretas para as
     *                     respectivas questões
     * @param respostas vetor de String contendo as respostas corretas das respectivas questões./
     */
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

    /**
     * Solicita a entrada de um inteiro válido, utilizando uma mensagem de erro padrão.
     * * <p>Este é um método de conveniência que chama
     * {@link #lerOpcoes(String, int[], String)} com a mensagem "Opção inválida!".
     *
     * @param textoParaEntrada texto exibido ao solicitar a entrada.
     * @param valoresValidos array de inteiros com as opções aceitas.
     * @return o valor inteiro válido inserido pelo usuário.
     */
    public static int lerOpcoes(String textoParaEntrada, int[] valoresValidos) {
        return lerOpcoes(textoParaEntrada, valoresValidos, "Opção inválida!");
    }

    /**
     * Solicita entrada de um inteiro para o usuário que esteja dentro do array de opções válidas
     * e reexibe a interface, solicitando novamente a entrada, caso o usuário insira valor inválido.
     * @param textoParaEntrada texto exibido ao solicitar a entrada.
     * @param valoresValidos array de inteiros com as opções aceitas.
     * @return o valor inteiro válido inserido pelo usuário.
     */
    public static int lerOpcoes(String textoParaEntrada, int[] valoresValidos, String mensagemErro) {
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
            handleErro(erroFormato ? "Erro: digite um número!" : mensagemErro);
        }
    }

    /**
     * Lida com o fluxo de erro, caso a entrada do usuário não tenha sido válida.
     * @param msg mensagem para ser exibida para o usuário no caso de erro no valor inserido.
     */
    public static void handleErro(String msg) {
        System.out.println(msg);
        System.out.println("\n");
        pausar();
        desenharTela(telaAtual);
    }

    /**
     * Solicita a entrada para o usuário e devolve a string lida.
     * @param textoParaEntrada texto para requisitar a entrada do usuário.
     * @return retorna a string inserida pelo usuário.
     */
    public static String lerString(String textoParaEntrada) {
        Scanner input = new Scanner(System.in);
        System.out.print(textoParaEntrada);
        return input.nextLine();
    }

    /**
     * Solicita a entrada de uma opção válida, utilizando uma mensagem de erro padrão.
     * * <p>Este é um método de conveniência que chama
     * {@link #lerOpcoes(String, String[], boolean, String)} com a mensagem "Opção inválida!".
     *
     * @param textoParaEntrada texto exibido ao solicitar a entrada.
     * @param valoresValidos   array de strings com as opções aceitas.
     * @param ignorarCaixa     se verdadeiro, ignora diferenças entre maiúsculas/minúsculas.
     * @return a string validada inserida pelo usuário.
     */
    public static String lerOpcoes(String textoParaEntrada, String[] valoresValidos, boolean ignorarCaixa) {
        return lerOpcoes(textoParaEntrada, valoresValidos, ignorarCaixa, "Opção inválida!");
    }

    /**
     * Solicita entrada de uma string para o usuário que esteja dentro do array de opções válidas
     * <p> Caso a entrada seja inválida, reexibe a interface, solicitando uma nova entrada.
     * @param textoParaEntrada texto exibido ao solicitar a entrada do usuário.
     * @param valoresValidos array de strings com as opções aceitas.
     * @param ignorarCaixa se verdadeiro, ignora diferenças entre maiúsculas/minúsculas.
     * @return retorna a string válida inserida pelo usuário.
     */
    public static String lerOpcoes(String textoParaEntrada, String[] valoresValidos, boolean ignorarCaixa, String mensagemErro) {
        textoParaEntrada = adicionarDoisPontos(textoParaEntrada);

        while(true) {
            String entradaUsuario = lerString(textoParaEntrada);
            if (contemValor(entradaUsuario, valoresValidos, ignorarCaixa)) {
                return entradaUsuario;
            }
            handleErro(mensagemErro);
        }
    }

    /**
     * Solicita a entrada de uma opção válida, utilizando uma mensagem de erro padrão.
     * * <p>Este é um método de conveniência que chama
     * {@link #lerComPadrao(String, String, String)} com a mensagem "Opção inválida!".
     *
     * @param textoParaEntrada texto exibido ao solicitar a entrada.
     * @param pattern regex especificando o padrão de entrada esperado.
     * @return a string válida inserida pelo usuário.
     */
    public static String lerComPadrao(String textoParaEntrada, String pattern) {
        return lerComPadrao(textoParaEntrada, pattern, "Opção inválida!");
    }

    /**
     * Solicita entrada de uma string para o usuário que esteja dentro do padrão especificada (regex).
     * @param textoParaEntrada texto exibido ao solicitar a entrada.
     * @param pattern regex especificando o padrão de entrada esperado.
     * @return a string válida inserida pelo usuário.
     */
    public static String lerComPadrao(String textoParaEntrada, String pattern, String mensagemErro) {
        textoParaEntrada = adicionarDoisPontos(textoParaEntrada);

        while(true) {
            String entradaUsuario = lerString(textoParaEntrada);
            if(entradaUsuario.matches(pattern)) {
                return entradaUsuario;
            }
            handleErro(mensagemErro);
        }
    }

    /**
     * Verifica se o texto possui ": " no final, adicionando-o se não tiver.
     * @param texto texto que vai ser verificado e formatado.
     * @return o texto formatado com ": " no final.
     */
    public static String adicionarDoisPontos(String texto) {
        //retira os espaços do início e fim
        texto = texto.trim();

        //verifica se não tem ':' no final
        if(!texto.matches("^.*:$")){
            texto += ": ";
        }else {
            texto += " ";
        }

        return texto;
    }

    /**
     * Busca um valor dentro de um array de inteiro e retorna true se for encontrado, senão retorna false.
     * @param busca valor para ser procurado no array.
     * @param fonte array no qual a procura será feita.
     * @return true ou false se o valor foi encontrado.
     */
    public static boolean contemValor(int busca, int[] fonte) {
        for(int valor : fonte) {
            if(valor == busca)
                return true;
        }
        return false;
    }

    /**
     * Busca um valor dentro de um array de String e retorna true se for encontrado, senão retorna false.
     * @param busca valor para ser procurado no array.
     * @param fonte array no qual a procura será feita.
     * @param ignorarCaixa indica se deve diferenciar maiúsculas e minúsculas na busca ou não.
     * @return true ou false se o valor foi encontrado.
     */
    public static boolean contemValor(String busca, String[] fonte, boolean ignorarCaixa) {
        for(String valor : fonte) {
            if(ignorarCaixa ? valor.equalsIgnoreCase(busca) : valor.equals(busca)) {
                return true;
            }
        }
        return false;
    }
}