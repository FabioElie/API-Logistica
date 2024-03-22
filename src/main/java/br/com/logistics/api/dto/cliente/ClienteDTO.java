package br.com.logistics.api.dto.cliente;

import br.com.logistics.api.dto.EnderecoDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record ClienteDTO(
        @NotBlank
        String nome,
        @NotBlank
        @Email
        String email,
        @NotBlank
        String telefone,
        @NotBlank
        @Pattern(regexp = "(\\d{3}.\\d{3}.\\d{3}-\\d{2}|\\d{2}.\\d{3}.\\d{3}/\\d{4}-\\d{2})", message = "Formato de CNPJ ou CPF inválido, tente 123.456.789-01 ou 01.234.567/8901-23")
        String cnpjCpf,
        @NotNull
        @Valid
        EnderecoDTO endereco) {
}
