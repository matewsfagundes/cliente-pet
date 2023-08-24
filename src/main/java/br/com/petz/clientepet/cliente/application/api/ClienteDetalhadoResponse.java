package br.com.petz.clientepet.cliente.application.api;

import java.time.LocalDateTime;
import java.util.UUID;

import lombok.Value;

@Value
public class ClienteDetalhadoResponse {
	private UUID idCliente;
	private String nomeCompleto;
	private String cpf;
	private String email;
	private String celular;
	private LocalDateTime dataHoraDaUltimaAlteracao;

}
