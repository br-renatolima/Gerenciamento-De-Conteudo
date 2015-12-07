package controle;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

import dao.EventoDAO;
import modelo.Evento;

@ManagedBean(name="EventoMB")
@RequestScoped
public class EventoMB {

	private Date data;
	private String endereco;
	private List<String> locais;
	private String local;
	private String nome;
	private String obs;
	private String situacao;
	private List<Evento> eventos = new ArrayList<Evento>();
	private List<Evento> eventosAtivos = new ArrayList<Evento>();
	private List<Evento> proximosEventos = new ArrayList<Evento>();
	private EventoDAO dao = new EventoDAO();
	private Evento evento;
	
	public EventoMB(){
		this.setEventos(dao.listarTodos());
		this.setProximosEventos(this.getEventos());
		this.setLocais(this.locais);
		
		if (evento == null) {
			// obtem parametro
			ExternalContext ctx = FacesContext.getCurrentInstance().getExternalContext();
	        String idParam = ctx.getRequestParameterMap().get("id");
			if (idParam != null && !idParam.equals("")) {
				try {
					this.evento = this.getEventoPorId(Integer.parseInt(idParam));
				} catch(NumberFormatException e) {
					System.out.println(e.getMessage());
				}
			}			
			if (this.evento == null) {
				this.evento = new Evento();
			}
		}
	}
	
	public Evento getEventoPorId(int id){
		for (Evento evento : this.eventos) {
			if(evento.getId() == id){
				return evento;
			}
		}
		return null;
	}

	public String salvar() throws Exception{
		Boolean datanaodisponivel = false;
		for (Evento evento : eventos) {
			if (evento.getData().compareTo(this.getData()) == 0){
				FacesContext facesContext = FacesContext.getCurrentInstance();
				facesContext.addMessage(null, new FacesMessage("Já existe um evento com essa data e hora!"));
				datanaodisponivel = true;
			}
		}
		
		if(!datanaodisponivel){
			Object e = new Evento(this.getData(), this.getLocal(), this.getEndereco(), this.getObs(), this.getNome(), "AT");
			this.dao.salvar(e);
		}
		
		return null;
	}
	
	public String atualizar() throws Exception{
		this.dao.atualizar(evento);
		return null;
	}
	
	
	public List<String> completeLocais(String query) {
        List<String> results = new ArrayList<String>();
        for (Evento e : this.eventos) {
			results.add(query + e.getLocal());
		}
         
        return results;
    }
     
	public void onDateSelect(SelectEvent event) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Date Selected", format.format(event.getObject())));
    }
     
    public void click() {
        RequestContext requestContext = RequestContext.getCurrentInstance();         
        requestContext.update("form:display");
        requestContext.execute("PF('dlg').show()");
    }
	
	
	public void setEventos(List<Evento> eventos) {
		Calendar calendario = Calendar.getInstance();
		for (Evento evento : eventos) {
			calendario.setTime(evento.getData());
			evento.setAno(calendario.get(Calendar.YEAR));
			evento.setMes(calendario.get(Calendar.MONTH));
			evento.setDia(calendario.get(Calendar.DAY_OF_MONTH));
			evento.setHora(calendario.get(Calendar.HOUR_OF_DAY));
			if(String.valueOf(calendario.get(Calendar.MINUTE)).length() < 2){
				evento.setMinuto( String.valueOf(calendario.get(Calendar.MINUTE) ) + "0");
			}else{
				evento.setMinuto( String.valueOf(calendario.get(Calendar.MINUTE) ));
			}
			

			evento.setMes(evento.getMes() + 1);
			
			
			switch (evento.getMes()) {
				case 1: evento.setMesExtenso("Janeiro"); break;
				case 2: evento.setMesExtenso("Fevereiro"); break;
				case 3: evento.setMesExtenso("Março"); break;
				case 4: evento.setMesExtenso("Abril"); break;
				case 5: evento.setMesExtenso("Maio"); break;
				case 6: evento.setMesExtenso("Junho"); break;
				case 7: evento.setMesExtenso("Julho"); break;
				case 8: evento.setMesExtenso("Agosto"); break;
				case 9: evento.setMesExtenso("Setembro"); break;
				case 10: evento.setMesExtenso("Outubro"); break;
				case 11: evento.setMesExtenso("Novembro"); break;
				case 12: evento.setMesExtenso("Dezembro"); break;
			}
			this.eventos.add(evento);
			
			if(evento.getSituacao().equals("AT")){
				this.eventosAtivos.add(evento);
			}
		}
	}
	
			public EventoDAO getDaoListEvento() {
				return dao;
			}
		
			public void setDaoListEvento(EventoDAO daoListEvento) {
				this.dao = daoListEvento;
			}
		
			public Evento getEvento() {
				return this.evento;
			}
		
			public void setEvento(Evento evento) {
				this.evento = evento;
			}
		
			public void setEventosAtivos(List<Evento> eventosAtivos) {
			}
			
			public List<Evento> getEventos() {
				return this.eventos;
			}
			
			public List<Evento> getEventosAtivos(){
				return eventosAtivos;
			}


			public List<Evento> getProximosEventos() {
				return proximosEventos;
			}


			public void setProximosEventos(List<Evento> eventos) {
				Date d = new Date();
				Calendar cal = new GregorianCalendar();
				cal.setTime(d);
				for (Evento e : eventos) {
					if(e.getData().after(cal.getTime())){
						this.proximosEventos.add(e);
					}
				}
			}
			
			
			
			public Date getData() {
				return data;
			}

			public void setData(Date data) {
				this.data = data;
			}

			public String getEndereco() {
				return endereco;
			}

			public void setEndereco(String endereco) {
				this.endereco = endereco;
			}

			public String getLocal() {
				return local;
			}

			public void setLocal(String local) {
				this.local = local;
			}

			public String getNome() {
				return nome;
			}

			public void setNome(String nome) {
				this.nome = nome;
			}

			public String getObs() {
				return obs;
			}

			public void setObs(String obs) {
				this.obs = obs;
			}

			public String getSituacao() {
				return situacao;
			}

			public void setSituacao(String situacao) {
				this.situacao = situacao;
			}

			public EventoDAO getDAOEvento() {
				return dao;
			}

			public void setDAOEvento(EventoDAO dAOEvento) {
				dao = dAOEvento;
			}

			public List<String> getLocais() {
				return locais;
			}

			public void setLocais(List<String> locais) {
				this.locais = locais;
			}

}
