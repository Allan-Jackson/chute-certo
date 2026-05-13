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

	public static final int TELA_SAIR = 0;

	/**
	 * Variável que armazena o estado da tela atual do quiz.
	 */
	public static int telaAtual = TELA_DESAFIO;

	public static int pontuacao;

	public static int numeroDesafio;
	public static String perguntaDesafio;
	public static String opcao1;
	public static String opcao2;
	public static String opcao3;
	public static String opcao4;
	public static int resposta;

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
		desenharTela(TELA_TITULO);

		while(true) {
			int op = lerOpcoes("Escolha", new int[]{1, 2, 3});
			limparTela();

			switch (op) {
				case 1 -> {
					iniciarJogo();
				}
				case 2 -> {
					telaRegras();
					desenharTela(TELA_TITULO);
				}
				case 3 -> sair();
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
			case TELA_SAIR -> desenharSair();
			case TELA_INTRODUCAO -> desenharIntroducao();
		}
	}

	/**
	 * Desenha na tela o título do jogo em ASCII Art
	 */
	public static void desenharLogo() {
		System.out.println();
		System.out.println("                        BY                        ");
		System.out.println("         ALLAN JACKSON SILVA OLIVEIRA             ");
		System.out.println("      ____  ______   _________  ______   ___      ");
		System.out.println("     |    |/      \\ /         \\/      \\ /   \\ ");
		System.out.println("     |    |   __   |    ______|   __   |     |    ");
		System.out.println("     |    |  |  |  |   |  ____|  |  |  |     |    ");
		System.out.println("     |    |  |  |  |   | /_   \\  |  |  |\\___/   ");
		System.out.println("   __|    |  \\__/  |   |___|  |  |__|  | ___     ");
		System.out.println("  |       |        |          |        |/   \\    ");
		System.out.println("   \\_____/ \\______/ \\_________/\\______/ \\___/");
	}

	/**
	 * Desenha o MENU INICIAL com as opções para o usuário.
	 */
	public static void desenharMenuTitulo() {
		System.out.println("\n-----------------------------------------------\n");
		System.out.println("[1] -> Iniciar jogo");
		System.out.println("[2] -> Ler regras");
		System.out.println("[3] -> Sair");
	}

	/**
	 * Desenha a pergunta e as alternativas para os
	 * desafios do jogo.
	 */
	public static void desenharDesafio() {
		System.out.println("DESAFIO " + numeroDesafio);
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
		System.out.println("1. Responda as perguntas digitando o valor das opções 'A', 'B', 'C' ou 'D'.");
		System.out.println("2. Cada resposta correta somará 10 pontos para o score.");
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
		System.out.println("Tudo começa em 1930 quando os países encantados por um incrível esporte decidem criar um" +
				" campeonato mundial para decidir qual a melhor nação.");
		System.out.println("E assim começa a Copa do Mundo!");
		System.out.println("Bem-vindo ao nosso Quiz da Bola! Aqui você pode provar que entende tudo do assunto!");
		System.out.println("\n\n");
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
		desafio1();
	}

	private static void telaRegras() {
		desenharTela(TELA_REGRAS);

		lerString("Tecle ENTER para continuar...");
	}

	/**
	 * Exibe a história introdutório do jogo
	 */
	public static void telaIntroducao() {
		desenharTela(TELA_INTRODUCAO);
		lerString("Tecle ENTER para continuar...");
	}

	/**
	 * Exibe uma mensagem para o usuário e encerra o jogo.
	 */
	public static void sair() {
		desenharTela(TELA_SAIR);
		System.exit(0);
	}

	/**
	 * Exibe para o usuário o primeiro desafio do jogo
	 */
	public static void desafio1() {
		numeroDesafio = 1;
		perguntaDesafio = PERGUNTAS[0];

		opcao1 = ALTERNATIVAS[0][0];
		opcao2 = RESPOSTAS[0];
		opcao3 = ALTERNATIVAS[0][1];
		opcao4 = ALTERNATIVAS[0][2];

		resposta = 2;


		int escolha;
		Scanner entrada = new Scanner(System.in);

		boolean respostaValida = false;

		do { //repete enquanto a resposta não for válida

			desenharDesafio();

			System.out.print("Diga sua resposta (ou 0 para regras): ");
			escolha = entrada.nextInt();
			entrada.nextLine(); //limpar o buffer

			respostaValida = validarResposta(escolha);

			if(!respostaValida){
				System.out.println("Opção inválida!");
			}else if(escolha == 0) {
				telaRegras();
			}else if(!(escolha == resposta)) {
				/*TODO: PERDE VIDA E VERIFICA SE DEU GAME OVER*/
				System.out.println("Você sofreu um ataque por errar a resposta e perdeu uma vida!");
				System.out.println("Vc tem X vidas restantes.\n");
			}

		}while(!respostaValida || !(escolha == resposta));

		//passou do desafio com vitória
		System.out.println();
		System.out.println("Você completou a primeira missão com sucesso!");
		System.out.println("Vamos para a próxima missão!");
		System.out.println("Aperte enter para continuar...");
		entrada.nextLine();

		limparTela();
		desafio2();
	}

	/**
	 * Exibe para o usuário o segundo desafio do jogo
	 */
	public static void desafio2() {
		numeroDesafio = 2;
		perguntaDesafio = PERGUNTAS[1];

		opcao1 = ALTERNATIVAS[1][0];
		opcao2 = ALTERNATIVAS[1][1];
		opcao3 = ALTERNATIVAS[1][2];
		opcao4 = RESPOSTAS[1];

		resposta = 4;

		int op;
		Scanner entrada = new Scanner(System.in);

		boolean respostaValida = false;

		do { //repete enquanto a resposta não for válida

			desenharDesafio();

			System.out.print("Diga sua resposta (ou 0 para regras): ");
			op = entrada.nextInt();
			entrada.nextLine(); //limpar o buffer

			respostaValida = validarResposta(op);

			if(!respostaValida){
				System.out.println("Opção inválida!");
			}else if(op == 0) {
				telaRegras();
			}else if(!(op == resposta)) {
				/*TODO: PERDE VIDA E VERIFICA SE DEU GAME OVER*/
				System.out.println("Você sofreu um ataque por errar a resposta e perdeu uma vida!");
				System.out.println("Vc tem X vidas restantes.\n");
			}

		}while(!respostaValida || !(op == resposta));

		//passou do desafio com vitória
		System.out.println("Você completou a segunda e última missão com sucesso!");
		System.out.println("Aperte enter para continuar...");
		entrada.nextLine();

		limparTela();
		telaTitulo();
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

			//troca para as questões
			String tempQuestoes = questoes[i];
			questoes[i] = questoes[j];
			questoes[j] = tempQuestoes;

			//troca para as respostas
			String tempRespostas = respostas[i];
			respostas[i] = respostas[j];
			respostas[j] = tempRespostas;

			//troca para as alternativas
			String[] tempAlternativa = alternativas[i];
			alternativas[i] = alternativas[j];
			alternativas[j] = tempAlternativa;
		}
	}


	/**
	 * Verifica se a resposta inserida pelo jogador é válida
	 * @param resposta número inteiro representando a opção que o jogador escolheu
	 * @return retorna true caso a resposta seja válida e false caso contrário
	 */
	public static boolean validarResposta(int resposta) {
		return resposta >= 0 && resposta <= 4;
	}

	/**
	 * Solicita entrada de um inteiro para o usuário que esteja dentro do array de opções válidas
	 * e reexibe a interface, solicitando novamente a entrada, caso o usuário insira valor inválido.
	 * @param textoParaEntrada texto para requisitar a entrada do usuário.
	 * @param valoresValidos valores permitidos e considerados válidos para a entrada.
	 * @return retorna o valor inteiro válido inserido pelo usuário.
	 */
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

	/**
	 * Lida com o fluxo de erro, caso a entrada do usuário não tenha sido válida.
	 * @param msg mensagem para ser exibida para o usuário no caso de erro no valor inserido.
	 */
	public static void handleErro(String msg) {
		System.out.println(msg);
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
	 * Solicita entrada de uma string para o usuário que esteja dentro do array de opções válidas
	 * e reexibe a interface, solicitando novamente a entrada, caso o usuário insira valor inválido.
	 * @param textoParaEntrada texto para requisitar a entrada do usuário.
	 * @param valoresValidos valores permitidos e considerados válidos para a entrada.
	 * @param ignorarCaixa indica se deve diferenciar maiúsculas e minúsculas na comparação.
	 * @return retorna a string válida inserida pelo usuário.
	 */
	public static String lerOpcoes(String textoParaEntrada, String[] valoresValidos, boolean ignorarCaixa) {
		textoParaEntrada = adicionarDoisPontos(textoParaEntrada);

		while(true) {
			String entradaUsuario = lerString(textoParaEntrada);

			if (contemValor(entradaUsuario, valoresValidos, ignorarCaixa)) { //verifica se está na lista
				return entradaUsuario;
			}

			handleErro("Opção inválida!");
		}
	}

	/**
	 * Solicita entrada de uma string para o usuário que esteja dentro do padrão especificada (regex).
	 * @param textoParaEntrada texto para requisitar a entrada do usuário.
	 * @param pattern regex especificando o padrão de entrada esperado.
	 * @return retorna a string válida inserida pelo usuário.
	 */
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

	/**
	 * Verifica se o texto possui ": " no final, adicionando-o se não tiver.
	 * @param texto texto que vai ser verificado e formatado.
	 * @return o texto formatado com ": " no final.
	 */
	public static String adicionarDoisPontos(String texto) {
		//retira os espaços do começo e final
		texto = texto.trim();

		//adiciona ': ' ao final, caso não tenha
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
