package br.com.logistics.api.controller;

import br.com.logistics.api.dto.caminhao.CaminhaoDTO;
import br.com.logistics.api.dto.caminhao.DadosAtualizacaoCaminhao;
import br.com.logistics.api.dto.caminhao.DadosListagemCaminhao;
import br.com.logistics.api.dto.vendedor.DadosDetalhamentoVendedor;
import br.com.logistics.api.dto.vendedor.VendedorDTO;
import br.com.logistics.api.entity.Caminhoes;
import br.com.logistics.api.entity.Vendedores;
import br.com.logistics.api.repository.CaminhoesRepository;
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
@RequestMapping("caminhoes")
public class CaminhaoController {

    @Autowired
    private CaminhoesRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity<DadosListagemCaminhao> cadastrar(@RequestBody @Valid CaminhaoDTO dados, UriComponentsBuilder uriComponentsBuilder) {
        var caminhao = repository.save(new Caminhoes(dados));
        var uri = uriComponentsBuilder.path("/caminhoes/{id}").buildAndExpand(caminhao.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosListagemCaminhao(caminhao));
    }

    @PostMapping("/lista")
    @Transactional
    public ResponseEntity<List<DadosListagemCaminhao>> cadastrarCaminhoes(@RequestBody @Valid List<CaminhaoDTO> dados, UriComponentsBuilder uriComponentsBuilder) {

        List<Caminhoes> caminhoes = dados.stream()
                .map(Caminhoes::new)
                .map(c -> repository.save(c))
                .toList();

        List<DadosListagemCaminhao> listaCaminhoes = caminhoes.stream()
                .map(DadosListagemCaminhao::new)
                .collect(Collectors.toList());

        var uri = uriComponentsBuilder.path("/caminhoes").build().toUri();

        return ResponseEntity.created(uri).body(listaCaminhoes);
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

    @GetMapping("/{id}")
    public ResponseEntity<DadosListagemCaminhao> detalharPorId(@PathVariable Long id) {
        var caminhao = repository.getReferenceById(id);
        return ResponseEntity.ok(new DadosListagemCaminhao(caminhao));
    }
}

