package controle;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dao.VotoDAO;
import modelo.Voto;

public class VotoMB {
	
	private List<Voto> votos = new ArrayList<Voto>();
	private Voto voto = new Voto();
	private VotoDAO dao = new VotoDAO(); 
	
	public VotoMB() {
		this.setVotos(dao.listarTodos());
	}
	
	public void avaliar(int voto, String id_Video) throws UnknownHostException{
		Voto v = new Voto();
		Date data = new Date();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		v.setData(df.format(data));
		v.setId_video(Integer.parseInt(id_Video));
		v.setIp(InetAddress.getLocalHost().getHostAddress());
		v.setVoto(voto);
		dao.inserir(v);
	}

	public List<Voto> getVotos() {
		return votos;
	}

	public void setVotos(List<Voto> votos) {
		this.votos = votos;
	}

	public Voto getVoto() {
		return voto;
	}

	public void setVoto(Voto voto) {
		this.voto = voto;
	}
	
	

}
