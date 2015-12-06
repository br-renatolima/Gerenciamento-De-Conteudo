package controle;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import dao.TextoDAO;
import modelo.Texto;

@ManagedBean(name="TextoMB")
@RequestScoped
public class TextoMB {
	
	private Texto texto;
	private TextoDAO dao;
	private String release;
	private String agradecimento;
	private String conteudo;
	private List<Texto> textos;
	
	public TextoMB() {
		this.texto = new Texto();
		this.dao = new TextoDAO();
		this.textos = new ArrayList<Texto>();
		
		this.setTextos(dao.ListarTodosTextos());
		this.setRelease();
		this.setAgradecimento();
	}
	
	public TextoDAO getDao() {
		return dao;
	}

	public void setDao(TextoDAO dao) {
		this.dao = dao;
	}

	public Texto getTexto() {
		return texto;
	}

	public void setTexto(Texto texto) {
		this.texto = texto;
	}	

	public String getRelease() {
		return release;
	}

	public String getConteudo() {
		return conteudo;
	}

	public void setConteudo(String conteudo) {
		this.conteudo = conteudo;
	}

	public void setRelease() {
		for (Texto texto : textos) {
			if(texto.getId() == 1){
				this.release = texto.getTexto();
			}
		};
	}

	public String getAgradecimento() {
		return agradecimento;
	}

	public void setAgradecimento() {
		for (Texto texto : textos) {
			if(texto.getId() == 1){
				this.agradecimento = texto.getTexto();
			}
		};
	}

	public List<Texto> getTextos() {
		return textos;
	}

	public void setTextos(List<Texto> textos) {
		if (textos.isEmpty()) {
			Texto t = new Texto("<p>A Banda Orbita Cristã foi gerada na cabeça e no coração do Vocalista e Compositor"
		            + " Vinícius Gonçalves através de uma oração, na qual o músico pedia a Deus uma banda de"
					+ " rock cristã, quando Deus lhe entregou o nome (<b>Órbita Cristã</b>)... O músico que já havia"
		            + " integrado algumas bandas seculares, como a Banda Reverse 69, a banda à qual ele abriu"
					+ " mão para servir a Deus em 2009, ainda tinha o sonho de se tornar um musico, porém,"
		            + " dessa vez com canções e banda que por onde passasse levasse a palavra de Deus... </p><p>"
					+ " Mas iniciou no ano de 2011 e não passou de esboço... Muitas idéias, planos, sonhos, e"
		            + " um só objetivo: Alcançar o Almejado. Segundos, minutos, horas, dias, meses e até"
					+ " mesmo anos se passaram, para que essa idéia saísse do papel e tomasse forma... </p><p> Em"
		            + " setembro de 2013, Vinícius Gonçalves que já conhecia, não só como amigos de igreja"
					+ " como também já haviam tirado som com o baterista Paulo Freitas, que sempre sonhou"
		            + " em integrar a uma banda de rock cristão e o Guitarrista Renato Lima, que já integrava"
		            + " uma banda de rock cristã, a qual estava parada cumprindo alguns propósitos pessoais,"
		            + " também sonhava em realizar seu sonho de anunciar a palavra de Deus através de"
		            + " canções impactantes, então resolveram tirar tudo do papel e por em prática, foi quando "
		            + " um rápido ensaio com musicas autorais, que superou as expectativas de todos... </p><p>"
		            + " Pois a banda que hoje já se faz existente, começou apenas com um vocal, um batera e uma"
		            + " guitarra, e logo conseguiu seu segundo guitarrista Davi Coutinho, que também é da mesma"
		            + " igreja que os outros integrantes e tinha o imenso sonho de se tornar musico profissional e"
		            + " tocar em uma banda, e assim foi convidado a fazer parte dessa família... </p><p>"
		            + " Desde então não mais apenas um esboço, um rabisco e nem só uma ideia, mais já havia tomado sua"
		            + " forma real para alcançar o Almejado que era anunciar a palavra de Deus através de canções impactantes... </p><p>"
		            + " A Banda então passou a existir, aonde começaram ensaios, e as divulgações, hoje o objetivo de um se tornou objetivo da Banda... </p><p>"
		            + " A banda que tem diversas músicas autorais, na qual algumas estão em fase de arranjos, outras já"
		            + " fechadas, aguardando a oportunidade em DEUS para tirá-las do papel."
		            + " </p><p>No dia 10 de Janeiro de 2014, Renato Lima, resolve se dedicar exclusivamente a Banda Órbita Cristã, e anuncia sua saída, de sua antiga banda, que estava em período de termino."
		            + " </p><p> Após um período de conversas e análises, no dia 04 de Janeiro de 2014, a banda passou a contar"
		            + " com o baixista Pedro Fraga, conhecido de longa data do guitarrista Renato Lima, e primo da namorada do vocalista Vinícius Gonçalves, que após um ensaio, o novo membro então passou a se dedicar à banda e mais ainda ao seu ministério."
		            + " </p><p> No dia 09 de Janeiro de 2014, Andressa Ferreira, que é da mesma igreja que parte da banda, já conhecida e amiga de longas data dos integrantes, foi convidada a estar integrando como segundo vocal da"
		            + " banda, e alguns meses após sua entrada na banda, realizaram as gravações da singles <b>AO LADO TEU</b>& <b>LOUCURA QUE TRÁS VIDA</b>, providenciando também, Fotos Promo, Vídeos e seu próprio site."
		            + " </p><p> No dia 22 de Abril 2015 foi anunciado oficialmente a saída do Guitarrista Davi Coutinho, para cumprir seus propósitos pessoais e ministeriais, onde ele deixou uma grande lição para todos, obedeçendo os propósitos de Deus pra vida dele."
		            + " </p><p> Contudo a banda que é da baixada Fluminense, da cidade de Belford Roxo RJ, ainda é uma banda independente, vem buscando aprimorar conhecimentos e tentando entrar no cenário underground, como uma banda de Rock"
		            + " Cristão..."
		            + " <br><Br>"
		            + " Integrada por:"
		            + " <br><br>"
		            + " Vinícius Gonçalves : Vocal / Composições / Back<br>"
		            + " Andressa Ferreira : Vocal / Composições / Back<br>"
		            + " Renato Lima : Guitarra <br>"
		            + " Paulo Freitas : Bateria <br>"
		            + " Pedro Fraga : Contrabaixo <br>"
		            + " </p>");
			dao.inserir(t);
			textos.add(t);
		}
		this.textos = textos;
	}
	
	public String atualizar(){
		dao.atualizar(texto);
		return null;
	}
	
	public String inserir(){
		Texto t = new Texto(this.getConteudo());
		dao.inserir(t);
		return null;
	}

}
