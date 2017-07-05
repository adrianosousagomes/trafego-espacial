package br.com.aotrabalho.trafego.espacial.common;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;

/**
 * Responsavel por interagir com servico REST.
 * @author adriano.gomes
 *
 */
public class RestClient {
	
	private static final String urlAPI = "http://swapi.co/api/";
	/**
	 * Realizar requisicao http com servico rest.
	 * @param String operacao
	 * @return ClientResponse
	 */
	public static final ClientResponse get(String operacao){
		try {
			/*
			 * adriano.gomes - Configuracao para permitir mapeamento de json para um objeto.
			 */
			ClientConfig clientConfig = new DefaultClientConfig();
			clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
			
			Client client = Client.create(clientConfig);
	        WebResource resource = client.resource(urlAPI + operacao);
	 
	        ClientResponse response = resource.accept("application/json")
	        		.header("user-agent", "")//adriano.gomes - Cabecalho adicionado para solucionar problema de erro 403 (CORS)
	                .get(ClientResponse.class);
	        
	        if (response.getStatus() != 200) {
	 		   throw new RuntimeException("Failed : HTTP error code : "
	 			+ response.getStatus());
	 		}
	        
	        return response;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
