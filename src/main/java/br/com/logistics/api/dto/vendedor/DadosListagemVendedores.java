package br.com.logistics.api.dto.vendedor;

import br.com.logistics.api.entity.Vendedor;

public record DadosListagemVendedores(Long id, String nome, String email, String telefone, String cpf) {

    public DadosListagemVendedores(Vendedor vendedor) {
        this(vendedor.getId(), vendedor.getNome(), vendedor.getEmail(), vendedor.getTelefone(), vendedor.getCpf());
    }
}
