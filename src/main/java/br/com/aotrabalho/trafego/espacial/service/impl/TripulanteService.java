package br.com.aotrabalho.trafego.espacial.service.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.xml.ws.WebServiceException;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.codehaus.jettison.json.JSONObject;

import br.com.aotrabalho.trafego.espacial.common.RestClient;
import br.com.aotrabalho.trafego.espacial.model.Tripulante;
import br.com.aotrabalho.trafego.espacial.service.ITripulante;

import com.sun.jersey.api.client.ClientResponse;

/**
 * Implementacao do servico Tripulante. 
 * @author adriano.gomes
 *
 */
@Stateless
public class TripulanteService implements ITripulante{

	@Override
	public List<Tripulante> listar() throws WebServiceException {
		try {
			ClientResponse response = RestClient.get("people?page=1");			
			JSONObject json = response.getEntity(JSONObject.class);			
			String results = json.getString("results");
			
			ObjectMapper mapper = new ObjectMapper();
	    	TypeReference<List<Tripulante>> mapType = new TypeReference<List<Tripulante>>() {};
	    	List<Tripulante> tripulantes = mapper.readValue(results, mapType);
	    	
	    	return tripulantes;

		} catch (Exception e) {
			throw new WebServiceException(e);
		}
	}

}
