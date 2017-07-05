package br.com.aotrabalho.trafego.espacial.service;

import java.util.List;
import javax.xml.ws.WebServiceException;
import br.com.aotrabalho.trafego.espacial.model.Nave;

/**
 * Interface do servico Nave, responsavel por interagir com o webservice externo.
 * @author adriano.gomes
 *
 */
public interface INave {
	/**
	 * Obtem uma lista de naves.
	 * @return List<Nave>
	 * @throws WebServiceException
	 */
	List<Nave> listar() throws WebServiceException;
}
