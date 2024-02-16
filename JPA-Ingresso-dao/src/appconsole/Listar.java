package appconsole;
/**********************************
 * IFPB - Curso Superior de Tec. em Sist. para Internet
 * POB - Persistencia de Objetos
 * Prof. Fausto Ayres
 *
 */

import modelo.IngressoTeste;
import modelo.Jogo;
import modelo.Time;
import regras_negocio.Fachada;


public class Listar {
	public Listar(){
		Fachada.inicializar();
		/*try {
			System.out.println("\n---listar times---");  
			for (Time t : Fachada.listarTimes())
				System.out.println(t);
			
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		System.out.println("\n---listar jogos---");
		for (Jogo j : Fachada.listarJogos())
			System.out.println(j);*/
		
		System.out.println("\n---listar ingressos---");
		for (IngressoTeste i : Fachada.listarIngressos())
			System.out.println(i);

		Fachada.finalizar();
		System.out.println("Fim do programa...");
		}

	//=================================================
	public static void main(String[] args) {
		new Listar();
	}
}


