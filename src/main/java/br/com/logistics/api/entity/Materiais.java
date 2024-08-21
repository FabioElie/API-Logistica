package br.com.logistics.api.entity;

import br.com.logistics.api.dto.material.DadosAtualizacaoMaterial;
import br.com.logistics.api.dto.material.MaterialDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity
public class Materiais {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private BigDecimal densidade;
    private BigDecimal preco;
    private Boolean ativo;


    public Materiais(MaterialDTO dados) {
        this.nome = dados.nome();
        this.densidade = dados.densidade();
        this.preco = dados.preco();
        this.ativo = true;
    }

    public void atualizarInformacoesDoMaterial(DadosAtualizacaoMaterial dados) {
        if (dados.nome() != null) {
            this.nome = dados.nome();
        }
        if (dados.densidade() != null) {
            this.densidade = dados.densidade();
        }
        if (dados.preco() != null) {
            this.preco = dados.preco();
        }
    }

    public void excluir() {
        this.ativo = false;
    }

}
