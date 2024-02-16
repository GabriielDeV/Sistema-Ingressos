/**********************************
 * IFPB - Curso Superior de Tec. em Sist. para Internet
 * Persistencia de objetos
 * Prof. Fausto Maranh�o Ayres
 **********************************/
package modelo;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("grupo")
public class IngressoGrupo extends IngressoTeste {
	

	public IngressoGrupo() {}

	public IngressoGrupo(int codigo) {
		super(codigo);
	}

	public double calcularValor() {
		double soma=0;
		for(Jogo j : getJogosT())
			soma = soma + j.getPreco();
		
		return 0.9 * soma;
	}
	
	public void adicionar(Jogo j){
		jogos.add(j);
		j.setEstoque(j.getEstoque() - 1 );	
	}
	
	public void adicionarUmJogo(Jogo j){
		jogos.add(j);
	}

	public void remover(Jogo j){
		jogos.remove(j);
	}
    
	public Jogo localizar(int id){
		for(Jogo j : jogos){
			if(j.getId() == id)
				return j;
		}
		return null;
	}



	@Override
	public String toString() {
		String texto = "codigo=" + codigo + ", jogos:";
		
		for(Jogo j : jogos)
			texto += j.getId() + ",";
		
		return texto;
	}
	
	
}
