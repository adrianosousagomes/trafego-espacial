package br.com.aotrabalho.trafego.espacial.service.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.xml.ws.WebServiceException;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.codehaus.jettison.json.JSONObject;

import br.com.aotrabalho.trafego.espacial.common.RestClient;
import br.com.aotrabalho.trafego.espacial.model.Nave;
import br.com.aotrabalho.trafego.espacial.service.INave;

import com.sun.jersey.api.client.ClientResponse;

/**
 * Implementacao do servico Nave. 
 * @author adriano.gomes
 *
 */
@Stateless
public class NaveService implements INave{

	@Override
	public List<Nave> listar() throws WebServiceException {
		try {
			ClientResponse response = RestClient.get("starships?page=1");			
			JSONObject json = response.getEntity(JSONObject.class);			
			String results = json.getString("results");
			
			ObjectMapper mapper = new ObjectMapper();
	    	TypeReference<List<Nave>> mapType = new TypeReference<List<Nave>>() {};
	    	List<Nave> naves = mapper.readValue(results, mapType);
	    	
	    	return naves;

		} catch (Exception e) {
			throw new WebServiceException(e);
		}
	}

}
