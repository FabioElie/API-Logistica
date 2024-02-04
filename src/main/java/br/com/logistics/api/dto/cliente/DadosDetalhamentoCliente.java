package br.com.logistics.api.dto.cliente;

import br.com.logistics.api.entity.Cliente;
import br.com.logistics.api.entity.Endereco;

public record DadosDetalhamentoCliente(Long id, String nome, String email, String telefone, Endereco endereco) {

    public DadosDetalhamentoCliente(Cliente cliente) {
        this(cliente.getId(), cliente.getNome(), cliente.getEmail(), cliente.getTelefone(), cliente.getEndereco());
    }
}

