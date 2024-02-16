package daojpa;

import java.util.List;

import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import modelo.IngressoIndividual;
import modelo.Jogo;

public class DAOIngressoIndividual extends DAO<IngressoIndividual> {

	public IngressoIndividual read(Object chave) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public List<IngressoIndividual> ingressosindividualdojogo (int id) {
		try {
			TypedQuery <IngressoIndividual> q = manager.createQuery
					("select i from IngressoIndividual i join i.jogos j where j.id = :codigo",IngressoIndividual.class);
			q.setParameter("codigo", id);
			List<IngressoIndividual> i = q.getResultList();
			if(i.size() > 0) {
				return i;
			}else {
				return null;
			}
		}
		catch(NoResultException e) {
			return null;
		}
	}

}
