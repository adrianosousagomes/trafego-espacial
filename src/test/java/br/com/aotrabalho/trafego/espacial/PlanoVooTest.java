package br.com.aotrabalho.trafego.espacial;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import br.com.aotrabalho.trafego.espacial.model.Nave;
import br.com.aotrabalho.trafego.espacial.model.Planeta;
import br.com.aotrabalho.trafego.espacial.model.PlanoVoo;
import br.com.aotrabalho.trafego.espacial.model.Tripulante;

public class PlanoVooTest {

	@Test
	public void valido() {
		Nave nave = new Nave("Teste", "Teste F100", 6);
		Planeta planeta = new Planeta("Terra", "123456789", "temperado", "floresta", "123456789");
		List<Tripulante> tripulantes = new ArrayList<Tripulante>();
		Tripulante trip = new Tripulante("Adriano Gomes", "Homem");
		tripulantes.add(trip);
		
		PlanoVoo plano = new PlanoVoo(nave, planeta, tripulantes, null);
		
		boolean resultado = plano.isValido();
		assertTrue(resultado);		
	}
	
	@Test
	public void invalidoNave() {
		Nave nave = null;
		Planeta planeta = new Planeta("Terra", "123456789", "temperado", "floresta", "123456789");
		List<Tripulante> tripulantes = new ArrayList<Tripulante>();
		Tripulante trip = new Tripulante("Adriano Gomes", "Homem");
		tripulantes.add(trip);
		
		PlanoVoo plano = new PlanoVoo(nave, planeta, tripulantes, null);
		
		boolean resultado = plano.isValido();
		assertFalse(resultado);		
	}
	
	@Test
	public void invalidoPlaneta() {
		Nave nave = new Nave("Teste", "Teste F100", 6);
		Planeta planeta = null;
		List<Tripulante> tripulantes = new ArrayList<Tripulante>();
		Tripulante trip = new Tripulante("Adriano Gomes", "Homem");
		tripulantes.add(trip);
		
		PlanoVoo plano = new PlanoVoo(nave, planeta, tripulantes, null);
		
		boolean resultado = plano.isValido();
		assertFalse(resultado);		
	}
	
	@Test
	public void invalidoTripulante() {
		Nave nave = new Nave("Teste", "Teste F100", 6);
		Planeta planeta = new Planeta("Terra", "123456789", "temperado", "floresta", "123456789");
		List<Tripulante> tripulantes = null;
		
		PlanoVoo plano = new PlanoVoo(nave, planeta, tripulantes, null);
		
		boolean resultado = plano.isValido();
		assertFalse(resultado);		
	}
	
	@Test
	public void invalidoTripulanteEmpty() {
		Nave nave = new Nave("Teste", "Teste F100", 6);
		Planeta planeta = new Planeta("Terra", "123456789", "temperado", "floresta", "123456789");
		List<Tripulante> tripulantes = new ArrayList<Tripulante>();
		
		PlanoVoo plano = new PlanoVoo(nave, planeta, tripulantes, null);
		
		boolean resultado = plano.isValido();
		assertFalse(resultado);		
	}
	
	@Test
	public void tripulanteABordo() {
		Nave nave = new Nave("Teste", "Teste F100", 6);
		Planeta planeta = new Planeta("Terra", "123456789", "temperado", "floresta", "123456789");
		List<Tripulante> tripulantes = new ArrayList<Tripulante>();
		
		Tripulante trip1 = new Tripulante("Adriano Gomes", "Homem");
		Tripulante trip2 = new Tripulante("Joao Mares", "Homem");
		tripulantes.add(trip1);
		tripulantes.add(trip2);
		
		PlanoVoo plano = new PlanoVoo(nave, planeta, tripulantes, null);
		
		boolean resultado = plano.existeTripulante(trip2);
		assertTrue(resultado);		
	}
	
	@Test
	public void tripulanteNaoEstaABordo() {
		Nave nave = new Nave("Teste", "Teste F100", 6);
		Planeta planeta = new Planeta("Terra", "123456789", "temperado", "floresta", "123456789");
		List<Tripulante> tripulantes = new ArrayList<Tripulante>();
		
		Tripulante trip1 = new Tripulante("Adriano Gomes", "Homem");
		Tripulante trip2 = new Tripulante("Joao Mares", "Homem");
		tripulantes.add(trip1);
		tripulantes.add(trip2);
		
		Tripulante trip3 = new Tripulante("Maria do Carmo", "Mulher");
		
		PlanoVoo plano = new PlanoVoo(nave, planeta, tripulantes, null);
		
		boolean resultado = plano.existeTripulante(trip3);
		assertFalse(resultado);		
	}
	
	
	@Test
	public void numeroTripulacaoBaixoPermiteAdicionar() {
		Nave nave = new Nave("Teste", "Teste F100", 6);
		Planeta planeta = new Planeta("Terra", "123456789", "temperado", "floresta", "123456789");
		List<Tripulante> tripulantes = new ArrayList<Tripulante>();
		
		Tripulante trip1 = new Tripulante("Adriano Gomes", "Homem");
		Tripulante trip2 = new Tripulante("Joao Mares", "Homem");
		Tripulante trip3 = new Tripulante("Maria do Carmo", "Mulher");
		tripulantes.add(trip1);
		tripulantes.add(trip2);
		tripulantes.add(trip3);
		
		PlanoVoo plano = new PlanoVoo(nave, planeta, tripulantes, null);
		
		boolean resultado = plano.permiteAdicionarTripulacao();
		assertTrue(resultado);
	}
	
