/**********************************
 * IFPB - SI
 * POB - Persistencia de Objetos
 * Prof. Fausto Ayres
 *
 */
package regras_negocio;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import daojpa.DAO;
import daojpa.DAOIngresso;
import daojpa.DAOIngressoGrupo;
import daojpa.DAOIngressoIndividual;
import daojpa.DAOJogo;
import daojpa.DAOTime;
import modelo.IngressoGrupo;
import modelo.IngressoIndividual;
import modelo.IngressoTeste;
//import daojpa.DAOVaga;
//import modelo.Ingresso;
import modelo.Jogo;
import modelo.Time;
//import modelo.Vaga;

public class Fachada {
	//private static DAOVaga daovaga = new DAOVaga();
	private static DAOJogo daojogo = new DAOJogo();
	private static DAOTime daotime = new DAOTime();
	private static DAOIngresso daoingresso = new DAOIngresso();
	private static DAOIngressoIndividual daoingressoindividual = new DAOIngressoIndividual();
	private static DAOIngressoGrupo daoingressogrupo = new DAOIngressoGrupo();

	public static void inicializar(){
		DAO.open();
	}
	public static void finalizar(){
		DAO.close();
	}
	
	public static Jogo 	criarJogo(String data, String local, int estoque, double preco, String nometime1, String nometime2)  throws Exception {
		DAO.begin();

		//localizar time1 e time2
		Time time1 = daotime.read(nometime1);
		Time time2 = daotime.read(nometime2);
		ArrayList<Jogo> verificarjogo = daojogo.verificardata(data);

		//verificar regras de negocio
		if(time1 == null || time2 == null) {
			if(time1 == null) {
				throw new Exception(nometime1 + " => Nï¿½o Existe");
			}else {
				throw new Exception(nometime2 + " => Nï¿½o Existe");
			}			
		}	

		//Um jogo nï¿½o pode ter dois times iguais
		if (time1.getNome() == time2.getNome()) {
			throw new Exception ("Nï¿½o pode ter dois times iguais");
		}

		if(verificarjogo == null){
			//criar jogo 
			Jogo jogo = new Jogo(data, local, estoque, preco);


			//relacionar o jogo com os times e vice-versa 
			jogo.setTime1(time1);
			jogo.setTime2(time2);
			time1.adicionar(jogo);
			time2.adicionar(jogo);


			//gravar jogo no banco
			daojogo.create(jogo);
			daotime.update(time1);
			daotime.update(time2);
			DAO.commit();
			//System.out.print(jogo);
			return jogo;
		}else{
			for(Jogo j : verificarjogo){
				String nome1 = j.getTime1().getNome();
				String nome2 = j.getTime2().getNome();

				if(nome1.equals(nometime1) || nome1.equals(nometime2)){
					throw new Exception(nome1 + " JÃ¡ possui jogo nessa data");
				}
				if(nome2.equals(nometime2) || nome2.equals(nometime1)){
					throw new Exception(nome2 + " JÃ¡ possui jogo nessa data");
				}
			}

			//criar jogo 
			Jogo jogo = new Jogo(data, local, estoque, preco);


			//relacionar o jogo com os times e vice-versa 
			jogo.setTime1(time1);
			jogo.setTime2(time2);
			time1.adicionar(jogo);
			time2.adicionar(jogo);


			//gravar jogo no banco
			daojogo.create(jogo);
			daotime.update(time1);
			daotime.update(time2);
			DAO.commit();
			//System.out.print(jogo);
			return jogo;

		}
	}
	
	public static Time 	criarTime(String nome, String origem) throws Exception {
		DAO.begin();
		Time verificartime = daotime.read(nome);

		if(verificartime != null)
			throw new Exception("Time: " + nome + " jï¿½ foi cadastrado");

		//verificar regras de negocio
		//criar o time
		Time time = new Time(nome,origem);

		//gravar time no banco
		daotime.create(time);
		DAO.commit();
		return time;
	}
	
