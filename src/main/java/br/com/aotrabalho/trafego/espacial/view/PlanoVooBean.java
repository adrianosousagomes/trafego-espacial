package br.com.aotrabalho.trafego.espacial.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.log4j.Logger;

import br.com.aotrabalho.trafego.espacial.common.Utilitario;
import br.com.aotrabalho.trafego.espacial.model.Nave;
import br.com.aotrabalho.trafego.espacial.model.Planeta;
import br.com.aotrabalho.trafego.espacial.model.PlanoVoo;
import br.com.aotrabalho.trafego.espacial.model.Tripulante;
import br.com.aotrabalho.trafego.espacial.service.INave;
import br.com.aotrabalho.trafego.espacial.service.IPlaneta;
import br.com.aotrabalho.trafego.espacial.service.ITripulante;

/**
 * Representa a tela plano de voo.
 * @author adriano.gomes
 */
@ManagedBean(name = "planoVooBean")
@ViewScoped
public class PlanoVooBean implements Serializable {

	private static final long serialVersionUID = 2896273242319467670L;

	private static final Logger log = Logger.getLogger(PlanoVooBean.class);
	/**
	 * Armazena uma lista de planos de voo para exibir na tela.
	 */
	private List<PlanoVoo> planos;
	/**
	 * Armazena plano de voo para adicao na tela.
	 */
	private PlanoVoo planoVoo;
	/**
	 * Armazena uma tripulante selecionado em tela para realizacao de cadastro.
	 */
	private Tripulante tripulanteSelecionado;	
	/**
	 * Armazena uma lista de naves para exibicao no combo.
	 */
	private List<Nave> comboNave;
	/**
	 * Armazena uma lista de planetas para exibicao no combo.
	 */
	private List<Planeta> comboPlaneta;
	/**
	 * Armazena uma lista de tripulantes para exibicao no combo.
	 */
	private List<Tripulante> comboTripulante;
	
	@EJB
	private INave serviceNave;
	@EJB
	private IPlaneta servicePlaneta;
	@EJB
	private ITripulante serviceTripulante;
	
	@PostConstruct
	public void init() {
		this.cargaInicial();
	}
	
