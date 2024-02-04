package br.com.logistics.api.controller;

import br.com.logistics.api.dto.vendedor.DadosAtualizacaoVendedor;
import br.com.logistics.api.dto.vendedor.DadosDetalhamentoVendedor;
import br.com.logistics.api.dto.vendedor.DadosListagemVendedores;
import br.com.logistics.api.dto.vendedor.VendedorDTO;
import br.com.logistics.api.entity.Vendedor;
import br.com.logistics.api.repository.VendedorRepository;
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
@RequestMapping("vendedores")
public class VendedorController {

    @Autowired
    private VendedorRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid VendedorDTO dados, UriComponentsBuilder uriComponentsBuilder) {
        var vendedor = repository.save(new Vendedor(dados));
        var uri = uriComponentsBuilder.path("/vendedores/{id}").buildAndExpand(vendedor).toUri();
        return ResponseEntity.created(uri).body(vendedor);
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemVendedores>> listar(@PageableDefault(size = 10, sort = {"nome"})Pageable paginacao) {
        var page = repository.findAllByAtivoTrue(paginacao).map(DadosListagemVendedores::new);
        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoVendedor> atualizar(@RequestBody @Valid DadosAtualizacaoVendedor dados){
        var vendedor = repository.getReferenceById(dados.id());
        vendedor.atualizarInformacoesDoVendedor(dados);
        return ResponseEntity.ok(new DadosDetalhamentoVendedor(vendedor));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Long id){
        var vendedor = repository.getReferenceById(id);
        vendedor.excluir();
        return ResponseEntity.noContent().build();
    }

}
