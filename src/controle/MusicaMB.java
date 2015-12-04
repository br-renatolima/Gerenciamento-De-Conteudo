package controle;

import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import dao.MusicaDAO;
import modelo.Musica;

@ManagedBean(name="MusicaMB")
@RequestScoped
public class MusicaMB {
	
	private List<Musica> musicas = new ArrayList<Musica>();
	private MusicaDAO dao = new MusicaDAO();
	private String codigo;
	private String nome;
	private Musica musica;

	public MusicaMB() {
		this.setMusicas(dao.ListarTodas());
		
		if (musica == null) {
			// obtem parametro
			ExternalContext ctx = FacesContext.getCurrentInstance().getExternalContext();
	        String idParam = ctx.getRequestParameterMap().get("id");

			if (idParam != null && !idParam.equals("")) {
				try {
					this.musica = this.getMusicaPorId(Integer.parseInt(idParam));
				} catch(NumberFormatException e) {
					// log 
				}
			}
			
			if (this.musica == null) {
				this.musica = new Musica();
			}
		}
		
	}

	public Musica getMusicaPorId(int id) {
		for (Musica musica : musicas) {
			if(musica.getId() == id){
				return musica;
			}			
		}
		return null;
	}
	
	public String atualizar(){
		this.dao.atualizar(musica);
		return null;
	}
	
	public String remover(){
		dao.remover(musica);
		return null;
	}
	
	
	
	public List<Musica> getMusicas() {
		return musicas;
	}

	public void setMusicas(List<Musica> musicas) {
		this.musicas = musicas;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String salvar(){
		Musica m = new Musica();
		m.setCodigo(this.codigo);
		m.setNome(this.nome);
		dao.inserir(m);
		return null;
	}	

	public Musica getMusica() {
		return musica;
	}

	public void setMusica(Musica musica) {
		this.musica = musica;
	}

}