	public List<PlanoVoo> getPlanos() {
		return planos;
	}
	public void setPlanos(List<PlanoVoo> planos) {
		this.planos = planos;
	}
	public PlanoVoo getPlanoVoo() {
		return planoVoo;
	}
	public void setPlanoVoo(PlanoVoo planoVoo) {
		this.planoVoo = planoVoo;
	}
	public List<Nave> getComboNave() {
		return comboNave;
	}
	public void setComboNave(List<Nave> comboNave) {
		this.comboNave = comboNave;
	}
	public List<Planeta> getComboPlaneta() {
		return comboPlaneta;
	}
	public void setComboPlaneta(List<Planeta> comboPlaneta) {
		this.comboPlaneta = comboPlaneta;
	}
	public List<Tripulante> getComboTripulante() {
		return comboTripulante;
	}
	public void setComboTripulante(List<Tripulante> comboTripulante) {
		this.comboTripulante = comboTripulante;
	}	
	public Tripulante getTripulanteSelecionado() {
		return tripulanteSelecionado;
	}
	public void setTripulanteSelecionado(Tripulante tripulanteSelecionado) {
		this.tripulanteSelecionado = tripulanteSelecionado;
	}
	/**
	 * Limpar campos utilizados na tela.
	 */
	public void limparCampos(){
		this.planoVoo = null;
		this.planoVoo = new PlanoVoo();
		this.planoVoo.setTripulantes(new ArrayList<Tripulante>());		
		this.tripulanteSelecionado = null;
	}
	/**
	 * Preparar campos e tela para cadastro de novo plano de voo.
	 */
	public void novo(){		
		this.limparCampos();
		/**
		 * Obter ultimo voo cadastrado e vincular ao novo voo.
		 */
		if(this.planos != null && !this.planos.isEmpty()){
			int lastIndex = this.planos.size() - 1;
			PlanoVoo vooAnterior = this.planos.get(lastIndex);
			this.planoVoo.setVooAnterior(vooAnterior);
		}
		
		org.primefaces.context.RequestContext.getCurrentInstance().execute("PF('modalNovoPlanoVoo').show()");
	}
	/**
	 * Limpar campos de tela e fechar modal de cadastro de plano de voo.
	 */
	public void cancelar(){
		try {
			this.limparCampos();
			org.primefaces.context.RequestContext.getCurrentInstance().execute("PF('modalNovoPlanoVoo').hide()");
			
		} catch (Exception e) {
			log.error(e);
			Utilitario
					.showMessage(
							"Erro ao tentar cancelar cadastro de plano de voo, aguarde alguns instantes e tente novamente!",
							FacesMessage.SEVERITY_WARN);
		}
	}
	/**
	 * Salvar plano de voo em memoria.
	 */
	public void salvar(){
		try {
			boolean cadastroValido = true;
			
			if(!this.planoVoo.validarNave()){
				Utilitario.showMessage("Selecione uma nave!", FacesMessage.SEVERITY_WARN);
				cadastroValido = false;
			} 
			
			if(!this.planoVoo.validarPlaneta()){
				Utilitario.showMessage("Selecione um planeta!", FacesMessage.SEVERITY_WARN);
				cadastroValido = false;
			} else if(!this.planoVoo.planetaDestinoDiferenteUltimoVoo()){
				Utilitario.showMessage("O planeta destino não pode ser o mesmo do último voo, por favor selecione outro!", FacesMessage.SEVERITY_WARN);
				cadastroValido = false;
			}
			
			if(!this.planoVoo.validarTripulantes()){
				Utilitario.showMessage("Adicione ao menos um tripulante ao plano de voo!", FacesMessage.SEVERITY_WARN);
				cadastroValido = false;
			}
			
			if(cadastroValido){
				this.planos.add(this.planoVoo);
				org.primefaces.context.RequestContext.getCurrentInstance().execute("PF('modalNovoPlanoVoo').hide()");
			}
			
		} catch (Exception e) {
			Utilitario
					.showMessage(
							"Um erro ocorreu ao tentar cadastrar o plano de voo, tente novamente em instantes!",
							FacesMessage.SEVERITY_ERROR);
			log.error(e);
		}
	}
	/**
	 * Adicionar tripulante ao plano de voo. 
	 */
	public void adicionarTripulante() {
		try {
			if(this.tripulanteSelecionado == null){
				Utilitario
				.showMessage(
						"Selecione um tripulante!",
						FacesMessage.SEVERITY_WARN);
			} else {
				boolean existe = this.planoVoo.existeTripulante(this.tripulanteSelecionado);
				if(existe){
					Utilitario
					.showMessage(
							"O Tripulante " + this.tripulanteSelecionado.getName() + " + já adicionado ao plano de voo, selecione outro!",
							FacesMessage.SEVERITY_WARN);
				} else{
					if(!this.planoVoo.permiteAdicionarTripulacao()){
						Utilitario
						.showMessage(
								"O limite de tripulantes foi atingido, não é mais permitido adicionar tripulantes!",
								FacesMessage.SEVERITY_WARN);
					} else {
						this.planoVoo.getTripulantes().add(this.tripulanteSelecionado);
						this.tripulanteSelecionado = null;
					}
				}
			}
		} catch (Exception e) {
			Utilitario
			.showMessage(
					"Ocorreu um erro ao tentar adicionar tripulante ao plano de voo, tente novamente em instantes!",
					FacesMessage.SEVERITY_ERROR);
			log.error(e);
		}
	}
	
	/**
	 * Carga inicial do sistema.
	 */
	public void cargaInicial(){
		try {
			this.carregarComboNave();
			this.carregarComboPlaneta();
			this.carregarComboTripulante();
			
			Nave nave = this.comboNave.get(0);
			Planeta planeta = new Planeta("Terra", "123456789", "temperado", "floresta", "123456789");
			List<Tripulante> tripulantes = new ArrayList<Tripulante>();
			Tripulante trip = new Tripulante("Adriano Gomes", "Homem");
			tripulantes.add(trip);
			
			PlanoVoo plano = new PlanoVoo(nave, planeta, tripulantes, null);
			this.planos = new ArrayList<PlanoVoo>();
			this.planos.add(plano);
		} catch (Exception e) {
			log.error("cargaInicial ", e);
			Utilitario
			.showMessage(
					e.getMessage(),
					FacesMessage.SEVERITY_ERROR);
			
		}
	}
	/**
	 * Carregar lista de naves.
	 * @throws Exception
	 */
	private void carregarComboNave() throws Exception{
		try {
			this.comboNave = this.serviceNave.listar();
		} catch (Exception e) {
			throw new Exception("Um erro ocorreu ao tentar carregar lista de naves!", e);
		}
	}
	/**
	 * Carregar lista de planetas.
	 * @throws Exception
	 */
	private void carregarComboPlaneta() throws Exception{
		try {
			this.comboPlaneta = this.servicePlaneta.listar();
		} catch (Exception e) {
			throw new Exception("Um erro ocorreu ao tentar carregar lista de planetas!", e);
		}
	}
	/**
	 * Carregar lista de tripulantes.
	 * @throws Exception
	 */
	private void carregarComboTripulante() throws Exception{
		try {
			this.comboTripulante = this.serviceTripulante.listar();
		} catch (Exception e) {
			throw new Exception("Um erro ocorreu ao tentar carregar lista de tripulantes!", e);
		}
	}
}
