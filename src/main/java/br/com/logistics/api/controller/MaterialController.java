package br.com.logistics.api.controller;

import br.com.logistics.api.dto.material.DadosAtualizacaoMaterial;
import br.com.logistics.api.dto.material.DadosListagemMateriais;
import br.com.logistics.api.dto.material.MaterialDTO;
import br.com.logistics.api.entity.Material;
import br.com.logistics.api.repository.MaterialRepository;
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
@RequestMapping("materiais")
public class MaterialController {

    @Autowired
    private MaterialRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid MaterialDTO dados, UriComponentsBuilder uriComponentsBuilder) {
        var material = repository.save(new Material(dados));
        var uri = uriComponentsBuilder.path("/materiais/{id}").buildAndExpand(material).toUri();
        return  ResponseEntity.created(uri).body(material);
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemMateriais>> listar(@PageableDefault(size = 10, sort = {"nome"})Pageable paginacao) {
        var page = repository.findAllByAtivoTrue(paginacao).map(DadosListagemMateriais::new);
        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DadosListagemMateriais> atualizar(@RequestBody @Valid DadosAtualizacaoMaterial dados){
        var material = repository.getReferenceById(dados.id());
        material.atualizarInformacoesDoMaterial(dados);
        return ResponseEntity.ok(new DadosListagemMateriais(material));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Long id){
        var material = repository.getReferenceById(id);
        material.excluir();
        return ResponseEntity.noContent().build();
    }


}
