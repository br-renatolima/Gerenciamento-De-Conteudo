package controle;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import modelo.Administrador;
import dao.AdministradorDAO;

@ManagedBean(name = "UsuarioMB")
@RequestScoped
public class UsuarioMBImpl {
	private AdministradorDAO dao = new AdministradorDAO();
	private Administrador usuarioLogado;
	private boolean loggedIn;
	private String usuario;
	private String senha;

	public String doLogin() {
		Administrador usuarioFound = dao.isUsuarioReadyToLogin(usuario, convertStringToMd5(senha));
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		if (usuarioFound == null) {
			FacesMessage msg = new FacesMessage("Usu�rio ou Senha incorreto, tente novamente!");
			FacesContext.getCurrentInstance().addMessage(null, msg);
			return "/pagina/publico/login.jsf?faces-redirect=true";
		} else {
			session.setAttribute("usuarioLogado", usuarioFound);
			return "/adm/painel.jsf?faces-redirect=true";
		}
	}

	public String doLogout() {
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		session.setAttribute("usuarioLogado", null);
		FacesMessage msg = new FacesMessage("Logout realizado com sucesso!");
		FacesContext.getCurrentInstance().addMessage(null, msg);
		
		return "/login.jsf?faces-redirect=true";
	}
	
	private String convertStringToMd5(String valor) {
		MessageDigest mDigest;
		try {
			mDigest = MessageDigest.getInstance("MD5");
			byte[] valorMD5 = mDigest.digest(valor.getBytes("UTF-8"));
			StringBuffer sb = new StringBuffer();

			for (byte b : valorMD5) {
				sb.append(Integer.toHexString((b & 0xFF) | 0x100).substring(1, 3));
			}
			return sb.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	
	public Administrador getUsuarioLogado() {
		return usuarioLogado;
	}

	public void setUsuarioLogado(Administrador usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}

	public boolean isLoggedIn() {
		return loggedIn;
	}

	public void setLoggedIn(boolean loggedIn) {
		this.loggedIn = loggedIn;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
}