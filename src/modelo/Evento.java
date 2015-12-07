package modelo;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="evento")
public class Evento {
	@GeneratedValue
	@Id
	private int id;	
	private String nome;
	private String endereco;
	private String local;
	private String obs;	
	private Date data;
	private String situacao;
	
	@Transient
	private int dia;
	@Transient
	private int mes;
	@Transient
	private int ano;
	@Transient
	private int hora;
	@Transient
	private String minuto;
	@Transient
	private String mesExtenso;
	
	
	public Evento() {
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getLocal() {
		return local;
	}


	public void setLocal(String local) {
		this.local = local;
	}


	public String getEndereco() {
		return endereco;
	}


	public String getObs() {
		return obs;
	}


	public void setObs(String obs) {
		this.obs = obs;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public Evento(Date data, String local,
			String endereco, String obs, String nome, String situacao) {
		this.local = local;
		this.endereco = endereco;
		this.obs = obs;
		this.nome = nome;
		this.data = data;
		this.situacao = situacao;
	}


	public Date getData() {
		return data;
	}


	public void setData(Date data) {
		this.data = data;
	}


	public String getSituacao() {
		return situacao;
	}


	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}


	public int getDia() {
		return dia;
	}


	public void setDia(int dia) {
		this.dia = dia;
	}


	public int getMes() {
		return mes;
	}


	public void setMes(int mes) {
		this.mes = mes;
	}


	public int getAno() {
		return ano;
	}


	public void setAno(int ano) {
		this.ano = ano;
	}


	public int getHora() {
		return hora;
	}


	public void setHora(int hora) {
		this.hora = hora;
	}


	public String getMinuto() {
		return minuto;
	}


	public void setMinuto(String minuto) {
		this.minuto = minuto;
	}


	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}


	public String getMesExtenso() {
		return mesExtenso;
	}


	public void setMesExtenso(String mesExtenso) {
		this.mesExtenso = mesExtenso;
	}

}
