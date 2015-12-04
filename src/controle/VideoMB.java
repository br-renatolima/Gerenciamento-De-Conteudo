package controle;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import modelo.Video;
import org.primefaces.event.RateEvent;
import dao.VideoDAO;

@ManagedBean(name="VideoMB")
@RequestScoped
public class VideoMB {
	
	private List<Video> videos = new ArrayList<Video>();
	private VideoDAO dao = new VideoDAO();
	private Video video;
	private String nome;
	private int love;
	private Date data;
	private String codigo;
	private int voto;
	private int id;
	private VotoMB votoMb = new VotoMB();
	
	public VideoMB(){
		this.setVideos(dao.listarTodos());
		
		if (video == null) {
			// obtem parametro
			ExternalContext ctx = FacesContext.getCurrentInstance().getExternalContext();
	        String idParam = ctx.getRequestParameterMap().get("id");

			if (idParam != null && !idParam.equals("")) {
				try {
					this.video = this.getVideoPorId(Integer.parseInt(idParam));
				} catch(NumberFormatException e) {
					// log 
				}
			}
			
			if (this.video == null) {
				this.video = new Video();
			}
		}
	}

	private Video getVideoPorId(int id) {
		for (Video video : videos) {
			if(video.getId() == id){
				return video;
			}
		}
		return null;
	}

	public List<Video> getVideos() {
		return videos;
	}

	public void setVideos(List<Video> list) {
		this.videos = list;
	}
	
	public String salvar(){
		Video video = new Video(this.getCodigo(), new Date(), this.getLove(), this.getNome());
		dao.inserir(video);
		return null;
	}
	
	public String atualizar(){
		dao.atualizar(video);
		return null;
	}
	
	public String remover(){
		dao.remover(video);
		return null;
	}
	
	public void onrate(RateEvent rateEvent) throws UnknownHostException {
		FacesContext fc = FacesContext.getCurrentInstance();
		Map<String,String> params = fc.getExternalContext().getRequestParameterMap();
		params.get("id_Video");
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Rate Event", "Você votou:" + ((Integer) rateEvent.getRating()).intValue() + " - Com o vídeo " + params.get("id_Video"));
        FacesContext.getCurrentInstance().addMessage(null, message);
        votoMb.avaliar(((Integer) rateEvent.getRating()).intValue(), params.get("id_Video"));
    }
     
    public void oncancel() {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cancel Event", "Voto Retirado!");
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public VideoDAO getDao() {
		return dao;
	}

	public void setDao(VideoDAO dao) {
		this.dao = dao;
	}

	public int getLove() {
		return love;
	}

	public void setLove(int love) {
		this.love = love;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public int getVoto() {
		return voto;
	}

	public void setVoto(int voto){
		this.voto = voto;
	}
	
	public Video getVideo() {
		return video;
	}

	public void setVideo(Video video) {
		this.video = video;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
}
