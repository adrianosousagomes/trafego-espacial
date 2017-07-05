package br.com.aotrabalho.trafego.espacial.model;

import java.io.Serializable;
import java.util.List;

/**
 * Representa um plano de voo.
 * @author adriano.gomes
 */
public class PlanoVoo implements Serializable {

	private static final long serialVersionUID = 1309223968165665148L;
	private Nave nave;
	private Planeta planeta;
	private List<Tripulante> tripulantes;
	private PlanoVoo vooAnterior;
	
	public PlanoVoo() {
		super();
	}

	public PlanoVoo(Nave nave, Planeta planeta, List<Tripulante> tripulantes,
			PlanoVoo vooAnterior) {
		super();
		this.nave = nave;
		this.planeta = planeta;
		this.tripulantes = tripulantes;
		this.vooAnterior = vooAnterior;
	}

	public Nave getNave() {
		return nave;
	}
	public void setNave(Nave nave) {
		this.nave = nave;
	}
	public Planeta getPlaneta() {
		return planeta;
	}
	public void setPlaneta(Planeta planeta) {
		this.planeta = planeta;
	}
	public List<Tripulante> getTripulantes() {
		return tripulantes;
	}
	public void setTripulantes(List<Tripulante> tripulantes) {
		this.tripulantes = tripulantes;
	}
	public PlanoVoo getVooAnterior() {
		return vooAnterior;
	}
	public void setVooAnterior(PlanoVoo vooAnterior) {
		this.vooAnterior = vooAnterior;
	}

	/**
	 * Validar nave do plano de voo.
	 * @return boolean
	 */
	public boolean validarNave(){
		if(this.nave == null){
			return false;
		} else {
			return true;
		}
	}
	/**
	 * Validar planeta do plano de voo.
	 * @return boolean
	 */
	public boolean validarPlaneta(){
		if(this.planeta == null){
			return false;
		} else {
			return true;
		}
	}
	/**
	 * Validar tripulantes do plano de voo.
	 * @return boolean
	 */
	public boolean validarTripulantes(){
		if(this.tripulantes == null || this.tripulantes.isEmpty()){
			return false;
		} else {
			return true;
		}
	}
	/**
	 * Verifica se plano de voo e valido.
	 * @return boolean
	 */
	public boolean isValido(){
		if(this.validarNave() && this.validarPlaneta() && this.validarTripulantes()){
			return true;
		} else {
			return false;
		}
	}
	/**
	 * Verificar se tripulante ja adicionado ao plano de voo.
	 * @param tripulante
	 * @return boolean
	 */
	public boolean existeTripulante(Tripulante tripulante){
		for (Tripulante t : tripulantes) {
			if(t.equals(tripulante)){
				return true;
			}
		}
		return false;
	}
	/**
	 * Verificar se e permitido adicionar mais tripulacao ao plano de voo.
	 * @return boolean
	 */
	public boolean permiteAdicionarTripulacao(){
		if(this.nave == null && (this.tripulantes == null || this.tripulantes.isEmpty())){
			return true;
		} else {
			if(this.tripulantes.size() < this.nave.getPassengers()){
				return true;
			} else {
				return false;
			}
		}
	}
	/**
	 * Validar se o planeta do plano de voo nao e o mesmo do ultimo voo,
	 * se sim, o voo nao sera permitido.
	 * @return boolean.
	 */
	public boolean planetaDestinoDiferenteUltimoVoo(){
		if(this.vooAnterior == null){
			return true;
		}
		
		if(this.planeta.equals(this.vooAnterior.getPlaneta())){
			return false;
		} else {
			return true;
		}
	}
}
