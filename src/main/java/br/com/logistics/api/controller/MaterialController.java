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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("materiais")
public class MaterialController {

    @Autowired
    private MaterialRepository repository;

    @PostMapping
    @Transactional
    public void cadastrar(@RequestBody @Valid MaterialDTO dados) {
        repository.save(new Material(dados));
    }

    @GetMapping
    public Page<DadosListagemMateriais> listar(@PageableDefault(size = 10, sort = {"nome"})Pageable paginacao) {
        return repository.findAllByAtivoTrue(paginacao).map(DadosListagemMateriais::new);
    }

    @PutMapping
    @Transactional
    public void atualizar(@RequestBody @Valid DadosAtualizacaoMaterial dados){
        var material = repository.getReferenceById(dados.id());
        material.atualizarInformacoesDoMaterial(dados);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void excluir(@PathVariable Long id){
        var material = repository.getReferenceById(id);
        material.excluir();
    }


}
