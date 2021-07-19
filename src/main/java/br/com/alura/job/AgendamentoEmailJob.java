package br.com.alura.job;

import java.util.List;

import javax.annotation.Resource;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.JMSConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.Queue;

import br.com.alura.domain.AgendamentoEmail;
import br.com.alura.service.AgendamentoEmailService;

@Singleton
public class AgendamentoEmailJob {
 
	@Inject
	private AgendamentoEmailService agendamentoEmailService;
	
	@Inject
	@JMSConnectionFactory("java:jboss/DefaultJMSConnectionFactory")
	private JMSContext context;
	
	@Resource(mappedName = "java:/jms/queue/EmailQueue")
	private Queue queue;
	
	@Schedule(hour = "*", minute = "*", second = "*/10")
	public void enviarEmail() {
		List<AgendamentoEmail> listaEmailsNaoAgendados = agendamentoEmailService.listarPorNaoAgendado();
		listaEmailsNaoAgendados.forEach(emailNaoAgendado -> {
			context.createProducer().send(queue, emailNaoAgendado);
			agendamentoEmailService.alterar(emailNaoAgendado);
		});
		
	}
}
