package br.com.aotrabalho.trafego.espacial.common;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;

/**
 * Disponibiliza metodos comuns e utils em todo projeto.
 * @author adriano.gomes
 */
public class Utilitario {
	/**
	 * Apresenta mensagem de sucesso, alerta ou erro na tela.
	 * @param titulo
	 * @param detalhes
	 * @param severity
	 */
	public static void showMessage(String msg, Severity severity) {
        FacesMessage message = new FacesMessage(severity, msg, null);
        FacesContext.getCurrentInstance().addMessage(null, message);
	}
}
