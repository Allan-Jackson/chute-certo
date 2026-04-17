import java.util.Scanner;

/** ESQUELETO BASE PARA UM RPG QUIZ-STYLE NO TERMINAL
 * A ideia aqui é apenas ter a mecânica de como o jogo
 * se comportaria, sem preocupação com a história ou os
 * dados utilizados por enquanto
*/
public class EsqueletoRPG {
	
	public static final int ALTURA_TELA = 30;
	
	
	public static void main(String[] args) {
		exibirMenuInicial();
	}
	
	/**
	 * Exibe o MENU INICIAL para o usuário.
	 * Aqui o usuário tem a opção de começar o jogo,
	 * ler as regras ou sair do terminal
	 * Se a opção for inválida o menu é exibido outra vez
	 */
	public static void exibirMenuInicial() {
		
		 
		System.out.println("NOME DO JOGO");
		System.out.println("------------");
		System.out.println("1 - Iniciar jogo");
		System.out.println("2 - Ler regras");
		System.out.println("3 - Sair");
		
		Scanner entrada = new Scanner(System.in);
		System.out.print("Escolha: ");
		int op = entrada.nextInt();

		limparTela();

		switch(op) {
			case 1:
				/*TODO: INICIAR JOGO*/
				iniciarJogo();
				break;
			case 2:
				/*TODO: MOSTRAR AS REGRAS*/
				//código provisório com msg e volta para o menu inicial
				System.out.println("\n\nEis aí as regras:\nHAHAHAHAHAHAHA!\n\n");
				exibirMenuInicial();
				break;
			case 3:
				System.out.println("\n\nAté mais!\n\n");
				System.exit(0);
				break;
			default:
				exibirMenuInicial();
		}
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
			System.out.print("Diga sua resposta: ");
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
	 * Verifica se a resposta inserida pelo jogador é válida
	 * @param resposta número inteiro representando a opção que o jogador escolheu
	 * @return retorna true caso a resposta seja válida e false caso contrário
	 */
	public static boolean validarResposta(int resposta) {
		return resposta > 0 && resposta <= 4;
	}
}
