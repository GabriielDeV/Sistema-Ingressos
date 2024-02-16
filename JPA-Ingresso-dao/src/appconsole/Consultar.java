package appconsole;
/**********************************
 * IFPB - SI
 * Persistencia de Objetos
 * Prof. Fausto Ayres
 **********************************/

import java.util.Scanner;

import modelo.IngressoGrupo;
import modelo.IngressoIndividual;
import modelo.Jogo;
import modelo.Time;
import regras_negocio.Fachada;

public class Consultar {

	public Consultar(){
		Fachada.inicializar();
		
		/*try {
			System.out.println("Times que jogam na data...");
			for(Time t : Fachada.joganadata("11/07/2023")) {
				System.out.println(t.getNome());		
			}
			
		}catch(Exception e) {
			System.out.println(e.getMessage());	
			
		}*/
		
		/*try {
			System.out.println("Jogos do ingresso...");
			for(Jogo j : Fachada. ingressodojogo(16)) {
				System.out.println(j);		
			}
			
		}catch(Exception e) {
			System.out.println(e.getMessage());	
			
		}*/
		
		/*try {
			System.out.println("times que jogam em casa...");
			for(Jogo j : Fachada.jogosdotime("aaa")) {
				System.out.println(j);		
			}
			
		}catch(Exception e) {
			System.out.println(e.getMessage());	
		}*/
		
		/*try {
			System.out.println("Ingressos Grupo de um jogo...");
			for(IngressoGrupo i : Fachada.ingressosgrupodojogo(1)) {
				for(Jogo j : i.getJogosT()) {
					System.out.println(i.getCodigo() + ": "+ j.getTime1().getNome() + " x " + j.getTime2().getNome());					
				}
			}	
		}catch(Exception e){
			System.out.println(e.getMessage());	
		}*/
		
		
		try {
			System.out.println("Ingressos individuais do Jogo:");
			for (IngressoIndividual i : Fachada.ingressoindividualdojogo(1)){
				System.out.println(i.getCodigo());
				
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
//		
//		try {
//			Scanner teclado = new Scanner(System.in);
//			System.out.println("didite o id:");
//			int id = Integer.parseInt(teclado.nextLine());
//			System.out.println("didite o nome do passageiro:");
//			String nome = teclado.nextLine();
//			
//			Fachada.reservarVaga(id, nome);
//			System.out.println("reservou vaga 3 pro joao");
//		}
//		catch (Exception e) {
//			System.out.println(e.getMessage());
//		}
		Fachada.finalizar();
		System.out.println("fim do programa");
	}



	//=================================================
	public static void main(String[] args) {
		new Consultar();
	}
}

