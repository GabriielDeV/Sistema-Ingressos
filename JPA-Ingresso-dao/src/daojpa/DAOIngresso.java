package daojpa;

import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
//import modelo.Ingresso;
import modelo.IngressoTeste;

public class DAOIngresso extends DAO<IngressoTeste> {
	
	public IngressoTeste read (Object chave) {
		try {
			int codigo = (int) chave;
			TypedQuery <IngressoTeste> q = manager.createQuery
					("select i from IngressoTeste i where i.codigo = :codigo",IngressoTeste.class);
			q.setParameter("codigo", codigo);
			return q.getSingleResult();
		}
		catch(NoResultException e) {
			return null;
		}
		
		
		 
		
	}
	
	
	
//	 int codigo = (int) chave;
//     Query q = manager.query();
//     q.constrain(Ingresso.class);
//     q.descend("codigo").constrain(codigo);
//     List<Ingresso> resultado = q.execute();
//     if(resultado.size() > 0){
//         Ingresso i = resultado.get(0);
//         return i;
//     }
//     return null;


}
