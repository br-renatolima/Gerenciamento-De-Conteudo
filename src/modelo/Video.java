package modelo;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="video")
public class Video {

	@Id
	@GeneratedValue
	private int id;
	private String codigo;
	private Date data;
	private int love;
	private String nome;
	@Transient
	private List<Voto> voto;
	
	public Video(String codigo, Date data, int love, String nome) {
		this.codigo = codigo;
		this.data = data;
		this.love = love;
		this.nome = nome;
	}
	
	
	public Video() {
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public int getLove() {
		return love;
	}

	public void setLove(int love) {
		this.love = love;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}


	public List<Voto> getVoto() {
		return voto;
	}


	public void setVoto(List<Voto> voto) {
		this.voto = voto;
	}
}
