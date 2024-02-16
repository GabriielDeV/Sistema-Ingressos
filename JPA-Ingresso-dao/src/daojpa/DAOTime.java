package daojpa;

import java.util.List;

import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import modelo.Time;

public class DAOTime extends DAO<Time> {

	public Time read(Object chave) {
		try {
			String nome = (String) chave;
			TypedQuery <Time> q = manager.createQuery("select t from Time t where t.nome = :nome", Time.class);
			q.setParameter("nome", nome);
			return q.getSingleResult();
		}catch(NoResultException e) {
			return null;
		}
	}
	
	public List<Time> joganadata (String data){
		
		try {
			TypedQuery<Time> q = manager.createQuery("select t from Time t join t.jogos j where j.data = :data",Time.class);
			q.setParameter("data", data);
			List<Time> t = q.getResultList();
			if(t.size() > 0) {
				return t;
			}else {
				return null;
			}
			
		}catch(NoResultException e) {
			return null;
		}
		
	}
		/*Query q;
		q = manager.query();
		q.constrain(Time.class);
		q.descend("jogos").descend("data").constrain(data);
		List<Time> times = q.execute();
		if(times.size() > 0) {
			return times;		
		}
		
		return null;
		
	}*/

}
