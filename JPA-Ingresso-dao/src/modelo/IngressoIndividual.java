/**********************************
 * IFPB - Curso Superior de Tec. em Sist. para Internet
 * Persistencia de objetos
 * Prof. Fausto Maranh�o Ayres
 **********************************/
package modelo;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("individual")
public class IngressoIndividual extends IngressoTeste {
	private Jogo jogo;
	
	public IngressoIndividual() {}
	
	public IngressoIndividual(int codigo) {
		super(codigo);
	}

	public double calcularValor() {
		return 1.2 * jogo.getPreco();
	}

	public Jogo getJogo() {
		return jogo;
	}

	public void setJogo(Jogo jogo) {
		this.jogo = jogo;
		jogo.setEstoque(jogo.getEstoque() - 1 );
	}
	
	public void remover(Jogo j) {
		jogo.getIngressos().remove(j);
	}

	@Override
	public String toString() {
		return "codigo=" + codigo + ", jogo=" + jogo.getId();
	}
	
	
	
}
