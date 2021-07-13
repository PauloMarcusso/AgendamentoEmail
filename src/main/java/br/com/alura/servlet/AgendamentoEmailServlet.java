package br.com.alura.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.alura.domain.AgendamentoEmail;
import br.com.alura.service.AgendamentoEmailService;

@WebServlet("/emails")
public class AgendamentoEmailServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Inject
	private AgendamentoEmailService agendamentoEmailService;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter pw = resp.getWriter();
		agendamentoEmailService.listar().forEach(resultado -> pw.print("os emails dispoíveis são: " + resultado.getEmail()));
		pw.close();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		BufferedReader reader= req.getReader();
		String[] email = reader.readLine().split(",");
		AgendamentoEmail agendamentoEmail = new AgendamentoEmail();
		agendamentoEmail.setEmail(email[0]);
		agendamentoEmail.setMensagem(email[1]);
		agendamentoEmail.setAssunto(email[2]);
		agendamentoEmailService.inserir(agendamentoEmail);
	}

}
