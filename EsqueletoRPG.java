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
					System.out.println("\n\nAté mais!\n\n");
					System.exit(0);
					break;
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
		perguntaDesafio = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus interdum, tortor sed bibendum bibendum, arcu orci?";

		opcao1 = "Alternativa primeira";
		opcao2 = "Alternativa segunda";
		opcao3 = "Alternativa terceira";
		opcao4 = "Alternativa quarta";

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
		perguntaDesafio = "Lorem ipsum SEGUNDO, consectetur adipiscing elit. Vivamus interdum, tortor sed bibendum bibendum, arcu orci?";

		opcao1 = "Primeira alternativa";
		opcao2 = "Segunda alternativa";
		opcao3 = "Terceira alternativa";
		opcao4 = "Quarta alternativa";

		resposta = 4;

		int op;
		Scanner entrada = new Scanner(System.in);

		boolean respostaValida = false;

		do { //repete enquanto a resposta não for válida

			desenharDesafio();

			System.out.print("Diga sua resposta (ou 0 para regras): ");
			op = entrada.nextInt();

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
		System.out.println("Você completou a segunda missão com sucesso!");
		System.out.println("Mas ainda não acabou, existem novos desafios pela frente!");
		System.out.println("Continua...");
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
	 * Verifica se a resposta inserida pelo jogador é válida
	 * @param resposta número inteiro representando a opção que o jogador escolheu
	 * @return retorna true caso a resposta seja válida e false caso contrário
	 */
	public static boolean validarResposta(int resposta) {
		return resposta >= 0 && resposta <= 4;
	}
}
