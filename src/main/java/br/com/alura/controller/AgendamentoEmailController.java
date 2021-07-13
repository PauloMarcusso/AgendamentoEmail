package br.com.alura.controller;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.alura.domain.AgendamentoEmail;
import br.com.alura.service.AgendamentoEmailService;

@Path("emails")
public class AgendamentoEmailController {

	@Inject
	AgendamentoEmailService agendamentoEmailService;

	@GET
	@Produces(value = MediaType.APPLICATION_JSON)
	public Response listar() {
		return Response.ok(agendamentoEmailService.listar()).build();
	}

	@POST
	@Consumes(value = MediaType.APPLICATION_JSON)
	public Response inserir(AgendamentoEmail agendamentoEmail) {
		agendamentoEmailService.inserir(agendamentoEmail);
		return Response.status(201).build();
	}

}
