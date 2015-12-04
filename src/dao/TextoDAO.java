package dao;

import java.util.List;

import modelo.Texto;

import org.hibernate.HibernateException;
import org.hibernate.Session;

public class TextoDAO {
	private Session session;

	public TextoDAO() {
		this.session = HibernateUtil.getSession();
	}

	@SuppressWarnings("unchecked")
	public List<Texto> ListarTodosTextos() {
		return session.createCriteria(Texto.class).list();
	}
	
	public String inserir(Texto texto){
		session.beginTransaction();
		try {
			session.save(texto);
			session.getTransaction().commit();
		} catch (HibernateException e) {
			return e.getMessage();
		}
		return "Salvo com sucesso!";
	}
	
	public String atualizar(Texto texto){
		session.beginTransaction();
		try {
			session.update(texto);
			session.getTransaction().commit();
		} catch (HibernateException e) {
			return e.getMessage();
		}
		return "Atualizado com sucesso!";
	}

}
