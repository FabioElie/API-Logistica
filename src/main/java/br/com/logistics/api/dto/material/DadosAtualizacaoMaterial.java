package br.com.logistics.api.dto.material;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record DadosAtualizacaoMaterial(
        @NotNull
        Long id,
        String nome,
        @DecimalMin(value = "0.30", inclusive = true, message = "A densidade deve ser maior ou igual a 0.30")
        BigDecimal densidade,
        @DecimalMin(value = "0.00", inclusive = true, message = "O preço deve ser maior ou igual a 0.00")
        BigDecimal preco) {
}

