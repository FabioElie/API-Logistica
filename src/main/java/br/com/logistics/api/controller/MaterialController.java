package br.com.logistics.api.controller;

import br.com.logistics.api.dto.caminhao.CaminhaoDTO;
import br.com.logistics.api.dto.caminhao.DadosListagemCaminhao;
import br.com.logistics.api.dto.material.DadosAtualizacaoMaterial;
import br.com.logistics.api.dto.material.DadosListagemMateriais;
import br.com.logistics.api.dto.material.MaterialDTO;
import br.com.logistics.api.entity.Caminhoes;
import br.com.logistics.api.entity.Materiais;
import br.com.logistics.api.repository.MateriaisRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("materiais")
public class MaterialController {

    @Autowired
    private MateriaisRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity<DadosListagemMateriais> cadastrar(@RequestBody @Valid MaterialDTO dados, UriComponentsBuilder uriComponentsBuilder) {
        var material = repository.save(new Materiais(dados));
        var uri = uriComponentsBuilder.path("/materiais/{id}").buildAndExpand(material).toUri();
        return  ResponseEntity.created(uri).body(new DadosListagemMateriais(material));
    }

    @PostMapping("/lista")
    @Transactional
    public ResponseEntity<List<DadosListagemMateriais>> cadastrarMateriais(@RequestBody @Valid List<MaterialDTO> dados, UriComponentsBuilder uriComponentsBuilder) {

        List<Materiais> materiais = dados.stream()
                .map(Materiais::new)
                .map(m -> repository.save(m))
                .toList();

        List<DadosListagemMateriais> listaMateriais = materiais.stream()
                .map(DadosListagemMateriais::new)
                .collect(Collectors.toList());

        var uri = uriComponentsBuilder.path("/materiais").build().toUri();

        return ResponseEntity.created(uri).body(listaMateriais);
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemMateriais>> listar(@PageableDefault(size = 20, sort = {"nome"})Pageable paginacao) {
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
    public ResponseEntity<Void> excluir(@PathVariable Long id){
        var material = repository.getReferenceById(id);
        material.excluir();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DadosListagemMateriais> detalharPorId(@PathVariable Long id) {
        var material = repository.getReferenceById(id);
        return ResponseEntity.ok(new DadosListagemMateriais(material));
    }


}
