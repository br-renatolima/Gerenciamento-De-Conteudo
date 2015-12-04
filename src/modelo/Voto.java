package modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="voto")
public class Voto {

	@Id
	@GeneratedValue
	private int id;
	private String data;
	private int voto;
	private String ip;
	private int id_video;
		
	public Voto() {
	}

	public Voto(int id, String data, int voto, String ip) {
		this.id = id;
		this.data = data;
		this.voto = voto;
		this.ip = ip;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getData() {
		return data;
	}

	public void setData(String string) {
		this.data = string;
	}

	public int getVoto() {
		return voto;
	}

	public void setVoto(int voto) {
		this.voto = voto;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public int getId_video() {
		return id_video;
	}

	public void setId_video(int id_video) {
		this.id_video = id_video;
	}
	
}
