package daojpa;

import java.util.List;

import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import modelo.IngressoGrupo;
import modelo.IngressoTeste;
import modelo.Jogo;

public class DAOIngressoGrupo extends DAO<IngressoGrupo> {

	public IngressoGrupo read(Object chave) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public List<IngressoGrupo> ingressosgrupodojogo (int id) {
		
		try {
			TypedQuery <IngressoGrupo> q = manager.createQuery
					("select ig from IngressoGrupo ig join ig.jogos j where j.id = :id",IngressoGrupo.class);
			q.setParameter("id", id);
			List<IngressoGrupo> ig = q.getResultList();
			if(ig.size() > 0) {
				return ig;
			}else {
				return null;
			}
		}
		catch(NoResultException e) {
			return null;
		}
		
		/*Query q;
		q = manager.query();
		q.constrain(IngressoGrupo.class);
		q.descend("jogos").descend("id").constrain(id);
		List<IngressoGrupo> resultados = q.execute();
		if(resultados.size()>0) {
			return resultados;
		}
		return null;*/
		
		
		
	}
}
