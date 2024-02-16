package appconsole;
/**********************************
 * IFPB - SI
 * Persistencia de Objetos
 * Prof. Fausto Ayres
 **********************************/

import regras_negocio.Fachada;

public class Cadastrar {

	public Cadastrar(){
			//Cadastrar Jogos
			Fachada.inicializar();
			try {		
				System.out.println("Cadastrando Times...");
				Fachada.criarTime("Bayer", "al");
				Fachada.criarTime("City", "in");
				Fachada.criarTime("Flamengo", "br");
				Fachada.criarTime("Corinthians", "br");
				Fachada.criarTime("Argentina", "ar");
			}
			catch(Exception ex) {
				System.out.println("problema ao criar time-->"+ex.getMessage());
			}
			
			try {
				System.out.println("Cadastrando Jogos...");
				Fachada.criarJogo("11/04/2023", "Inglaterra", 10000, 20, "Bayer", "City");
				Fachada.criarJogo("15/04/2023", "maracanã", 10000, 20, "Flamengo", "Corinthians");
				Fachada.criarJogo("02/01/2022", "camp nou", 10000, 20.0, "City", "Flamengo");
				Fachada.criarJogo("01/04/2022", "camp nou", 10000, 20.0, "Corinthians", "Bayer");
				//Fachada.criarJogo("02/01/2022", "camp nou", 10000, 20.0, "Chelsea", "Real Madrid");
				//Fachada.criarJogo("01/01/2022", "camp nou", 10000, 20.0, "Chelsea", "Flamengo");*/
			}
			catch(Exception ex) {
				System.out.println("problema ao criar jogo-->"+ex.getMessage());
			}
			
			try {
				System.out.println("Criando Ingressos Individual...");
				Fachada.criarIngressoIndividual(4);		//id do jogo
				Fachada.criarIngressoIndividual(1);	//id do jogo
				Fachada.criarIngressoIndividual(2);	//id do jogo
				Fachada.criarIngressoIndividual(3);	//id do jogo
				Fachada.criarIngressoIndividual(1);		//id do jogo
				Fachada.criarIngressoIndividual(1);	//id do jogo*/
			}
			catch(Exception ex) {
				System.out.println("problema ao criar ingresso individual-->"+ex.getMessage());
			}
			
			try {
				System.out.println("Criando Ingressos Grupo...");
				Fachada.criarIngressoGrupo(new int[]{1,4} );		//id dos jogos
				Fachada.criarIngressoGrupo(new int[]{2,1,4} );		//id dos jogos
				Fachada.criarIngressoGrupo(new int[]{1,2,3} );		//id dos jogos
			}
			catch(Exception ex) {
				System.out.println("problema ao criar ingresso grupo-->"+ex.getMessage());
			}

		Fachada.finalizar();
		System.out.println("fim do programa");
	}

	
	//=================================================
	public static void main(String[] args) {
		new Cadastrar();
	}
}


