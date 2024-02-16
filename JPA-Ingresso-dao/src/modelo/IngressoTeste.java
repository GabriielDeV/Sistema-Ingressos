/**********************************
 * IFPB - Curso Superior de Tec. em Sist. para Internet
 * Persistencia de objetos
 * Prof. Fausto Maranh�o Ayres
 **********************************/

package modelo;

import java.util.ArrayList;

import jakarta.persistence.CascadeType;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_ingresso")
public abstract class IngressoTeste  {
	@Id
	protected int codigo;
	
	@ManyToMany(mappedBy = "ingressos",cascade={CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
	//@JoinColumn(name="idjogo")
	protected ArrayList<Jogo> jogos = new ArrayList<>();
	
	public IngressoTeste (){}
	
	public IngressoTeste(int codigo) {
		this.codigo = codigo;
	}

	public abstract double calcularValor();

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	
	public ArrayList<Jogo> getJogosT (){
		return this.jogos;
	}
}
