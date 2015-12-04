package controle;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.model.SelectItem;

import modelo.Album;
import modelo.Foto;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import dao.AlbumDAO;
import dao.FotoDAO;

@ManagedBean(name="FotoMB")
@ViewScoped
public class FotoMB implements Serializable{
	private static final long serialVersionUID = -275335211801099493L;
	private List<Foto> fotos = new ArrayList<Foto>();
	private FotoDAO DAOFoto = new FotoDAO();
	private Integer idAlbum;
	private String descricao;
	private AlbumDAO DAOAlbum = new AlbumDAO();
	private List<Album> albuns = new ArrayList<Album>();
	private Foto foto = new Foto();
	
	public FotoMB(){
		this.setFotos(DAOFoto.listarTodasFotos());
		this.setAlbuns(DAOAlbum.listarTodos());
	}
	
	public void fileUpload(FileUploadEvent event) throws IOException{
		int count = 0;
		try{
			Foto ft = new Foto();
			//Cria um arquivo UploadFile, para receber o arquivo do evento
			UploadedFile arq = event.getFile();
			//Transformar a imagem em bytes para salvar em banco de dados
			byte[] imagem = arq.getContents();
			ft.setNome(arq.getFileName());
			ft.getAlbum().setId(idAlbum);
			ft.setData(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			ft.setImagem(imagem);
			fotos.add(ft);
			DAOFoto.inserir(ft);
	 
			//Essa parte comentada deve ser usada caso queira salvar o arquivo em um local fisuco do servidor.
			/*
			InputStream in = new BufferedInputStream(arq.getInputstream());
			File file = new File("C://var//" + arq.getFileName());
			//O método file.getAbsolutePath() fornece o caminho do arquivo criado
			//Pode ser usado para ligar algum objeto do banco ao arquivo enviado
			String caminho = file.getAbsolutePath();
			FileOutputStream fout = new FileOutputStream(file);
			while(in.available() != 0){
				fout.write(in.read());
			}
			fout.close();
			*/
			
			if(count < 1){
				FacesMessage msg = new FacesMessage("Operação realizada com sucesso!");
				FacesContext.getCurrentInstance().addMessage(null, msg);
			}
			count++;
		}catch(Exception ex){
			ex.printStackTrace();
		}
	 }
	
	public List<Foto> getFotos() {
		return fotos;
	}

	public void setFotos(List<Foto> fotos) {
		this.fotos = fotos;
	}
	
	public void salvar(UploadedFile foto) throws IOException{
		String filename = FilenameUtils.getName(foto.getFileName());
	    InputStream input = foto.getInputstream();
	    File f = new File("teste.txt");
	    System.out.println(f.getAbsolutePath());
	    OutputStream output = new FileOutputStream(new File("../img/fotos/", filename));
	    try {
	        IOUtils.copy(input, output);
	    } finally {
	        IOUtils.closeQuietly(input);
	        IOUtils.closeQuietly(output);
	    }
	}
	
	public void recuperarDados(AjaxBehaviorEvent event){
		idAlbum = (Integer) event.getSource();
	}
	
	public String remover() throws SQLException{
		try {
			ExternalContext ctx = FacesContext.getCurrentInstance().getExternalContext();
	        String idParam = ctx.getRequestParameterMap().get("id");
	     	foto.setId(Integer.valueOf(idParam));
	     	foto.setAlbum(null);
	     	DAOFoto.remover(foto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		FacesMessage msg = new FacesMessage("Sucesso! Remoção da foto ", foto.getNome() + " conluida!");
		FacesContext.getCurrentInstance().addMessage(null, msg);
		
		this.setFotos(DAOFoto.listarTodasFotos());
		return "/adm/foto/lista.jsf?faces-redirect=true";
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void setAlbuns(List<Album> list) {
		Collection toReturn = new ArrayList();
		for (Album album : list) {
			toReturn.add(new SelectItem(album.getId(), album.getNome()));
		}
		this.albuns.addAll(toReturn);
	}
	
	public List<Album> getAlbuns(){
		return this.albuns;
	}

	public Foto getFoto() {
		return foto;
	}

	public void setFoto(Foto foto) {
		this.foto = foto;
	}

	public Integer getIdAlbum() {
		return idAlbum;
	}

	public void setIdAlbum(Integer idAlbum) {
		this.idAlbum = idAlbum;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}