package br.com.logistics.api.dto.vendedor;

import br.com.logistics.api.entity.Vendedores;

public record DadosDetalhamentoVendedor(Long id, String nome, String email, String telefone) {
    public DadosDetalhamentoVendedor(Vendedores vendedor) {
        this(vendedor.getId(), vendedor.getNome(), vendedor.getEmail(), vendedor.getTelefone());
    }
}
