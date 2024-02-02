package br.com.logistics.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record EnderecoDTO(
        @NotBlank
        String numero,
        @NotBlank
        String rua,
        @NotBlank
        String bairro,
        @NotBlank
        String cidade,
        @NotBlank
        String uf,

        String complemento,

        @Pattern(regexp = "\\d{5}-\\d{3}", message = "CEP invalido, tente 12345-123")
        String cep) {


}
