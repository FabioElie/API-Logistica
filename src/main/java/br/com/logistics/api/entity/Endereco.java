package br.com.logistics.api.entity;

import br.com.logistics.api.dto.EnderecoDTO;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor

public class Endereco {
    private String numero;
    private String rua;
    private String bairro;
    private String cidade;
    private String uf;
    private String complemento;
    private String cep;



    public Endereco(EnderecoDTO dados) {
        this.numero = dados.numero();
        this.rua = dados.rua();
        this.bairro = dados.bairro();
        this.cidade = dados.cidade();
        this.uf = dados.uf();
        this.complemento = dados.complemento();
        this.cep = dados.cep();
    }

    public void atualizarInformacoesDoEndereco(EnderecoDTO dados) {
        if (dados.numero() != null) {
            this.numero = dados.numero();
        }
        if (dados.rua() != null) {
            this.rua = dados.rua();
        }
        if (dados.bairro() != null) {
            this.bairro = dados.bairro();
        }
        if (dados.cidade() != null) {
            this.cidade = dados.cidade();
        }
        if (dados.uf() != null) {
            this.uf = dados.uf();
        }
        if (dados.complemento() != null) {
            this.complemento = dados.complemento();
        }
        if (dados.cep() != null) {
            this.cep = dados.cep();
        }
    }




}