	public static IngressoIndividual criarIngressoIndividual(int id) throws Exception{		
		DAO.begin();
		Jogo jogo = daojogo.read(id);
		if(jogo == null)
			throw new Exception ("ID: " + id + " => Invalido" + "\n" +
					"JOGO NÃƒO EXISTE...");


		//gerar codigo aleatÃ³rio
		//verificar unicididade do codigo no sistema; 

		int codigo;
		IngressoTeste ing;
		do {
			codigo = new Random().nextInt(100000);
			ing = daoingresso.read(codigo);

		} while ((ing != null));

		//criar o ingresso individual 
		IngressoIndividual ingresso = new IngressoIndividual(codigo);

		//relacionar este ingresso com o jogo e vice-versa
		ingresso.setJogo(jogo);
		jogo.adicionar(ingresso);
		//jogo.setEstoque(jogo.getEstoque()-1);

		//gravar ingresso no banco
		daojogo.update(jogo);
		daoingressoindividual.create(ingresso);
		DAO.commit();
		return ingresso;
	}
	
	public static IngressoGrupo	criarIngressoGrupo(int[] ids) throws Exception{
		DAO.begin();
		ArrayList<Jogo> jogos = new ArrayList<>();

		//verificar regras de negocio -> Verificar se o Jogo Existe
		int[] idsJogos = ids;
		for(int i=0; i<idsJogos.length; i++) {
			int id = idsJogos[i];
			Jogo id_ = daojogo.read(id);
			if(id_ == null) {
				jogos.clear();
				throw new Exception ("ID: " + id  + " nï¿½o existe...");	
			}
			jogos.add(id_);


		}

		//gerar codigo aleatï¿½rio 
		int codigo;
		IngressoTeste ing;
		do {
			codigo = new Random().nextInt(10000);
			ing = daoingresso.read(codigo);
		} while ((ing != null));


		//criar o ingresso grupo 
		IngressoGrupo ingresso = new IngressoGrupo(codigo);

		//relacionar este ingresso com os jogos indicados e vice-versa

		for (Jogo j: jogos) {
			j.adicionar(ingresso);
			j.setEstoque(j.getEstoque()-1);
			//ingresso.adicionar(j);
			daojogo.update(j);
			daoingressogrupo.update(ingresso);

		}

		//gravar ingresso no banco
		daoingressogrupo.create(ingresso);
		DAO.commit();
		return ingresso;
	}
	

	public static void	apagarTime(String nome) throws Exception {
		DAO.begin();
		//verificar regras de negocio
		Time t = daotime.read(nome);
		if(t == null) {
			DAO.rollback();
			throw new Exception ("Time: " + nome + " nï¿½o foi criado!!!");
		}

		//Um time nï¿½o poderï¿½ ser excluï¿½do se possuir jogos
		if(t.getjogos().size() != 0) {
			DAO.rollback();
			throw new Exception ("Time: " + nome + " nï¿½o pode ser excluido, pois possui jogos");
		}

		//apagar time no banco
		daotime.delete(t);
		DAO.commit();
	}
	
	public static void	apagarJogo(int id) throws Exception{
		DAO.begin();
		//verificar regras de negocio
		Jogo jogo = daojogo.read(id);
		if(jogo == null) {
			DAO.rollback();
			throw new Exception ("Nao existe nenhum jogo com este id: " + id);
		}

		if(jogo.getIngressos().size() != 0) {
			DAO.rollback();
			throw new Exception ("Jogo nao pode ser excluido! "
					+ "(JOGO POSSUI INGRESSOS VENDIDOS)");
		}

		Time t1 = jogo.getTime1();
		t1.remover(jogo);
		
		Time t2 = jogo.getTime2();
		t2.remover(jogo);
			
			

		//apagar jogo no banco
		daotime.update(t1);
		daotime.update(t2);	
		daojogo.delete(jogo);
		DAO.commit();
	}
	
	public static void	apagarIngresso(int codigo) throws Exception {
		DAO.begin();
		IngressoTeste ingresso = daoingresso.read(codigo);


		if(ingresso == null) {
			throw new Exception("Codigo: " + codigo+ " de ingresso nï¿½o existe no banco");
		}



		if (ingresso instanceof IngressoGrupo grupo) {
			 for (Jogo j : grupo.getJogosT()) {
				j.remover(ingresso);
				j.setEstoque(j.getEstoque()+1);
				//Atualizar ingresso e jogo no banco
				daojogo.update(j);

			}
			daoingressogrupo.delete(grupo);
			
		}
		else 
			if (ingresso instanceof IngressoIndividual individuo) {
				Jogo jogo = individuo.getJogo();
				jogo.remover(ingresso);
				jogo.setEstoque(jogo.getEstoque()+1);
				System.out.println(jogo);
				//Atualizar ingresso e jogo no banco
				daoingressoindividual.delete(individuo);
				daojogo.update(jogo);

			}

		
		DAO.commit();
	}

	
	
	
	
	
	
	
	
	
	
	
	
