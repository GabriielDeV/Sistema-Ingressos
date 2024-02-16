package appconsole;
import regras_negocio.Fachada;

public class Apagar {
	
	public Apagar() {
		Fachada.inicializar();
		/*try {
			System.out.println("Apagando Time...");
			Fachada.apagarTime("Brasil");
			
		}catch(Exception e) {
			System.out.println(e.getMessage());
			
		}*/
		
		/*try {
			System.out.println("Apagando Jogo...");
			Fachada.apagarJogo(3);
			
		}catch(Exception e) {
			System.out.println(e.getMessage());
			
		}*/
		
		try {
			System.out.println("Apagar Ingresso...");
			Fachada.apagarIngresso(1827);
		}catch(Exception ex) {
			System.out.println(ex.getMessage());
			
		}
		
		System.out.println("Fim do Programa...");
	}
	  
	public static void main(String[] args) {
		new Apagar();
	}
}
