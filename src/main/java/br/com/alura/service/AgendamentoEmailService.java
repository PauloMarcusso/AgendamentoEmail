package br.com.alura.service;

import java.util.List;

import javax.ejb.Stateless;

@Stateless
public class AgendamentoEmailService {

	public List<String> listar(){
		return List.of("paulo@hst.com.br", "pamela@midway.com.br");
	}
}
