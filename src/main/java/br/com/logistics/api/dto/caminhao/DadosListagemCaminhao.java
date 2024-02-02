package br.com.logistics.api.dto.caminhao;

import br.com.logistics.api.entity.Caminhao;
import br.com.logistics.api.util.TipoDoCaminhao;

import java.math.BigDecimal;

public record DadosListagemCaminhao(Long id, String nomeDoMotorista, String telefone, String cpf, String modelo, String placa, TipoDoCaminhao tipoDoCaminhao,
                                    BigDecimal capacidade) {

    public DadosListagemCaminhao(Caminhao caminhao) {
        this(caminhao.getId(), caminhao.getNomeDoMotorista(), caminhao.getTelefone(), caminhao.getCpf(), caminhao.getModelo(), caminhao.getPlaca(), caminhao.getTipoDoCaminhao(), caminhao.getCapacidade());
    }
}
