package br.com.alura.service;

import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.alura.dao.AgendamentoEmailDAO;
import br.com.alura.domain.AgendamentoEmail;

@Stateless
public class AgendamentoEmailService {
	
	private static final Logger logger = Logger.getLogger(AgendamentoEmailService.class.getName());
	
	@Inject
	private AgendamentoEmailDAO agendamentoEmailDAO;

	public List<AgendamentoEmail> listar(){
		return agendamentoEmailDAO.listar();
	}
	
	public void inserir(AgendamentoEmail agendamentoemail) {
		agendamentoemail.setAgendado(false);
		agendamentoEmailDAO.inserir(agendamentoemail);
	}
	
	public List<AgendamentoEmail> listarPorNaoAgendado(){
		return agendamentoEmailDAO.listarPorNaoAgendado();
	}
	
	public void alterar(AgendamentoEmail agendamentoEmail) {
		agendamentoEmail.setAgendado(true);
		agendamentoEmailDAO.alterar(agendamentoEmail);
	}
	
	public void enviarEmail(AgendamentoEmail agendamentoEmail) {
		try {
			Thread.sleep(5000);
			logger.info("O e-mail do usuário " + agendamentoEmail.getEmail() + " foi enviado!");
		} catch (Exception e) {
			logger.info(e.getMessage());
		}
	}
}
