package br.com.logistics.api.dto.caminhao;

import br.com.logistics.api.util.TipoDoCaminhao;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.math.BigDecimal;

public record DadosAtualizacaoCaminhao(
        @NotNull
        Long id,
        String nomeDoMotorista,
        String telefone,
        @Pattern(regexp = "(^\\d{3}\\x2E\\d{3}\\x2E\\d{3}\\x2D\\d{2}$)", message = "Formato de CPF inválido, tente 123.123.123-12.")
        String cpf,
        String modelo,
        @Pattern(regexp = "^[A-Z]{3}-[A-Z0-9]{4}$", message = "Formato de placa inválido, tente AAA-1B34 ou AAA-1234")
        String placa,
        TipoDoCaminhao tipoDoCaminhao,
        BigDecimal capacidade) {
}
