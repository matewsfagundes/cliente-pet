package br.com.petz.clientepet.cliente.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.br.CPF;

import br.com.petz.clientepet.cliente.application.api.ClienteRequest;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Entity
public class Cliente {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", updatable = false, unique = true, nullable = false)
	private UUID idCliente;
	@NotBlank
	private String nomeCompleto;
	@NotBlank
	@Email
	private String email;
	@NotBlank
	private String celular;
	private String telefone;
	private Sexo sexo;
	@NotNull
	private LocalDate dataNascimento;
	@CPF
	private String cpf;
	@NotNull
	private boolean aceitaTermos;

	private LocalDateTime dataHoraDoCadastro;
	private LocalDateTime dataHoraUltimaAlteracao;

	public Cliente(ClienteRequest clienteRequeste) {
		this.nomeCompleto = clienteRequeste.getNomeCompleto();
		this.email = clienteRequeste.getEmail();
		this.celular = clienteRequeste.getCelular();
		this.telefone = clienteRequeste.getTelefone();
		this.sexo = clienteRequeste.getSexo();
		this.dataNascimento =clienteRequeste.getDataNascimento();
		this.cpf = clienteRequeste.getCpf();
		this.aceitaTermos = clienteRequeste.isAceitaTermos();
		this.dataHoraDoCadastro = LocalDateTime.now();
	}
}
