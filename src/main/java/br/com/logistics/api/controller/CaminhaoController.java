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
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("caminhoes")
public class CaminhaoController {

    @Autowired
    private CaminhaoRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity<DadosListagemCaminhao> cadastrar(@RequestBody @Valid CaminhaoDTO dados, UriComponentsBuilder uriComponentsBuilder) {
        var caminhao = repository.save(new Caminhao(dados));
        var uri = uriComponentsBuilder.path("/caminhoes/{id}").buildAndExpand(caminhao.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosListagemCaminhao(caminhao));
    }


    @GetMapping
    public ResponseEntity<Page<DadosListagemCaminhao>> listar(@PageableDefault(size = 20, sort = {"placa"}) Pageable paginacao) {
        var page = repository.findAllByAtivoTrue(paginacao).map(DadosListagemCaminhao::new);
        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DadosListagemCaminhao> atualizar(@RequestBody @Valid DadosAtualizacaoCaminhao dados) {
        var caminhao = repository.getReferenceById(dados.id());
        caminhao.atualizarInformacoesDoCaminhao(dados);

        return ResponseEntity.ok(new DadosListagemCaminhao(caminhao));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        var caminhao = repository.getReferenceById(id);
        caminhao.excluir();

        return ResponseEntity.noContent().build();
    }
}

