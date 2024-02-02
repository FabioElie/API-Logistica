package br.com.logistics.api.dto.material;

import br.com.logistics.api.entity.Material;

import java.math.BigDecimal;

public record DadosListagemMateriais(Long id, String nome, BigDecimal densidade, BigDecimal preco) {

    public DadosListagemMateriais(Material material) {
        this(material.getId(), material.getNome(), material.getDensidade(), material.getPreco());
    }
}
