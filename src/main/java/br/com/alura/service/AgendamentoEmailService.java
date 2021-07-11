package br.com.alura.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.alura.dao.AgendamentoEmailDAO;
import br.com.alura.domain.AgendamentoEmail;

@Stateless
public class AgendamentoEmailService {
	
	@Inject
	private AgendamentoEmailDAO agendamentoEmailDAO;

	public List<AgendamentoEmail> listar(){
		return agendamentoEmailDAO.listar();
	}
}
