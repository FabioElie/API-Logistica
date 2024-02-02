package br.com.logistics.api.controller;

import br.com.logistics.api.dto.caminhao.CaminhaoDTO;
import br.com.logistics.api.dto.caminhao.DadosAtualizacaoCaminhao;
import br.com.logistics.api.dto.caminhao.DadosListagemCaminhao;
import br.com.logistics.api.entity.Caminhao;
import br.com.logistics.api.repository.CaminhaoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("caminhoes")
public class CaminhaoController {

    @Autowired
    private CaminhaoRepository repository;

    @PostMapping
    @Transactional
    public void cadastrar(@RequestBody @Valid CaminhaoDTO dados) {
        repository.save(new Caminhao(dados));
    }


    @GetMapping
    public Page<DadosListagemCaminhao> listar(@PageableDefault(size = 10, sort = {"placa"})Pageable paginacao) {
        return repository.findAllByAtivoTrue(paginacao).map(DadosListagemCaminhao::new);
    }

    @PutMapping
    @Transactional
    public void atualizar(@RequestBody @Valid DadosAtualizacaoCaminhao dados){
        var caminhao = repository.getReferenceById(dados.id());
        caminhao.atualizarInformacoesDoCaminhao(dados);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void excluir(@PathVariable Long id){
        var caminhao = repository.getReferenceById(id);
        caminhao.excluir();
    }
}

