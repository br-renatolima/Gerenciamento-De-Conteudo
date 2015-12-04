package controle;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import modelo.Administrador;
import dao.AdministradorDAO;

@ManagedBean(name = "AdministradorMB")
@RequestScoped
public class AdministradorMB {

	private AdministradorDAO dao = new AdministradorDAO();
	private List<Administrador> administradores = new ArrayList<Administrador>();
	private Administrador administrador;
	private String email;
	private String login;
	private String nome;
	private String sobrenome;
	private String telefone;
	private String senha;

	public AdministradorMB() {
		this.setAdministradores(dao.listarTodos());

		if (administrador == null) {
			// obtem parametro
			ExternalContext ctx = FacesContext.getCurrentInstance().getExternalContext();
			String idParam = ctx.getRequestParameterMap().get("id");

			if (idParam != null && !idParam.equals("")) {
				try {
					this.administrador = this.getAdministradorPorId(Integer.parseInt(idParam));
				} catch (NumberFormatException e) {
					// log
				}
			}

			if (this.administrador == null) {
				this.administrador = new Administrador();
			}
		}
	}
	
	private Administrador getAdministradorPorId(int id) {
		for (Administrador administrador : administradores) {
			if (administrador.getId() == id) {
				return administrador;
			}
		}
		return null;
	}

	public String salvar() {
		Administrador adm = new Administrador(this.getEmail(), this.getNome(), this.getSobrenome(), this.getLogin(), this.getSenha(), this.getTelefone());
		dao.inserir(adm);
		return null;
	}

	public String atualizar() {
		dao.atualizar(administrador);
		return null;
	}

	public String remover() {
		dao.remover(administrador);
		return null;
	}

	public List<Administrador> getAdministradores() {
		return administradores;
	}

	public void setAdministradores(List<Administrador> administradores) {
		this.administradores = administradores;
	}

	public AdministradorDAO getDao() {
		return dao;
	}

	public void setDao(AdministradorDAO dao) {
		this.dao = dao;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Administrador getAdministrador() {
		return administrador;
	}

	public void setAdministrador(Administrador administrador) {
		this.administrador = administrador;
	}
}