package daojpa;
import java.util.List;

import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
//import modelo.Vaga;

import java.util.ArrayList;
import java.util.List;

//import com.db4o.query.Candidate;
//import com.db4o.query.Evaluation;
//import com.db4o.query.Query;

import modelo.Jogo;

public class DAOJogo extends DAO<Jogo>{	
	
		/*public void create(Jogo obj) {
			int id = super.gerarId(); //Gera um id para o Jogo (.gerarId vem da classe DAO)
			obj.setId(id); //Seta o id gerado no jogo criado
			manager.store(obj);
		}*/
		
		public Jogo read(Object chave) {
			try {
				int id = (int) chave;
				TypedQuery <Jogo> q = manager.createQuery("select j from Jogo j where j.id = :id", Jogo.class);
				q.setParameter("id", id);
				return q.getSingleResult();	
			}catch(NoResultException e) {
				return null;
				
			}
		}	
		
		public ArrayList <Jogo> verificardata (String data){
			ArrayList<Jogo> jogos = new ArrayList<>();
			TypedQuery <Jogo> q = manager.createQuery("select j from Jogo j where j.data = :data", Jogo.class);
			q.setParameter("data", data);
			List<Jogo> resultado =  q.getResultList();
			if(resultado.size() > 0) {
				jogos.addAll(resultado);
				return jogos;
			}
			else {
				return null;
			}
			
			
		}

		/*public ArrayList<Jogo> verificardata (Object chave){
			String data = (String) chave;
			Query q = manager.query();
			q.constrain(Jogo.class);
			q.descend("data").constrain(data);
			List<Jogo> resultado = q.execute();
			ArrayList<Jogo> jogos = new ArrayList<>();
			if(resultado.size()>0){
				jogos.addAll(resultado);
				return jogos;
			}else{
				return null;
			}				
		}
		
		public List<Jogo> verificaringressos(){
			int n = 0;
			Query q;
			q = manager.query();
			q.constrain(Jogo.class);
			q.constrain(new Filtro(n));
			return q.execute();
			
		}
		
		//classe interna
/*		class Filtro implements Evaluation {
			private int n;
			public Filtro(int n) {
				this.n = n;
			}
			public void evaluate(Candidate candidate) {
				Jogo j = (Jogo) candidate.getObject();
				if(j.getIngressos().size() == n) 
					candidate.include(true); 
				else		
					candidate.include(false);
			}
		}*/
		
		public List<Jogo> jogosdotimeCasa (String time){
			
			try {
				TypedQuery <Jogo> q = manager.createQuery("select j from Jogo j join j.time1 t where t.nome = :nome ",Jogo.class);
				q.setParameter("nome", time);
				List<Jogo> jogos = q.getResultList();
				if(jogos.size() > 0) {
					return jogos;
				}else {
					return null;
				}
			}catch(NoResultException e) {
				return null;
			}
			
			/*Query q;
			q = manager.query();
			q.constrain(Jogo.class);
			q.descend("time1").descend("nome").constrain(time);
			List <Jogo> jogos = q.execute();
			if(jogos.size() > 0) {
				return jogos;
			}
			return null;
			*/
		}
		
		public List<Jogo> ingresso (int codigo) {
			try {
				TypedQuery <Jogo> q = manager.createQuery("select j from Jogo j join j.ingressos i where i.codigo = :codigo ",Jogo.class);
				q.setParameter("codigo", codigo);
				List<Jogo> jogos = q.getResultList();
				if(jogos.size() > 0) {
					return jogos;
				}else {
					return null;
				}
			}catch(NoResultException e) {
				return null;
			}
			
		}
		
		
}
