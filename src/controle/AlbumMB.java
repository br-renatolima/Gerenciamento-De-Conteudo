package controle;


import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import modelo.Album;
import dao.AlbumDAO;

@ManagedBean(name="AlbumMB")
@RequestScoped
public class AlbumMB {
	
	private List<Album> albuns = new ArrayList<Album>();
	private AlbumDAO dao = new AlbumDAO();
	private int id;
	private String nome;
	private String descricao;
	private Date data;
	private Album album = new Album();

	public AlbumMB() {
		this.setAlbuns(dao.listarTodos());
		
		if (album == null) {
			// obtem parametro
			ExternalContext ctx = FacesContext.getCurrentInstance().getExternalContext();
	        String idParam = ctx.getRequestParameterMap().get("id");
			if (idParam != null && !idParam.equals("")) {
				try {
					this.album = this.getAlbumPorId(Integer.parseInt(idParam));
				} catch(NumberFormatException e) {
					// log 
				}
			}			
			if (this.album == null) {
				this.album = new Album();
			}
		}
	}

	public Album getAlbumPorId(int parseInt) {
		for (Album album : albuns) {
			if(album.getId() == parseInt){
				return album;
			}
		}
		return null;
	}

	public String salvar(){
		album.setNome(this.getNome());
		album.setDescricao(getDescricao());
		album.setData(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		try {			
			dao.inserir(album);
			System.out.println("Salvo com Sucesso!");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		FacesMessage msg = new FacesMessage("Sucesso! Gravação do album ", album.getNome() + " conluida!");
		FacesContext.getCurrentInstance().addMessage(null, msg);
		return "";
	}
	
	public String editar()throws SQLException{
		try{
			ExternalContext ctx = FacesContext.getCurrentInstance().getExternalContext();
			String idParam = ctx.getRequestParameterMap().get("id");
			this.album = this.getAlbumPorId(Integer.parseInt(idParam));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/adm/album/editar.xhtml?faces-redirect=true";
	}
	
	public String atualizar() throws SQLException{
		try {
			ExternalContext ctx = FacesContext.getCurrentInstance().getExternalContext();
	        String idParam = ctx.getRequestParameterMap().get("id");
	     	album.setId(Integer.valueOf(idParam));
			dao.atualizar(album);
		} catch (Exception e) {
			e.printStackTrace();
		}
		FacesMessage msg = new FacesMessage("Sucesso! Alteração do album ", album.getNome() + " conluido!");
		FacesContext.getCurrentInstance().addMessage(null, msg);
		return "";
	}
	
	public String remover() throws SQLException{
		try {
			ExternalContext ctx = FacesContext.getCurrentInstance().getExternalContext();
	        String idParam = ctx.getRequestParameterMap().get("id");
	     	album.setId(Integer.valueOf(idParam));
			dao.remover(album);
		} catch (Exception e) {
			e.printStackTrace();
		}
		FacesMessage msg = new FacesMessage("Sucesso! Remoção do album ", album.getNome() + " conluido!");
		FacesContext.getCurrentInstance().addMessage(null, msg);
		
		this.setAlbuns(dao.listarTodos());
		return "/adm/album/lista.xhtml?faces-redirect=true";
	}
	
	public List<Album> getAlbuns() {
		return albuns;
	}

	public void setAlbuns(List<Album> albuns) {
		this.albuns = albuns;
	}
	
	public AlbumDAO getDAOAlbum() {
		return dao;
	}

	public void setDAOAlbum(AlbumDAO dAOAlbum) {
		this.dao = dAOAlbum;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Album getAlbum() {
		return album;
	}

	public void setAlbum(Album album) {
		this.album = album;
	}
}
