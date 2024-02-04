package br.com.logistics.api.dto.vendedor;

import br.com.logistics.api.entity.Vendedor;

public record DadosDetalhamentoVendedor(Long id, String nome, String email, String telefone) {
    public DadosDetalhamentoVendedor(Vendedor vendedor) {
        this(vendedor.getId(), vendedor.getNome(), vendedor.getEmail(), vendedor.getTelefone());
    }
}
