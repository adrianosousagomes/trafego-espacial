package br.com.aotrabalho.trafego.espacial.service;

import java.util.List;

import javax.xml.ws.WebServiceException;

import br.com.aotrabalho.trafego.espacial.model.Tripulante;

/**
 * Interface do servico Tripulante, responsavel por interagir com o webservice externo.
 * @author adriano.gomes
 *
 */
public interface ITripulante {
	/**
	 * Obtem uma lista de tripulantes.
	 * @return List<Tripulante>
	 * @throws WebServiceException
	 */
	List<Tripulante> listar() throws WebServiceException;
}
