import java.util.Scanner;

/** ESQUELETO BASE PARA UM RPG QUIZ-STYLE NO TERMINAL
 * A ideia aqui é apenas ter a mecânica de como o jogo
 * se comportaria, sem preocupação com a história ou os
 * dados utilizados por enquanto
*/
public class EsqueletoRPG {
	
	public static final int ALTURA_TELA = 30;
	public static void exibirLogo() {
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
		exibirTelaTitulo();
	}

	/**
	 * Exibe a tela de título do jogo
	 * com o logo e o menu inicial.
	 */
	public static void exibirTelaTitulo() {
		boolean continuarNoMenu = true;

		while(continuarNoMenu) {
			exibirLogo();
			exibirMenuTitulo();

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
	 * Exibe o MENU INICIAL com as opções para o usuário.
	 */
	public static void exibirMenuTitulo() {
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
		exibirDesafio1();
	}

	/**
	 * Exibe a história introdutório do jogo
	 */
	public static void introducao() {
		//TODO: desenvolver a história de introdução do jogo
		System.out.println("Tudo começa com... o roteirista escrevendo a PORCARIA da história.\nFim da introdução!");
	}

	/**
	 * Exibe para o usuário o primeiro desafio do jogo
	 */
	public static void exibirDesafio1() {
		String pergunta = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus interdum, tortor sed bibendum bibendum, arcu orci?";
		String op1 = "Lorem ipsum 1";
		String op2 = "Lorem ipsum 2";
		String op3 = "Lorem ipsum 3";
		String op4 = "Lorem ipsum 4";

		System.out.println("DESAFIO 1");
		System.out.println("---------");
		System.out.println(pergunta);
		System.out.println("---------");
		System.out.println("1. " + op1);
		System.out.println("2. " + op2);
		System.out.println("3. " + op3);
		System.out.println("4. " + op4);
		System.out.println();

		final int RESPOSTA = 2;
		int op;
		Scanner entrada = new Scanner(System.in);
		boolean respostaValida = false;

		do { //repete enquanto a resposta não for válida
			System.out.print("Diga sua resposta (ou 0 para regras): ");
			op = entrada.nextInt();

			respostaValida = validarResposta(op);

			if(!respostaValida){
				System.out.println("Opção inválida!");
			}else if(!(op == RESPOSTA)) {
				/*TODO: PERDE VIDA E VERIFICA SE DEU GAME OVER*/
				System.out.println("Você sofreu um ataque por errar a resposta e perdeu uma vida!");
				System.out.println("Vc tem X vidas restantes.\n");
			}

		}while(!respostaValida || !(op == RESPOSTA));

		//passou do desafio com vitória
		System.out.println("Você completou a primeira missão com sucesso!");
		System.out.println("Mas ainda não acabou, existem novos desafios pela frente!");
		System.out.println("Continua...");
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
		System.out.printf("Tecle ENTER para continuar...");

		pause.nextLine();
	}

	/**
	 * Verifica se a resposta inserida pelo jogador é válida
	 * @param resposta número inteiro representando a opção que o jogador escolheu
	 * @return retorna true caso a resposta seja válida e false caso contrário
	 */
	public static boolean validarResposta(int resposta) {
		return resposta > 0 && resposta <= 4;
	}
}
