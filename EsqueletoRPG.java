import java.util.Random;
import java.util.Scanner;

/** ESQUELETO BASE PARA UM RPG QUIZ-STYLE NO TERMINAL
 * A ideia aqui é apenas ter a mecânica de como o jogo
 * se comportaria, sem preocupação com a história ou os
 * dados utilizados por enquanto
*/
public class EsqueletoRPG {
	
	public static final int ALTURA_TELA = 30;

	public static int numeroDesafio;
	public static String perguntaDesafio;
	public static String opcao1;
	public static String opcao2;
	public static String opcao3;
	public static String opcao4;
	public static int resposta = 2;

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


	/**
	 * Configura variáveis e reseta valores necessários para jogo.
	 */
	public static void setup() {
		//TODO: zerar pontuação
		embaralharQuestoes(PERGUNTAS, ALTERNATIVAS, RESPOSTAS);
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
	
	public static void main(String[] args) {
		telaTitulo();
	}

	/**
	 * Exibe a tela de título do jogo
	 * com o logo e o menu inicial.
	 */
	public static void telaTitulo() {
		boolean continuarNoMenu = true;

		while(continuarNoMenu) {
			desenharLogo();
			desenharMenuTitulo();

			Scanner entrada = new Scanner(System.in);
			System.out.print("Escolha: ");

			//TODO: criar método para realizar a leitura de dados do usuário com a devida validação e tratamento de erros
			int op = entrada.nextInt();

			limparTela();

			switch (op) {
				case 1:
					iniciarJogo();
					continuarNoMenu = false;
					break;
				case 2:
					exibirRegras();
					break;
				case 3:
					sair();
			}
		}
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
		introducao();
		limparTela();
		desafio1();
	}

	/**
	 * Exibe a história introdutório do jogo
	 */
	public static void introducao() {
		Scanner pause = new Scanner(System.in);
		//TODO: desenvolver a história de introdução do jogo
		System.out.println("Tudo começa com... o roteirista escrevendo a PORCARIA da história.\nFim da introdução!");

		System.out.println("\nTecle ENTER para continuar...");
		pause.nextLine();
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
				limparTela();
				exibirRegras();
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
				limparTela();
				exibirRegras();
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
	 * Exibe as regras do jogo para o usuário
	 * IDEA: as regras podem ser verificadas durante o próprio jogo
	 * e deve voltar para a mesma tela que estava
	 */
	private static void exibirRegras() {
		Scanner pause = new Scanner(System.in);

		//código provisório com msg e volta para o menu inicial
		System.out.println("\n\nEis aí as regras:\nHAHAHAHAHAHAHA!\n\n");
		System.out.println("1. Eis aí as regras:\nHAHAHAHAHAHAHA!");
		System.out.println("2. Eis aí as regras:\nHAHAHAHAHAHAHA!");
		System.out.println("3. Eis aí as regras:\nHAHAHAHAHAHAHA!");
		System.out.println("4. Eis aí as regras:\nHAHAHAHAHAHAHA!");
		System.out.println("\n\nEis aí as regras:\nHAHAHAHAHAHAHA!\n\n");

		System.out.println("Tecle ENTER para continuar...");
		pause.nextLine();

		limparTela();
	}

	/**
	 * Exibe uma mensagem para o usuário e encerra o jogo.
	 */
	public static void sair() {
		System.out.println("\n\nAté mais!\n\n");
		System.exit(0);
	}

	/**
	 * Verifica se a resposta inserida pelo jogador é válida
	 * @param resposta número inteiro representando a opção que o jogador escolheu
	 * @return retorna true caso a resposta seja válida e false caso contrário
	 */
	public static boolean validarResposta(int resposta) {
		return resposta >= 0 && resposta <= 4;
	}
}
