package modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="texto")
public class Texto {
	@Id
	@GeneratedValue
	private int id;
	private String texto;
	
	public Texto(String texto) {
		this.texto = texto;
	}
	
	public Texto(){
		
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTexto() {
		return texto;
	}
	public void setTexto(String texto) {
		this.texto = texto;
	}
}
