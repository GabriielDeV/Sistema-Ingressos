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
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Jogo {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String data;
	private String local;
	private int estoque;
	private double preco;
	
	
	//private ArrayList<Jogo> jogos = new ArrayList<>();
	
	//Relacionamento Unidireciaonal 1:1
	@OneToOne(cascade={CascadeType.PERSIST, CascadeType.MERGE}, fetch=FetchType.LAZY)
	//@JoinColumn (name="Time1")
	private Time time1;
	@OneToOne(cascade={CascadeType.PERSIST, CascadeType.MERGE}, fetch=FetchType.LAZY)
	//@JoinColumn (name="Time2")
	private Time time2;
	
	//Relacionamento Unidireciaonal 1:*
	@ManyToMany(cascade={CascadeType.PERSIST, CascadeType.MERGE}, fetch=FetchType.LAZY)
	//@JoinColumn (name="idJogo")
	private ArrayList<IngressoTeste> ingressos = new ArrayList<>();
	
	/*@ManyToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinColumn (name="tipoIngresso")
	private Ingresso tipoIngresso;*/
	
	public Jogo() {}
	


	public Jogo(String data, String local, int estoque, double preco) {
		//id ser� gerado pelo banco;
		this.data = data;
		this.local = local;
		this.estoque = estoque;
		this.preco = preco;
		
	}

	/*public void adicionarjogo(Jogo j){
		this.jogos.add(j);
	}*/
	
	public void adicionar(IngressoTeste i){
		ingressos.add(i);
	}
	public void remover(IngressoTeste i){
		int index = this.ingressos.indexOf(i);
		ingressos.remove(index);
	}

	public IngressoTeste localizar(int codigo){
		for(IngressoTeste i : ingressos){
			if(i.getCodigo() == codigo)
				return i;
		}
		return null;
	}
	
	/*public void setTipoIngresso (String tipo) {
		this.tipoIngresso = tipo;
	}*/

	public double obterValorArrecadado() {
		double soma=0;
		for(IngressoTeste i : ingressos)
			soma = soma + i.calcularValor();

		return soma;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public double getPreco() {
		return preco;
	}
	public void setPreco(double preco) {
		this.preco = preco;
	}

	public String getLocal() {
		return local;
	}

	public void setLocal(String local) {
		this.local = local;
	}

	public int getEstoque() {
		return estoque;
	}

	public void setEstoque(int estoque) {
		this.estoque = estoque;
	}

	public Time getTime1() {
		return time1;
	}

	public Time getTime2() {
		return time2;
	}
	
	public void setTime1(Time time1) {
		this.time1 = time1;
	}

	public void setTime2(Time time2) {
		this.time2 = time2;
	}

	public ArrayList<IngressoTeste> getIngressos() {
		return ingressos;
	}
	
	/*public int getIngressoexpecifico(int codigo) {
		for (IngressoTeste i : ingressos) {
			if (i.getCodigo() == codigo) {
				return i.getCodigo();
			}
		}
		return (Integer) null;
		
	}*/
	@Override
	public String toString() {

		String texto = "id=" + id + ", data=" + data + ", local=" + local + ", estoque=" + estoque + ", preco=" + preco
				+ ", time1=" + time1.getNome() + " x time2=" + time2.getNome();

		texto += "\ningressos:";
		for(IngressoTeste i : ingressos)
			texto += i.getCodigo() + ",";
		return texto;
	}


}
