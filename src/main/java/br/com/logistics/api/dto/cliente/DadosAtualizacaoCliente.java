package br.com.logistics.api.dto.cliente;

import br.com.logistics.api.dto.EnderecoDTO;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;

public record DadosAtualizacaoCliente(
        @NotNull
        Long id,
        String nome,
        @Email
        String email,
        String telefone,
        @Validated
        EnderecoDTO endereco)
        {
}
