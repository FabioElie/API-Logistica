package br.com.logistics.api.dto.caminhao;

import br.com.logistics.api.util.TipoDoCaminhao;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.math.BigDecimal;

public record CaminhaoDTO(
        @NotBlank
        String nomeDoMotorista,
        @NotBlank
        String telefone,
        @NotBlank
        @Pattern(regexp = "(^\\d{3}\\x2E\\d{3}\\x2E\\d{3}\\x2D\\d{2}$)", message = "Formato de CPF inválido, utilize no formato 123.123.123-12.")
        String cpf,
        @NotBlank
        String modelo,
        @NotBlank
        @Pattern(regexp = "^[A-Z]{3}-[A-Z0-9]{4}$", message = "Formato de placa inválido, utilize no formato AAA-1B34 ou AAA-1234")
        String placa,
        @NotNull
        TipoDoCaminhao tipoDoCaminhao,
        @NotNull
        BigDecimal capacidade) {
}
