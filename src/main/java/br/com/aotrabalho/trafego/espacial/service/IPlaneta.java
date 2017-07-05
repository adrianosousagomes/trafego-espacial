package br.com.aotrabalho.trafego.espacial.service;

import java.util.List;

import javax.xml.ws.WebServiceException;

import br.com.aotrabalho.trafego.espacial.model.Planeta;

/**
 * Interface do servico Planeta, responsavel por interagir com o webservice externo.
 * @author adriano.gomes
 *
 */
public interface IPlaneta {
	/**
	 * Obtem uma lista de planetas.
	 * @return List<Planeta>
	 * @throws WebServiceException
	 */
	List<Planeta> listar() throws WebServiceException;
}
