package dao;

import java.util.List;

import modelo.Video;

import org.hibernate.Session;
import org.hibernate.criterion.Order;

public class VideoDAO {
	private Session session;
	
	public VideoDAO(){
		this.session = HibernateUtil.getSession();
	}
	
	@SuppressWarnings("unchecked")
	public List<Video> listarTodos(){
		return session.createCriteria(Video.class).addOrder(Order.desc("id")).list();
	}
	
	public String inserir(Video video){
		session.beginTransaction();
		try {
			session.save(video);
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			return e.getMessage();
		}
		return "Salvo com sucesso!";
	}

	public String atualizar(Video video) {
		session.beginTransaction();
		try {
			session.update(video);
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			return e.getMessage();
		}
		return "Salvo com sucesso!";		
	}

	public String remover(Video video) {
		session.beginTransaction();
		try {
			session.delete(video);
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			return e.getMessage();
		}
		return "Removido com sucesso!";		
	}
	
}
