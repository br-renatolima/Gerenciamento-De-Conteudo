package dao;

import modelo.Voto;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Order;

public class VotoDAO {

	private Session session;
	
	public VotoDAO() {
		this.session = HibernateUtil.getSession();
	}
	
	@SuppressWarnings("unchecked")
	public List<Voto> listarTodos(){
		return session.createCriteria(Voto.class).addOrder(Order.desc("id")).list();
	}
	
	public String inserir(Voto voto){
		session.beginTransaction();
		try {
			session.save(voto);
			session.getTransaction().commit();
		} catch (Exception e) {
			return e.getMessage(); 
		}
		return "Voto registrado com sucesso!";
	}
}
