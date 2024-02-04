package br.com.logistics.api.dto.material;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record MaterialDTO(
        @NotBlank
        String nome,
        @NotNull
        @DecimalMin(value = "0.30", message = "A densidade deve ser maior ou igual a 0.30")
        BigDecimal densidade,
        @NotNull
        @DecimalMin(value = "0.00", message = "O preço deve ser maior ou igual a 0.00")
        BigDecimal preco)
        {
}
