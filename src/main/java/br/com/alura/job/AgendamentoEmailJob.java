package br.com.alura.job;

import java.util.List;

import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.alura.domain.AgendamentoEmail;
import br.com.alura.service.AgendamentoEmailService;

@Singleton
public class AgendamentoEmailJob {
 
	@Inject
	private AgendamentoEmailService agendamentoEmailService;
	
	@Schedule(hour = "*", minute = "*", second = "*/10")
	public void enviarEmail() {
		List<AgendamentoEmail> listaEmailsNaoAgendados = agendamentoEmailService.listarPorNaoAgendado();
		listaEmailsNaoAgendados.forEach(emailNaoAgendado -> {
			agendamentoEmailService.enviarEmail(emailNaoAgendado);
			agendamentoEmailService.alterar(emailNaoAgendado);
		});
		
	}
}
