package br.com.petz.clientepet.cliente.infra;

import java.util.List;
import java.util.UUID;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import br.com.petz.clientepet.cliente.application.repository.ClienteRepository;
import br.com.petz.clientepet.cliente.domain.Cliente;
import br.com.petz.clientepet.handler.APIException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Repository
@Log4j2
@RequiredArgsConstructor
public class ClienteInfraRepository implements ClienteRepository {
	private final ClienteSpringJPARepository clienteSpringJPARepository;

	@Override
	public Cliente salva(Cliente cliente) {
		log.info("[inicia] ClienteInfraRepository - salva");
		try {
			clienteSpringJPARepository.save(cliente);
		} catch (DataIntegrityViolationException e) {
			throw APIException.build(HttpStatus.BAD_REQUEST, "existem dados duplicados", e);
		}
		log.info("[finaliza] ClienteInfraRepository - salva");
		return cliente;
	}

	@Override
	public List<Cliente> buscaTodosClientes() {
		log.info("[inicia] ClienteInfraRepository - buscaTodosClientes");
		List<Cliente> todosClientes = clienteSpringJPARepository.findAll();
		log.info("[finaliza] ClienteInfraRepository - buscaTodosClientes");
		return todosClientes;
	}

	@Override
	public Cliente buscaClienteAtravesId(UUID idCliente) {
		log.info("[inicia] ClienteInfraRepository - buscaClienteAtravesId");
		Cliente cliente = clienteSpringJPARepository.findById(idCliente)
				.orElseThrow(() -> APIException.build(HttpStatus.NOT_FOUND, "Cliente Não encontrado"));
		log.info("[finaliza] ClienteInfraRepository - buscaClienteAtravesId");
		return cliente;
	}

	@Override
	public void deletaClienteAtravesId(Cliente cliente) {
		log.info("[inicia] ClienteInfraRepository - deletaClienteAtravesId");
		clienteSpringJPARepository.delete(cliente);
		log.info("[finaçiza] ClienteInfraRepository - deletaClienteAtravesId");

	}
}