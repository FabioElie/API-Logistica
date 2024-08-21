package br.com.logistics.api.controller;

import br.com.logistics.api.dto.caminhao.CaminhaoDTO;
import br.com.logistics.api.dto.caminhao.DadosListagemCaminhao;
import br.com.logistics.api.dto.cliente.ClienteDTO;
import br.com.logistics.api.dto.cliente.DadosAtualizacaoCliente;
import br.com.logistics.api.dto.cliente.DadosDetalhamentoCliente;
import br.com.logistics.api.dto.cliente.DadosListagemClientes;
import br.com.logistics.api.entity.Caminhoes;
import br.com.logistics.api.entity.Clientes;
import br.com.logistics.api.repository.ClientesRepository;
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
@RequestMapping("clientes")
public class ClienteController {

    @Autowired
    private ClientesRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoCliente> cadastrar(@RequestBody @Valid ClienteDTO dados, UriComponentsBuilder uriComponentsBuilder) {
        var cliente = repository.save(new Clientes(dados));
        var uri = uriComponentsBuilder.path("/clientes./{id}").buildAndExpand(cliente.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhamentoCliente(cliente));

    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemClientes>> listar(@PageableDefault(size = 20, sort = {"nome"}) Pageable paginacao) {
        var page = repository.findAllByAtivoTrue(paginacao).map(DadosListagemClientes::new);
        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoCliente> atualizar(@RequestBody @Valid DadosAtualizacaoCliente dados) {
        var cliente = repository.getReferenceById(dados.id());
        cliente.atualizarInformacoesDoCliente(dados);
        return ResponseEntity.ok(new DadosDetalhamentoCliente(cliente));
    }

    @PostMapping("/lista")
    @Transactional
    public ResponseEntity<List<DadosListagemClientes>> cadastrarClientes(@RequestBody @Valid List<ClienteDTO> dados, UriComponentsBuilder uriComponentsBuilder) {

        List<Clientes> clientes = dados.stream()
                .map(Clientes::new)
                .map(c -> repository.save(c))
                .toList();

        List<DadosListagemClientes> listaClientes = clientes.stream()
                .map(DadosListagemClientes::new)
                .collect(Collectors.toList());

        var uri = uriComponentsBuilder.path("/clientes").build().toUri();

        return ResponseEntity.created(uri).body(listaClientes);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        var cliente = repository.getReferenceById(id);
        cliente.excluir();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DadosDetalhamentoCliente> detalharPorId(@PathVariable Long id) {
        var cliente = repository.getReferenceById(id);
        return ResponseEntity.ok(new DadosDetalhamentoCliente(cliente));
    }
}
