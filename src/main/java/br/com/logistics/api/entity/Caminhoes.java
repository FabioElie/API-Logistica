package br.com.logistics.api.entity;

import br.com.logistics.api.dto.caminhao.CaminhaoDTO;
import br.com.logistics.api.dto.caminhao.DadosAtualizacaoCaminhao;
import br.com.logistics.api.util.TipoDoCaminhao;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity(name = "Caminhao")
public class Caminhoes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nomeDoMotorista;
    private String telefone;
    private String cpf;
    private String modelo;
    private String placa;
    @Enumerated(EnumType.STRING)
    private TipoDoCaminhao tipoDoCaminhao;
    private BigDecimal capacidade;
    private Boolean ativo;

    public Caminhoes(CaminhaoDTO dados) {
        this.nomeDoMotorista = dados.nomeDoMotorista();
        this.telefone = dados.telefone();
        this.cpf = dados.cpf();
        this.modelo = dados.modelo();
        this.placa = dados.placa();
        this.tipoDoCaminhao = dados.tipoDoCaminhao();
        this.capacidade = dados.capacidade();
        this.ativo = true;
    }

    public void atualizarInformacoesDoCaminhao(DadosAtualizacaoCaminhao dados) {
        if (dados.nomeDoMotorista() != null) {
            this.nomeDoMotorista = dados.nomeDoMotorista();
        }
        if (dados.telefone() != null) {
            this.telefone = dados.telefone();
        }
        if (dados.cpf() != null) {
            this.cpf = dados.cpf();
        }
        if (dados.modelo() != null) {
            this.modelo = dados.modelo();
        }
        if (dados.placa() != null) {
            this.placa = dados.placa();
        }
        if (dados.tipoDoCaminhao() != null) {
            this.tipoDoCaminhao = dados.tipoDoCaminhao();
        }
        if (dados.capacidade() != null) {
            this.capacidade = dados.capacidade();
        }

    }

    public void excluir() {
        this.ativo = false;
    }
}
