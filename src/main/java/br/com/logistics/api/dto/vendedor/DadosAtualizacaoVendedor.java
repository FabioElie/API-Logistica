package br.com.logistics.api.dto.vendedor;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoVendedor(
        @NotNull
        Long id,

        String nome,
        @Email
        String email,
        String telefone)
        {
}

