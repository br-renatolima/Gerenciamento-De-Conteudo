package controle;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import dao.TextoDAO;
import modelo.Texto;

@ManagedBean(name="TextoMB")
@RequestScoped
public class TextoMB {
	
	private Texto texto;
	private TextoDAO dao;
	private String release;
	private String agradecimento;
	private List<Texto> textos;
	private String conteudo;
	
	public TextoMB() {
		this.texto = new Texto();
		this.dao = new TextoDAO();
		this.textos = new ArrayList<Texto>();
		
		this.setTextos(dao.ListarTodosTextos());
		this.setRelease();
		this.setAgradecimento();
	}
	
	public Texto getTextoPorId(int id){
		for (Texto texto : textos) {
			if (texto.getId() == id){
				return texto;
			}
		}
		return null;
	}
	
	public TextoDAO getDao() {
		return dao;
	}

	public void setDao(TextoDAO dao) {
		this.dao = dao;
	}

	public Texto getTexto() {
		return texto;
	}

	public void setTexto(Texto texto) {
		this.texto = texto;
	}
	

	public String getRelease() {
		return release;
	}

	public void setRelease() {
		for (Texto texto : textos) {
			if(texto.getId() == 1){
				this.release = texto.getTexto();
			}
		};
	}

	public String getAgradecimento() {
		return agradecimento;
	}

	public void setAgradecimento() {
		for (Texto texto : textos) {
			if(texto.getId() == 1){
				this.agradecimento = texto.getTexto();
			}
		};
	}

	public List<Texto> getTextos() {
		return textos;
	}

	public void setTextos(List<Texto> textos) {
		this.textos = textos;
	}
	
	public String inserir(){
		Texto t = new Texto(this.getConteudo());
		return dao.inserir(t);
	}
	
	public String atualizar(){
		return dao.atualizar(texto);
	}

	public String getConteudo() {
		return conteudo;
	}

	public void setConteudo(String conteudo) {
		this.conteudo = conteudo;
	}

}