	@Test
	public void numeroTripulacaoAtingido() {
		Nave nave = new Nave("Teste", "Teste F100", 6);
		Planeta planeta = new Planeta("Terra", "123456789", "temperado", "floresta", "123456789");
		List<Tripulante> tripulantes = new ArrayList<Tripulante>();
		
		tripulantes.add(new Tripulante("Adriano Gomes", "Homem"));
		tripulantes.add(new Tripulante("Joao Mares", "Homem"));
		tripulantes.add(new Tripulante("Maria do Carmo", "Mulher"));
		tripulantes.add(new Tripulante("Jose Alves", "Homem"));
		tripulantes.add(new Tripulante("Mario Brother", "Homem"));
		tripulantes.add(new Tripulante("Chamy", "Mulher"));
		
		PlanoVoo plano = new PlanoVoo(nave, planeta, tripulantes, null);
		
		boolean resultado = plano.permiteAdicionarTripulacao();
		assertFalse(resultado);
	}
	
	

	@Test
	public void planetaDestinoInvalido() {
		Nave nave = new Nave("Teste", "Teste F100", 6);
		Planeta planeta = new Planeta("Terra", "123456789", "temperado", "floresta", "123456789");
		List<Tripulante> tripulantes = new ArrayList<Tripulante>();
		
		tripulantes.add(new Tripulante("Adriano Gomes", "Homem"));
		tripulantes.add(new Tripulante("Joao Mares", "Homem"));
		tripulantes.add(new Tripulante("Maria do Carmo", "Mulher"));
		tripulantes.add(new Tripulante("Jose Alves", "Homem"));
		tripulantes.add(new Tripulante("Mario Brother", "Homem"));
		tripulantes.add(new Tripulante("Chamy", "Mulher"));
		
		PlanoVoo vooAnterior = new PlanoVoo(nave, planeta, tripulantes, null);
		
		Nave nave2 = new Nave("Teste 2", "Teste F200", 3);
		Planeta planeta2 = new Planeta("Terra", "123456789", "temperado", "floresta", "123456789");
		List<Tripulante> tripulantes2 = new ArrayList<Tripulante>();
		
		tripulantes.add(new Tripulante("Paulo Soares Gomes", "Homem"));
		tripulantes.add(new Tripulante("Tiririca", "n/a"));
		tripulantes.add(new Tripulante("Eduardo Borges", "Homem"));
		
		PlanoVoo novoPlanoVoo = new PlanoVoo(nave2, planeta2, tripulantes2, vooAnterior);
		
		boolean resultado = novoPlanoVoo.planetaDestinoDiferenteUltimoVoo();
		assertFalse(resultado);
	}
	
	@Test
	public void planetaDestinoValido() {
		Nave nave = new Nave("Teste", "Teste F100", 6);
		Planeta planeta = new Planeta("Terra", "123456789", "temperado", "floresta", "123456789");
		List<Tripulante> tripulantes = new ArrayList<Tripulante>();
		
		tripulantes.add(new Tripulante("Adriano Gomes", "Homem"));
		tripulantes.add(new Tripulante("Joao Mares", "Homem"));
		tripulantes.add(new Tripulante("Maria do Carmo", "Mulher"));
		tripulantes.add(new Tripulante("Jose Alves", "Homem"));
		tripulantes.add(new Tripulante("Mario Brother", "Homem"));
		tripulantes.add(new Tripulante("Chamy", "Mulher"));
		
		PlanoVoo vooAnterior = new PlanoVoo(nave, planeta, tripulantes, null);
		
		Nave nave2 = new Nave("Teste 2", "Teste F200", 3);
		Planeta planeta2 = new Planeta("Jupiter", "987654321", "polar", "gelo", "987654321");
		List<Tripulante> tripulantes2 = new ArrayList<Tripulante>();
		
		tripulantes.add(new Tripulante("Paulo Soares Gomes", "Homem"));
		tripulantes.add(new Tripulante("Tiririca", "n/a"));
		tripulantes.add(new Tripulante("Eduardo Borges", "Homem"));
		
		PlanoVoo novoPlanoVoo = new PlanoVoo(nave2, planeta2, tripulantes2, vooAnterior);
		
		boolean resultado = novoPlanoVoo.planetaDestinoDiferenteUltimoVoo();
		assertTrue(resultado);
	}
}
