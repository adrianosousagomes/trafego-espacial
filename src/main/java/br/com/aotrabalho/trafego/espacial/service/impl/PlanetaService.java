package br.com.aotrabalho.trafego.espacial.service.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.xml.ws.WebServiceException;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.codehaus.jettison.json.JSONObject;

import br.com.aotrabalho.trafego.espacial.common.RestClient;
import br.com.aotrabalho.trafego.espacial.model.Planeta;
import br.com.aotrabalho.trafego.espacial.service.IPlaneta;

import com.sun.jersey.api.client.ClientResponse;

/**
 * Implementacao do servico Planeta. 
 * @author adriano.gomes
 *
 */
@Stateless
public class PlanetaService implements IPlaneta{

	@Override
	public List<Planeta> listar() throws WebServiceException {
		try {
			ClientResponse response = RestClient.get("planets?page=1");			
			JSONObject json = response.getEntity(JSONObject.class);			
			String results = json.getString("results");
			
			ObjectMapper mapper = new ObjectMapper();
	    	TypeReference<List<Planeta>> mapType = new TypeReference<List<Planeta>>() {};
	    	List<Planeta> planetas = mapper.readValue(results, mapType);
	    	
	    	return planetas;

		} catch (Exception e) {
			throw new WebServiceException(e);
		}
	}

}
