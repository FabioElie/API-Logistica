package br.com.logistics.api.controller;

import br.com.logistics.api.dto.vendedor.DadosAtualizacaoVendedor;
import br.com.logistics.api.dto.vendedor.DadosListagemVendedores;
import br.com.logistics.api.dto.vendedor.VendedorDTO;
import br.com.logistics.api.entity.Vendedor;
import br.com.logistics.api.repository.VendedorRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("vendedores")
public class VendedorController {

    @Autowired
    private VendedorRepository repository;

    @PostMapping
    @Transactional
    public void cadastrar(@RequestBody @Valid VendedorDTO dados) {
        repository.save(new Vendedor(dados));
    }

    @GetMapping
    public Page<DadosListagemVendedores> listar(@PageableDefault(size = 10, sort = {"nome"})Pageable paginacao) {
        return repository.findAllByAtivoTrue(paginacao).map(DadosListagemVendedores::new);
    }

    @PutMapping
    @Transactional
    public void atualizar(@RequestBody @Valid DadosAtualizacaoVendedor dados){
        var vendedor = repository.getReferenceById(dados.id());
        vendedor.atualizarInformacoesDoVendedor(dados);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void excluir(@PathVariable Long id){
        var vendedor = repository.getReferenceById(id);
        vendedor.excluir();
    }

}
