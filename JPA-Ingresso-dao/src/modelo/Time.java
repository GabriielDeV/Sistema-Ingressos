/**********************************
 * IFPB - Curso Superior de Tec. em Sist. para Internet
 * Persistencia de objetos
 * Prof. Fausto Maranh�o Ayres
 **********************************/

package modelo;
import java.util.ArrayList;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Time {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String nome;
	private String origem;
	
	@OneToMany(cascade={CascadeType.PERSIST, CascadeType.MERGE}, fetch=FetchType.LAZY)
	//@JoinColumn(name="IdTime")
	private ArrayList <Jogo> jogos;
	
	public Time() {}
	
	public Time(String nome, String origem) {
		super();
		this.nome = nome;
		this.origem = origem;
		this.jogos = new ArrayList<Jogo>();
	}

	/*public double obterValorArrecadado() {
		double soma=0;
		for(Jogo j : jogos)
			soma = soma + j.obterValorArrecadado();
		
		return soma;
	}*/

	public void adicionar(Jogo j) {
		this.jogos.add(j);
	}

	public void remover(Jogo j) {
		int index = this.jogos.indexOf(j);
		this.jogos.remove(index);
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getOrigem() {
		return origem;
	}

	public void setOrigem(String origem) {
		this.origem = origem;
	}
	
	public ArrayList<Jogo> getjogos (){
		return jogos;
	}

	@Override
	public String toString() {
		String texto = "nome=" + nome + ", origem=" + origem ;
		
		texto += 	"\njogos: ";
		for(Jogo j : jogos)
			texto += "Id Jogo: " + j.getId() +"\n " +
					"Data: "+ j.getData() + "\n " +
					 "Local: "+ j.getLocal() + "\n" + 
					 "Confronto: " +  j.getTime1().nome + " x " + j.getTime2().nome + "\n" 
					 +"\n";
		return texto + "\n--------------------------";
	}

	
	
}
