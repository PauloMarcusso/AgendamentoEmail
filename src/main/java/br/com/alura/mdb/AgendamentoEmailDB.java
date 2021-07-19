package br.com.alura.mdb;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.inject.Inject;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

import br.com.alura.domain.AgendamentoEmail;
import br.com.alura.service.AgendamentoEmailService;

@MessageDriven(activationConfig = {
		@ActivationConfigProperty(propertyName = "destinationLookup", 
				propertyValue = "java:/jms/queue/EmailQueue"),
		@ActivationConfigProperty(propertyName = "destinationType",
		propertyValue = "javax.jms.Queue")
})
public class AgendamentoEmailDB implements MessageListener{
	
	@Inject
	private AgendamentoEmailService agendamentoEmailService;

	@Override
	public void onMessage(Message message) {
		try {
			AgendamentoEmail agendamentoEmail = message.getBody(AgendamentoEmail.class);
			agendamentoEmailService.enviarEmail(agendamentoEmail);			
		} catch (JMSException e) {
			throw new RuntimeException(e);
		}
		
	}
	
	

}