	public static List<Time> listarTimes(){
		DAO.begin();
		List<Time> times = daotime.readAll();
		DAO.commit();
		return times;
		
	}
	
	public static List<Jogo> listarJogos(String data) throws Exception {
		//retorna todos os jogos
		DAO.open();
		List<Jogo> resultado = daojogo.verificardata(data);
		if(resultado == null) {
			throw new Exception("Nï¿½o existe jogo nesta data: "+data);
		}
		//System.out.println(resultado);
		//retorna todos os ingressos 
		return resultado;
	}
	
	public static List<Jogo> listarJogos() {
		//retorna todos os jogos
		DAO.open();
		List<Jogo> resultado = daojogo.readAll();
		DAO.commit();
		//System.out.println(resultado);
		//retorna todos os ingressos 
		return resultado;
	}
	
	public static Jogo	localizarJogo(int id) throws Exception {
		//retorna o jogo com o id fornecido
		DAO.open();
		Jogo j = daojogo.read(id);
		if(j == null) {
			throw new Exception("Jogo com id: " + id + " nï¿½o existe!!!");
		}

		return j;
	}
	
	public static List<IngressoTeste> listarIngressos() {
		DAO.open();
		List<IngressoTeste> resultado = daoingresso.readAll();
		//System.out.println(resultado);
		//retorna todos os ingressos 
		return resultado;
	}
	
	public static List<Time> joganadata (String data) throws Exception {
		DAO.open();
		List<Time> times = daotime.joganadata(data);
		if(times == null) {
			throw new Exception ("Nenhum time joga na data: " + data);
		}
		return times;
	}
	
	public static List<Jogo> ingressodojogo(int codigo) throws Exception {
		DAO.open();
		List<Jogo> j = daojogo.ingresso(codigo);
		if(j == null){
			throw new Exception ("Ingesso: " + codigo + " não existe!!!"); 
		}
		return j;
	}
	
	public static List<Jogo> jogosdotime (String time) throws Exception {
		DAO.open();
		List<Jogo> jogos = daojogo.jogosdotimeCasa(time);
		//List<Time> timesdobanco = listarTimes();	
		Time t;
		
		/*for (Time t : timesdobanco) {
			if(t.getNome().equals(time)) {
				throw new Exception (time + " não tem jogos em casa");
			}
		}*/
		if(jogos == null){	
			t = daotime.read(time);
			if(t != null) {
				throw new Exception (time + " não tem jogos em casa");			
			}
			
			throw new Exception (time + " não existe no banco"); 
			
		}
		return jogos;	
	}
	
	public static List<IngressoGrupo> ingressosgrupodojogo (int idjogo) throws Exception {
		DAO.open();
		List<IngressoGrupo> i = daoingressogrupo.ingressosgrupodojogo(idjogo);
		ArrayList<IngressoGrupo> ingressofiltrado = new ArrayList<IngressoGrupo>();
		
		if(i == null){
			throw new Exception ("Jogo de id: " + idjogo + " não tem ingressos Grupo vendidos"); 
		}
		
		
		for(IngressoGrupo ig : i) {
			for (int a = 0; a < ig.getJogosT().size(); a++){
				if(ig.getJogosT().get(a).getId() == idjogo) {
					Jogo igrupo = ig.getJogosT().get(a);
					ig.getJogosT().clear();
					ig.adicionarUmJogo(igrupo);
					ingressofiltrado.add(ig);
				}
				
			}
		}	
		return ingressofiltrado;
	}
	
	public static List<IngressoIndividual> ingressoindividualdojogo (int idjogo) throws Exception {
		DAO.open();
		List<IngressoIndividual> i = daoingressoindividual.ingressosindividualdojogo(idjogo);
		if(i == null){
			throw new Exception ("Jogo de id: " + idjogo + " não tem ingressos individuais vendidos"); 
		}
		return i;
	}

}
