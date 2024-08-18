package br.com.logistics.api.dto.cliente;

import br.com.logistics.api.entity.Clientes;
import br.com.logistics.api.entity.Endereco;

    public record DadosListagemClientes(Long id, String nome, String email, String telefone, String cnpjCpf, Endereco endereco) {

    public DadosListagemClientes(Clientes cliente) {
        this(cliente.getId(), cliente.getNome(), cliente.getEmail(), cliente.getTelefone(), cliente.getCnpjCpf(), cliente.getEndereco());
        }
}
