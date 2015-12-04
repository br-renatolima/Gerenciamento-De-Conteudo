package dao;

import java.util.List;

import modelo.Evento;

import org.hibernate.Session;
import org.hibernate.criterion.Order;

public class EventoDAO implements IDao{
	
	String retorno = "";
	private Session session;
	
	public EventoDAO(){
		this.session =  HibernateUtil.getSession();
	}
	
	@SuppressWarnings("unchecked")
	public List<Evento> listarTodos(){
		return session.createCriteria(Evento.class).addOrder(Order.asc("data")).list();
		}
	
	
	public String salvar(Object obj){
		Evento objEvento = (Evento) obj;
		session.beginTransaction();		
		try{
			
			session.save(objEvento);
			session.getTransaction().commit();
			
		}catch (Exception error){
			session.getTransaction().rollback();
			retorno =  error.getMessage();	
			return retorno;
		}
		retorno = "Salvo com sucesso!";
		return retorno;
	
	}
	
	public String atualizar(Object obj) {
		Evento objEvento = (Evento) obj;
		session.beginTransaction();
		try{
			session.update(objEvento);
			session.beginTransaction().commit();

		}catch (Exception error){
			session.getTransaction().rollback();
			retorno = error.getMessage();
			return retorno;
		}
		retorno = "Atualizado com sucesso!";
		return retorno;
	}

	@Override
	public String remover(Object obj) {
		return null;
	}

}
